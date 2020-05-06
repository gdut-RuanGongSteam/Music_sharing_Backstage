package com.zhuanye.music_system.service;

import com.zhuanye.music_system.dao.UserDao;
import com.zhuanye.music_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    @Autowired
    private UserDao userDao;


    public User getUserByUserID(Integer id){
        return userDao.getUserByUserID(id);
    }



}
