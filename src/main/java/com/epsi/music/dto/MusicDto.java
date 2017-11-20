package com.epsi.music.dto;

import com.epsi.music.domain.Music;
import lombok.Data;

import java.util.Date;
@Data
public class MusicDto {
    private Music musicDomain;
    private io.spring.guides.gs_producing_web_service.Music musicXsd;

    private MusicDto(){
        musicDomain.s
    }



}
