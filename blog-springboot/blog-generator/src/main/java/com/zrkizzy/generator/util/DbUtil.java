package com.zrkizzy.generator.util;

import com.zrkizzy.generator.constant.DbConst;
import com.zrkizzy.generator.entity.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zrkizzy.generator.constant.DbConst.*;

/**
 * 数据库操作工具类
 *
 * @author zhangrongkang
 * @since 2023/6/8
 */
public class DbUtil {

    /**
     * 根据表格名称获取列信息
     *
     * @param tableName 表格名称
     * @return 列信息
     */
    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        // 定义返回结果对象
        List<Field> list = new ArrayList<>();
        // 获取数据库连接
        Connection connection = getConnection();
        // 定义SQL语句
        String sql = "SHOW FULL COLUMNS FROM " + tableName;
        // 编译对象
        Statement statement = connection.createStatement();
        // 执行SQL语句并返回结果集
        ResultSet resultSet = statement.executeQuery(sql);

        // 处理查询结果
        if (null != resultSet) {
            // 遍历每一列数据
            while (resultSet.next()) {
                // 字段名
                String field = resultSet.getString(DbConst.FIELD);
                // 公共属性在BaseEntity中已经定义好，无需在实体类中创建
                if (ID.equals(field) || CREATE_TIME.equals(field) || UPDATE_TIME.equals(field)) {
                    continue;
                }
                // 字段类型
                String type = resultSet.getString(TYPE);
                // 字段注释
                String comment = resultSet.getString(COMMENT);
                // 是否可以为空
                Boolean isNull = resultSet.getString(NULL).equals(YES) ?
                        Boolean.TRUE : Boolean.FALSE;
                // Java类型
                String javaType = castSqlTypeToJavaType(type);
                // 全参构造
                Field entity = new Field(field, type, comment, isNull, javaType, lineToHump(field));
                // 添加当前对象
                list.add(entity);
            }
        }

        return list;
    }

    /**
     * 根据数据库表格名称获取数据库表注释
     *
     * @param tableName 表格名称
     * @return 数据库表注释
     */
    public static String getTableComment(String tableName) throws Exception {
        // 获取数据库连接
        Connection connection = getConnection();
        // 定义SQL语句
        String sql = "SELECT table_comment AS tableComment FROM information_schema.tables WHERE table_name = '" + tableName + "'";
        // 预编译SQL语句
        Statement statement = connection.createStatement();
        // 返回查询结果对象
        ResultSet resultSet = statement.executeQuery(sql);
        String tableComment = "";
        if (resultSet.next()) {
            tableComment = resultSet.getString(DbConst.TABLE_COMMENT);
        }
        // 关闭对应参数
        close(connection, statement, null, resultSet);
        return tableComment;
    }

    /**
     * 将表格字段类型转为Java类型
     *
     * @param columnType 字段类型
     * @return Java类型
     */
    private static String castSqlTypeToJavaType(String columnType) {
        // 将所有字符都转为小写
        String typeCast = columnType.substring(0, columnType.indexOf("(")).toLowerCase();
        // 判断并返回字符类型
        return switch (typeCast) {
            // 字符类型
            case VARCHAR, TEXT, CHAR -> "String";
            // 整数类型
            case INT -> "Integer";
            case BIG_INT -> "Long";
            case TINY_INT -> "Boolean";
            // 浮点类型
            case DOUBLE -> "Double";
            // 时间类型
            case DATE_TIME -> "LocalDateTime";
            // 默认返回String类型
            default -> "String";
        };
    }

    /**
     * 转换表名称为Java类名称
     *
     * @param tableName 表名称
     * @param prefix 表前缀
     * @return Java类名称
     */
    public static String castTableNameToEntityName(String tableName, String prefix) {
        // 去除掉表格掉前缀
        tableName = tableName.replace(prefix, "");
        // 返回Java类名称
        return lineToUpHump(tableName);
    }

    /**
     * 转换表名称为访问路径
     *
     * @param tableName 表格名称
     * @param prefix 表格前缀
     * @return 访问路径名称
     */
    public static String castTableNameToMapping(String tableName, String prefix) {
        // 去掉表格前缀
        tableName = tableName.replace(prefix, "");
        // 返回路径名称
        return tableName.toLowerCase().replace("_", "-");
    }

    /**
     * 下划线转大驼峰
     *
     * @param str 带有下划线的字符
     * @return 驼峰规则实体类名称
     */
    private static String lineToUpHump(String str) {
        String line = lineToHump(str);
        // 将首字母大写
        return line.substring(0, 1).toUpperCase() + line.substring(1);
    }

    /**
     * 下划线转驼峰
     *
     * @param str 带有下划线的字符
     * @return 驼峰规则字段名称
     */
    private static String lineToHump(String str) {
        StringBuilder result = new StringBuilder();
        // 创建正则表达式，寻找下划线字符
        Pattern linePattern = Pattern.compile("_(\\w)");
        // 将字符串全部转换为小写
        str = str.toLowerCase();
        // 将正则表达式应用到字符串并创建匹配器对象
        Matcher matcher = linePattern.matcher(str);
        while (matcher.find()) {
            // 把下划线后一位字符替换为对应的大写字符
            matcher.appendReplacement(result, matcher.group(1).toUpperCase());
        }
        // 查找结束后，将未匹配的部分追加到头部
        matcher.appendTail(result);
        // 返回拼接结果
        return result.toString();
    }

    /**
     * 建立数据库连接
     *
     * @return 数据库连接对象
     */
    private static Connection getConnection() throws Exception {
        // 定义数据库连接参数
        String url = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        // 加载驱动
        Class.forName(driver);
        // 根据连接参数返回连接对象
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 关闭SQL相关资源
     *
     * @param connection 连接对象
     * @param statement 编译对象
     * @param preparedStatement 预编译对象
     * @param resultSet 结果集对象
     * @throws SQLException SQL异常
     */
    private static void close(Connection connection, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        // 关闭连接
        if (null != connection) {
            connection.close();
        }
        // 关闭编译对象
        if (null != statement) {
            statement.close();
        }
        // 关闭预编译对象
        if (null != preparedStatement) {
            preparedStatement.close();
        }
        // 关闭结果集
        if (null != resultSet) {
            resultSet.close();
        }
    }

}
