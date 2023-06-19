package ${serviceImplPackage};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.constant.SecurityConst;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.mapper.${entityName}Mapper;
import com.zrkizzy.data.domain.${entityName};
import com.zrkizzy.data.dto.${entityName}DTO;
import com.zrkizzy.data.query.${entityName}Query;
import com.zrkizzy.data.vo.${entityName}VO;
import ${servicePackage}.I${entityName}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * ${objectName}业务逻辑接口实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Slf4j
public class ${entityName}ServiceImpl implements I${entityName}Service {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private ${entityName}Mapper ${entityLowerName}Mapper;

    /**
     * 获取所有${objectName}
     *
     * @param ${entityLowerName}Query ${objectName}查询对象
     * @return ${objectName}分页数据
     */
    @Override
    public Page<${entityName}> list${entityName}s(${entityName}Query ${entityLowerName}Query) {
        // 开启分页
        Page<${entityName}> page = new Page<>(${entityLowerName}Query.getCurrentPage(), ${entityLowerName}Query.getPageSize());
        // 定义查询条件
        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();
        <#list fieldList as field>
            <#if !field.isNull>
        // ${field.comment}
            <#if field.javaType == "String">
        if (StringUtils.hasLength(${entityLowerName}Query.get${field.javaUpName}())) {
            queryWrapper.eq("${field.columnName}", ${entityLowerName}Query.get${field.javaUpName}());
        }
                </#if>
            </#if>
        </#list>
        // 获取时间范围
        <#assign T = "String">
        List<${T}> dataRange = ${entityLowerName}Query.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return ${entityLowerName}Mapper.selectPage(page, queryWrapper);
    }

    /**
     * 添加或更新${objectName}
     *
     * @param ${entityLowerName}DTO ${objectName}数据接收对象
     * @return 前端响应对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> save${entityName}(${entityName}DTO ${entityLowerName}DTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("${entityLowerName}DTO: {}", ${entityLowerName}DTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != ${entityLowerName}DTO.getId()) {
            // 更新${objectName}
            return update${entityName}(${entityLowerName}DTO);
        }
        // 添加${objectName}
        return insert${entityName}(${entityLowerName}DTO);
    }

    /**
     * 获取指定${objectName}信息
     *
     * @param ${entityLowerName}Id ${objectName}ID
     * @return ${objectName}数据返回对象
     */
    @Override
    public ${entityName} get${entityName}ById(Long ${entityLowerName}Id) {
        return ${entityLowerName}Mapper.selectById(${entityLowerName}Id);
    }
    
    /**
     * 批量删除${objectName}数据
     *
     * @param ids ${objectName}ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    <#assign L = "Long">
    public Boolean deleteBatch(List<${L}> ids) {
        return ${entityLowerName}Mapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前${objectName}
     *
     * @param ${entityLowerName}DTO ${objectName}数据接收对象
     * @return 前端响应对象
     */
    private Result<?> update${entityName}(${entityName}DTO ${entityLowerName}DTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对${objectName}进行更新操作并返回响应结果
        return ${entityLowerName}Mapper.updateById(BeanCopyUtil.copy(${entityLowerName}DTO, ${entityName}.class)) == 1 ?
        Result.success(HttpStatusEnum.SUCCESS, "${objectName}更新成功") : Result.failure();
    }
    
    /**
     * 添加新的${objectName}
     *
     * @param ${entityLowerName}DTO ${objectName}数据接收对象
     * @return 前端响应对象
     */
    <#assign V = "VO">
    private Result<${entityName}${V}> insert${entityName}(${entityName}DTO ${entityLowerName}DTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成${objectName}ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        ${entityLowerName}DTO.setId(id);
        // 添加${objectName}数据并返回添加结果
        return ${entityLowerName}Mapper.insert(BeanCopyUtil.copy(${entityLowerName}DTO, ${entityName}.class)) == 1 ?
            Result.success(HttpStatusEnum.SUCCESS, "${objectName}添加成功") :
            Result.failure();
    }

}
