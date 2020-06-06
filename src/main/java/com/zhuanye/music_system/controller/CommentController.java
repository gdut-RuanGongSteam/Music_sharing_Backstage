package com.zhuanye.music_system.controller;

import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.entity.Comment;
import com.zhuanye.music_system.entity.CommentEntity;
import com.zhuanye.music_system.entity.PraiseUserComment;
import com.zhuanye.music_system.entity.User;
import com.zhuanye.music_system.service.CommentService;
import com.zhuanye.music_system.support.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //添加评论
    @RequestMapping("/addComment")
    public boolean AddComment(Integer song_id , String content , HttpServletRequest request){
        Comment comment = new Comment();
        comment.setSong_id(song_id);
        comment.setContent(content);
        User user = (User) request.getSession().getAttribute("user");
        comment.setUser_id(user.getId());

        comment.setTime(new Date());

        commentService.addComment(comment);

        return true;
    }

    //添加回复
    @RequestMapping("/addReply")
    public boolean addReply( Integer comment_id ,  String content , HttpServletRequest request){

        Comment commentById = commentService.getCommentById(comment_id);

        System.out.println(commentById.toString());

        Comment comment = new Comment();
        comment.setSong_id(commentById.getSong_id());
        comment.setContent(content);
        comment.setFather_comment_id(commentById.getComment_id());

        User user = (User) request.getSession().getAttribute("user");
        comment.setUser_id(user.getId());

        comment.setTime(new Date());
        commentService.addReply(comment);

        return true;
    }

    //分页查询精彩评论
    @RequestMapping("/getCommentList")
    public PageInfo<CommentEntity> getCommentList(PageRequest pageRequest, Integer song_id,HttpServletRequest request ){
        User user = (User) request.getSession().getAttribute("user");
        return commentService.getCommentList(pageRequest, song_id, user.getId());
    }

    //分页查询最新评论
    @RequestMapping("/getNewCommentList")
    public PageInfo<CommentEntity> getNewCommentList(PageRequest pageRequest, Integer song_id,HttpServletRequest request ){
        User user = (User) request.getSession().getAttribute("user");
        return commentService.getNewCommentList(pageRequest, song_id, user.getId());
    }

    //点赞
    @RequestMapping("/praise")
    public boolean praise(Integer comment_id, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        PraiseUserComment praiseUserComment = new PraiseUserComment();
        praiseUserComment.setComment_id(comment_id);
        praiseUserComment.setUser_id(user.getId());
        commentService.praise(praiseUserComment);

        return true;
    }

    //取消点赞
    @RequestMapping("/unPraise")
    public boolean unPraise(Integer comment_id, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        PraiseUserComment praiseUserComment = new PraiseUserComment();
        praiseUserComment.setComment_id(comment_id);
        praiseUserComment.setUser_id(user.getId());
        commentService.unPraise(praiseUserComment);
        return true;
    }

    //删除评论
    @RequestMapping("/deleteComment")
    public boolean deleteComment(Integer comment_id){
        commentService.deleteComment(comment_id);
        return true;
    }

    //获取我的全部评论
    @RequestMapping("/getMyCommentList")
    public PageInfo<CommentEntity> getMyCommentList(PageRequest pageRequest, HttpServletRequest request ){
        User user = (User) request.getSession().getAttribute("user");
        return commentService.getMyCommentList(pageRequest,  user.getId());
    }




}
