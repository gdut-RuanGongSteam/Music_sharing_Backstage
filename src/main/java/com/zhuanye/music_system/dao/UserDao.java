package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    //根据用户id获取用户信息
    User getUserByUserID( Integer id );

    //根据邮箱获取用户信息
    User getUserByEmail(String mailbox);

    //添加用户
    void addUser(User user);

    //激活用户
    void enabled(Integer id);

    //上传头像
    void uploadHeadPicture(User user);

    //修改密码
    void updatePassword(User user);

    //修改用户资料
    void updateMessage(User user);

    //增加用户歌曲分享数量
    void increaseShareNum(Integer id);
}
