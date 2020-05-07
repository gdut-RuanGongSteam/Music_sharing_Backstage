package com.zhuanye.music_system.controller;

import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.service.UserService;
import com.zhuanye.music_system.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther:sabot
 * @date:2020/05/06
 * @description:User控制类
 */
@RestController
@RequestMapping("/music_sharing/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserByUserID/{id}",method = RequestMethod.GET)
    public User getUserByUserID( @PathVariable Integer id){
        return userService.getUserByUserID(id);
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultMessage login(String mailbox,String password){

        //调用service处理
        ResultMessage resultMessage = userService.login(mailbox, password);

        return resultMessage;
    }
}
