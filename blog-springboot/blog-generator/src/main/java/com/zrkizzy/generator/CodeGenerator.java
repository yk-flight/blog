package com.zrkizzy.generator;

import com.zrkizzy.generator.util.DbUtil;

/**
 * 业务逻辑代码生成器
 *
 * @author zhangrongkang
 * @since 2023/6/7
 */
public class CodeGenerator {

    /**
     * 表格名称
     */
    public static final String TABLE_NAME = "tb_file";

    public static void main(String[] args) throws Exception {
        // 表格注释
        String tableComment = DbUtil.getTableComment(TABLE_NAME);
        System.out.println("表名称：" + TABLE_NAME + ", 表注释：" + tableComment);
        // 表格列信息


    }
}
