package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**默认时间逆序*/
    List<Song> selectAll();

    /**根据下载量排序*/
    List<Song> selectAllOrderByDownload();
    /**
     *  绑定分享者
     * @param userId
     * @param songerId
     */
    void bindShareUser(@Param("userId") int userId, @Param("songId") int songerId);

    /**
     * 收藏歌曲
     * @param userId
     * @param songerId
     * @return
     */
    int collectSongById(@Param("userId") int userId, @Param("songId") int songerId);

    /**
     * 是否收藏过
     * @param userId
     * @param songerId
     * @return
     */
    int ifCollectSong(@Param("userId") int userId, @Param("songId") int songerId);

    /**
     * 根据用户id查询收藏歌曲
     * @param userId
     * @return
     */
    List<Song> selectCollectSongByUserId(int userId);

}
