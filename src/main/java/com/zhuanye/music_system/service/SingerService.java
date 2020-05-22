package com.zhuanye.music_system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.dao.SingerDao;
import com.zhuanye.music_system.entity.Singer;
import com.zhuanye.music_system.entity.Song;
import com.zhuanye.music_system.support.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SingerService {

    @Resource
    private SingerDao singerDao;

    public int inserSinger(Singer singer) {
        return singerDao.insertSinger(singer);
    }

    public int deleteSingerById(int id) {
        return singerDao.deleteSingerById(id);
    }

    public PageInfo<Singer> selectAll(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Singer>(singerDao.selectAll());
    }

    public List<Singer> selectSingerByName(String name) {
        return singerDao.selectSingerByName(name);

    }

    public void updatesinger(Singer singer){
        singerDao.updateSinger(singer);
    }

    public Singer selectSingerById(int id) {
        return  singerDao.selectSingerById(id);
    }

    public boolean isExistByName(String name) {
        if (singerDao.isExistByName(name) > 0) {
            return true;
        }else {
            return false;
        }
    }
    /**模糊分页查询歌手名*/
    public PageInfo<Singer> getPageInfoByName(PageRequest pageRequest, String name) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return new PageInfo<Singer>(singerDao.selectSingerByName(name));
    }


}
