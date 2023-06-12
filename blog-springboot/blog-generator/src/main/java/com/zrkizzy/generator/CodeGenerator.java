package com.zrkizzy.generator;

import com.zrkizzy.common.utils.TimeUtil;
import com.zrkizzy.generator.constant.DbConst;
import com.zrkizzy.generator.entity.Field;
import com.zrkizzy.generator.util.DbUtil;
import com.zrkizzy.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 业务逻辑代码生成器
 *
 * @author zhangrongkang
 * @since 2023/6/7
 */
public class CodeGenerator {

    /**
     * 作者名称
     */
    private static final String AUTHOR_NAME = "zhangrongkang";

    /**
     * 创建时间
     */
    private static final String DATE = "date";

    /**
     * 表格前缀
     */
    private static final String TABLE_PREFIX = "tb_";

    /**
     * 是否开启二级缓存
     */
    private static final Boolean ENABLE_CACHE = false;

    /**
     * 是否开启链式编程
     */
    private static final Boolean IS_CHAIN = false;

    /**
     * 是否生成Controller类
     */
    private static final Boolean IS_CONTROLLER = false;

    /**
     * 是否生成Service类
     */
    private static final Boolean IS_SERVICE = false;

    /**
     * 实体类生成路径
     */
    private static final String ENTITY_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\domain\\";

    /**
     * Mapper生成路径
     */
    private static final String MAPPER_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\mapper\\";

    /**
     * Mapper映射文件生成路径
     */
    private static final String MAPPER_XML_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\resources\\mapper\\";

    public static void main(String[] args) throws Exception {
        // 交互式生成
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入表格名称：");
        // 用户输入的表格名称
        String table = scanner.next();
        // 获取表格注释以及实体类名称
        String tableComment = DbUtil.getTableComment(table);
        String objectName = tableComment.substring(0, tableComment.length() - 1);
        System.out.println("表名称：" + table + ", 表注释：" + tableComment);
        String entityName = DbUtil.castTableNameToEntityName(table, TABLE_PREFIX);
        System.out.println("Java类名称：" + entityName);
        // 获取表格列信息
        List<Field> fieldList = DbUtil.getColumnByTableName(table);
        for (Field field : fieldList) {
            System.out.println("列信息：" + field);
        }
        // 设置生成文件的数据
        Map<String, Object> map = new HashMap<>();
        // 作者
        map.put(DbConst.AUTHOR, AUTHOR_NAME);
        // 创建时间
        map.put(DATE, TimeUtil.getNowDate());
        // 是否开启链式编程
        map.put(DbConst.CHAIN_MODEL, IS_CHAIN);
        // 是否开启二级缓存
        map.put(DbConst.ENABLE_CACHE, ENABLE_CACHE);
        // 表格名称
        map.put(DbConst.TABLE_NAME, table);
        // 实体类名称（中文）
        map.put(DbConst.OBJECT_NAME, objectName);
        // Java类名称
        map.put(DbConst.ENTITY_NAME, entityName);
        // 所有列信息
        map.put(DbConst.FIELD_LIST, fieldList);

        System.out.println("---------------------------------- 开始生成代码 ----------------------------------");

        generateEntity(entityName, map);
        generateMapper(entityName, map);

        System.out.println("---------------------------------- 结束生成代码 ----------------------------------");
    }

    /**
     * 生成表格实体类
     */
    private static void generateEntity(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成实体类...");
        FreemarkerUtil.initConfig("entity.ftl");
        FreemarkerUtil.generator(ENTITY_PATH + objectName + ".java", map);
    }

    /**
     * 生成Mapper接口和映射文件
     */
    private static void generateMapper(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成Mapper接口...");
        FreemarkerUtil.initConfig("mapper.ftl");
        FreemarkerUtil.generator(MAPPER_PATH + objectName + "Mapper.java", map);
        System.out.println("开始生成Mapper映射文件...");
        FreemarkerUtil.initConfig("mapper.xml.ftl");
        FreemarkerUtil.generator(MAPPER_XML_PATH + objectName + "Mapper.xml", map);
    }
}
