package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SongDao {

    Song selectById(int id);


    void insertSong(Song song);

    /**
     * 增加歌下栽次数
     * @param path
     */
    void increaseDownloadNumber(String path);

    /**
     * 根据歌名或歌手名模糊查询歌
     * @param name
     * @return
     */
    List<Song> selectByNameOrAuthor(String name);

    /**
     * 根据歌手名查询歌
     * @param author
     * @return
     */
    List<Song> selectByAuthor(String author);

    List<Song> selectAll();
}
