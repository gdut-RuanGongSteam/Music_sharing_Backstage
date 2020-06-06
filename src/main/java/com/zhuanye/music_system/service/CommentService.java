package com.zhuanye.music_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.dao.CommentDao;
import com.zhuanye.music_system.entity.Comment;
import com.zhuanye.music_system.entity.CommentEntity;
import com.zhuanye.music_system.entity.PraiseUserComment;
import com.zhuanye.music_system.support.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public void addComment( Comment comment){

        commentDao.addComment(comment);


    }

    public void addReply( Comment comment){

        commentDao.addReply(comment);


    }

    public Comment getCommentById( Integer comment_id){
        return commentDao.getCommentById(comment_id);
    }

    public PageInfo<CommentEntity> getCommentList(PageRequest pageRequest, Integer song_id , Integer user_id){
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());

        return new PageInfo<CommentEntity>(commentDao.getCommentList(song_id,user_id));
    }

    public PageInfo<CommentEntity> getNewCommentList(PageRequest pageRequest, Integer song_id , Integer user_id){
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());

        return new PageInfo<CommentEntity>(commentDao.getNewCommentList(song_id,user_id));
    }

    public boolean praise(PraiseUserComment praiseUserComment){

        commentDao.addPraise(praiseUserComment);
        commentDao.addPraise_number( praiseUserComment.getComment_id() );

        return true;
    }

    public boolean unPraise(PraiseUserComment praiseUserComment){

        commentDao.delPraise(praiseUserComment);
        commentDao.delPraise_number( praiseUserComment.getComment_id() );

        return true;
    }

    public boolean deleteComment(Integer comment_id){

        commentDao.updateSonComment(comment_id);
        commentDao.deleteComment(comment_id);

        return true;
    }

    public PageInfo<CommentEntity> getMyCommentList(PageRequest pageRequest,  Integer user_id){

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());

        return new PageInfo<CommentEntity>(commentDao.getMyCommentList(user_id));
    }


}
