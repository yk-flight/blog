<template>
  <div class="navbar-container">
    <hamburger class="hamburger-container"></hamburger>
    <breadcrumb class="breadcrumb-container"></breadcrumb>
    <div class="right-menu">右侧菜单栏</div>
  </div>
</template>

<script>
import Hamburger from './components/Hamburger.vue'
import Breadcrumb from './components/Breadcrumb.vue'

export default {
  name: 'NavBar',

  components: { Hamburger, Breadcrumb },

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
}
</style>
