package com.zhuanye.music_system.entity;


import java.util.Date;

/**
 * @author:CZM
 * @date:2020/05/06
 * 数据库表song_table对应的实体类
 */
public class Song {
    /**
     * 主键（递增）
     */
    private int id;
    /**
     * 歌曲路径
     */
    private String path;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 歌曲作者
     */
    private String author;
    /***
     * 上传日期
     */
    private Date uploaderTime;
    /**
     * 下载数量
     */
    private int downloaderNumber;


    /**
     * 歌词
     */
    private String lyric;

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUploaderTime() {
        return uploaderTime;
    }

    public void setUploaderTime(Date uploaderTime) {
        this.uploaderTime = uploaderTime;
    }

    public int getDownloaderNumber() {
        return downloaderNumber;
    }

    public void setDownloaderNumber(int downloaderNumber) {
        this.downloaderNumber = downloaderNumber;
    }


}
