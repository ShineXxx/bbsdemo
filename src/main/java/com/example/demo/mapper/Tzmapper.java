package com.example.demo.mapper;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Tzmapper {
    @Insert("INSERT INTO tz(userid,title,text,photo,time) VALUES (#{userid},#{title},#{text},#{photo},#{time})")
    public int postTz(Tz tz);

    @Select("select count(*) from tz")
    public int countOfTz();

    @Select("select count(*) from tz where userid = #{userid} ")
    public int countByuserid(User user);

    @Select("select * from tz where userid = #{userid}")
    public List<Tz> getAllTzByuid(Integer userid);

    @Select("SELECT * FROM tz ORDER BY tzid DESC")
    @Results({
            @Result(property="tzid",column="tzid"),
            @Result(property = "user", column = "userid",
                    one = @One(select = "com.example.demo.mapper.Usermapper.getUserByid")),
            @Result(property = "numofC", column = "tzid",
                    one = @One(select = "com.example.demo.mapper.Commentmapper.getCountBytzid"))
    })
    public List<Tz> getAllTz();
    @Select("select * from tz where tzid = #{tzid}")
    @Results({
            @Result(property="tzid",column="tzid"),
            @Result(property = "user", column = "userid",
                    one = @One(select = "com.example.demo.mapper.Usermapper.getUserByid")),
            @Result(property = "commentList", column = "tzid",
                    many = @Many(select = "com.example.demo.mapper.Commentmapper.getCommentsByTzid"))
    })
    public Tz getTzByid(Integer tzid);
    @Select("SELECT * FROM tz WHERE extract=1")
    @Results({
            @Result(property="tzid",column="tzid"),
            @Result(property = "numofC", column = "tzid",
                    one = @One(select = "com.example.demo.mapper.Commentmapper.getCountBytzid"))
    })
    public List<Tz> getAllExtraTz();
    @Update("UPDATE tz SET extract=#{extra} where tzid = #{tzid}")
    public int addExBytzID(Tz tz);

    @Update("UPDATE TZ SET TITLE=#{title},TEXT=#{text},PHOTO=#{photo} WHERE TZID =#{tzid}")
    public int updateTz(Tz tz);

    @Select("SELECT * FROM tz WHERE title like #{title}")
    public List<Tz> searchByTitle(String title);

/*    @Select("SELECT * FROM tz WHERE name like #{author}")
    public List<Tz> searchByAuthor(String author);*/

    @Select("SELECT * FROM tz WHERE text like #{text}")
    public List<Tz> searchByContext(String text);

    @Delete("DELETE FROM TZ WHERE TZID=#{tzid}")
    public int delTzByid(Integer tzid);

}
