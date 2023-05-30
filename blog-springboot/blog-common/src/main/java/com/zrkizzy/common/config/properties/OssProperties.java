package com.zrkizzy.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 对象存储配置类
 *
 * @author zhangrongkang
 * @since 2023/5/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.oss")
public class OssProperties {

    /**
     * Bucket域名
     */
    private String domain;

    /**
     * 地域节点
     */
    private String endpoint;

    /**
     * 密钥ID
     */
    private String accessKeyId;

    /**
     * 密钥
     */
    private String accessKeySecret;

    /**
     * 桶名称
     */
    private String bucketName;

}
