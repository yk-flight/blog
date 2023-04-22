<template>
  <div>
    <logo :collapse="this.$store.getters.isCollapse"></logo>
    <el-scrollbar>
      <el-menu
        :collapse="this.$store.getters.isCollapse"
        :background-color="variable.menuBackground"
        :text-color="variable.menuText"
        :active-text-color="variable.menuActiveText"
        mode="vertical"
      >
        <sidebar-item
          v-for="(route, index) in sidebarRouters"
          :item="route"
          :key="route.path + index"
          :base-path="route.path"
        ></sidebar-item>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import Logo from './Logo.vue'
import variable from '../../style/scss/variable.scss'
import SidebarItem from './SidebarItem'
import { mapGetters } from 'vuex'

export default {
  name: 'Sidebar',
  components: {
    Logo,
    SidebarItem
  },
  data () {
    return {
    }
  },
  computed: {
    ...mapGetters(['sidebarRouters']),
    // 监听scss文件中的颜色变量
    variable () {
      return variable
    }
  },
  mounted () {},

  methods: {}
}
</script>

<style lang="scss" scoped>
.el-scrollbar {
  height: calc(100vh - 50px);
}
</style>
