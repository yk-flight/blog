package com.zrkizzy.server.service.monitor.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.dto.OperateLogDTO;
import com.zrkizzy.data.mapper.OperateLogMapper;
import com.zrkizzy.data.query.OperateLogQuery;
import com.zrkizzy.data.vo.OperateLogVO;
import com.zrkizzy.server.service.monitor.IOperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private SnowFlakeUtil snowFlakeUtil;

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
        queryWrapper.eq("module_id", operateLogQuery.getModuleId());
        // 操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询
        queryWrapper.eq("type", operateLogQuery.getType());
        // 请求方式
        if (StringUtils.hasLength(operateLogQuery.getRequestMethod())) {
            queryWrapper.eq("request_method", operateLogQuery.getRequestMethod());
        }
        // 操作状态 0 失败 1 成功 
        queryWrapper.eq("status", operateLogQuery.getStatus());
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
     * 添加或更新操作日志
     *
     * @param operateLogDTO 操作日志数据接收对象
     * @return 前端响应对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveOperateLog(OperateLogDTO operateLogDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("operateLogDTO: {}", operateLogDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != operateLogDTO.getId()) {
            // 更新操作日志
            return updateOperateLog(operateLogDTO);
        }
        // 添加操作日志
        return insertOperateLog(operateLogDTO);
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

    /**
     * 更新当前操作日志
     *
     * @param operateLogDTO 操作日志数据接收对象
     * @return 前端响应对象
     */
    private Result<?> updateOperateLog(OperateLogDTO operateLogDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对操作日志进行更新操作并返回响应结果
        return operateLogMapper.updateById(BeanCopyUtil.copy(operateLogDTO, OperateLog.class)) == 1 ?
        Result.success() : Result.failure();
    }
    
    /**
     * 添加新的操作日志
     *
     * @param operateLogDTO 操作日志数据接收对象
     * @return 前端响应对象
     */
    private Result<OperateLogVO> insertOperateLog(OperateLogDTO operateLogDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成操作日志ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        operateLogDTO.setId(id);
        // 添加操作日志数据并返回添加结果
        return operateLogMapper.insert(BeanCopyUtil.copy(operateLogDTO, OperateLog.class)) == 1 ?
            Result.success() : Result.failure();
    }

}
