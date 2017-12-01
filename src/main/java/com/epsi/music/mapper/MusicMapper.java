package com.epsi.music.mapper;

import com.epsi.music.domain.Media;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface MusicMapper {

    @Select("SELECT * FROM media WHERE id_media = #{id} ")
    Media findById(String id);

    @Select("SELECT * FROM media ")
    List<Media> findAll();

    @Insert("INSERT INTO media (id_media, type_media, author, title, creation, genre, isavailable) " +
            "VALUES(#{id_media},#{type_media},#{author},#{title},#{creation},#{genre},#{isavailable})")
    void addMusic(Media media);

    @Update("UPDATE media set isavailable = #{isavailable} WHERE id_media = #{id_media} ")
    void updateMusic(Media media);

    @Select("SELECT * FROM media WHERE id_media = #{searchTerm} OR title = #{searchTerm} OR author = #{searchTerm}")
    List<Media> search(String searchTerm);

}
