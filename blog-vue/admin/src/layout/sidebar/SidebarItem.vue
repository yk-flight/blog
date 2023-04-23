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
      // 如果链接是外部链接
      if (isExternal(routePath)) {
        return routePath
      }
      // 如果基本路径也是外部链接
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      return path.resolve(this.basePath, routePath)
    },
    /**
     * 返回只有一个子菜单时是否展示
     */
    hasOneShowChild (children, parent) {
      // 如果不存在子菜单
      if (!children) {
        // 直接将子菜单置空
        children = []
      }
      const showingChildren = children.filter(item => {
        // 子菜单隐藏
        if (item.hidden) {
          return false
        } else {
          this.onlyOneChild = item
          return true
        }
      })
      if (showingChildren.length === 1) {
        return true
      }

      // 递归到底时，此时的parent就是要加载的每一个子菜单项
      if (showingChildren.length === 0) {
        this.onlyOneChild = {
          ...parent,
          path: ''
        }
        return true
      }
      return false
    }
  }
}
</script>

<style lang="scss" scoped></style>
