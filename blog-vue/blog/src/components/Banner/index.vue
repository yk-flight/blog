<template>
  <div class="banner-container" :style="cover">
    <!-- 文字层 -->
    <div class="animated animate__zoomInDown banner-body">
      <h1>YK'S Blog</h1>
    </div>
    <!-- 向下跳动图标 -->
    <v-icon color="#fff" class="icon-car" @click="scrollDown">
      fas fa-chevron-down
    </v-icon>
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
    // 向下滚动
    scrollDown () {
      window.scrollTo({
        behavior: 'smooth',
        top: document.documentElement.clientHeight
      })
    }
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
@keyframes bounce {
  0%,
  100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(30px);
  }
}
.icon-car {
  position: absolute;
  bottom: 60px;
  left: 50%;
  font-size: 2rem;
  color: #fff;
  cursor: pointer;
  animation-name: bounce;
  animation-duration: 2.5s;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}
h1 {
  font-family: OldEnglish,sans-serif;
  font-size: 55px!important;
  font-weight: lighter
}
</style>
