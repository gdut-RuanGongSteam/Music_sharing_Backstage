package com.zhuanye.music_system.entity;


import java.sql.Date;

/**
 * @auther:sabot
 * @date:2020/05/06
 * @description:数据库users_table对应实体类
 */
public class User {

    private Integer id;
    private String name;
    private String password;
    private String mailbox;
    private boolean gender;
    private String phone;
    private String head_picture;
    private Date create_data;
    private boolean enabled;
    private Integer song_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }

    public Date getCreate_data() {
        return create_data;
    }

    public void setCreate_data(Date create_data) {
        this.create_data = create_data;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getSong_number() {
        return song_number;
    }

    public void setSong_number(Integer song_number) {
        this.song_number = song_number;
    }

}
