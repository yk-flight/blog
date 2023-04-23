<template>
  <div>
    <logo :collapse="isCollapse"></logo>
    <el-scrollbar>
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variable.menuBackground"
        :text-color="variable.menuText"
        :active-text-color="variable.menuActiveText"
        :collapse-transition="false"
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
    activeMenu () {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    variable () {
      return variable
    },
    isCollapse () {
      return this.$store.getters.isCollapse
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
