package com.zrkizzy.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import static com.zrkizzy.common.constant.CommonConst.*;


/**
 * Ip地址工具类
 *
 * @author zhangrongkang
 * @since 2023/3/15
 */
public class IpUtil {

    /**
     * 获取客户端IP地址
     *
     * @param request HttpServletRequest对象
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (!StringUtils.hasLength(ipAddress)) {
            ipAddress = LOCAL_HOST;
        }

        return ipAddress;
    }

    /**
     * 解析ip地址
     *
     * @param ipAddress ip地址
     * @return 解析后的ip地址
     */
    public static String getIpLocation(String ipAddress) {
        if (LOCAL_HOST.equals(ipAddress) || LOOP_BACK_HOST.equals(ipAddress)) {
            return LOCAL_NAME;
        }
        try {
            URL url = new URL("http://opendata.baidu.com/api.php?query=" + ipAddress + "&co=&resource_id=6006&oe=utf8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            Map map = JSON.parseObject(result.toString(), Map.class);
            List<Map<String, String>> data = (List) map.get("data");
            String location = data.get(0).get("location");
            return location.contains(" ") ? location.substring(0, location.indexOf(" ")) : location;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取主机名
     *
     * @return 本地主机名
     */
    public static String getHostName() {
        try {
            // 获取本机名称
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return UNKNOWN_HOST_NAME;
        }
    }
}