<template>
  <div class="logo-container" :class="{'collapse':collapse}">
    <transition name="sidebarLogoFade">
      <router-link to="/home" key="collapse" class="logo-link" v-if="collapse">
        <img :src="logo" alt="" class="logo-image"/>
        <h1 class="logo-title">{{ title }}</h1>
      </router-link>
      <router-link to="/home" key="expand" class="logo-link" v-else>
        <img :src="logo" alt="" class="logo-image"/>
        <h1 class="logo-title">{{ title }}</h1>
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

.logo-container {
  position: relative;
  text-align: center;
  width: 100%;
  height: 50px;

  &.collapse {
    .logo-image {
      margin-right: 0px;
    }
  }
}
.logo-link {
  height: 100%;
  width: 100%;
  text-decoration: none;
}
.logo-image {
  width: 32px;
  height: 32px;
  vertical-align: middle;
  margin-right: 12px;
}
.logo-title {
  display: inline-block;
  margin: 0;
  color: #fff;
  font-weight: 600;
  line-height: 50px;
  font-size: 14px;
  font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
  vertical-align: middle;
}

</style>
