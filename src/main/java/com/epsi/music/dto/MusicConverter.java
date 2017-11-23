package com.epsi.music.dto;

import com.epsi.music.domain.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MusicConverter {

    @Mappings({
            @Mapping(source = "id", target = "id_music"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "genre", target = "genre"),
            @Mapping(source = "creation", target = "creation")
    })
    Media musictoMusicDto(io.spring.guides.gs_producing_web_service.Music music);

    @Mappings({
            @Mapping(source = "id_music", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "genre", target = "genre"),
            @Mapping(source = "creation", target = "creation")
    })
    io.spring.guides.gs_producing_web_service.Music musicDtoToMusic(Media musicDto);


}
