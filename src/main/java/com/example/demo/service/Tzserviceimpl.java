package com.example.demo.service;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;
import com.example.demo.mapper.Tzmapper;
import com.example.demo.mapper.Usermapper;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class Tzserviceimpl implements Tzservice{
    @Autowired
    Tzmapper tzmapper;
    @Autowired
    Usermapper usermapper;
    @Override
    public int post(Tz tz) {
        return  tzmapper.postTz(tz);
    }

    @Override
    public List<Tz> getallTz(User user) {
        return tzmapper.getAllTzByuid(user.getUserid());
    }

    @Override
    public int getnumberofTz(User user) {
        return tzmapper.countByuserid(user);
    }

    @Override
    public List<Tz> getmainTz() {
        List<Tz> tzList=tzmapper.getAllTz();
        Collections.sort(tzList, new Comparator<Tz>() {
            @Override
            public int compare(Tz o1, Tz o2) {
                if (o1.getTzid()<o2.getTzid())
                    return 1;
                if (o1.getTzid()==o2.getTzid())
                    return 0;
                return -1;
            }

        });
        return tzList;
    }

    @Override
    public Tz getTzByid(Integer tzid) {
        return tzmapper.getTzByid(tzid);
    }

    @Override
    public List<Tz> getExtraTzs() {
        return tzmapper.getAllExtraTz();
    }

    @Override
    public int addExBytzid(Tz tz) {
        return tzmapper.addExBytzID(tz);
    }

    @Override
    public List<Tz> searchTzBytype(String context, Integer type) {
        List<Tz> list=null;
        switch (type){
            case 1:list=tzmapper.searchByTitle("%"+context+"%");break;
            case 2:list=tzmapper.searchByContext("%"+context+"%");break;
            default: list=null;
        }
        return list;
    }

    @Override
    public int updateTz(Tz tz) {
        return tzmapper.updateTz(tz);
    }

    @Override
    public int delTzByID(Integer tzid) {
        return tzmapper.delTzByid(tzid);
    }


}
