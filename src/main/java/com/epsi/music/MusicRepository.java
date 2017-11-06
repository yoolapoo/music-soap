package com.epsi.music;

import io.spring.guides.gs_producing_web_service.Music;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MusicRepository {
    private static final Map<Integer,Music> musics = new HashMap<>();

    @PostConstruct
    public void initData(){

        Music astonishing = new Music();
        astonishing.setAuthor("Dream Theater");
        astonishing.setId(1);
        astonishing.setIsbn("123456789");
        astonishing.setTitle("The Astonishing");
        musics.put(astonishing.getId(),astonishing);


        Music similitude = new Music();
        similitude.setAuthor("The Neal Morse Band");
        similitude.setId(2);
        similitude.setIsbn("987654321");
        similitude.setTitle("The Similitude Of A Dream");
        musics.put(similitude.getId(),similitude);
    }

    public Music findMusic(Integer id) {
        Assert.notNull(id, "The music's id must not be null");
        return musics.get(id);
    }
}
