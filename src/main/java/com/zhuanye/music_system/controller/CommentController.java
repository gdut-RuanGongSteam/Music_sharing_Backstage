package com.zhuanye.music_system.controller;

import com.zhuanye.music_system.entity.Comment;
import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;


    //添加评论
    @RequestMapping("/addComment")
    @ResponseBody
    public String AddComment(Integer song_id , String content , HttpServletRequest request){
        Comment comment = new Comment();
        comment.setSong_id(song_id);
        comment.setContent(content);
        User user = (User) request.getSession().getAttribute("user");
        comment.setUser_id(user.getId());

        comment.setTime(new Date());

        commentService.addComment(comment);

        return "添加成功";
    }
}
