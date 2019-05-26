package com.example.odata.own.web;

import com.example.odata.own.repository.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/users")
@Controller
public class IndexController {

    @Resource
    private UserDao userDao;


    @ResponseBody
    @RequestMapping("/index")
    public Object index() {
        return userDao.findAll();
    }
}
