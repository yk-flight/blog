package com.zrkizzy.data.vo.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 模块角色关联数据返回对象
 *
 * @author zhangrongkang
 * @since 2023/7/31
 */
@Data
@AllArgsConstructor
public class ModuleRoleVO implements Serializable {

    /**
     * 当前模块选中的ID
     */
    private List<String> checkIds;

    /**
     * 模块树形数据
     */
    private List<ModuleTreeVO> moduleTree;
}
