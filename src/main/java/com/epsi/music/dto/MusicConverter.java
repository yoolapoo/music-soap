package com.epsi.music.dto;

import com.epsi.music.domain.MusicDto;
import io.spring.guides.gs_producing_web_service.Music;
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
    MusicDto musictoMusicDto(Music music);

    @Mappings({
            @Mapping(source = "id_music", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "genre", target = "genre"),
            @Mapping(source = "creation", target = "creation")
    })
    Music musicDtoToMusic(MusicDto musicDto);


}
