<template>
  <div @click="setCollapse">
    <svg-icon
      :icon="this.$store.getters.isCollapse ? 'hamburger-closed' : 'hamburger-opened'"
      class="hamburger">
    </svg-icon>
  </div>
</template>

<script>
export default {
  name: 'Hamburger',

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {
    // 获取vuex中设置菜单是否折叠的方法
    setCollapse () {
      this.$store.commit('app/SET_COLLAPSE')
    }
  }
}
</script>

<style lang="scss" scoped>
.hamburger {
  display: inline-block;
  vertical-align: middle;
  width: 18px;
  height: 18px;
}
</style>
