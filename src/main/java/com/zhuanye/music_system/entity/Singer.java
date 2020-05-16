package com.zhuanye.music_system.entity;

/**
 * @author CZM
 * @description 歌手实体类
 *
 */
public class Singer {

    /**
     * 歌手id主键（递增）
     */
    private int id;
    /**
     *姓名
     */
    private String name;
    /**
     *性别 false：女，true：男
     */
    private boolean gender;
    /**
     * 歌手图路径
     */
    private String picturePath;

    public Singer() {
    }

    public Singer(int id, String name, boolean gender, String picturePath) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
