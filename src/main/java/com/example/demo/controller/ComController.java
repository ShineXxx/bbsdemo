package com.example.demo.controller;

import com.example.demo.bean.Comment;
import com.example.demo.bean.User;
import com.example.demo.service.Comservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ComController {
    @Autowired
    Comservice comservice;
    @ResponseBody
    @RequestMapping("/discuss")
    public int discuss(@RequestParam(value = "context",required = true)String context,
                       HttpSession session,@RequestParam(value = "tzid",required = true) Integer tzid){
        Comment comment=new Comment();
        User user= (User) session.getAttribute("userinfo");
        if (user == null) {
            return 0;
        }
        comment.setUser(user);
        comment.setUserid(user.getUserid());
        comment.setText(context);
        Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        comment.setTime(format.format(new Date()));
        comment.setTzid(tzid);
        return  comservice.insertDis(comment);
    }
}
