<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <!-- 面包屑导航切换动画 -->
    <transition-group name="breadcrumb">
      <!-- 面包屑导航子菜单 -->
      <el-breadcrumb-item v-for="(item,index) in breadList" :key="item.path">
        <span v-if="item.redirect === 'noRedirect' || index == breadList.length - 1" class="no-redirect">
          {{ item.meta.title }}
        </span>
        <a v-else @click.prevent="handleLink(item)">
          {{ item.meta.title }}
        </a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
export default {
  name: 'Breadcrumb',

  data () {
    return {
      // 面包屑展示路由数组
      breadList: null
    }
  },

  watch: {
    /**
     * 监听当前路由
     *
     * @param {*} route
     */
    $route (route) {
      // 如果路由的路径开头为重定向则直接返回
      if (route.path.startsWith('/redirect/')) {
        return
      }
      this.getBreadcrumb()
    }
  },

  created () {
    this.getBreadcrumb()
  },

  methods: {
    /**
     * 获取面包屑导航
     */
    getBreadcrumb () {
      // 匹配面包屑导航数组中含有meta.title的路由，只对meta中含有title的路由进行展示
      const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      // 获取第一个元素用于判断是否为工作台
      // const home = matched[1]
      // // 判断当前的元素是否为工作台
      // if (!this.isHome(home)) {
      //   // 使用工作台路由拼接过滤的路由
      //   matched = [{ path: '/home', meta: { title: '工作台' } }].concat(matched)
      // }
      // 过滤当前路由数组中符合条件的路由
      this.breadList = matched.filter(item => item.meta && item.meta.title)
    },
    /**
     * 判断当前路由是否为工作台
     *
     * @param {*} route
     */
    isHome (route) {
      // 获取当前路由名称
      const name = route && route.name
      // 如果路由不存在名称
      if (!name) {
        return false
      }
      // 判断组件名称是否为工作台
      return name.trim() === 'Home'
    },
    handleLink (item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      // 跳转到指定路径
      this.$router.push(path)
    }
  }
}
</script>

<style lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
