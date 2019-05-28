package com.example.demo.controller;


import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody User user) {

//        user.setId(id);
//        user.setUserName(username);
//        user.setAge(age);
        userService.insert(user);
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable("id") int id){
        userService.delete(id);
    }
}

