package com.zrkizzy.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.query.LoginInfoQuery;
import com.zrkizzy.data.vo.LoginInfoVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户登录信息数据持久化接口
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/7/4
 */
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

    /**
     * 分页查询用户登录日志数据
     *
     * @param page 分页对象
     * @param loginInfoQuery 登录日志查询对象
     * @return 登录日志分页对象
     */
    Page<LoginInfoVO> listLoginInfo(@Param("page") Page<LoginInfo> page, @Param("loginInfoQuery") LoginInfoQuery loginInfoQuery);
}
