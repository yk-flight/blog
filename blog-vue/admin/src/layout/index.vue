<template>
  <div
    class="app-wrapper"
    :class="this.$store.getters.isCollapse ? 'hideSidebar' : 'openSidebar'">
    <side-bar class="sidebar-container"></side-bar>
    <div class="main-container">
      <div>
        <nav-bar></nav-bar>
        <tags-view></tags-view>
      </div>
      <app-main></app-main>
      <right-panel>
        <setting></setting>
      </right-panel>
    </div>
  </div>
</template>

<script>
import Setting from './components/Setting.vue'
import RightPanel from '../components/RightPanel'
import SideBar from './SideBar/index'
import NavBar from './NavBar'
import TagsView from './components/TagsView/index'
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
    sidebarClass () {
      return {
        hideSidebar: this.$store.getters.isCollapse
      }
    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.app-wrapper {
  height: 100%;
  width: 100%;
}

</style>
