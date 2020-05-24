package com.zhuanye.music_system.service;

import com.zhuanye.music_system.dao.CommentDao;
import com.zhuanye.music_system.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public void addComment( Comment comment){

        commentDao.addComment(comment);


    }
}
