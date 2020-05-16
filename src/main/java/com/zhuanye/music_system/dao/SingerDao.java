package com.zhuanye.music_system.dao;

import com.zhuanye.music_system.entity.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingerDao {
    /**
     * 新增
     * @param singer
     * @return
     */
    int insertSinger(Singer singer);

    /**
     *  根据歌手id删除歌手
     * @param id
     * @return
     */
    int deleteSingerById(int id);

    /**
     * 根据歌手id查找歌手信息
     * @param id
     * @return
     */
    Singer selectSingerById(int id);

    /**
     * 根据歌手名模糊查询歌手
     * @param name
     * @return
     */
    List<Singer> selectSingerByName(String name);

    /**
     * 根据歌手名检验是否已经存在
     * @param name
     * @return
     */
    int isExistByName(String name);

    /**
     * 查询所有歌手
     * @return
     */
    List<Singer> selectAll();

    /**
     * 更新歌手信息
     * @param singer
     */
    void updateSinger(Singer singer);

}
