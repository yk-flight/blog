package com.zrkizzy.common.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.security.MessageDigest;

/**
 * 文件操作工具类
 *
 * @author zhangrongkang
 * @since 2023/5/24
 */
public class FileUtil {

    /**
     * 获取文件MD5哈希值
     *
     * @param inputStream 文件输入流
     * @return 文件MD5哈希值
     */
    public static String getFileMd5(InputStream inputStream) {
        try {
            // 通过MD5算法进行哈希处理
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 字节数组对象
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                // 更新哈希值，从字节数组的第0个位置开始，更新长度为length的字节
                md5.update(buffer, 0, length);
            }
            // 将MessageDigest对象的哈希值转换为十六进制字符串并返回
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常则返回null
            return null;
        } finally {
            // 关闭文件输入流
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名称
     * @return 文件扩展名 jpg、png、gif...
     */
    public static String getFileExtName(String fileName) {
        // 截取文件格式并返回
        System.out.println(fileName);
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/zhangrongkang/Downloads/IMG_1931.JPG");
        InputStream in = new FileInputStream(file);
        System.out.println(getFileMd5(in));
    }
}
