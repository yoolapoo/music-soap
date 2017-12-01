package com.epsi.music.service;

import com.epsi.music.domain.Media;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MusicService {
    Media findById(String id);
    List<Media> findAll();
    List<Media> search(String searchTerm);
    void addMusic(Media media);
    void updateMusic(Media media);
}
