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

    @Select("SELECT * FROM music WHERE id_music = #{id} ")
    Optional<Music> findById(String id);

    @Select("SELECT * FROM music ")
    List<Music> findAll();

}
