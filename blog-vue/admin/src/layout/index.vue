<template>
  <div class="app-wrapper" :class="isCollapse ? 'hideSidebar' : 'openSidebar'">
    <side-bar class="sidebar-container"></side-bar>
    <div class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <nav-bar></nav-bar>
        <tags-view v-if="tagsView"></tags-view>
      </div>
      <app-main :class="fixedHeader ? 'app-container' : ''"></app-main>
      <right-panel>
        <setting></setting>
      </right-panel>
    </div>
  </div>
</template>

<script>
import Setting from './components/Setting.vue'
import RightPanel from '../components/RightPanel'
import SideBar from './sidebar/index'
import NavBar from './NavBar'
import TagsView from './components/tagsview/index'
import AppMain from './AppMain.vue'

export default {
  name: 'Layout',
  components: {
    SideBar,
    NavBar,
    TagsView,
    AppMain,
    RightPanel,
    Setting
  },
  data () {
    return {

    }
  },

  computed: {
    // 侧边栏样式
    sidebarClass () {
      return this.$store.getters.isCollapse
    },
    // 是否开启标签页
    tagsView () {
      return this.$store.getters.tagsView
    },
    // 是否固定顶部导航栏
    fixedHeader () {
      return this.$store.getters.fixedHeader
    },
    // 侧边栏是否折叠
    isCollapse () {
      return this.$store.getters.isCollapse
    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
@import "../style/scss/variable.scss";

.app-wrapper {
  height: 100%;
  width: 100%;
}

.fixed-header {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 9;
  width: calc(100% - #{$base-sidebar-width});
  transition: width 0.28s;
}

.hideSidebar .fixed-header {
 width: calc(100% - 54px);
}

.sidebarHide .fixed-header {
  width: 100%;
}

.app-container {
  position: relative;
  padding-top: 85px;
}
</style>
