package com.zrkizzy.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.data.domain.LoginInfo;
import com.zrkizzy.data.query.LoginInfoQuery;

import java.util.List;

/**
 * 用户登录信息业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/6/20
 */
public interface ILoginInfoService {

    /**
     * 获取所有用户登录信息
     *
     * @param loginInfoQuery 用户登录信息信息查询对象
     * @return 用户登录信息分页数据
     */
    Page<LoginInfo> listLoginInfos(LoginInfoQuery loginInfoQuery);

    /**
     * 批量删除用户登录信息数据
     *
     * @param ids 用户登录信息ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

}