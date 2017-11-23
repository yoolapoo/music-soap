package com.epsi.music.dto;

import com.epsi.music.domain.Media;

public class Converter {

    public Media ConverterXsdToDto(io.spring.guides.gs_producing_web_service.Music musicXSD){
        Media musicDto = new Media();
        musicDto.setId_media(musicXSD.getId());
        musicDto.setAuthor(musicXSD.getAuthor());
        musicDto.setCreation(musicXSD.getCreation());
        musicDto.setGenre(musicXSD.getGenre());
        musicDto.setTitle(musicXSD.getTitle());
        musicDto.setType_media("music");
        return musicDto;
    }

    public io.spring.guides.gs_producing_web_service.Music ConverterDtoToXsd(Media musiDto){
        io.spring.guides.gs_producing_web_service.Music musicXsd = new io.spring.guides.gs_producing_web_service.Music();
        musicXsd.setId(musiDto.getId_media());
        musicXsd.setAuthor(musiDto.getAuthor());
        musicXsd.setCreation(musiDto.getCreation());
        musicXsd.setGenre(musiDto.getGenre());
        musicXsd.setTitle(musiDto.getTitle());
        return musicXsd;
    }
}
