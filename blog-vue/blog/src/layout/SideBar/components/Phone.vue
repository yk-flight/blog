<template>
  <div class="wow animated slideInLeft phone-container">

    <!-- 手机头部 -->
    <div class="phone-header" ref="phoneRef">
      <span style="width: 27%;text-align: center;">{{ nowTime }}</span>
      <div class="island"></div>
      <div class="icon">
        <svg-icon icon="signal"></svg-icon>
        <svg-icon icon="wifi"></svg-icon>
        <svg-icon icon="battery"></svg-icon>
      </div>
    </div>

    <!-- 手机内部样式 -->
    <div class="phone-body">
      <!-- 区域信息以及博主信息 -->
      <div class="body-header">
        <div class="header-item">
          <div style="width: 100%; margin-top: 5px; margin-left: 10px; font-size: 10px;">
            西湖区
            <svg-icon icon="guide"></svg-icon>
          </div>
          <span style="width: 100%; margin-left: 10px; font-size: 25px;">33°</span>
        </div>
        <div class="header-item">
          <router-link to="/">
            <img src="https://s1.ax1x.com/2023/04/26/p9K181A.jpg" height="100%" width="100%" class="on-hover"/>
          </router-link>
        </div>
      </div>

      <!-- 博客功能区域 -->
      <div class="body-blog">

      </div>

      <!-- 社交链接区域 -->
      <div class="body-social">
        <a target="_blank" href="https://github.com/yk-flight">
          <svg-icon icon="github"></svg-icon>
        </a>
        <a target="_blank" href="https://gitee.com/dream-flight">
          <svg-icon icon="gitee"></svg-icon>
        </a>
      </div>
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
      // 当前时间
      nowTime: ''
    }
  },

  created () {
    this.getTime()
  },

  mounted () {
    this.$wow.init()
  },

  methods: {
    getTime () {
      const that = this
      // 导入DayJS
      const dayjs = require('dayjs')
      // 使用format()方法来格式化日期对象，只显示当前时间
      setInterval(() => {
        // 创建一个Day.js对象
        const now = dayjs()
        that.nowTime = now.format('HH:mm')
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped>
.phone-container {
  background: url('../../../assets/images/iPhone-background.jpg') center center/ cover no-repeat;
  border: 9px solid #000;
  height: 32.75rem;
  width: 100%;
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
    width: 27%;
    .svg-icon {
      margin-left: 4px;
    }
  }
}

.phone-body {
  width: 100%;
  height: 28.625rem;
  padding: 10px 15px;
}

.phone-footer {
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
.body-header {
  position: relative;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;

  .header-item {
    width: calc(50% - 5px);
    height: 100px;
    border-radius: 12px;
    overflow: hidden;
    color: #fff;
    background: linear-gradient(to bottom, #1976D2, #BBDEFB);
  }
}
.on-hover {
  object-fit: cover;
  transition: all 0.3s;
  border-radius: 12px;
  background: rgba(0, 0, 0, 0.2);
}
.header-item a:hover .on-hover {
  transform: scale(1.1);
}
.body-blog {
  width: 100%;
  height: calc(100% - 180px);
  margin-bottom: 15px;
  background-color: green;
}

.body-social {
  width: 100%;
  height: 50px;
  background-color: rgba(186, 184, 184, 0.6);
  border-radius: 17px;
  backdrop-filter: blur(10px);
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  padding: 5px 20px;
  font-size: 25px;

  a {
    text-decoration: none;
    color: #000;
  }

  .svg-icon {
    margin: 0 3px;
  }
}
</style>
