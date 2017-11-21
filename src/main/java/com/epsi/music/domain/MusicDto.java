package com.epsi.music.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MusicDto {
    private String id_music;
    private String author;
    private String title;
    private String creation;
    private String genre;
}
