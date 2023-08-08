<template>
  <div>
  <!-- 网页端导航栏 -->
  <v-app-bar short fixed elevate-on-scroll hide-on-scroll elevation="0" :color="navColor">
    <router-link to="/">
      <img class="blog-avatar" src="../assets/logo.png" transition="scale-transition" />
    </router-link>

    <v-spacer></v-spacer>

    <ul>
      <li :class="navbarClass">
        <router-link to="/" >
          <v-icon>fas fa-home</v-icon>
          <span>首页</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/home">
          <v-icon>fas fa-archive</v-icon>
          <span>归档</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/">
          <v-icon>fas fa-th-large</v-icon>
          <span>分类</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/">
          <v-icon>fas fa-tags</v-icon>
          <span>标签</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/about">
          <v-icon>fas fa-user</v-icon>
          <span class="nav-btn">关于</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/">
          <v-icon>fas fa-link</v-icon>
          <span>友链</span>
        </router-link>
      </li>
      <li :class="navbarClass">
        <router-link to="/">
          <v-icon>fas fa-comments</v-icon>
          <span>留言</span>
        </router-link>
      </li>
    </ul>

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
</div>
</template>

<script>
export default {
  name: 'NavBar',

  data () {
    return {
      // 顶部导航栏背景色 transparent
      navColor: 'transparent',
      // 样式
      navbarClass: 'navbar-item',
      // 网站Logo
      logo: '../'
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
      const currentScroll = window.scrollY * 3

      if (currentScroll >= screenHeight) {
        // 滚动菜单样式
        this.navbarClass = 'navbar-item-scroll'
        this.navColor = 'rgba(255,255,255)'
      } else {
        // 滚动菜单样式
        this.navbarClass = 'navbar-item'
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
  font-size: 16px;
  color: #fff;
  cursor: pointer;
  position: relative;
  display: inline-block;
  padding-bottom: 5px;
  a {
    color: #fff;
  }

  .v-icon {
    margin-right: 5px;
    font-size: 14px;
    color: #fff;
  }
}
.navbar-item::after {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 3px; /* 设置横线的高度 */
  border-radius: 2px;
  background-color: #fff; /* 设置横线的颜色 */
  transform: scaleX(0); /* 初始时横线不可见 */
  transition: transform 0.5s ease-in-out; /* 添加过渡效果 */
}

.navbar-item:hover::after {
  transform: scaleX(1); /* 悬停时横线出现 */
}
.navbar-item-scroll {
  font-size: 16px;
  position: relative;
  display: inline-block;
  padding-bottom: 5px;
  color: #333;
  cursor: pointer;

  a {
    color: #333;
  }

  .v-icon {
    margin-right: 5px;
    font-size: 14px;
    color: #333;
  }
}

.navbar-item-scroll::after {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 3px; /* 设置横线的高度 */
  border-radius: 2px;
  background-color: #333; /* 设置横线的颜色 */
  transform: scaleX(0); /* 初始时横线不可见 */
  transition: transform 0.5s ease-in-out; /* 添加过渡效果 */
}

.navbar-item-scroll:hover::after {
  transform: scaleX(1); /* 悬停时横线出现 */
}

ul {
  list-style: none;

  li {
    margin: 0 0.5rem;
  }

  a {
    display: flex;
    align-items: center;
    text-decoration: none;
  }
}
.blog-avatar {
  width: 60px;
  transition: all 0.5s;
  cursor: pointer;
}
.blog-avatar:hover {
  transform: rotate(360deg);
}
</style>
