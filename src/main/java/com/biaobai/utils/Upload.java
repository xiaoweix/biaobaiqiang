package com.biaobai.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 上传文件
 */
public class Upload {
    public static String uploadImages(MultipartFile file, String path) throws IOException {
        String filename = file.getOriginalFilename();// 上传文件的真实名称
        String suffix = filename.substring(filename.lastIndexOf(".")); // 后缀
        String name = getNanoTimeID() + suffix;// 文件新名称
        File temp = new File(path, name);
        if (temp.getParentFile().exists()) {
            temp.getParentFile().mkdir();
        }
        if (temp.exists()) {
            temp.delete();
        }
        temp.createNewFile();
        file.transferTo(temp);
        return temp.getName();
    }

    /**
     * 当前纳秒 + 随机6位随机数
     *
     * @return
     */
    public static String getNanoTimeID() {
        Long l = System.nanoTime();
        return l.toString() + (int) ((Math.random() + 1) * 100000);
    }
}
