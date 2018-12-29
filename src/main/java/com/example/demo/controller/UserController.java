package com.example.demo.controller;

import com.example.demo.bean.Tz;
import com.example.demo.bean.User;
import com.example.demo.mapper.Usermapper;
import com.example.demo.service.Tzservice;
import com.example.demo.service.Userservice;
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
import java.util.List;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    Tzservice tzservice;
    @Autowired
    Userservice userservice;
    @Value("D:\\demo\\target\\demo-0.0.1-SNAPSHOT\\imges\\")
    private String uploadPicPath;

    @RequestMapping("/Anonymouslogin")
    public String Anonymouslogin(HttpSession session, Model model) {
        User user = new User();
        user.setUserid(-1);
        user.setGender(2);
        user.setName("游客" + new Random().nextInt(Integer.MAX_VALUE));
        user.setPhoto("/imges/favicon-16x16.png");
        user.setStatus(-1);

        session.setAttribute("userinfo", user);
        model.addAttribute("pageInfo", getPageInfo(1));
        model.addAttribute("expageInfo", getExtPageInfo(1));
        return "MainPage";
    }

    @ResponseBody
    @RequestMapping("/signup")
    public int signUp(@RequestParam(value = "file", required = false) MultipartFile multipartFile, User user) {
        try {
            if (multipartFile != null) {
                storePic(multipartFile);
                user.setPhoto("/imges/" + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
            } else user.setPhoto("/imges/1.png");
//            photoname = "<img src=\"/imges/"+StringUtils.cleanPath(multipartFile.getOriginalFilename())+"\">";
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userservice.hasUser(user.getName())) return 2;
        if (user.getGender() != null && user.getName() != null && user.getPassword() != null)
            userservice.addUser(user);
        return 0;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String confirm(Model model, @RequestParam("name") String name, @RequestParam("password") String password,
                          @RequestParam(value = "pagenumber", required = false) Integer pn, HttpSession session) {
        User user = userservice.getUserByName(name);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        } else {
            session.setAttribute("userinfo", user);
            return "redirect:/main";
        }

    }

    @RequestMapping("/pagenum")
    public String Pagehelp(@RequestParam(value = "pn", required = true) Integer pn, Model model) {
        model.addAttribute("pageInfo", getPageInfo(pn));
        model.addAttribute("expageInfo", getExtPageInfo(1));
        return "MainPage";
    }

    @RequestMapping("/userinfo")
    public String userinfo(HttpServletRequest request, Model model, Integer userid,
                           @RequestParam(value = "pn", required = true, defaultValue = "1") Integer pn) {
        User user = new User();
        user.setUserid(userid);
        PageHelper.startPage(pn, 10);
        List<Tz> list = tzservice.getallTz(user);
        PageInfo pageInfo = new PageInfo(list, 5);
        User user1 = userservice.getUserByid(userid);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("user", user1);
        model.addAttribute("count", tzservice.getnumberofTz(user));
        return "userinfo";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("pageInfo", getPageInfo(1));
        model.addAttribute("expageInfo", getExtPageInfo(1));
        return "MainPage";
    }

    @ResponseBody
    @RequestMapping("/restatus")
    public int reStatusByname(User user, @RequestParam(value = "type", required = true, defaultValue = "0") Integer type) {
        if (user.getName().isEmpty()) return 0;
        switch (type){
            case 0: userservice.delUserByName(user.getName());break;
            case 1:if (user.getStatus()==null) return 0;
                userservice.restateByName(user);break;
            default: ;
        }
        return 1;
    }

    public PageInfo<Tz> getPageInfo(Integer pn) {
        PageHelper.startPage(pn, 15);
        List<Tz> list = tzservice.getmainTz();
        PageInfo<Tz> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    public PageInfo<Tz> getExtPageInfo(Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Tz> list = tzservice.getExtraTzs();
        PageInfo<Tz> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
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
