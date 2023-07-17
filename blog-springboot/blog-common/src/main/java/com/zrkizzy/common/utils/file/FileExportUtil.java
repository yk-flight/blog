package com.zrkizzy.common.utils.file;

import com.zrkizzy.common.enums.file.FIleTypeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件导出工具类
 *
 * @author zhangrongkang
 * @since 2023/7/17
 */
public class FileExportUtil {

    /**
     * 设置响应结果
     *
     * @param response 响应结果对象
     * @param exportFileName  导出文件名
     * @param excelTypeEnum Excel文件后缀枚举
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    public static void setResponseProp(HttpServletResponse response, String exportFileName, FIleTypeEnum excelTypeEnum) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(exportFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + excelTypeEnum.getType());
    }
}
