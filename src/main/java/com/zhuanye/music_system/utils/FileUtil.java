package com.zhuanye.music_system.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtil {
    public static InputStream getInputStream(String path) {
        InputStream io = null;
        try {
            io = new FileInputStream(path);
        } catch (Exception e) {
            System.err.println("文件打开失败！path="+path);
            return null;
        }
        return io;
    }
}
