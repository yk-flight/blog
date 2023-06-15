package com.zrkizzy.server.service.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.data.domain.Link;
import com.zrkizzy.data.dto.LinkDTO;
import com.zrkizzy.data.query.LinkQuery;

import java.util.List;

/**
 * 友情链接业务逻辑接口
 *
 * @author zhangrongkang
 * @since 2023/6/13
 */
public interface ILinkService {

    /**
     * 获取所有友情链接
     *
     * @param linkQuery 友情链接信息查询对象
     * @return 友情链接分页数据
     */
    Page<Link> listLinks(LinkQuery linkQuery);

    /**
     * 添加或更新友情链接
     *
     * @param linkDTO 友情链接数据接收对象
     * @return 前端响应对象
     */
    Result<?> saveLink(LinkDTO linkDTO);

    /**
     * 获取指定友情链接信息
     *
     * @param linkId 友情链接ID
     * @return 友情链接对象
     */
    Link getLinkById(Long linkId);

    /**
     * 批量删除友情链接数据
     *
     * @param ids 友情链接ID
     * @return true：删除成功，false：删除失败
     */
    Boolean deleteBatch(List<Long> ids);

}