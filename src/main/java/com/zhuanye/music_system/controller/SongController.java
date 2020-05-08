package com.zhuanye.music_system.controller;


import com.mpatric.mp3agic.*;
import com.zhuanye.music_system.entity.Song;
import com.zhuanye.music_system.service.SongService;
import com.zhuanye.music_system.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/song")
public class SongController {

    @Resource
    SongService songService;

    @Value("${song_path}")
    String song_path;

    @Value("${picture_path}")
    String picture_path;

    @RequestMapping(value = "/getSongById/{id}", method = RequestMethod.GET)
    public Song getSongById(@PathVariable int id) {
        return songService.findById(id);
    }

    @RequestMapping("/uploadSong")
    public boolean uploadSong(Song song, MultipartFile file) {
        if (file == null) {
            return false;
        }
        song.setUploaderTime(new Date());
        String fileNewName =  UUID.randomUUID().toString()+".mp3";
        File f = new File(song_path+file.getOriginalFilename());
        try {
            if (f.exists()){
                f.delete();
            }
            f.createNewFile();
            file.transferTo(f.getAbsoluteFile());
            Mp3File mp3File = new Mp3File(f);
            ID3v2 id3v2Tag = null;
            if (mp3File.hasId3v2Tag()) {
                id3v2Tag = mp3File.getId3v2Tag();
            }else {
                id3v2Tag = new ID3v23Tag();
            }
            id3v2Tag.setTitle(song.getName());
            id3v2Tag.setArtist(song.getAuthor());
            // 歌词
            if (song.getLyric() != null) {
                songService.transformLyric(song);
                id3v2Tag.setLyrics(song.getLyric());
            }
            mp3File.setId3v2Tag(id3v2Tag);
            mp3File.save(song_path+fileNewName);
            f.delete();
            song.setPath(fileNewName);
            songService.insertSong(song);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @RequestMapping("/getSongFile/{path}")
    public void getSongFileByPath(@PathVariable String path, HttpServletResponse response){
        String path2 = song_path+path;
        InputStream in = FileUtil.getInputStream(path2);
        if (in == null) {
            response.setStatus(404);
        } else {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-type","audio/mpeg");
            response.addHeader("charset","utf-8");
            /**
             *  支持断点下载
             */
            response.addHeader("Accept-Ranges","bytes");
            try (BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())){
                response.setContentLengthLong(in.available());
                response.addHeader("Content-disposition", "attchement; filename="+URLEncoder.encode(new Mp3File(path2).getId3v2Tag().getTitle()+".mp3","UTF-8"));
                IOUtils.copy(in, outputStream);
                outputStream.flush();
                outputStream.close();
                response.flushBuffer();
            } catch (Exception e) {
                System.err.println("拷贝出错");
                response.setStatus(502);
            } finally {
                if (in != null) {
                    try {
                        in.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @RequestMapping("/downloader")
    public void downloade(String path, int id, HttpServletResponse response) {
        getSongFileByPath(path, response);
        songService.increaseDownloadNumber(id);
    }

//    @RequestMapping("/getSongFile/{path}")
//    public ResponseEntity<byte[]> download(@PathVariable String path){
//        String path2 = song_path+"d55d87cc-31eb-42c8-98ab-208c31e89ff8.mp3";
//        byte[] body = null;
//        InputStream in = FileUtil.getInputStream(path2);
//        if (in != null) {
//            try {
//                body = new byte[in.available()];
//                in.read(body);
//                HttpHeaders headers = new HttpHeaders();
//                headers.add("Accept-Ranges","bytes");
//                headers.setContentLength(in.available());
//                headers.add("Content-Disposition", "attchement;filename="+URLEncoder.encode(new Mp3File(path2).getId3v2Tag().getTitle()+".mp3","UTF-8"));
//                headers.add("Content-type","audio/mpeg");
//                headers.add("charset","utf-8");
//                HttpStatus statusCode = HttpStatus.OK;
//                ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
//                return entity;
//            } catch (Exception e) {
//                System.err.println("拷贝出错");
//            } finally {
//                if (in != null) {
//                    try {
//                        in.close();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }
//        return null;
//    }

}
