package com.zrkizzy.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Freemarker模板工具类
 *
 * @author zhangrongkang
 * @since 2023/6/11
 */
public class FreemarkerUtil {

    /**
     * Freemarker模板文件路径
     */
    private static final String FILE_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-generator\\src\\main\\resources\\template\\";

    /**
     * Freemarker模板类
     */
    private static Template template;

    /**
     * 初始化模板引擎所需的配置和模板文件
     *
     * @param fileName 文件名称
     */
    public static void initConfig(String fileName) throws IOException {
        // 读取模板
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        // 设置模板所在的根目录
        configuration.setDirectoryForTemplateLoading(new File(FILE_PATH));
        // 设置对象包装器
        configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_31));
        template = configuration.getTemplate(fileName);
    }

    /**
     * 生成模板方法
     *
     * @param fileName 包含路径的文件名
     * @throws IOException       IO异常
     * @throws TemplateException Template异常
     */
    public static void generator(String fileName, Map<String, Object> map) throws IOException, TemplateException {
        // 根据模板生成文件
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        template.process(map, bufferedWriter);
        // 关闭流
        bufferedWriter.flush();
        fileWriter.close();
    }

}
