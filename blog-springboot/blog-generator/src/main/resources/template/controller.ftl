package com.zrkizzy.web.controller.system;

<#if isService>
import com.zrkizzy.data.domain.${entityName};
import com.zrkizzy.data.dto.${entityName}DTO;
import com.zrkizzy.data.query.${entityName}Query;
import com.zrkizzy.data.vo.${entityName}VO;
import com.zrkizzy.server.service.system.I${entityName}Service;
</#if>
import io.swagger.annotations.Api;
<#if isService>
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.*;

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
</#if>


}
