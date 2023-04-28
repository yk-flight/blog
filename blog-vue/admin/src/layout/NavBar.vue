<template>
  <div class="navbar-container">
    <hamburger class="hamburger-container"></hamburger>
    <breadcrumb class="breadcrumb-container"></breadcrumb>
    <div class="right-menu">
        <el-tooltip effect="dark" content="博客地址" placement="bottom">
          <a :href="blogUrl" target="__blank">
            <svg-icon icon="link" class="right-menu-item hover-effect"></svg-icon>
          </a>
        </el-tooltip>

        <el-tooltip effect="dark" content="Gitee" placement="bottom">
          <a :href="GiteeUrl" target="__blank">
            <svg-icon icon="gitee" class="right-menu-item hover-effect"></svg-icon>
          </a>
        </el-tooltip>

        <el-tooltip effect="dark" content="全屏展示" placement="bottom">
          <screen-full class="right-menu-item hover-effect"></screen-full>
        </el-tooltip>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img src="https://s1.ax1x.com/2023/04/26/p9K181A.jpg" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import ScreenFull from '../components/ScreenFull/index.vue'
import Hamburger from './components/Hamburger.vue'
import Breadcrumb from './components/Breadcrumb.vue'

export default {
  name: 'NavBar',

  components: { Hamburger, Breadcrumb, ScreenFull },

  data () {
    return {
      // 项目Gitee地址
      GiteeUrl: 'https://gitee.com/dream-flight/blog',
      // 博客前台地址
      blogUrl: 'https://www.zrkizzy.com'
    }
  },

  mounted () {

  },

  methods: {
    /**
     * 用户退出方法
     */
    async logout () {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('user/logout').then(() => {
          location.href = '/login'
        })
      }).catch(() => {})
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar-container {
  height: 50px;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  background: #fff;
}

.hamburger-container {
  float: left;
  height: 100%;
  line-height: 46px;
  padding: 0 15px;
  cursor: pointer;
  // hover 动画
  transition: background 0.5s;

  &:hover {
    background-color: rgba(0, 0, 0, 0.1);
  }
}

.breadcrumb-container {
  float: left;
  height: 100%;
  line-height: 50px;
  margin-left: 0.5rem;
}

.right-menu {
  float: right;
  height: 100%;
  line-height: 46px;

  &:focus {
    outline: none;
  }

  .right-menu-item {
    display: inline-block;
    padding: 0 8px;
    height: 100%;
    color: #5a5e66;
    vertical-align: text-bottom;

    &.svg-icon {
      font-size: 18px;
    }

    &.hover-effect {
      cursor: pointer;
      transition: background .3s;
      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }
  }

  .avatar-container {
    margin-right: 30px;

    .avatar-wrapper {
      margin-top: 5px;
      position: relative;
      .user-avatar {
        cursor: pointer;
        width: 40px;
        height: 40px;
         border-radius: 10px;
      }

      .el-icon-caret-bottom {
        cursor: pointer;
        position: absolute;
        right: -20px;
        top: 25px;
        font-size: 12px;
      }
    }
  }
}
</style>
