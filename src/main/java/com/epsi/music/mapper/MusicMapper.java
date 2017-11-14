package com.epsi.music.mapper;

import com.epsi.music.domain.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface MusicMapper {

    @Select("SELECT * FROM media WHERE id_media = #{id}")
    List<Music> findById(String id);

    @Select("SELECT * FROM media")
    List<Music> findAll();

}
