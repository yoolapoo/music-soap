package com.epsi.music.controller;


import com.epsi.music.data.MediaApiStatus;
import com.epsi.music.domain.Media;
import com.epsi.music.exception.AllMusicsAlreadyReturnedException;
import com.epsi.music.exception.MusicNotFoundException;
import com.epsi.music.exception.UnavailableMusicException;
import com.epsi.music.service.LoanService;
import com.epsi.music.service.MusicService;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class MusicController {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private MusicService musicService;
    private LoanService loanService;

    @Autowired
    public MusicController(MusicService musicService, LoanService loanService) {
        this.musicService = musicService;
        this.loanService = loanService;
    }

    /**
     * Get a music from its id
     *
     * @param request the id of the wanter music
     * @return a music with the given id if there is one
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMusicRequest")
    @ResponsePayload
    public GetMusicResponse getMusic(@RequestPayload GetMusicRequest request) {
        GetMusicResponse response = new GetMusicResponse();
        Media media = musicService.findById(request.getName());
        if (media.getType_media().equals("music") && media != null) {
            Music music = getInfo(media);
            response.setMusic(music);
            return response;
        } else {
            return response;
        }
    }

    /**
     * Get all musics of the library
     *
     * @return the musics
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMusicRequest")
    @ResponsePayload
    public List<Music> getMusics(@RequestPayload GetAllMusicRequest request) {
        List<Music> responses = new ArrayList<>();
        musicService.findAll().stream().forEach(media -> {
            if (media.getType_media().equals("music")) {
                Music music = getInfo(media);
                responses.add(music);
            }
        });
        return responses;
    }


    /**
     * Add a music with the given ISBN
     *
     * @param isbn the ISBN
     * @return the id of the added music if the isbn exists
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMusicRequest")
    @ResponsePayload
    public String addMusic(@RequestPayload AddMusicRequest request) {
        String id = request.getMusic().getId();
        if (musicService.findById(id) == null) {
            Media media = setInfo(request.getMusic());
            musicService.addMusic(media);
            return media.getId_media();
        }
        return id;
    }


    /**
     * Borrow a music from the library
     *
     * @param id       the id of the borrowed music
     * @param username the name of the user
     * @throws MusicNotFoundException    if no music in the library has the given id
     * @throws UnavailableMusicException if all musics in the library with the given id have been borrowed
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "borrowRequest")
    public void borrowMusic(@RequestPayload BorrowMusicRequest request) throws MusicNotFoundException, UnavailableMusicException {
        Media media = musicService.findById(request.getId());
        if (media == null) {
            throw new MusicNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if (media.isAvailable()) {
            media.setAvailable(false);
            musicService.updateMusic(media);
            loanService.addLoan(request.getId(), request.getUsername());
        } else {
            throw new UnavailableMusicException(HttpStatus.NO_CONTENT, MediaApiStatus.MEDIA_API_STATUS_1401.toString(), MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }
    }


    /**
     * Return a music back to the library
     *
     * @param id       the id of the music to borrow
     * @param username the name of the user
     * @throws MusicNotFoundException            if no music in the library has the given id
     * @throws AllMusicsAlreadyReturnedException if all musics with the given id are already returned
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "returnRequest")
    public void returnMusic(@RequestPayload ReturnRequest request) throws MusicNotFoundException, AllMusicsAlreadyReturnedException {
        Media media = musicService.findById(request.getId());
        if (media == null) {
            throw new MusicNotFoundException(HttpStatus.BAD_REQUEST, MediaApiStatus.MEDIA_API_STATUS_1402.toString(), MediaApiStatus.MEDIA_API_STATUS_1402.getReason());
        }
        if (media.isAvailable()) {
            media.setAvailable(true);
            musicService.updateMusic(media);
            loanService.deleteLoan(request.getId(), request.getUsername());
        } else {
            throw new AllMusicsAlreadyReturnedException(HttpStatus.ALREADY_REPORTED, MediaApiStatus.MEDIA_API_STATUS_1403.toString(), MediaApiStatus.MEDIA_API_STATUS_1401.getReason());
        }

    }


    /**
     * Return all musics with an author, a title or an ISBN matching the search term
     *
     * @param searchTerm the searched term
     * @return the musics matching the search term
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMusicBySearchTermRequest")
    @ResponsePayload
    public List<Music> searchMusics(@RequestPayload GetMusicBySearchTermRequest request) {
        List<Music> responses = new ArrayList<>();
        musicService.search(request.getName()).stream().forEach(item -> {
            Music music = getInfo(item);
            responses.add(music);
        });

        return responses;
    }


    /**
     * Class to convert a media model to music XSD
     *
     * @param media
     * @return Music
     */
    public Music getInfo(Media media) {
        Music music = new Music();
        if (media.getType_media().equals("music")) {
            music.setId(media.getId_media());
            music.setTitle(media.getTitle());
            music.setAuthor(media.getAuthor());
            music.setGenre(media.getGenre());
            music.setCreation(media.getCreation());
            music.setIsavailable(media.isAvailable());
            return music;
        }else {
            return null;
        }

    }

    /**
     * Class to convert a music XSD to media model
     *
     * @param music
     * @return Media
     */
    public Media setInfo(Music music) {
        Media media = new Media();
        media.setId_media(music.getId());
        media.setTitle(music.getTitle());
        media.setAuthor(music.getAuthor());
        media.setGenre(music.getGenre());
        media.setCreation(music.getCreation());
        media.setAvailable(music.isIsavailable());
        media.setType_media("music");
        return media;

    }
}
