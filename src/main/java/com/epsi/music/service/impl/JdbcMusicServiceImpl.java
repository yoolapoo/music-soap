package com.epsi.music.service.impl;

import com.epsi.music.domain.Media;
import com.epsi.music.mapper.MusicMapper;
import com.epsi.music.service.MusicService;

import java.util.List;
import java.util.Optional;

public class JdbcMusicServiceImpl implements MusicService {

    private MusicMapper musicMapper;

    public JdbcMusicServiceImpl(MusicMapper musicMapper){

        super();
        this.musicMapper = musicMapper;
    }

    @Override
    public Optional<Media> findById(long id) {
        return this.musicMapper.findById(id);
    }

    @Override
    public List<Media> findAll() {
        return this.musicMapper.findAll();
    }

}
