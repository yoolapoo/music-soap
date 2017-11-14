package com.epsi.music.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Music {
    private String id;
    private String author;
    private String title;
    private Date created;
    private String genre;
}
