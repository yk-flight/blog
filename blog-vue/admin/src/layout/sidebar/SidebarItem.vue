<template>
  <div v-if="!item.hidden">
    <template v-if="!item.children">
      <app-link :to="resolvePath(item.path)">
        <el-menu-item :index="item.path">
          <menu-item
            :icon="item.meta.icon"
            :title="item.meta.title"
          ></menu-item>
        </el-menu-item>
      </app-link>
    </template>
    <!-- 含有子菜单时展示菜下拉菜单 -->
    <el-submenu v-else :index="item.path">
      <template slot="title">
        <menu-item :icon="item.meta.icon" :title="item.meta.title"></menu-item>
      </template>
      <sidebar-item
        v-for="child in item.children"
        :key="child.path"
        :item="child"
        :base-path="resolvePath(item.path)"
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
    }
  }
}
</script>

<style lang="scss" scoped></style>
