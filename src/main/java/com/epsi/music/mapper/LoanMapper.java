package com.epsi.music.mapper;

import com.epsi.music.domain.Loan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface LoanMapper {

    @Insert("INSERT INTO loan(id_user, id_media) VALUES( #{id_user}, #{id_media})")
    void addLoan(String username, String id );

    @Delete("DELETE FROM loan WHERE id_media = #{id} AND id_user = #{username}")
    void deleteLoan(String id, String username);
}
