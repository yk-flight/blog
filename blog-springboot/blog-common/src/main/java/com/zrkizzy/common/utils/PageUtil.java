package com.zrkizzy.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author zhangrongkang
 * @since 2023/7/12
 */
public class PageUtil {

    /**
     * 根据页号和页面大小对指定集合进行分页
     *
     * @param list 指定集合
     * @param currentPage 当前页数
     * @param pageSize 页面大小
     * @return 分页后的结果
     * @param <T> 泛型指定类
     */
    public static <T> List<T> page(List<T> list, int currentPage, int pageSize) {
        // 获取当前数据总数
        int size = list.size();
        // 特殊情况
        if (size <= pageSize) {
            return list;
        }
        // 定义新的集合用于存储返回结果
        List<T> result = new ArrayList<>();
        // 开始截取的位置
        int start = currentPage == 1 ? 0 : (currentPage - 1) * pageSize;
        // 结束截取的位置
        int end = Math.min(size, currentPage * pageSize);
        // 分页并返回
        if (size > start) {
            result = list.subList(start, end);
        }
        return result;
    }
}
