package com.zhuanye.music_system.service;

import com.alibaba.fastjson.JSONObject;
import com.zhuanye.music_system.dao.SongDao;
import com.zhuanye.music_system.entity.Song;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SongService {

    @Resource
    SongDao songDao;

    public Song findById(int id) {
        Song song =songDao.findById(id);
        return song;
    }



    public void insertSong(Song song) {
        songDao.insertSong(song);
    }


    public void increaseDownloadNumber(int id){
        songDao.increaseDownloadNumber(id);
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
