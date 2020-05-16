package com.zhuanye.music_system.util;

import com.mpatric.mp3agic.Mp3File;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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



    /***
     * @Description: 压缩文件
     * @param filePaths 需要压缩的文件名列表
     * @param prePath 需要压缩的文件目录
     * @param zipFilePath 需要压缩到哪个zip文件
     * @return
     * @throws IOException
     */
    public static File compress(List<String> filePaths, String prePath, String zipFilePath) throws IOException {
        byte[] buf = new byte[1024];
        File zipFile = new File(prePath+zipFilePath);
        //zip文件不存在，则创建文件，用于压缩
        if(!zipFile.exists()) {
            zipFile.createNewFile();
        }
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for(int i = 0; i < filePaths.size(); i++){
                String relativePath = filePaths.get(i);
                if(StringUtils.isEmpty(relativePath)){
                    filePaths.remove(i);
                    continue;
                }
                File sourceFile = new File(prePath+relativePath);
                if(sourceFile == null || !sourceFile.exists()){
                    filePaths.remove(i);
                    continue;
                }
                FileInputStream fis = new FileInputStream(sourceFile);
                zos.putNextEntry(new ZipEntry(new Mp3File(sourceFile).getId3v2Tag().getTitle() + ".mp3"));
                int len = 0;
                while((len = fis.read(buf)) > 0){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipFile;
    }

}
