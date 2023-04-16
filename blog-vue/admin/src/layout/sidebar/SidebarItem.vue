<template>
  <div v-if="!item.hidden">
    <!-- 含有子菜单时展示菜下拉菜单 -->
    <el-submenu v-if="item.children" :index="item.path">
      <template slot="title">
        <span>{{ item.meta.title }}</span>
      </template>
      <sidebar-item v-for="child in item.children" :key="child.path" :item="child"></sidebar-item>
    </el-submenu>
    <!-- 不含有子菜单时展示点击菜单 -->
    <el-menu-item v-else :index="item.path">
      <menu-item :icon="item.meta.icon" :title="item.meta.title"></menu-item>
    </el-menu-item>
  </div>
</template>

<script>
import MenuItem from './MenuItem.vue'

export default {
  name: 'SidebarItem',
  components: {
    MenuItem
  },

  props: {
    // 菜单子项
    item: {
      type: Object,
      // 必填项
      require: true
    }
  },
  data () {
    return {}
  },

  mounted () {},

  methods: {}
}
</script>

<style lang="scss" scoped></style>
