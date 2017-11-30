package com.epsi.music.controller;


import com.epsi.music.domain.Media;
import com.epsi.music.service.MusicService;
import io.spring.guides.gs_producing_web_service.GetMusicRequest;
import io.spring.guides.gs_producing_web_service.GetMusicResponse;
import io.spring.guides.gs_producing_web_service.Music;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
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
            Music music = new Music();
            music.setId(request.getName());
            music.setTitle(media.getTitle());
            music.setAuthor(media.getAuthor());
            music.setGenre(media.getGenre());
            music.setCreation(media.getCreation());
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
    public List<Music> getMusics() {
        List<Music> responses = new ArrayList<>();
        musicService.findAll().stream().forEach(media -> {
            //GetMusicResponse response = new GetMusicResponse();
            if (media.getType_media().equals("music")) {
                Music music = new Music();
                music.setId(media.getId_media());
                music.setTitle(media.getTitle());
                music.setAuthor(media.getAuthor());
                music.setGenre(media.getGenre());
                music.setCreation(media.getCreation());
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
    //Optional<String> addMusic(Music music);
    /**
     * Borrow a music from the library
     *
     * @param id the id of the borrowed music
     * @param username the name of the user
     * @throws MusicNotFoundException if no music in the library has the given id
     * @throws UnavailableMusicException if all musics in the library with the given i
    Projet Webservice
    Objectif
    Description du projetd have been borrowed
     */
    //void borrowMusic(String id, String username) throws MusicNotFoundException, UnavailablemusicException;
    /**
     * Return a music back to the library
     *
     * @param id the id of the music to borrow
     * @param username the name of the user
     * @throws MusicNotFoundException if no music in the library has the given id
     * @throws AllMusicsAlreadyReturnedException if all musics with the given id are a
    lready returned
     */
    //void returnMusic(String id, String username) throws MusicNotFoundException, AllMusicsAlreadyReturnedException;

    /**
     * Return all musics with an author, a title or an ISBN matching the search term
     *
     * @param searchTerm the searched term
     * @return the musics matching the search term
     */
    //List<musics> searchMusics(String searchTerm);

}
