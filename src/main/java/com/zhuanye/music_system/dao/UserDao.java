package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    //根据用户id获取用户信息
    User getUserByUserID( Integer id );
}
