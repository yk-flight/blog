package com.zrkizzy.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 对象存储配置类
 *
 * @author zhangrongkang
 * @since 2023/5/20
 */
@Data
public class OssProperties {

    /**
     * 上传终点
     */
    @Value("oss.endpoint")
    private String endpoint;

    /**
     * 公钥
     */
    @Value("oss.accessKey")
    private String accessKey;

    /**
     * 密钥
     */
    @Value("oss.secretKey")
    private String secretKey;

    /**
     * 桶名称
     */
    @Value("oss.bucketName")
    private String bucketName;

}
