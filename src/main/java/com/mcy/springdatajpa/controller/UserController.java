package com.mcy.springdatajpa.controller;

import com.mcy.springdatajpa.entity.User;
import com.mcy.springdatajpa.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    //注入UserServer
    @Resource
    private UserService userService;

    //保存方法
    @RequestMapping("/save")
    public String save(){
        //实例化一个对象，添加值
        User user = new User();
        user.setLoginName("admin");
        user.setUsername("一念之间");
        user.setSex('男');
        user.setAge(19);
        //保存数据
        userService.save(user);
        return "保存数据成功！";
    }

    //修改
    @RequestMapping("/update")
    public String update(){
        //修改的对象必须是持久化对象，所以先从数据库查询id为1的对象开始修改
        User user = userService.getById(1);
        userService.update(user);
        return "修改成功！";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(){
        userService.delete(1);
        return "删除成功！";
    }

    //查询所有
    @RequestMapping("/getAll")
    public Iterable<User> getAl(){
        //查询所有的用户数据
        return userService.findAll();
    }
}
