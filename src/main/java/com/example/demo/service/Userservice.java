package com.example.demo.service;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;

import java.util.List;

public interface Userservice {
    public void post( Tz tz);
    public User getUserByid(Integer userid);
    public User getUserByName(String name);
    public int addUser(User user);
    public boolean hasUser(String name);
    public List<User> searchUsByname(String name);
    public int delUserByName(String name);
    public int restateByName(User user);
}
