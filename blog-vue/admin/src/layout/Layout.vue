<template>
  <div>
    <side-bar></side-bar>
    <div>
      <div>
        <nav-bar></nav-bar>
        <tags-view></tags-view>
      </div>
      <app-main></app-main>
    </div>
  </div>
</template>

<script>
import SideBar from './sidebar/index'
import NavBar from './NavBar'
import TagsView from './tagsview/index'
import AppMain from './AppMain.vue'

export default {
  name: 'Layout',
  components: {
    SideBar,
    NavBar,
    TagsView,
    AppMain
  },
  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>

</style>
