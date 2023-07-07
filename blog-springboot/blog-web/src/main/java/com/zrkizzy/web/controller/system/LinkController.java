package com.zrkizzy.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.annotation.OperateLogAnnotation;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Link;
import com.zrkizzy.data.dto.LinkDTO;
import com.zrkizzy.data.query.LinkQuery;
import com.zrkizzy.data.vo.LinkVO;
import com.zrkizzy.server.service.system.ILinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 友情链接数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/6/19
 */
@Api(tags = "友情链接控制器")
@RestController
@RequestMapping("/admin/link")
public class LinkController {

     @Autowired
     private ILinkService linkService;

    @OperateLogAnnotation
    @ApiOperation("获取所有友情链接")
    @PostMapping("/list")
    public Result<PageResult<LinkVO>> listLinks(@RequestBody LinkQuery linkQuery) {
        // 查询到对应的友情链接集合
        Page<Link> linkPage = linkService.listLinks(linkQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<LinkVO>builder().total(linkPage.getTotal())
            // 友情链接集合
            .list(BeanCopyUtil.copyList(linkPage.getRecords(), LinkVO.class)).build());
    }
            
    @ApiOperation("添加-更新友情链接") 
    @PostMapping("/save")
    public Result<?> saveLink(@RequestBody LinkDTO linkDTO) {
        // 保存友情链接数据
        return linkService.saveLink(linkDTO); 
    }

    @ApiOperation("获取指定友情链接信息")
    @GetMapping("/getLinkById/{linkId}")
    public Result<LinkVO> getLinkById (@PathVariable Long linkId) {
        return Result.success(BeanCopyUtil.copy(linkService.getLinkById(linkId), LinkVO.class));
    }

    @ApiOperation("批量删除友情链接数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (linkService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "友情链接数据删除失败");
    }

}
