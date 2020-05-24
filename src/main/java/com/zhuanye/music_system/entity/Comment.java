package com.zhuanye.music_system.entity;

import java.util.Date;

public class Comment {

    private Integer comment_id;
    private Integer user_id;
    private Integer song_id;
    private String  content;
    private Integer praise_number;
    private Date time;

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
}
