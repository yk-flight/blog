<template>
  <div>
    <!-- 网页端导航栏 -->
    <v-app-bar short fixed elevate-on-scroll elevation="0" :color="navColor" style="backdrop-filter: blur(4px);">
      <router-link to="/" :class="logoClass">
        <!-- <img class="blog-avatar" src="../assets/logo.png" transition="scale-transition" /> -->
        <span>雅康的个人博客</span>
      </router-link>

      <v-spacer></v-spacer>

      <ul>
        <li :class="navbarClass">
          <router-link to="/" >
            <svg-icon icon="home"></svg-icon>
            <span>首页</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/home">
            <svg-icon icon="form"></svg-icon>
            <span>归档</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/">
            <svg-icon icon="category"></svg-icon>
            <span>分类</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/">
            <svg-icon icon="tags"></svg-icon>
            <span>标签</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/about">
            <svg-icon icon="guide"></svg-icon>
            <span class="nav-btn">关于</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/">
            <svg-icon icon="form"></svg-icon>
            <span>友链</span>
          </router-link>
        </li>
        <li :class="navbarClass">
          <router-link to="/">
            <svg-icon icon="message"></svg-icon>
            <span>留言</span>
          </router-link>
        </li>
      </ul>

      <v-spacer></v-spacer>

      <div>
        <div :class="navbarClass" style="margin: 0 8px;">
          <svg-icon icon="search"></svg-icon>
        </div>
        <div :class="navbarClass" style="margin: 0 8px;">
          <svg-icon icon="moon"></svg-icon>
        </div>
      </div>

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
      // 菜单样式
      navbarClass: 'navbar-item',
      // 方法功能
      functionClass: 'function-container',
      // 网站标题CSS
      logoClass: 'logo'
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
      const currentScroll = window.scrollY + 20

      // 如果配置了展示Banner
      // if () {
      if (currentScroll >= screenHeight) {
        // 滚动菜单样式
        this.navbarClass = 'navbar-item-scroll'
        this.logoClass = 'logo-scroll'
        this.navColor = 'rgba(255,255,255, 0.65)'
      } else {
        // 滚动菜单样式
        this.navbarClass = 'navbar-item'
        this.logoClass = 'logo'
        this.navColor = 'transparent'
        // this.navColor = 'transparent'
      }
      // } else {
      // 没有配置展示Banner则固定顶部导航栏样式
      // this.navbarClass = 'navbar-item-scroll'
      // this.navColor = 'rgba(255,255,255, 0.65)'
      // }
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

  .svg-icon {
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
  .svg-icon {
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
.logo {
  display: flex;
  align-items: center;

  span {
    font-weight: 700;
    margin-left: 5px;
    font-size: 18px;
    color: #fff;
  }
}
.logo-scroll {
  display: flex;
  align-items: center;

  span {
    font-weight: 700;
    margin-left: 5px;
    font-size: 18px;
    color: #000;
  }
}
</style>
