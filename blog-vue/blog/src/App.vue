<template>
  <v-app>
    <Header></Header>
    <v-main>
      <transition name="animate__animated animate__backInDown" mode="out-in">
        <router-view />
      </transition>
    </v-main>
    <Footer></Footer>
  </v-app>
</template>

<script>
// 导入公共导航头组件
import Header from "@/layout/Header.vue";
// 导入公共页脚组件
import Footer from "@/layout/Footer.vue";

export default {
  name: "App",
  components: {
    Footer,
    Header,
  },
  data: () => ({
    //
  }),
};
</script>

<style scoped>
.container {
  position: relative;
}
.custom-classes-enter-active,
.custom-classes-leave-active {
  animation-duration: 0.5s !important; /*设置过渡时间*/
  animation-fill-mode: both !important;
}
.custom-classes-enter-to,
.custom-classed.leave-from {
  opacity: 0.2 !important; /*初始状态的透明度*/
  animation-fill-mode: both !important;
}
.animate__animated {
  /*必须添加，否则无法触发animate.css中定义的样式*/
  -webkit-animation-duration: 0.5s !important;
  -moz-animation-duration: 0.5s !important;
  -o-animation-duration: 0.5s !important;
  animation-duration: 0.5s !important;
}
.custom-classes-enter,
.custom-clasess.leave-to {
  opacity: 0 !important; /*初始状态的透明度*/
  animation-fill-mode: both;
}
/* 进入动画效果 */
.animate__fadeInRightBig.enter {
  animation-name: fadeInRightBig;
}
@keyframes fadeInRightBig {
  from {
    opacity: 0;
    transform-origin: right center;
    transform: scale3d(0.1, 0.1, 0.1) translate3d(1000px, 0, 0);
  }
  to {
    opacity: 1;
    transform: none;
  }
}
/* 离开动画效果 */
.animate__fadeOutLeftBig.leave-to {
  animation-name: fadeOutLeftBig;
}
@keyframes fadeOutLeftBig {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
    transform: translateX(-200px);
  }
}
</style>
