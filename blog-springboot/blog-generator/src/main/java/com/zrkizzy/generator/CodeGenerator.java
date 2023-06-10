package com.zrkizzy.generator;

import com.zrkizzy.generator.constant.DbConst;
import com.zrkizzy.generator.entity.Field;
import com.zrkizzy.generator.util.DbUtil;

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
     * 表格前缀
     */
    private static final String TABLE_PREFIX = "tb_";

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
        // 表格名称
        map.put(DbConst.TABLE_NAME, table);
        // 实体类名称（中文）
        map.put(DbConst.OBJECT_NAME, objectName);
        // Java类名称
        map.put(DbConst.ENTITY_NAME, entityName);
        generate();
    }

    /**
     * 生成表格数据
     */
    private static void generate() {
        System.out.println("开始根据表格名称生成数据");
    }
}
