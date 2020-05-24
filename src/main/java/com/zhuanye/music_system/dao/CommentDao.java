package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao {

    void addComment( Comment comment);
}
