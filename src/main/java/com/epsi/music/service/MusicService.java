package com.epsi.music.service;

import com.epsi.music.domain.Music;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MusicService {

    Optional<Music> findById(String id);
    List<Music> findAll();
}
