package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.mapper.OperateLogMapper;
import com.zrkizzy.data.query.OperateLogQuery;
import com.zrkizzy.server.service.monitor.IOperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    public Page<OperateLog> listOperateLogs(OperateLogQuery operateLogQuery) {
        // 开启分页
        Page<OperateLog> page = new Page<>(operateLogQuery.getCurrentPage(), operateLogQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<OperateLog> queryWrapper = new QueryWrapper<>();
        // 模块ID
        if (null != operateLogQuery.getModuleId()) {
            queryWrapper.eq("module_id", operateLogQuery.getModuleId());
        }
        // 操作类型
        if (null != operateLogQuery.getType()) {
            // 操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询
            queryWrapper.eq("type", operateLogQuery.getType());
        }
        // 请求方式
        if (StringUtils.hasLength(operateLogQuery.getRequestMethod())) {
            queryWrapper.eq("request_method", operateLogQuery.getRequestMethod());
        }
        // 操作状态
        if (null != operateLogQuery.getStatus()) {
            queryWrapper.eq("status", operateLogQuery.getStatus());
        }
        // 获取时间范围
        List<String> dataRange = operateLogQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return operateLogMapper.selectPage(page, queryWrapper);
    }

    /**
     * 获取指定操作日志信息
     *
     * @param operateLogId 操作日志ID
     * @return 操作日志数据返回对象
     */
    @Override
    public OperateLog getOperateLogById(Long operateLogId) {
        return operateLogMapper.selectById(operateLogId);
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

}
