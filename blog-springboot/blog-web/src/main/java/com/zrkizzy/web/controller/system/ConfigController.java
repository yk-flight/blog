package com.zrkizzy.web.controller.system;

import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.data.dto.ConfigDTO;
import com.zrkizzy.data.vo.ConfigVO;
import com.zrkizzy.server.service.system.IConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/21
 */
@Api(tags = "系统配置控制器")
@RestController
@RequestMapping("/admin/config")
public class ConfigController {

     @Autowired
     private IConfigService configService;

    @ApiOperation("获取所有系统配置")
    @GetMapping("/getConfig")
    public Result<ConfigVO> getConfig() {
        // 处理对应数据集合并返回数据
        return Result.success(configService.getConfig());
    }
            
    @ApiOperation("添加-更新系统配置") 
    @PostMapping("/save") 
    public Result<?> saveConfig(@RequestBody ConfigDTO configDTO) {
        // 保存系统配置数据
        return configService.saveConfig(configDTO);
    }

    @ApiOperation("批量删除系统配置数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (configService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "系统配置数据删除失败");
    }

}
