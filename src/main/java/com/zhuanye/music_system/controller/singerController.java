package com.zhuanye.music_system.controller;

import com.github.pagehelper.PageInfo;
import com.zhuanye.music_system.entity.Singer;
import com.zhuanye.music_system.entity.Song;
import com.zhuanye.music_system.service.SingerService;
import com.zhuanye.music_system.support.PageRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author CZM
 * @descriptior 歌手controller类
 * @date 2020/05/12
 */
@RestController
@RequestMapping("/singer")
@CrossOrigin
public class singerController {

    @Resource
    private SingerService singerService;

    @Value("${picture_path}")
    private String picture_path;

    /**歌手名模糊查询*/
    @RequestMapping("/singerList")
    public PageInfo<Singer> singerList(PageRequest pageRequest){
        return singerService.selectAll(pageRequest);
    }

    /**歌手名模糊查询*/
    @RequestMapping("/findSingerByName")
    public PageInfo<Singer> findSingerByName(String name, PageRequest pageRequest){
        return singerService.getPageInfoByName(pageRequest, name);
    }

    @RequestMapping("/findSingerById")
    public Singer findSingerById(int id) {
        return singerService.selectSingerById(id);
    }

    @RequestMapping("/isExistByName")
    public boolean isExistByName(String name) {
        return singerService.isExistByName(name);
    }

    /**新增歌手*/
    @RequestMapping("/addSinger")
    public boolean addSinger(Singer singer, MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename()+"###");
        System.out.println(StringUtils.isNotBlank(file.getOriginalFilename()));
        System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')));
        if (singer != null) {
            if(StringUtils.isNotBlank(file.getOriginalFilename())) {
                File picturePathFile = createPicturePath(singer, file);
                if (picturePathFile != null) {
                    System.out.println(picturePathFile.getName());
                    singer.setPicturePath(picturePathFile.getName());
                }
            }
            singerService.inserSinger(singer);
            return true;
        }
        return false;
    }

    @RequestMapping("/updateSinger")
    public boolean updateSinger(Singer singer, MultipartFile file) {
        if (singer != null) {
            Singer singerOld = singerService.selectSingerById(singer.getId());
            if(StringUtils.isNotBlank(file.getOriginalFilename())) {
                //图片更新失败
                if(createPicturePath(singer, file)==null) {
                    singer.setPicturePath(singerOld.getPicturePath());
                }else {
                    //删除旧图片
                    File f = new File(picture_path+singerOld.getPicturePath());
                    if (f.exists()){
                        f.delete();
                    }
                }
            } else {
                singer.setPicturePath(singerOld.getPicturePath());
            }
            singerService.updatesinger(singer);
            return true;
        }
        return false;
    }

    //创建保存图片
    private File createPicturePath(Singer singer, MultipartFile file){
        String fileNewName =  UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        File f = new File(picture_path+fileNewName);
        if (f.exists()){
            f.delete();
        }
        try {
            f.createNewFile();
            file.transferTo(f.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            f.delete();
            return null;
        }
        singer.setPicturePath(fileNewName);
        return f;
    }

}
