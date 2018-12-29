package com.example.demo.controller;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;
import com.example.demo.mapper.Tzmapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.service.Tzservice;
import com.example.demo.service.Userservice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TzController {
    @Autowired
    Tzservice tzservice;
    @Autowired
    Userservice userservice;
    @Value("D:\\demo\\target\\demo-0.0.1-SNAPSHOT\\imges\\")
    private String uploadPicPath;

    @ResponseBody
    @RequestMapping("/post")
    public int post(HttpServletRequest request, Tz tz,
                    @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        String photoname = null;
        try {
            if (multipartFile!=null){
                photoname="<img src=\"/imges/" +storePic(multipartFile) + "\">";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(multipartFile+tz.getTitle()+tz.getText());
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        if (userinfo == null) return 0;
        if (userinfo.getStatus() == 1) return 1;
        tz.setUserid(userinfo.getUserid());
        Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        tz.setTime(format.format(new Date()));
        if (tz.getTitle() != null && tz.getText() != null) {
            if (tz.getTitle().isEmpty()||tz.getText().isEmpty()) return 0;
            if (photoname != null)
                tz.setText(tz.getText() + photoname);
            return tzservice.post(tz);
        } else return 0;
    }

    @RequestMapping("/tzinfo")
    public String gettzByid(@RequestParam(value = "tzid", required = true) Integer tzid, Model model) {
        model.addAttribute("tz", tzservice.getTzByid(tzid));
        return "tzinfo";
    }

    @ResponseBody
    @RequestMapping("/addextz")
    public int addExtz(Tz tz) {
        return tzservice.addExBytzid(tz);
    }

    @RequestMapping("/searchresults")
    public String searchResults(@RequestParam(value = "condit", required = true) String condit,
                                Model model,
                                @RequestParam(value = "type", required = true) Integer type) {
        List<User> users=null;
        List<Tz> list=null;
        if (type==0) {
            PageHelper.startPage(1,15);
            users = userservice.searchUsByname("%" + condit + "%");
            PageInfo pageInfo=new PageInfo(users,5);
            model.addAttribute("userspageinfo",pageInfo);
        }else {
            PageHelper.startPage(1,15);
            list=tzservice.searchTzBytype(condit,type);
            PageInfo pageInfo=new PageInfo(list,5);
            model.addAttribute("pageinfo",pageInfo);
        }
        return "searchresults";
    }

    @ResponseBody
    @RequestMapping("/updatetz")
    public int updateTz(Tz tz,@RequestParam(value = "file",required = false) MultipartFile multipartFile){
        int i=0;
        String photoname = null;
        String photo=null;
        try {
            if (multipartFile!=null){
                photo=storePic(multipartFile);
                photoname="<img src=\"/imges/" +photo + "\">";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (photoname != null){
            tz.setText(tz.getText() + photoname);
            tz.setPhoto("/imges/"+photo);
        }else tz.setPhoto("");
        if (tz.getTzid()!=null&&tz.getTitle()!=null&&tz.getText()!=null&&tz.getPhoto()!=null){
            i=tzservice.updateTz(tz);
            return i;
        }
        else return  i;
    }

    @ResponseBody
    @RequestMapping("/deltz")
    public int delTz(@RequestParam(value = "tzid",required = true) Integer tzid){
        if (tzid!=null)
            return  tzservice.delTzByID(tzid);
        else return 0;
    }

    private String storePic(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {


            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + filename), // 这里指定了下载的位置
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new Exception("失败！" + filename, e);
        }
        return filename;
    }

}
