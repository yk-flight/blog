<template>
  <div class="sidebar-logo-container" :class="{'collapse': collapse}">
    <transition name="sidebarLogoFade">
      <router-link to="/home" key="collapse" class="sidebar-logo-link" v-if="collapse">
        <img :src="logo" alt="" class="sidebar-logo"/>
      </router-link>
      <router-link to="/home" key="expand" class="sidebar-logo-link" v-else>
        <img :src="logo" alt="" class="sidebar-logo"/>
        <h1 class="sidebar-title">{{ title }}</h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
import logoImage from '../../assets/images/logo.png'

export default {
  name: 'Logo',
  props: {
    // 是否折叠
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data () {
    return {
      // logo路径
      logo: logoImage,
      // 标题
      title: '后台管理系统'
    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #304156;
  text-align: center;
  overflow: hidden;

  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      line-height: 50px;
      font-size: 14px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>
