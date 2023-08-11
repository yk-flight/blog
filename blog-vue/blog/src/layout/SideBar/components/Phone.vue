<template>
  <div class="wow animated slideInRight phone-container">

    <!-- 手机头部 -->
    <div class="phone-header" ref="phoneRef">
      <span style="width: 25%;">00:31</span>
      <div class="island"></div>
      <div class="icon">
        <svg-icon icon="signal"></svg-icon>
        <svg-icon icon="wifi"></svg-icon>
        <svg-icon icon="battery"></svg-icon>
      </div>
    </div>

    <!-- 手机内部样式 -->
    <div class="phone-body">

    </div>

    <div class="phone-footer">
      <div class="home"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogPhone',

  data () {
    return {
      // 是否显示动画
      showAnimation: false,
      // 当前的视觉高度
      currentScroll: 0
    }
  },

  mounted () {
    this.$wow.init()
  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.phone-container {
  background: url('../../../assets/images/iPhone-background.jpg') center center/ cover no-repeat;
  border: 9px solid #000;
  height: 33.75rem;
  width: 18.5rem;
  border-radius: 36px;
  box-shadow: 0 0 2px #000;
  transition: box-shadow 0.3s ease;
  // animation: slideInFromRight 1s ease-in-out forwards;
}
.phone-container:hover {
  box-shadow: 1px 2px 10px #000;
}
.phone-header {
  width: 100%;
  height: 1.5rem;
  margin-top: 8px;
  padding: 0 0.625rem;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .island {
  width: 45%;
  height: 1.25rem;
  background-color: #000;
  border-radius: 10px;
  }
  .icon {
    width: 25%;
    .svg-icon {
      margin-left: 4px;
    }
  }
}

.phone-body {
  width: 100%;
  height: 30rem;
  padding: 10px;
}

.phone-footer {
  // position: relative;
  // margin-top: 4.625rem;
  display: flex;
  justify-content: center;
  width: 100%;

  .home {
    height: 0.3rem;
    width: 120px;
    border-radius: 3px;
    background-color: #fff;
  }
}
@keyframes slideInFromRight {
  0% {
    transform: translateX(100%);
    opacity: 0;
  }
  100% {
    transform: translateX(0);
    opacity: 1;
  }
}
</style>
