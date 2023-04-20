<template>
  <div v-if="!item.hidden">
    <!-- 只含有一个子菜单或不含有子菜单时 -->
    <template v-if="hasOneShowChild(item.children, item)">
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)">
          <menu-item
            :icon="onlyOneChild.meta.icon"
            :title="onlyOneChild.meta.title"
          ></menu-item>
        </el-menu-item>
      </app-link>
    </template>

    <!-- 含有两个及以上子菜单时展示菜下拉菜单 -->
    <el-submenu v-else :index="resolvePath(item.path)">
      <template slot="title">
        <menu-item :icon="item.meta.icon" :title="item.meta.title"></menu-item>
      </template>
      <sidebar-item
        v-for="child in item.children"
        :key="child.path"
        :item="child"
        :base-path="resolvePath(child.path)"
      ></sidebar-item>
    </el-submenu>
  </div>
</template>

<script>
import MenuItem from './MenuItem.vue'
import AppLink from './AppLink.vue'
import path from 'path'
import { isExternal } from '../../utils/validate'

export default {
  name: 'SidebarItem',
  components: {
    MenuItem,
    AppLink
  },
  // 对外暴露组件
  props: {
    // 菜单子项
    item: {
      type: Object,
      // 必填项
      require: true
    },
    // 基本路径
    basePath: {
      type: String,
      // 默认值
      default: ''
    }
  },
  created () {
    console.log('******************* 创建新组件 *******************')
    console.log('组件参数菜单子项：', this.item)
    console.log('组件参数基本路径：', this.basePath)
    console.log('******************* 完成新组件 *******************')
  },
  data () {
    // 只有一个子菜单时
    this.onlyOneChild = null
    return {
    }
  },
  methods: {
    /**
     * 拼接路由
     */
    resolvePath (routePath) {
      console.log('================== resolvePath() ================ start')
      console.log('基础路由：', this.basePath)
      console.log('传来的onlyChildRoute子菜单路由：', routePath)
      console.log('传来路由是否为外链：', isExternal(routePath))
      console.log('基础路由是否为外链：', isExternal(this.basePath))
      // 如果链接是外部链接
      if (isExternal(routePath)) {
        return routePath
      }
      // 如果基本路径也是外部链接
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      console.log('合并路由：', path.resolve(this.basePath, routePath))
      console.log('================== resolvePath() ================ end')
      return path.resolve(this.basePath, routePath)
    },
    /**
     * 返回只有一个子菜单时是否展示
     */
    hasOneShowChild (children, parent) {
      console.log('------------------ hasOneShowChild() ------------------ start')
      console.log('当前传来进行判断的子路由为：', children)
      console.log('当前传来进行判断的父路由为：', parent)
      // 如果不存在子菜单
      if (!children) {
        // 直接将子菜单置空
        children = []
      }
      console.log('进入子路由的循环...')
      const showingChildren = children.filter(item => {
        // 子菜单隐藏
        if (item.hidden) {
          return false
        } else {
          this.onlyOneChild = item
          return true
        }
      })
      console.log('遍历结束后的onlyOneChild为：', this.onlyOneChild)
      console.log('showingChildren：', showingChildren)
      if (showingChildren.length === 1) {
        console.log('------------------ hasOneShowChild() ------------------ end')
        return true
      }

      if (showingChildren.length === 0) {
        this.onlyOneChild = {
          ...parent,
          path: ''
        }
        console.log('重新赋值后的onlyOneChild为：', this.onlyOneChild)
        console.log('当前是否可以设置路由：true')
        console.log('------------------ hasOneShowChild() ------------------ end')
        return true
      }
      console.log('当前是否可以设置路由：false')
      console.log('------------------ hasOneShowChild() ------------------ end')
      return false
    }
  }
}
</script>

<style lang="scss" scoped></style>
