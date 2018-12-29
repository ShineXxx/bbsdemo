package com.example.demo.service;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;
import com.example.demo.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userserviceimpl implements Userservice{
    @Autowired
    Usermapper usermapper;

    @Override
    public void post(Tz tz) {
    }

    @Override
    public User getUserByid(Integer userid) {
        return usermapper.getUserByid(userid);
    }

    @Override
    public User getUserByName(String name) {
        return usermapper.getUserByName(name);
    }

    @Override
    public int addUser(User user) {
        return usermapper.addUser(user);
    }

    @Override
    public boolean hasUser(String name) {
        User user=usermapper.getUserByName(name);
        return user!=null;
    }

    @Override
    public List<User> searchUsByname(String name) {
        return usermapper.searchUsersByname(name);
    }

    @Override
    public int delUserByName(String name) {
        return usermapper.deleteUserByName(name);
    }

    @Override
    public int restateByName(User user) {
        return usermapper.restateByName(user);
    }
}
