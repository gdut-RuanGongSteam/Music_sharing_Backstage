package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Comment;
import com.zhuanye.music_system.entity.CommentEntity;
import com.zhuanye.music_system.entity.PraiseUserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {

    Comment getCommentById(Integer comment_id);

    void addComment( Comment comment);

    void addReply( Comment comment);

    List<CommentEntity> getCommentList(@Param("song_id") Integer song_id , @Param("user_id") Integer user_id);

    List<CommentEntity> getNewCommentList(@Param("song_id") Integer song_id , @Param("user_id") Integer user_id);

    List<CommentEntity> getMyCommentList( Integer user_id);

    void addPraise_number(Integer comment_id);

    void addPraise( PraiseUserComment praiseUserComment );

    void delPraise_number(Integer comment_id);

    void delPraise( PraiseUserComment praiseUserComment );

    void deleteComment(Integer comment_id);

    void updateSonComment(Integer comment_id);






}
