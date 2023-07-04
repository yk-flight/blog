package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.OperateLog;
import com.zrkizzy.data.query.OperateLogQuery;
import com.zrkizzy.data.vo.OperateLogVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 操作日志数据持久化接口
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
public interface OperateLogMapper extends BaseMapper<OperateLog> {

    /**
     * 分页查询操作日志集合
     *
     * @param page 分页对象
     * @param operateLogQuery 操作日志查询对象
     * @return 操作日志分页数据
     */
    Page<OperateLogVO> listOperateLog(@Param("page") Page<OperateLog> page,
                                      @Param("operateLogQuery") OperateLogQuery operateLogQuery);

    /**
     * 清空操作日志
     */
    void truncate();
}
