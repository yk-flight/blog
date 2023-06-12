<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zrkizzy.data.mapper.${entityName}Mapper">
    <#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
    </#if>

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="com.zrkizzy.data.domain.${entityName}">
        <id column="id" property="id" />
        <#list fieldList as field>
        <result column="${field.columnName}" property="${field.javaName}" />
        </#list>
        <result column="create_time" property="createTime" />
        <result column="update_time" property="updateTime" />
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        id, <#list fieldList as field>${field.columnName}, </#list>create_time, update_time
    </sql>
</mapper>
