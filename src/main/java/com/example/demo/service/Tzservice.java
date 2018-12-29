package com.example.demo.service;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;

import java.util.List;

public interface Tzservice {

    public int post(Tz tz);
    public List<Tz> getallTz(User user);
    public int getnumberofTz(User user);
    public List<Tz> getmainTz();
    public Tz getTzByid(Integer tzid);
    public List<Tz> getExtraTzs();
    public int addExBytzid(Tz tz);
    public List<Tz> searchTzBytype(String context,Integer type);
    public int updateTz(Tz tz);
    public int delTzByID(Integer tzid);
}
