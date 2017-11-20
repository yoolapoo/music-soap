package com.epsi.music;

import com.epsi.music.domain.Music;
import com.epsi.music.service.MusicService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MusicRepository {
    private static final Map<String, Music> musics = new HashMap<>();
    private MusicService musicService;

    @PostConstruct
    public void initData() {

/*        Music astonishing = new Music();
        astonishing.setAuthor("Dream Theater");
        astonishing.setId("1");
        astonishing.setGenre("Metal Progressive");
        astonishing.setTitle("The Astonishing");
        astonishing.setCreation("2016/01/29");
        musics.put(astonishing.getId(), astonishing);


        Music similitude = new Music();
        similitude.setAuthor("The Neal Morse Band");
        similitude.setId("2");
        similitude.setCreation("2016/11/11");
        similitude.setGenre("Christian Metal Progressive Rock");
        similitude.setTitle("The Similitude Of A Dream");
        musics.put(similitude.getId(), similitude);*/

    }

/*    public Optional<Music> getMusic(String id) {
        Optional<Music> music = Optional.of(musics.get(id));
        if(music.isPresent()){
            Assert.notNull(id, "The music's id must not be null");
            return music;
        }
        return Optional.empty();*/

    public Music getMusic(String id) {
        List<Music> liste = musicService.findById(id);
        liste.forEach(music->{
            musics.put(id,music);
        });
        Assert.notNull(id, "The music's id must not be null");
        return musics.get(id);
    }
    public List<Music> getMusics(){
        List<Music> musics = musicService.findAll();
        return musics;
    }
}
