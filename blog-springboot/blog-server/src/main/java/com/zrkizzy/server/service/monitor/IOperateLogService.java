package com.zrkizzy.server.service.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.dto.OperateLogDTO;
import com.zrkizzy.data.query.OperateLogQuery;

import java.util.List;

/**
 * 操作日志业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
public interface IOperateLogService {

    /**
     * 获取所有操作日志
     *
     * @param operateLogQuery 操作日志信息查询对象
     * @return 操作日志分页数据
     */
    Page<OperateLog> listOperateLogs(OperateLogQuery operateLogQuery);

    /**
     * 添加或更新操作日志
     *
     * @param operateLogDTO 操作日志数据接收对象
     * @return 前端响应对象
     */
    Result<?> saveOperateLog(OperateLogDTO operateLogDTO);

    /**
     * 获取指定操作日志信息
     *
     * @param operateLogId 操作日志ID
     * @return 操作日志对象
     */
    OperateLog getOperateLogById(Long operateLogId);

    /**
     * 批量删除操作日志数据
     *
     * @param ids 操作日志ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

}