package com.epsi.music.service;

import com.epsi.music.domain.Music;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicService {

    List<Music> findById(String id);
    List<Music> findAll();
}
