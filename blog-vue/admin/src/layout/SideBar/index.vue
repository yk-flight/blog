<template>
  <div :class="{'has-logo':showLogo}" :style="{ backgroundColor: settings.sideTheme === 'theme-dark' ? variable.menuBackground : variable.menuLightBackground }">
    <logo v-if="showLogo" :collapse="isCollapse"></logo>
    <el-scrollbar :class="settings.sideTheme">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="settings.sideTheme === 'theme-dark' ? variable.menuBackground : variable.menuLightBackground"
        :text-color="settings.sideTheme === 'theme-dark' ? variable.menuText : variable.menuLightText"
        :active-text-color="settings.theme"
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
import SidebarItem from './SidebarItem.vue'
import { mapGetters, mapState } from 'vuex'

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
    ...mapState(['settings']),
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
    // scss样式
    variable () {
      return variable
    },
    // 菜单栏是否折叠
    isCollapse () {
      return this.$store.getters.isCollapse
    },
    // 是否展示logo
    showLogo () {
      return this.$store.getters.sidebarLogo
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
