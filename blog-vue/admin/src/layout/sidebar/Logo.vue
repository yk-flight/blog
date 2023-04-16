<template>
  <div class="logo-container">
    <router-link to="/home" class="logo-link">
      <img :src="logo" alt="" class="logo-image"/>
      <h1 class="logo-title">{{ title }}</h1>
    </router-link>
  </div>
</template>

<script>
import logoImage from '../../assets/images/logo.png'

export default {
  name: 'Logo',

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
.logo-container {
  width: 100%;
  height: 50px;
}
.logo-link {
  display: flex;
  height: 100%;
  width: 100%;
  text-decoration: none;
  justify-content: center;
  align-items: center;
}
.logo-image {
  width: 32px;
  height: 32px;
  vertical-align: middle;
  margin-right: 12px;
}
.logo-title {
  display: inline-block;
  color: #fff;
  font-size: 14px;
  font-weight: 600;
}

</style>
