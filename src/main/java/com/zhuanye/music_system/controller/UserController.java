package com.zhuanye.music_system.controller;

import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music_sharing/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByUserID/{id}",method = RequestMethod.GET)
    public User getUserByUserID( @PathVariable Integer id){
        return userService.getUserByUserID(id);
    }
}
