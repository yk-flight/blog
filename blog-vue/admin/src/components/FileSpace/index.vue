<template>
  <el-dialog
    title="文件空间"
    :visible="visible"
    width="1000px"
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
                <div
                  v-for="item in fileMenu"
                  :key="item.url"
                  class="list-item"
                  :class="isActive(item.url) ? 'active' : ''"
                  @click="change(item.url)"
                >
                  <span>{{ item.name }}</span>
                  <svg-icon :icon="isActive(item.url) ? 'more' : ''"></svg-icon>
                </div>
              </li>
            </ul>
          </div>
        </el-scrollbar>
      </div>
      <div class="space-right">
        <el-scrollbar>
          <el-row>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
            <el-col :span="6">
              <el-image
                style="width: 140px; height: 140px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="contain">
              </el-image>
            </el-col>
          </el-row>
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
      url: 'local',
      // 文件分类列表
      fileMenu: [
        {
          name: '阿里云',
          url: 'aliyun'
        },
        {
          name: '腾讯云',
          url: 'tencent'
        },
        {
          name: '七牛云',
          url: 'qiniucloud'
        },
        {
          name: '本地',
          url: 'local'
        }
      ]
    }
  },

  mounted () {},

  methods: {
    // 关闭文件空间
    closeFileSpace () {
      // 调用外部组件绑定的close方法
      this.$emit('close', false)
    },
    // 判断当前标签页是否被选中
    isActive (url) {
      return this.url === url
    },
    // 切换文件分类
    change (url) {
      // 如果当前选中标签相同则直接返回，防止多次请求
      if (this.url === url) {
        return
      }
      this.url = url
      console.log('执行查询操作')
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

  .svg-icon {
    width: 18px;
    height: 18px;
  }

  .space-left {
    border-right: 1px solid #dcdfe6;
    width: 240px;
  }

  .space-right {
    width: calc(100% - 240px);
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
}

.el-scrollbar {
  height: 500px;
}
</style>
