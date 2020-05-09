package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Song;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SongDao {
    Song findById(int id);

    void insertSong(Song song);

    void increaseDownloadNumber(int id);
}
