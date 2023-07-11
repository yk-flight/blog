package com.zrkizzy.server.service.monitor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.query.monitor.OperateLogQuery;
import com.zrkizzy.data.vo.OperateLogVO;

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
    Page<OperateLogVO> listOperateLogs(OperateLogQuery operateLogQuery);

    /**
     * 批量删除操作日志数据
     *
     * @param ids 操作日志ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

    /**
     * 清空操作日志
     */
    void clearOperateLogs();
}