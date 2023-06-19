package com.zrkizzy.server.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.enums.HttpStatusEnum;
import com.zrkizzy.common.utils.BeanCopyUtil;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.data.mapper.LinkMapper;
import com.zrkizzy.data.domain.Link;
import com.zrkizzy.data.dto.LinkDTO;
import com.zrkizzy.data.query.LinkQuery;
import com.zrkizzy.data.vo.LinkVO;
import com.zrkizzy.server.service.system.ILinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 友情链接业务逻辑接口实现类
 * </p>
 *
 * @author zhangrongkang
 * @since 2023/6/19
 */
@Service
@Slf4j
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private LinkMapper linkMapper;

    /**
     * 获取所有友情链接
     *
     * @param linkQuery 友情链接查询对象
     * @return 友情链接分页数据
     */
    @Override
    public Page<Link> listLinks(LinkQuery linkQuery) {
        // 开启分页
        Page<Link> page = new Page<>(linkQuery.getCurrentPage(), linkQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        // 网站名称
        if (StringUtils.hasLength(linkQuery.getName())) {
            queryWrapper.eq("name", linkQuery.getName());
        }
        // 获取时间范围
        List<String> dataRange = linkQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        // 查询分页
        return linkMapper.selectPage(page, queryWrapper);
    }

    /**
     * 添加或更新友情链接
     *
     * @param linkDTO 友情链接数据接收对象
     * @return 前端响应对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveLink(LinkDTO linkDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("linkDTO: {}", linkDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != linkDTO.getId()) {
            // 更新友情链接
            return updateLink(linkDTO);
        }
        // 添加友情链接
        return insertLink(linkDTO);
    }

    /**
     * 获取指定友情链接信息
     *
     * @param linkId 友情链接ID
     * @return 友情链接数据返回对象
     */
    @Override
    public Link getLinkById(Long linkId) {
        return linkMapper.selectById(linkId);
    }
    
    /**
     * 批量删除友情链接数据
     *
     * @param ids 友情链接ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return linkMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前友情链接
     *
     * @param linkDTO 友情链接数据接收对象
     * @return 前端响应对象
     */
    private Result<?> updateLink(LinkDTO linkDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对友情链接进行更新操作并返回响应结果
        return linkMapper.updateById(BeanCopyUtil.copy(linkDTO, Link.class)) == 1 ?
        Result.success(HttpStatusEnum.SUCCESS, "友情链接更新成功") : Result.failure();
    }
    
    /**
     * 添加新的友情链接
     *
     * @param linkDTO 友情链接数据接收对象
     * @return 前端响应对象
     */
    private Result<LinkVO> insertLink(LinkDTO linkDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成友情链接ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        linkDTO.setId(id);
        // 添加友情链接数据并返回添加结果
        return linkMapper.insert(BeanCopyUtil.copy(linkDTO, Link.class)) == 1 ?
            Result.success(HttpStatusEnum.SUCCESS, "友情链接添加成功") :
            Result.failure();
    }

}
