package com.zhuanye.music_system.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.dao.SongDao;
import com.zhuanye.music_system.entity.Song;
import com.zhuanye.music_system.support.PageRequest;
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
        List<Song> Songs = songDao.selectByNameOrAuthor(name);
        return new PageInfo<Song>(Songs);
    }

    public PageInfo<Song> getPageInfoTotal(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Song> Songs = songDao.selectAll();
        return new PageInfo<Song>(Songs);
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
}
