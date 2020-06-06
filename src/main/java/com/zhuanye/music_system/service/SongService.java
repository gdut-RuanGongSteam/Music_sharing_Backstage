package com.zhuanye.music_system.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.dao.SongDao;
import com.zhuanye.music_system.entity.Song;
import com.zhuanye.music_system.support.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SongService {

    @Resource
    SongDao songDao;

    public Song selectById(int id) {
        Song song =songDao.selectById(id);
        return song;
    }


    /**
     * 调用分页插件完成分页
     * @param pageRequest 页面信息
     * @param name 歌名
     * @return
     */
    public PageInfo<Song> getPageInfoByNameOrAuthor(PageRequest pageRequest, String name) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Song>(songDao.selectByNameOrAuthor(name));
    }

    public PageInfo<Song> getPageInfoTotal(PageRequest pageRequest,boolean sortByDownloadNum) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        if(sortByDownloadNum) {
            return new PageInfo<Song>(songDao.selectAllOrderByDownload());
        }else {
            return new PageInfo<Song>(songDao.selectAll());
        }
    }

    public PageInfo<Song> getPageInfoByName(PageRequest pageRequest, String author) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Song>(songDao.selectByAuthor(author));
    }

    public void insertSong(Song song) {
        songDao.insertSong(song);
    }


    public void increaseDownloadNumber(String path){
        songDao.increaseDownloadNumber(path);
    }

    public void transformLyric(Song song) {
        String lyric = song.getLyric();
        String[] splits = lyric.split("\\[");
        JSONObject jsonObject = new JSONObject();
        for (String split: splits) {
            String[] split1 = split.split("]");
            if (split1.length == 1) {
                jsonObject.put(split1[0], null);
            } else {
                jsonObject.put(split1[0], split1[1]);
            }
        }
        song.setLyric(jsonObject.toString());

    }

//    public void bindShareUser(int userId,int songId) {
//        songDao.bindShareUser(userId, songId);
//    }

    public boolean collectSongById(int userId, int songId) {
        if (songDao.ifCollectSong(userId, songId) > 0) {
            return false;
        }else if (songDao.collectSongById(userId, songId) > 0){
            return true;
        } else {
            return false;
        }
    }

    public PageInfo<Song> selectCollectSongByUserId(int userId, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Song>(songDao.selectCollectSongByUserId(userId));
    }

    public PageInfo<Song> selectSongBySharerName(String sharerName, PageRequest pageRequest){
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Song>(songDao.selectSongBySharerName(sharerName));
    }

    public boolean hadShareSong(String name, String author, String sharerName){
        if (songDao.hadShareSong(name, author,sharerName)>0) {
            return true;
        }
        return false;
    }
}
