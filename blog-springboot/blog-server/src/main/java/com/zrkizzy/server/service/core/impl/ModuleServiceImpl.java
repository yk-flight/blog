package com.zrkizzy.server.service.core.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zrkizzy.common.base.response.OptionsVO;
import com.zrkizzy.common.utils.SnowFlakeUtil;
import com.zrkizzy.common.utils.bean.BeanCopyUtil;
import com.zrkizzy.data.domain.Module;
import com.zrkizzy.data.dto.ModuleDTO;
import com.zrkizzy.data.mapper.ModuleMapper;
import com.zrkizzy.data.query.ModuleQuery;
import com.zrkizzy.server.service.core.IModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 请求模块业务逻辑接口实现类
 *
 * @author zhangrongkang
 * @since 2023/7/3
 */
@Service
@Slf4j
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * 获取模块选项集合
     *
     * @return 模块选项集合
     */
    @Override
    public List<OptionsVO> listModuleOptions() {
        return moduleMapper.listModuleOptions();
    }

    /**
     * 分页获取资源模块
     *
     * @param moduleQuery 资源模块查询对象
     * @return 资源模块分页数据
     */
    @Override
    public Page<Module> listModules(ModuleQuery moduleQuery) {
        // 开启分页
        Page<Module> page = new Page<>(moduleQuery.getCurrentPage(), moduleQuery.getPageSize());
        // 定义查询条件
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        // 模块名称
        String moduleName = moduleQuery.getName();
        if (StringUtils.hasLength(moduleName)) {
            // 拼接查询条件
            queryWrapper.like("name", moduleName);
        }
        // 获取时间范围
        List<String> dataRange = moduleQuery.getDataRange();
        // 如果时间范围不为空
        if (!CollectionUtils.isEmpty(dataRange)) {
            // 拼接时间范围查询条件
            queryWrapper.between("create_time", dataRange.get(0), dataRange.get(1));
        }
        return moduleMapper.selectPage(page, queryWrapper);
    }

    /**
     * 添加或更新资源模块
     *
     * @param moduleDTO 资源模块数据接收对象
     * @return 是否添加/更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveModule(ModuleDTO moduleDTO) {
        log.info("--------------------- 开始进行新增-更新操作 ---------------------");
        log.info("moduleDTO: {}", moduleDTO);
        // 根据是否包含ID来判断添加-更新操作
        if (null != moduleDTO.getId()) {
            // 更新资源模块
            return updateModule(moduleDTO);
        }
        // 添加资源模块
        return insertModule(moduleDTO);
    }

    /**
     * 获取指定资源模块信息
     *
     * @param moduleId 资源模块ID
     * @return 资源模块数据返回对象
     */
    @Override
    public Module getModuleById(Long moduleId) {
        return moduleMapper.selectById(moduleId);
    }

    /**
     * 批量删除资源模块数据
     *
     * @param ids 资源模块ID
     * @return true：删除成功，false：删除失败
     */
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        return moduleMapper.deleteBatchIds(ids) == ids.size();
    }

    /**
     * 更新当前资源模块
     *
     * @param moduleDTO 资源模块数据接收对象
     * @return 是否更新成功
     */
    private Boolean updateModule(ModuleDTO moduleDTO) {
        log.info("--------------------- 执行更新操作 ---------------------");
        // 对资源模块进行更新操作并返回响应结果
        return moduleMapper.updateById(BeanCopyUtil.copy(moduleDTO, Module.class)) == 1;
    }

    /**
     * 添加新的资源模块
     *
     * @param moduleDTO 资源模块数据接收对象
     * @return 是否添加成功
     */
    private Boolean insertModule(ModuleDTO moduleDTO) {
        log.info("--------------------- 开始进行新增操作 ---------------------");
        // 生成资源模块ID
        Long id = snowFlakeUtil.nextId();
        // 设置ID
        moduleDTO.setId(id);
        // 更新时间
        moduleDTO.setUpdateTime(LocalDateTime.now());
        // 添加资源模块数据并返回添加结果
        return moduleMapper.insert(BeanCopyUtil.copy(moduleDTO, Module.class)) == 1;
    }

}
