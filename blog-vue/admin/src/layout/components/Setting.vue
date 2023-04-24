<template>
  <div class="drawer-container">
    <div class="setting-drawer-content">
      <div class="setting-drawer-title">
        <h1 class="drawer-title">主题风格设置</h1>
      </div>

      <div class="setting-drawer-block-checbox">
        <div class="setting-drawer-block-checbox-item" @click="handleTheme('theme-dark')">
          <img src="@/assets/images/dark.svg" alt="dark" />
        </div>
        <div
          class="setting-drawer-block-checbox-item"
          @click="handleTheme('theme-light')"
        >
          <img src="@/assets/images/light.svg" alt="light" />
        </div>
      </div>
    </div>

    <el-divider></el-divider>

    <div class="setting-drawer-title">
      <h1 class="drawer-title">系统布局配置</h1>
    </div>
      <div class="drawer-item">
        <span>开启标签页</span>
        <el-switch v-model="tagsView" class="drawer-switch" />
      </div>

      <div class="drawer-item">
        <span>固定导航栏</span>
        <el-switch v-model="fixedHeader" class="drawer-switch" />
      </div>

      <div class="drawer-item">
        <span>显示 Logo</span>
        <el-switch v-model="sidebarLogo" class="drawer-switch" />
      </div>

    <el-divider/>

    <el-button size="small" type="primary" plain icon="el-icon-document-add" @click="saveSetting">保存配置</el-button>
    <el-button size="small" plain icon="el-icon-refresh" @click="resetSetting">重置配置</el-button>
  </div>
</template>

<script>
export default {
  name: 'Setting',

  computed: {
    // 是否开启标签页
    tagsView: {
      get () {
        return this.$store.getters.tagsView
      },
      set (val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'tagsView',
          value: val
        })
      }
    },
    // 是否显示Logo
    sidebarLogo: {
      get () {
        return this.$store.getters.sidebarLogo
      },
      set (val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'sidebarLogo',
          value: val
        })
      }
    },
    // 固定顶部导航栏
    fixedHeader: {
      get () {
        return this.$store.getters.fixedHeader
      },
      set (val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'fixedHeader',
          value: val
        })
      }
    }
  },

  data () {
    return {
      // 侧边栏主题
      sideTheme: this.$store.getters.sideTheme
    }
  },

  mounted () {},

  methods: {
    // 切换侧边栏主题
    handleTheme (val) {
      this.$store.dispatch('settings/changeSetting', {
        key: 'sideTheme',
        value: val
      })
      this.sideTheme = val
    },
    // 保存配置
    saveSetting () {
      // 加载等待框
      const loading = this.$loading({
        lock: true,
        text: '正在保存当前配置...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      // 将内容存储到本地
      window.localStorage.setItem(
        'layout-setting',
        `{
            "tagsView":${this.tagsView},
            "fixedHeader":${this.fixedHeader},
            "sidebarLogo":${this.sidebarLogo},
            "sideTheme":"${this.sideTheme}"
          }`
      )
      setTimeout(() => {
        loading.close()
      }, 1000)
    },
    // 重置系统设置
    resetSetting () {
      const loading = this.$loading({
        lock: true,
        text: '正在保存当前配置并刷新...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      window.localStorage.removeItem('layout-setting')
      setTimeout(() => {
        loading.close()
        // 刷新当前页面
        window.location.reload()
      }, 1000)
    }
  }
}
</script>

<style lang="scss" scoped>
.setting-drawer-content {
  .setting-drawer-title {
    margin-bottom: 12px;
    color: rgba(0, 0, 0, 0.85);
    font-size: 14px;
    line-height: 22px;
    font-weight: bold;
  }

  .setting-drawer-block-checbox {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-top: 10px;
    margin-bottom: 20px;

    .setting-drawer-block-checbox-item {
      position: relative;
      margin-right: 16px;
      border-radius: 2px;
      cursor: pointer;

      img {
        width: 48px;
        height: 48px;
      }

      .setting-drawer-block-checbox-selectIcon {
        position: absolute;
        top: 0;
        right: 0;
        width: 100%;
        height: 100%;
        padding-top: 15px;
        padding-left: 24px;
        color: #1890ff;
        font-weight: 700;
        font-size: 14px;
      }
    }
  }
}

.drawer-container {
  padding: 20px;
  font-size: 14px;
  line-height: 1.5;
  word-wrap: break-word;

  .drawer-title {
    margin-bottom: 12px;
    color: rgba(0, 0, 0, 0.85);
    font-size: 14px;
    line-height: 22px;
  }

  .drawer-item {
    color: rgba(0, 0, 0, 0.65);
    font-size: 14px;
    padding: 12px 0;
  }

  .drawer-switch {
    float: right;
  }
}
</style>
