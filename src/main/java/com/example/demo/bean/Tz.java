package com.example.demo.bean;

import java.util.List;

public class Tz {
    private Integer tzid;
    private Integer userid;
    private Integer extra;
    private String title;
    private String text;
    private String photo;
    private String time;
    private User user;
    private Integer numofC;
    private List<Comment> commentList;

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public Integer getNumofC() {
        return numofC;
    }

    public void setNumofC(Integer numofC) {
        this.numofC = numofC;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTzid() {
        return tzid;
    }

    public void setTzid(Integer tzid) {
        this.tzid = tzid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
