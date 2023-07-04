package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.mapper.OperateLogMapper;
import com.zrkizzy.data.query.OperateLogQuery;
import com.zrkizzy.data.vo.OperateLogVO;
import com.zrkizzy.server.service.monitor.IOperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Service
@Slf4j
public class OperateLogServiceImpl implements IOperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 获取所有操作日志
     *
     * @param operateLogQuery 操作日志查询对象
     * @return 操作日志分页数据
     */
    @Override
    public Page<OperateLogVO> listOperateLogs(OperateLogQuery operateLogQuery) {
        // 开启分页
        Page<OperateLog> page = new Page<>(operateLogQuery.getCurrentPage(), operateLogQuery.getPageSize());
        // 查询分页
        return operateLogMapper.listOperateLog(page, operateLogQuery);
    }
    
    /**
     * 批量删除操作日志数据
     *
     * @param ids 操作日志ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return operateLogMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 清空操作日志
     */
    @Override
    public void clearOperateLogs() {
        // 清空当前表格数据
        operateLogMapper.truncate();
    }

}
