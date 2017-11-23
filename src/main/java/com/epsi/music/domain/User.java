package com.epsi.music.domain;

import lombok.Data;

@Data
public class User {
    private long id_user;
    private String username;
    private String email;
    private String salt;
    private String passhash;
}
