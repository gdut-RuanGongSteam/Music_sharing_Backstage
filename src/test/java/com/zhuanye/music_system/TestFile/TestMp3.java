package com.zhuanye.music_system.TestFile;


import com.mpatric.mp3agic.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class TestMp3 {
    private static final String SONG_NAME_KEY = "TIT2";
    private static final String ARTIST_KEY = "TPE1";
    private static final String ALBUM_KEY = "TALB";

    @Autowired
    private Environment environment;


    @Test
    void test2() throws InvalidDataException, IOException, UnsupportedTagException, NotSupportedException {
//        Mp3File mp3file = new Mp3File("D:\\kugou\\薛之谦 - 天份 - 副本.mp3");
        Mp3File mp3file = new Mp3File("D:\\薛之谦 - 天份 - 副本.mp3");
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            System.out.println(id3v2Tag.getClass());
            System.out.println("唱片歌曲数量: " + id3v2Tag.getTrack());
            System.out.println("艺术家: " + id3v2Tag.getArtist());
            System.out.println("歌曲名: " + id3v2Tag.getTitle());
            id3v2Tag.setTitle("天份");
            System.out.println("唱片名: " + id3v2Tag.getAlbum());
            System.out.println("歌曲长度:"+mp3file.getLengthInSeconds()+"秒");
            System.out.println("码率: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
            System.out.println("专辑插画 : "+id3v2Tag.getAlbumImage());
            System.out.println("专辑插画类型"+id3v2Tag.getAlbumImageMimeType());
            System.out.println("发行时间: " + id3v2Tag.getYear());
            System.out.println("流派: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
            System.out.println("注释: " + id3v2Tag.getComment());
            System.out.println("歌词: " + id3v2Tag.getLyrics());
            System.out.println("作曲家: " + id3v2Tag.getComposer());
            System.out.println("发行公司: " + id3v2Tag.getPublisher());
            System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
            System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
            System.out.println("版权: " + id3v2Tag.getCopyright());
            System.out.println("URL: " + id3v2Tag.getUrl());
            System.out.println("编码格式: " + id3v2Tag.getEncoder());
            byte[] albumImageData = id3v2Tag.getAlbumImage();
            if (albumImageData != null) {
                System.out.println("专辑插图长度: " + albumImageData.length + " bytes");
                System.out.println("专辑插图类型: " + id3v2Tag.getAlbumImageMimeType());
            }
        }
//        mp3file.save("D:\\薛之谦 - 天份 - 副本.mp3");

    }

    @Test
    void testRandomString() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    void testsplite(){
        String str = "[00:00.00]陈奕迅 - 渐渐\n" +
                "[00:02.00]作词：陈咏谦\n" +
                "[00:04.00]作曲：张杰邦\n" +
                "[00:05.00]编曲：the duo band\n" +
                "[00:06.00]监制：王双骏\n" +
                "[00:07.00]歌词编辑：果果\n" +
                "[00:08.00]QQ:765708831\n" +
                "[00:10.00]Lrc歌词网：www.90lrc.cn\n" +
                "[00:12.00]\n" +
                "[00:14.22]在夜晚 说早晨";
        String[] split = str.split("\\[");
        System.out.println(split[0].split("]").length);
        System.out.println(split[1].split("]").length);
    }

    @Test
    void setFile(){
        File f = new File("./files/songs/你好.mp3");
        System.out.println(f.isFile());
    }
}
