package com.example.demo.mapper;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Usermapper {
    @Select("select * from user where name = #{name}")
    public User getUserByName(String name);

    @Delete("delete from user where userid = #{userid}")
    public int deleteUserById(Integer userid);

    @Update("UPDATE USER SET STATUS = #{status} WHERE NAME = #{name}")
    public int restateByName(User user);

    @Delete("DELETE FROM USER WHERE NAME = #{name}")
    public int deleteUserByName(String name);

    @Insert("INSERT INTO user(name,password,photo,gender) VALUES(#{name},#{password},#{photo},#{gender})")
    public int addUser(User user);

    @Update("update user set name=#{name},password=#{password},photo=#{photo} where userid=#{userid} ")
    public int updateUserInfo(User user);
    @Select("select * from user where userid = #{userid}")
    public User getUserByid(Integer userid);

    @Select("SELECT * FROM user WHERE name like #{name}")
    public List<User> searchUsersByname(String name);
}
