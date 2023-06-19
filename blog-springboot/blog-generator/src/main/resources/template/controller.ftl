package ${controllerPackage};

<#if isService>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.${entityName};
import com.zrkizzy.data.dto.${entityName}DTO;
import com.zrkizzy.data.query.${entityName}Query;
import com.zrkizzy.data.vo.${entityName}VO;
import ${servicePackage}.I${entityName}Service;
</#if>
import io.swagger.annotations.Api;
<#if isService>
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.*;

<#if isService>
import java.util.List;
</#if>

/**
 * ${objectName}数据返回对象
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${objectName}控制器")
@RestController
@RequestMapping("/admin/${mapping}")
public class ${entityName}Controller {

<#if isService>
     @Autowired
     private I${entityName}Service ${entityLowerName}Service;

    @ApiOperation("获取所有${objectName}")
    @PostMapping("/list")
    <#assign P = "PageResult">
    <#assign V = "VO">
    public Result<${P}<${entityName}${V}>> list${entityName}s(@RequestBody ${entityName}Query ${entityLowerName}Query) {
        // 查询到对应的${objectName}集合
        Page<${entityName}> ${entityLowerName}Page = ${entityLowerName}Service.list${entityName}s(${entityLowerName}Query);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<${entityName}${V}>builder().total(${entityLowerName}Page.getTotal())
            // ${objectName}集合
            .list(BeanCopyUtil.copyList(${entityLowerName}Page.getRecords(), ${entityName}VO.class)).build());
    }
            
    @ApiOperation("添加-更新${objectName}") 
    @PostMapping("/save") 
    public Result<?> save${entityName}(@RequestBody ${entityName}DTO ${entityLowerName}DTO) {
        // 保存${objectName}数据
        return ${entityLowerName}Service.save${entityName}(${entityLowerName}DTO); 
    }

    @ApiOperation("获取指定${objectName}信息")
    @GetMapping("/get${entityName}ById/{${entityLowerName}Id}")
    public Result<${entityName}${V}> get${entityName}ById (@PathVariable Long ${entityLowerName}Id) {
        return Result.success(BeanCopyUtil.copy(${entityLowerName}Service.get${entityName}ById(${entityLowerName}Id), ${entityName}VO.class));
    }

    @ApiOperation("批量删除${objectName}数据")
    @DeleteMapping("/delete")
    <#assign L = "Long">
    public Result<?> delete(@RequestBody List<${L}> ids) {
        if (${entityLowerName}Service.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "${objectName}数据删除失败");
    }
</#if>

}
