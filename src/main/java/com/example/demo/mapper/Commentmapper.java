package com.example.demo.mapper;

import com.example.demo.bean.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Commentmapper {
    @Select("select * from comment where tzid = #{tzid}")
    @Results({
            @Result(property = "user", column = "userid",
                    many = @Many(select = "com.example.demo.mapper.Usermapper.getUserByid"))
    })
    public List<Comment> getCommentsByTzid(Integer tzid);

    @Select("select count(*) from comment where tzid = #{tzid}")
    public int getCountBytzid(Integer tzid);

    @Insert("INSERT INTO comment(userid,tzid,text,time) VALUES (#{userid},#{tzid},#{text},#{time})")
    public int insertCom(Comment comment);
}
