<template>
  <div class="banner-container" :style="cover">
    <!-- 遮罩层 -->
    <div class="banner-shade"></div>
  </div>
</template>
<script>
export default {
  name: 'BlogBanner',

  data () {
    return {

    }
  },

  computed: {
    cover () {
    // 定义要拼接的CSS
      const cover = 'https://www.static.banq.ink/photos/fa74451429d178e2682a3cf6e833c019.png'
      // 循环获取首页的图片
      // this.$store.getters.navBarList.forEach((item) => {
      //   if (item.label === 'home') {
      //     cover = item.image
      //   }
      // })
      // 返回对应的CSS
      return 'background: url(' + cover + ') center center / cover no-repeat;background-attachment: fixed;'
    }
  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.banner-container {
  width: 100%;
  height: calc(90vh);
}

.banner-shade {
  height: 100%;
  background: #000;
  opacity: 0.3;
}
</style>
