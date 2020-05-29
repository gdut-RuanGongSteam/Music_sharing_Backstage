package com.zhuanye.music_system.entity;

public class PraiseUserComment {

    private Integer praise_id;
    private Integer user_id;
    private Integer comment_id;


    public Integer getPraise_id() {
        return praise_id;
    }

    public void setPraise_id(Integer praise_id) {
        this.praise_id = praise_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }
}
