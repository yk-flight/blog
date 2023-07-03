package com.zrkizzy.web.controller.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.PageResult;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.dto.OperateLogDTO;
import com.zrkizzy.data.query.OperateLogQuery;
import com.zrkizzy.data.vo.OperateLogVO;
import com.zrkizzy.server.service.monitor.IOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Api(tags = "操作日志控制器")
@RestController
@RequestMapping("/admin/operate-log")
public class OperateLogController {

     @Autowired
     private IOperateLogService operateLogService;

    @ApiOperation("获取所有操作日志")
    @PostMapping("/list")
    public Result<PageResult<OperateLogVO>> listOperateLogs(@RequestBody OperateLogQuery operateLogQuery) {
        // 查询到对应的操作日志集合
        Page<OperateLog> operateLogPage = operateLogService.listOperateLogs(operateLogQuery);
        // 处理对应数据集合并返回数据
        return Result.success(PageResult.<OperateLogVO>builder().total(operateLogPage.getTotal())
            // 操作日志集合
            .list(BeanCopyUtil.copyList(operateLogPage.getRecords(), OperateLogVO.class)).build());
    }
            
    @ApiOperation("添加-更新操作日志") 
    @PostMapping("/save") 
    public Result<?> saveOperateLog(@RequestBody OperateLogDTO operateLogDTO) {
        // 保存操作日志数据
        return operateLogService.saveOperateLog(operateLogDTO); 
    }

    @ApiOperation("获取指定操作日志信息")
    @GetMapping("/getOperateLogById/{operateLogId}")
    public Result<OperateLogVO> getOperateLogById (@PathVariable Long operateLogId) {
        return Result.success(BeanCopyUtil.copy(operateLogService.getOperateLogById(operateLogId), OperateLogVO.class));
    }

    @ApiOperation("批量删除操作日志数据")
    @DeleteMapping("/delete")
    public Result<?> delete(@RequestBody List<Long> ids) {
        if (operateLogService.deleteBatch(ids)) {
            return Result.success();
        }
        return Result.failure(HttpStatusEnum.INTERNAL_SERVER_ERROR, "操作日志数据删除失败");
    }

}
