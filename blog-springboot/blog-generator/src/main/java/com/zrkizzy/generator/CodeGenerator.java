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

    // =================================================================== 以下为必填项 ===================================================================

    /**
     * 作者名称
     */
    private static final String AUTHOR_NAME = "zhangrongkang";

    /**
     * 表格前缀
     */
    private static final String TABLE_PREFIX = "tb_";

    /**
     * Controller生成路径
     */
    private static final String CONTROLLER_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-web\\src\\main\\java\\com\\zrkizzy\\web\\controller\\system\\";

    /**
     * Service生成路径
     */
    private static final String SERVICE_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-server\\src\\main\\java\\com\\zrkizzy\\server\\service\\system\\";

    /**
     * Service实现类生成路径
     */
    private static final String SERVICE_IMPL_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-server\\src\\main\\java\\com\\zrkizzy\\server\\service\\system\\impl\\";

    /**
     * 是否开启二级缓存
     */
    private static final Boolean ENABLE_CACHE = false;

    /**
     * 是否开启链式编程
     */
    private static final Boolean IS_CHAIN = false;

    // =================================================================== 必填项结束 ===================================================================

    /**
     * 创建时间
     */
    private static final String DATE = "date";

    /**
     * 实体类生成路径
     */
    private static final String ENTITY_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\domain\\";

    /**
     * DTO类生成路径
     */
    private static final String DTO_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\dto\\";

    /**
     * VO类生成路径
     */
    private static final String VO_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\vo\\";

    /**
     * 实体类生成路径
     */
    private static final String QUERY_PATH = "E:\\JavaProject\\blog\\blog-springboot\\blog-data\\src\\main\\java\\com\\zrkizzy\\data\\query\\";

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

        // 与用户交互，是否生成Controller、Service、Query、DTO、VO类
        System.out.print("请输入是否生成Query类（1-生成/0-不生成）：");
        // 是否生成Query类（默认生成）
        Boolean isQuery = isGenerator(scanner.nextInt());
        System.out.print("请输入是否生成Controller层（1-生成/0-不生成）：");
        // 是否生成Controller类（默认生成）
        Boolean isController = isGenerator(scanner.nextInt());
        //是否生成Service类（默认生成）
        System.out.print("请输入是否生成Service层（1-生成/0-不生成）：");
        Boolean isService = isGenerator(scanner.nextInt());

        // 获取表格注释以及实体类名称
        String tableComment = DbUtil.getTableComment(table);
        String objectName = tableComment.substring(0, tableComment.length() - 1);
        System.out.println("表名称：" + table + ", 表注释：" + tableComment);
        String entityName = DbUtil.castTableNameToEntityName(table, TABLE_PREFIX);
        // 首字母小写后的Java类名称
        String entityLowerName = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
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
        // Java类名称
        map.put(DbConst.ENTITY_LOWER_NAME, entityLowerName);
        // 所有列信息
        map.put(DbConst.FIELD_LIST, fieldList);
        // 映射路径
        map.put(DbConst.MAPPING, DbUtil.castTableNameToMapping(table, TABLE_PREFIX));
        // 是否生成Controller类
        map.put(DbConst.IS_CONTROLLER, isController);
        // 是否生成Service类
        map.put(DbConst.IS_SERVICE, isService);
        // 是否生成Query类
        map.put(DbConst.IS_QUERY, isQuery);

        System.out.println("---------------------------------- 开始生成代码 ----------------------------------");
        // 1. 生成实体类、Query、DTO和VO
        generateEntity(entityName, map);
        if (isQuery) {
            generateQuery(entityName, map);
        }
        // 2. 生成Mapper接口和映射文件
        generateMapper(entityName, map);
        // 3. 生成Service
        if (isService) {
            generateService(entityName, map);
        }
        // 4. 生成Controller
        if (isController) {
            generateController(entityName, map);
        }

        System.out.println("---------------------------------- 结束生成代码 ----------------------------------");
    }

    /**
     * 生成表格实体类
     */
    private static void generateEntity(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成实体类...");
        FreemarkerUtil.initConfig("entity.ftl");
        FreemarkerUtil.generator(ENTITY_PATH + objectName + ".java", map);

        System.out.println("开始生成DTO类...");
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(DTO_PATH + objectName + "DTO.java", map);

        System.out.println("开始生成VO类...");
        FreemarkerUtil.initConfig("vo.ftl");
        FreemarkerUtil.generator(VO_PATH + objectName + "VO.java", map);
    }

    /**
     * 生成实体类查询对象
     */
    private static void generateQuery(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成实体类Query对象...");
        FreemarkerUtil.initConfig("query.ftl");
        FreemarkerUtil.generator(QUERY_PATH + objectName + "Query.java", map);
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

    /**
     * 生成Controller类
     */
    private static void generateController(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成Controller层...");
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(CONTROLLER_PATH + objectName + "Controller.java", map);
    }

    /**
     * 生成Mapper接口和映射文件
     */
    private static void generateService(String objectName, Map<String, Object> map) throws IOException, TemplateException {
        System.out.println("开始生成Service接口...");
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator( SERVICE_PATH + "I" + objectName + "Service.java", map);
        System.out.println("开始生成ServiceImpl实现类...");
//        FreemarkerUtil.initConfig("serviceImpl.ftl");
//        FreemarkerUtil.generator(SERVICE_IMPL_PATH + objectName + "ServiceImpl.xml", map);
    }

    /**
     * 根据flag值判断是否需要生成对应层
     *
     * @param flag 用户输入值
     * @return true：生成；false：不生成
     */
    private static Boolean isGenerator(Integer flag) {
        return switch (flag) {
            // 不生成
            case 0 -> false;
            // 生成
            case 1 -> true;
            // 默认生成
            default -> true;
        };
    }
}
