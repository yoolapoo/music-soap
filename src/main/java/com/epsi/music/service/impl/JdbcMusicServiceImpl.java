package com.epsi.music.service.impl;

import com.epsi.music.domain.Media;
import com.epsi.music.mapper.MusicMapper;
import com.epsi.music.service.MusicService;

import java.util.List;
import java.util.Optional;

public class JdbcMusicServiceImpl implements MusicService {

    private MusicMapper musicMapper;

    public JdbcMusicServiceImpl(MusicMapper musicMapper) {

        super();
        this.musicMapper = musicMapper;
    }

    @Override
    public Media findById(String id) {
        return this.musicMapper.findById(id);
    }

    @Override
    public List<Media> findAll() {
        return this.musicMapper.findAll();
    }

    @Override
    public void addMusic(Media media){this.musicMapper.addMusic(media);}

    @Override
    public void updateMusic(Media media){this.musicMapper.updateMusic(media);}

    @Override
    public List<Media> search(String searchTerm){return this.musicMapper.search(searchTerm);}
}
