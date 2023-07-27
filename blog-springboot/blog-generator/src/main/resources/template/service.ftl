package ${servicePackage};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.${entityName};
import com.zrkizzy.data.dto.${entityName}DTO;
import com.zrkizzy.data.query.${entityName}Query;

import java.util.List;

/**
 * ${objectName}业务逻辑接口
 *
 * @author ${author}
 * @since ${date}
 */
public interface I${entityName}Service {

    /**
     * 获取所有${objectName}
     *
     * @param ${entityLowerName}Query ${objectName}信息查询对象
     * @return ${objectName}分页数据
     */
    Page<${entityName}> list${entityName}s(${entityName}Query ${entityLowerName}Query);

    /**
     * 添加或更新${objectName}
     *
     * @param ${entityLowerName}DTO ${objectName}数据接收对象
     * @return 是否添加/更新成功
     */
    <#assign B = "Boolean">
    Result<${B}> save${entityName}(${entityName}DTO ${entityLowerName}DTO);

    /**
     * 获取指定${objectName}信息
     *
     * @param ${entityLowerName}Id ${objectName}ID
     * @return ${objectName}对象
     */
    ${entityName} get${entityName}ById(Long ${entityLowerName}Id);

    /**
     * 批量删除${objectName}数据
     *
     * @param ids ${objectName}ID
     * @return true：删除成功，false：删除失败
     */
    <#assign T = "Long">
    Boolean deleteBatch(List<${T}> ids);

}