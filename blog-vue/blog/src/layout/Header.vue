<template>
  <div class="header-container">
    <v-app-bar app :class="navbarClass">
      <!-- 菜单导航栏容器 -->
      <div class="container">
        <div class="navbar-title">
          <v-avatar color="teal" size="25" rounded style="margin-right: 0.5rem">
            <img src="../.../../assets/logo.png" />
          </v-avatar>
          <span>世纪末的架构师</span>
        </div>

        <!-- 手机端导航栏 -->
        <v-menu offset-y close-on-click>
          <template #activator="{ on }">
            <v-icon class="menu-icon" v-on="on" @click.stop="drawer = !drawer" size="25" color="#fff">
              fas fa-bars
            </v-icon>
          </template>
        </v-menu>
        <!-- 在PC端显示完整菜单 -->
        <div class="navbar-body">
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

            <li class="navbar-item">
              <v-icon>fas fa-search</v-icon>
            </li>

            <li class="navbar-item">
              <v-icon>fas fa-sun</v-icon>
            </li>
          </ul>
        </div>
      </div>
    </v-app-bar>

    <!-- 手机端抽屉展示栏 -->
    <v-navigation-drawer
      v-model="drawer"
      absolute
      app
      temporary
      dark
      height="100vh"
      width="75vw"
      class="menu-drawer"
    >
      <v-list nav>
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title class="text-h6">
              世纪末的架构师
            </v-list-item-title>
            <v-list-item-subtitle>
              https://www.zrkizzy.com
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item-group v-model="isShow">
          <v-list-item>
            <v-list-item-icon>
              <v-icon>fas fa-home</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>首页</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>fas fa-archive</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>归档</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>fas fa-th-large</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>分类</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>fas fa-tags</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>标签</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>fas fa-user</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>关于</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "Header",

  data() {
    return {
      // 顶部导航栏是否在顶部
      navbarClass: "navbar-default",
      // 定义手机端抽屉菜单是否展开
      drawer: false,
      // 定义抽屉是否展示方法
      isShow: null,
    };
  },
  watch: {
    // 监听当前抽屉是否展示
    isShow() {
      this.drawer = false;
    },
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  computed: {
    showMobileMenu() {
      console.log()
      return this.$vuetify.breakpoint.width < 960;
    },
  },
  methods: {
    handleScroll(event) {
      // 定义当前监视器
      const scrollTop =
        document.documentElement.scrollTop || document.body.scrollTop;
      // 如果当前导航栏离开顶部
      if (scrollTop >= 64) {
        // 标记当前导航栏为离开顶部
        this.navbarClass = "navbar-scroll";
      } else {
        this.navbarClass = "navbar-default";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.header-container {
  z-index: 9999;

  .navbar-title {
    font-weight: 700;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    padding-top: 0.3125rem;
    padding-bottom: 0.3125rem;
    margin-right: 1rem;
    font-size: 1.25rem;
    line-height: inherit;
    white-space: nowrap;
    display: flex;
    align-items: center;
  }

  .navbar-body {
    display: flex;
    justify-content: right;
    height: 100%;
    flex-basis: 100%;
    flex-grow: 1;
    align-items: center;

    .v-icon {
      color: #fff;
      font-size: 14px;
      margin-right: 0.3125rem;
    }
  }
}

.navbar-default {
  width: 100% !important;
  display: flex;
  align-items: center;
  justify-content: space-between;
  transition: 0.8s !important;
  background-color: rgba(0, 0, 0, 0) !important;
}

.navbar-scroll {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: rgba(47, 65, 84, 0.7) !important;
  height: 50px !important;
  transition: 0.8s;
}

.navbar-item {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #fff;
  cursor: pointer;
}
.navbar-item:hover {
  transition: 0.8s;
  color: #30a9de;

  .v-icon {
    transition: 0.8s;
    color: #30a9de;
  }
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

.menu-icon {
  color: #fff;
}

.menu-drawer {
  background: rgba(47, 65, 84) !important;
}

.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  max-width: 1110px;
  height: 100%;
  padding: 0 15px;
  margin-right: auto;
  margin-left: auto;
}

/* 大于等于 800px 时隐藏元素 */
@media (min-width: 900px) {
  .menu-icon {
    display: none !important;
  }
}

/* 小于 900px 时显示元素 */
@media (max-width: 899px) {
  .menu-icon {
    display: block !important;
  }

  .navbar-body {
    display: none !important;
  }
}
</style>
