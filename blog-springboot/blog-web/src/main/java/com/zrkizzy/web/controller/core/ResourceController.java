package com.zrkizzy.web.controller.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Resource;
import com.zrkizzy.data.dto.ResourceDTO;
import com.zrkizzy.data.query.ResourceQuery;
import com.zrkizzy.data.vo.ResourceVO;
import com.zrkizzy.server.service.core.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/27
 */
@Api(tags = "资源控制器")
@RestController
@RequestMapping("/admin/resource")
public class ResourceController {

     @Autowired
     private IResourceService resourceService;

    @ApiOperation("获取所有资源")
    @PostMapping("/list")
    public Result<PageResult<ResourceVO>> listResources(@RequestBody ResourceQuery resourceQuery) {
        // 查询到对应的资源集合
        Page<Resource> resourcePage = resourceService.listResources(resourceQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<ResourceVO>builder().total(resourcePage.getTotal())
            // 资源集合
            .list(BeanCopyUtil.copyList(resourcePage.getRecords(), ResourceVO.class)).build());
    }
            
    @ApiOperation("添加-更新资源") 
    @PostMapping("/save")
    public Result<Boolean> saveResource(@RequestBody ResourceDTO resourceDTO) {
        // 保存资源数据
        return Result.success(resourceService.saveResource(resourceDTO));
    }

    @ApiOperation("获取指定资源信息")
    @GetMapping("/getResourceById/{resourceId}")
    public Result<ResourceVO> getResourceById (@PathVariable Long resourceId) {
        return Result.success(BeanCopyUtil.copy(resourceService.getResourceById(resourceId), ResourceVO.class));
    }

    @ApiOperation("批量删除资源数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (resourceService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "资源数据删除失败");
    }

}
