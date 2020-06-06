package com.zhuanye.music_system.entity;

import java.util.Date;

public class CommentEntity {

    private Integer comment_id;
    private Integer user_id;
    private Integer song_id;
    private String  content;
    private Integer praise_number;
    private Date time;
    private Integer father_comment_id;

    private Integer praise_id ;

    private User user;

    private Comment father_comment;

    private User father_user;





    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraise_number() {
        return praise_number;
    }

    public void setPraise_number(Integer praise_number) {
        this.praise_number = praise_number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getFather_comment_id() {
        return father_comment_id;
    }

    public void setFather_comment_id(Integer father_comment_id) {
        this.father_comment_id = father_comment_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getFather_comment() {
        return father_comment;
    }

    public void setFather_comment(Comment father_comment) {
        this.father_comment = father_comment;
    }

    public User getFather_user() {
        return father_user;
    }

    public void setFather_user(User father_user) {
        this.father_user = father_user;
    }

    public Integer getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(Integer praise_id) {
        this.praise_id = praise_id;
    }
}
