package com.zrkizzy.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 本地上传策略配置类
 *
 * @author zhangrongkang
 * @since 2023/5/30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.local")
public class LocalProperties {

    /**
     * 文件路径
     */
    private String path;

    /**
     * 访问域名
     */
    private String domain;

}
