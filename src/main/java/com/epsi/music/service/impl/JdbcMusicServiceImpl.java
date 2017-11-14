package com.epsi.music.service.impl;

import com.epsi.music.domain.Music;
import com.epsi.music.mapper.MusicMapper;
import com.epsi.music.service.MusicService;

import java.util.List;

public class JdbcMusicServiceImpl implements MusicService {

    private MusicMapper musicMapper;

    public JdbcMusicServiceImpl(MusicMapper musicMapper){

        super();
        this.musicMapper = musicMapper;
    }

    @Override
    public List<Music> findById(String id){
        return this.musicMapper.findById(id);
    }

    @Override
    public List<Music> findAll() {
        return this.musicMapper.findAll();
    }
}
