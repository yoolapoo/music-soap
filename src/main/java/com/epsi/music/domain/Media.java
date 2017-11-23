package com.epsi.music.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Media {
    private long id_media;
    private String type_media;
    private String author;
    private String title;
    private String creation;
    private String genre;
}
