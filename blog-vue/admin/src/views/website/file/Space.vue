<template>
  <div class="space-container">
    <!-- 左侧文件菜单类型容器 -->
    <div :class="isCollapse ? 'hideLeft' : 'space-left'">
      <!-- 文件菜单内容 -->
      <div class="left-header">
        <span class="header-title">分类</span>
        <!-- 菜单按钮组 -->
        <div class="menu-grounp">
          <!-- 刷新 -->
          <div class="menu-item">
            <el-tooltip effect="dark" content="刷新" placement="bottom">
              <svg-icon icon="refresh"></svg-icon>
            </el-tooltip>
          </div>
          <!-- 新增菜单类型 -->
          <div class="menu-item">
            <el-tooltip effect="dark" content="新增" placement="bottom">
              <svg-icon icon="add"></svg-icon>
            </el-tooltip>
          </div>
        </div>
      </div>
      <el-scrollbar style="height: calc(100% - 50px);">
        <div class="menu-list">
          <!-- 文件类型菜单 -->
          <div
            v-for="item in fileMenuList"
            :key="item.path"
            class="list-item"
            :class="isActive(item.path) ? 'active' : ''"
            @click="change(item)"
          >
            <span>{{ item.name }}</span>
            <svg-icon :icon="isActive(item.path) ? 'more' : ''"></svg-icon>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <!-- 右侧文件内容区域 -->
    <div :class="isCollapse ? 'openRight' : 'space-right'">
      <!-- 内容头部 -->
      <div class="right-header">
        <div class="right-icon" @click="setCollapse">
          <svg-icon :icon="isCollapse ? 'right' : 'left'"></svg-icon>
        </div>
        <span>文件列表 - {{ fileMenu.name }}</span>
      </div>
      <!-- 文件选项 -->
      <div class="button-group">
        <el-button size="small">刷新</el-button>
        <el-button type="primary" size="small">点击上传</el-button>
        <el-button type="danger" disabled size="small">删除选中文件</el-button>
      </div>

      <!-- 文件内容 -->
      <div class="list">
        <!-- 单个文件 -->
        <file-item v-for="file in fileList" :file="file" :key="file.id"></file-item>
      </div>
    </div>
  </div>
</template>

<script>
import FileItem from './components/FileItem.vue'

export default {
  name: 'Space',

  components: {
    FileItem
  },

  data () {
    return {
      // 文件分类列表
      fileMenuList: [
        {
          name: '阿里云',
          path: 'aliyun'
        },
        {
          name: '腾讯云',
          path: 'tencent'
        },
        {
          name: '七牛云',
          path: 'qiniucloud'
        },
        {
          name: '本地',
          path: 'local'
        }
      ],
      // 当前选中的文件分类对象
      fileMenu: {
        name: '本地',
        path: 'local'
      },
      // 菜单栏是否折叠
      isCollapse: false,
      // 文件列表
      fileList: [
        {
          id: 1,
          name: 'p90rISA.jpg',
          preview: 'https://s1.ax1x.com/2023/05/09/p90rISA.jpg',
          src: 'https://s1.ax1x.com/2023/05/09/p90rISA.jpg',
          type: '图片'
        },
        {
          id: 2,
          name: 'p9tnRu4.jpg',
          preview: 'https://s1.ax1x.com/2023/05/04/p9tnRu4.jpg',
          src: 'https://s1.ax1x.com/2023/05/04/p9tnRu4.jpg',
          type: '图片'
        },
        {
          id: 3,
          name: 'p9K181A.jpg',
          preview: 'https://s1.ax1x.com/2023/04/26/p9K181A.jpg',
          src: 'https://s1.ax1x.com/2023/04/26/p9K181A.jpg',
          type: '图片'
        },
        {
          id: 4,
          name: 'p90roQI.jpg',
          // 预览
          preview: 'https://s1.ax1x.com/2023/05/09/p90roQI.jpg',
          src: 'https://s1.ax1x.com/2023/05/09/p90roQI.jpg',
          type: '图片'
        }
      ]
    }
  },

  mounted () {},

  methods: {
    // 判断当前文件分类是否被选中
    isActive (path) {
      return this.fileMenu.path === path
    },
    // 切换文件分类
    change (data) {
      // 如果当前选中标签相同则直接返回，防止多次请求
      if (this.fileMenu.path === data.path) {
        return
      }
      // 更改当前选中文件的名称
      this.fileMenu.name = data.name
      // 更改当前文件类型的路径
      this.fileMenu.path = data.path
    },
    // 设置菜单栏折叠或展开
    setCollapse () {
      this.isCollapse = !this.isCollapse
    }
  }
}
</script>

<style lang="scss" scoped>
.space-container {
  display: flex;
  justify-content: space-between;
  height: 100%;
  border-radius: 5px;
  background: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);

  .svg-icon {
    width: 18px;
    height: 18px;
  }

  .space-left {
    border-right: 1px solid #dcdfe6;
    width: 200px;
    // min-height: calc(100vh - 125px);
    transition: .3s;
  }

  .hideLeft {
    transition: .3s;
    display: none;
  }

  .left-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 5px 10px;
    height: 40px;
    transition: .3s;
  }

  .menu-grounp {
    display: flex;
  }

  .menu-item {
    padding: 5px;
    cursor: pointer;
    transition: background 0.3s;
  }

  .menu-item:hover {
    padding: 5px;
    background: rgba(0, 0, 0, 0.025);
  }

  .menu-list {
    width: 100%;
  }

  .list-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    list-style: none;
    box-sizing: border-box;
    padding: 10px;
    margin: 0 10px 10px;
    cursor: pointer;
    font-size: 13px;
    border-radius: 3px;
    color: #666;
    position: relative;
    background-color: #f7f7f7;

    &.active {
      color: #fff;
      background: #409eff;
    }
  }

  .space-right {
    float: right;
    width: calc(100% - 200px);
  }

  .openRight {
    width: 100%;
    min-height: calc(100vh - 125px);
    transition: .3s;
  }

  .right-header {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 40px;
    position: relative;
    font-size: 15px;
    border-bottom: 1px solid #dcdfe6;
  }

  .list {
    display: flex;
    flex-wrap: wrap;
    padding: 0 10px;
  }

  .right-icon {
    display: flex;
    align-items: center;
    position: absolute;
    left: 0;
    top: 0;
    font-size: 14px;
    cursor: pointer;
    height: 100%;
    padding: 0 10px;
    transition: .3s;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .button-group {
    display: flex;
    align-items: center;
    height: 50px;
    padding: 5px 10px;
  }
}
</style>
