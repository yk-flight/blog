<template>
  <div class="banner-container" :style="cover">
    <!-- 文字层 -->
    <div class="animated animate__zoomInDown banner-body">
      <h1>雅康的个人博客</h1>
    </div>
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
  position: relative;
  width: 100%;
  height: calc(100vh);
}

.banner-container::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3); /* 这里的0.5表示遮罩的透明度，可以根据需要进行调整 */
  z-index: 0; /* 确保遮罩层在图片之上 */
}

.banner-body {
  position: relative;
  height: 100%;
  width: 100%;
  // top: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
}

h1 {
  color: #fff;
}
</style>
