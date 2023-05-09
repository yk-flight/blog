<template>
  <el-dialog
    title="文件空间"
    :visible="visible"
    width="900px"
    :before-close="closeFileSpace"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
  >
    <div class="space-container">
      <div class="space-left">
        <div class="left-header">
          <span>分类</span>
          <div class="menu-grounp">
            <div class="menu-item">
              <el-tooltip effect="dark" content="刷新" placement="bottom">
                <svg-icon icon="refresh"></svg-icon>
              </el-tooltip>
            </div>

            <div class="menu-item">
              <el-tooltip effect="dark" content="新增" placement="bottom">
                <svg-icon icon="add"></svg-icon>
              </el-tooltip>
            </div>
          </div>
        </div>
        <el-scrollbar>
          <div class="list">
            <!-- 文件类型菜单 -->
            <ul>
              <li>
                <!-- 文件类型菜单 -->
                <div
                  v-for="item in fileMenu"
                  :key="item.path"
                  class="list-item"
                  :class="isActive(item.path) ? 'active' : ''"
                  @click="change(item.path)"
                >
                  <span>{{ item.name }}</span>
                  <svg-icon :icon="isActive(item.path) ? 'more' : ''"></svg-icon>
                </div>
              </li>
            </ul>
          </div>
        </el-scrollbar>
      </div>
      <div class="space-right">
        <!-- 文件选项组 -->
        <div style="margin-bottom: 10px">
          <el-button size="small">刷新</el-button>
          <el-button type="primary" size="small">点击上传</el-button>
          <el-button type="danger" size="small" disabled>
            删除选中的文件
          </el-button>
        </div>
        <!-- 防止文件过多 -->
        <el-scrollbar>
          <div class="image-container">

            <!-- 单个文件 -->
            <div class="item" v-for="file in fileList" :key="file.id">
              <div class="image-file_wrap">
                <div class="item-file" @click="selectFile(file)">
                  <!-- 文件 -->
                  <el-image
                    ref="preview"
                    :src="file.preview"
                    fit="contain"
                    :preview-src-list="previews"
                  >
                  </el-image>
                  <!-- 文件类型 -->
                  <span
                    class="item-file__type"
                    style="background-color: rgb(103, 194, 58)">
                    {{ file.type }}
                  </span>
                  <!-- 文件选项 -->
                  <div class="item-file__actions">
                    <el-button
                      style="z-index: 2;"
                      type="text"
                      class="el-icon-zoom-in"
                      @click="preview(file.src)"
                      ></el-button>
                    <el-button type="text" class="el-icon-edit-outline"></el-button>
                    <el-button type="text" class="el-icon-delete"></el-button>
                  </div>
                  <!-- 文件选中标识 -->
                  <div v-if="isSelect(file)" class="item-file__index">
                    <span>
                      <i class="el-icon-check"></i>
                    </span>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </el-scrollbar>
      </div>
    </div>

    <span slot="footer">
      <div style="margin-top: 10px">
        <el-button @click="closeFileSpace">取消</el-button>
        <el-button type="success" :loading="loading">选择 0/9</el-button>
      </div>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'FileSpace',

  props: {
    // // 用户ID
    // id: {
    //   type: Number,
    //   require: true
    // },
    // 定义是否展示
    visible: {
      type: Boolean,
      default: false
    }
  },

  data () {
    return {
      loading: false,
      // 选中的文件分类
      path: 'local',
      // 文件分类列表
      fileMenu: [
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
      ],
      // 选中文件列表
      selectFileList: [],
      // 图片预览列表
      previews: []
    }
  },

  mounted () {},

  methods: {
    // 关闭文件空间
    closeFileSpace () {
      // 调用外部组件绑定的close方法
      this.$emit('close', false)
    },
    // 判断当前文件分类是否被选中
    isActive (path) {
      return this.path === path
    },
    // 是否选中图片
    isSelect (file) {
      return this.selectFileList.includes(file)
    },
    // 切换文件分类
    change (path) {
      // 如果当前选中标签相同则直接返回，防止多次请求
      if (this.path === path) {
        return
      }
      this.path = path
    },
    // 选中当前文件
    selectFile (file) {
      // 如果当前文件已经选择
      if (this.isSelect(file)) {
        // 移除当前文件
        this.selectFileList.splice(this.selectFileList.indexOf(file), 1)
        return
      }
      // 将当前点击文件添加到选中列表中
      this.selectFileList.push(file)
    },
    // 查看当前图片
    preview (data) {
      // 清空当前图片预览列表
      this.previews = []
      // 将当前图片数据添加到图片预览表格中
      this.previews.push(data)
      // 查看当前图片
      // this.$refs.preview.clickHandler()
    }
  }
}
</script>

<style lang="scss" scoped>
.space-container {
  display: flex;
  justify-content: space-between;
  border-top: 1px solid #dcdfe6;
  border-bottom: 1px solid #dcdfe6;

  ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }
  .el-col {
    margin: 10px 0;
  }

  .svg-icon {
    width: 18px;
    height: 18px;
  }

  .space-left {
    border-right: 1px solid #dcdfe6;
    width: 200px;
  }

  .space-right {
    width: calc(100% - 200px);
    padding: 10px;
  }

  .left-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 5px 10px;
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

  .list {
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

  .image-container {
    display: flex;
    flex-wrap: wrap;
    padding: 10px 0 0 25px;
  }

  .item {
    height: 0;
    min-height: 140px;
    min-width: 140px;
    width: calc(12.5% - 20px);
    padding-top: calc(12.5% - 20px);
    margin: 0 20px 20px 0;
    position: relative;
    box-sizing: border-box;
  }

  .image-file_wrap {
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 100%;
  }

  .item-file {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 100%;
    cursor: pointer;
    border-radius: 5px;
    overflow: hidden;
    box-sizing: border-box;
    background-color: #0000001a;
    margin-bottom: 10px;
  }

  .item-file__type {
    position: absolute;
    top: 5px;
    left: 5px;
    color: #fff;
    font-size: 12px;
    padding: 2px 5px;
    border-radius: 3px;
  }

  .item-file__actions {
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #00000080;
    border-radius: 5px;
    opacity: 0;
    transition: opacity 0.3s cubic-bezier(0.55, 0, 0.1, 1);

    .el-button {
      color: #fff;
      font-size: 20px;
      margin: 0 8px;
    }
  }

  .item-file:hover .item-file__actions {
    opacity: 1
  }

  .item-file__index {
    position: absolute;
    left: 0;
    top: 0;
    height: 100%;
    width: 100%;
    background-color: #0000004d;
    border-radius: 5px;
    pointer-events: none;

    span {
      position: absolute;
      right: 5px;
      top: 5px;
      background-color: #67c23a;
      color: #fff;
      display: inline-block;
      height: 20px;
      width: 20px;
      text-align: center;
      line-height: 20px;
      border-radius: 20px;
      font-size: 14px;
    }
  }
}

.el-scrollbar {
  height: 500px;
}
</style>
