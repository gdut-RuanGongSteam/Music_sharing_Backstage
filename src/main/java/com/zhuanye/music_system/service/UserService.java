package com.zhuanye.music_system.service;

import com.zhuanye.music_system.dao.UserDao;
import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.support.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserDao userDao;

    //根据id获取用户信息
    public User getUserByUserID(Integer id){
        return userDao.getUserByUserID(id);
    }

    //登录
    public ResultMessage login(String mailbox,String password){

        ResultMessage resultMessage = new ResultMessage();
        List<String> msg = new ArrayList<>();

        //数据库查询用户
        User userByEmail = userDao.getUserByEmail(mailbox);

        if (userByEmail == null){
            msg.add("用户不存在");
        }else{
            if (userByEmail.getPassword().equals(password)){
                resultMessage.setFlag(true);
            }else{
                msg.add("密码错误");
            }
        }
        resultMessage.setMsg(msg);
        return resultMessage;
    }

}
