package com.zhuanye.music_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author:CZM
 * @date:2020/05/25
 * 歌手列表视图数据
 */
public class SingerListView {
    /**
     *姓名
     */
    private String name;
    /**
     * 歌手图路径
     */
    private String picturePath;

    public SingerListView() {

    }

    public SingerListView(String name, String picturePath) {
        this.name = name;
        this.picturePath = picturePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "SingerListView{" +
                "name='" + name + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
