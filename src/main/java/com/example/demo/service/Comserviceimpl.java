package com.example.demo.service;

import com.example.demo.bean.Comment;
import com.example.demo.mapper.Commentmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Comserviceimpl implements Comservice{
    @Autowired
    Commentmapper commentmapper;
    @Override
    public int insertDis(Comment comment) {
        return commentmapper.insertCom(comment);
    }
}
