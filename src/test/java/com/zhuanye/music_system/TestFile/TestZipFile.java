package com.zhuanye.music_system.TestFile;

import com.alibaba.fastjson.JSONArray;
import com.zhuanye.music_system.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestZipFile {
    @Test
    void ZipUtil(){
        try {
        List<String> paths = new ArrayList<String>();
        paths.add("f8ed936a-2ad8-4f7f-b40a-c80d95fc50e1.mp3");
        paths.add("d55d87cc-31eb-42c8-98ab-208c31e89ff8.mp3");
        paths.add("7bd0e716-718a-463b-9cf5-4cac1601af89.mp3");
//        FileUtil.compress(paths,"files/songs/","files/songs/song.zip");
//            String[] paths = {"d55d87cc-31eb-42c8-98ab-208c31e89ff8.mp3","d11ea914-0f9f-4437-937d-f0bc73ccc7df.mp3"};
        FileUtil.compress(paths,"files/songs/","files/songs/song.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testJsonArray() {
        System.out.println(new File("").getAbsolutePath());
        JSONArray objects = JSONArray.parseArray("[32dc,87]");
        System.out.println(objects);
    }

}
