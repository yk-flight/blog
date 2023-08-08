<template>
  <!-- 网页端导航栏 -->
  <v-app-bar fixed short elevate-on-scroll hide-on-scroll elevation="0" :color="navColor">
    <div class="d-flex align-center">
      <v-img class="shrink mr-2" contain :src="logo" transition="scale-transition" width="30"/>
    </div>

    <v-spacer></v-spacer>

    <div>
      <ul>
        <li>
          <router-link to="/" class="navbar-item">
            <v-icon>fas fa-home</v-icon>
            <span>首页</span>
          </router-link>
        </li>
        <li>
          <router-link to="/home" class="navbar-item">
            <v-icon>fas fa-archive</v-icon>
            <span>归档</span>
          </router-link>
        </li>
        <li>
          <router-link to="/" class="navbar-item">
            <v-icon>fas fa-th-large</v-icon>
            <span>分类</span>
          </router-link>
        </li>
        <li>
          <router-link to="/" class="navbar-item">
            <v-icon>fas fa-tags</v-icon>
            <span>标签</span>
          </router-link>
        </li>
        <li>
          <router-link to="/about" class="navbar-item">
            <v-icon>fas fa-user</v-icon>
            <span>关于</span>
          </router-link>
        </li>
        <li>
          <router-link to="/" class="navbar-item">
            <v-icon>fas fa-link</v-icon>
            <span>友链</span>
          </router-link>
        </li>
        <li>
          <router-link to="/" class="navbar-item">
            <v-icon>fas fa-comments</v-icon>
            <span>留言</span>
          </router-link>
        </li>
      </ul>
    </div>

    <v-spacer></v-spacer>

    <v-btn
      href="https://github.com/vuetifyjs/vuetify/releases/latest"
      target="_blank"
      text
    >
      <span class="mr-2">Latest Release</span>
      <v-icon>mdi-open-in-new</v-icon>
    </v-btn>

  </v-app-bar>
</template>

<script>
export default {
  name: 'NavBar',

  data () {
    return {
      // 顶部导航栏背景色 transparent
      navColor: 'transparent',
      // 网站Logo
      logo: 'https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png'
    }
  },

  mounted () {
    window.addEventListener('scroll', this.handleScroll)
  },

  beforeDestroy () {
    window.removeEventListener('scroll', this.handleScroll)
  },

  methods: {
    // 监听滚动
    handleScroll () {
      // 屏幕高度
      const screenHeight = window.innerHeight || document.documentElement.clientHeight
      // 当前滚动位置
      const currentScroll = window.scrollY * 1.5

      if (currentScroll >= screenHeight) {
        this.navColor = 'white'
      } else {
        this.navColor = 'transparent'
        // this.navColor = 'transparent'
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar-body {
  display: flex;
  justify-content: right;
  height: 100%;
  flex-basis: 100%;
  flex-grow: 1;
  align-items: center;
}
.navbar-item {
  display: flex;
  align-items: center;
  font-size: 16px;
  margin: 0 3px;
  color: #fff;
  cursor: pointer;

  .v-icon {
    margin-right: 5px;
    font-size: 14px;
    color: #fff;
  }
}
.navbar-item:hover {
  transition: 0.8s;
  color: rgba(66, 185, 133, 0.8);
  // color: rgba(66, 185, 133, 0.8) !important;

  .v-icon {
    transition: 0.8s;
    color: #30a9de;
  }
}

.navbar-item:hover:after {
  width: 100%;
}
.navbar-item a:after {
  position: absolute;
  bottom: -5px;
  left: 0;
  z-index: -1;
  width: 0;
  height: 3px;
  // background-color: rgba(66, 185, 133, 0.8);
  background-color: #409eff;
  content: "";
  transition: all 0.3s ease-in-out;
}

ul {
  display: flex;
  list-style: none;

  li {
    margin-right: 1rem;
  }

  a {
    text-decoration: none;
    color: #fff;
  }
}
</style>
