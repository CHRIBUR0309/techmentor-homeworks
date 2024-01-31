package com.example.restful_todo.mapper;

import com.example.restful_todo.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT user_id FROM users WHERE user_id = #{userId.getUserId()} AND password = #{hashedPassword.getHashedPassword()}")
    User getUser(UserId userId, HashedPassword hashedPassword);

    @Insert("INSERT INTO users (user_id, password) VALUES (#{userId.getUserId()}, #{hashedPassword.getHashedPassword()})")
    User createUser(UserId userId, HashedPassword hashedPassword);

    @Update("UPDATE users SET password = #{hashedPasswordAfter.getHashedPassword()} WHERE user_id = #{userId.getUserId()} AND password = #{hashedPasswordBefore.getHashedPassword()}")
    User updateUser(UserId userId, HashedPassword hashedPasswordBefore,
            HashedPassword hashedPasswordAfter);

    @Delete("DELETE FROM users WHERE user_id = #{userId.getUserId()} AND password = #{hashedPassword.getHashedPassword()}")
    void deleteUser(UserId userId, HashedPassword hashedPassword);
}
