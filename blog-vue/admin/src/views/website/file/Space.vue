<template>
  <div class="space-container"
    v-loading="loading"
    element-loading-text="拼命加载文件资源中"
    >
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
            :key="item.mark"
            class="list-item"
            :class="isActive(item.mark) ? 'active' : ''"
            @click="change(item)"
          >
            <span>{{ item.name }}</span>
            <svg-icon :icon="isActive(item.mark) ? 'more' : ''"></svg-icon>
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
        <span>文件列表 - {{ activeMenu.name }}</span>
      </div>
      <!-- 文件选项 -->
      <div class="button-group">
        <!-- 刷新文件 -->
        <el-button
          icon="el-icon-refresh"
          size="small">
          刷新
        </el-button>
        <!-- 上传文件 -->
        <el-button
          icon="el-icon-upload"
          type="primary"
          size="small"
          @click="uploadVisible = true">
          上传文件
        </el-button>
        <!-- 清空选中文件 -->
        <el-button
          icon="el-icon-folder-opened"
          type="warning"
          :disabled="selection.length === 0"
          size="small"
          @click="clearSelect()"
          >
          清空选中文件
        </el-button>
        <!-- 删除选中文件 -->
        <el-button
          icon="el-icon-delete"
          type="danger"
          :disabled="selection.length === 0"
          size="small">
          删除选中文件
        </el-button>
      </div>

      <!-- 文件内容 -->
      <div class="list">
        <!-- 单个文件 -->
        <file-item
          v-for="file in fileList"
          :file="file"
          :key="file.id"
          @confirm="confirm"
          @selection="selection"
          >
        </file-item>
      </div>
    </div>

    <el-dialog
      :visible="uploadVisible"
      :before-close="handleClose"
      :modal-append-to-body="true"
      :append-to-body="true"
      width="500px"
      title="文件上传">
      <div class="upload-container">
        <div class="select-item">
          <el-select v-model="value" placeholder="请选择上传方式" size="small" style="width: 360px;">
            <el-option
              v-for="item in fileMenuList"
              :key="item.mark"
              :label="item.name"
              :value="item.mark">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.mark }}</span>
            </el-option>
          </el-select>
        </div>

        <div class="upload-item">
          <el-upload
            drag
            action="#"
            multiple
            :show-file-list="false"
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">{{ text }}</div>
          </el-upload>
        </div>
      </div>

      <div slot="footer">
        <el-button size="small" type="danger" @click="uploadVisible = false">取 消</el-button>
        <el-button size="small" type="success" @click="uploadVisible = false">确 定</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import FileItem from './components/FileItem.vue'
import { listFiles } from '../../../api/file'

export default {
  name: 'Space',

  props: {
    // 可以选择文件的数量
    limit: {
      type: Number,
      default: 9
    },
    // 接受文件上传类型
    accept: {
      type: Array,
      default: () => []
    },
    // 文件上传描述
    text: {
      type: String,
      require: true
    }
  },

  components: {
    FileItem
  },

  created () {
    this.listFiles()
  },

  data () {
    return {
      // 是否加载
      loading: false,
      // 文件分类列表
      fileMenuList: [],
      // 当前选中的文件分类对象
      activeMenu: {
        name: undefined,
        mark: undefined
      },
      // 菜单栏是否折叠
      isCollapse: false,
      // 文件列表
      fileList: [],
      // 选中的文件集合
      selection: [],
      // 文件上传对话框
      uploadVisible: false,
      // 文件对话框分类
      value: ''
    }
  },

  watch: {
    // 监听父组件中选择文件列表的变化
    selection: {
      handler (value, oldValue) {
        this.$emit('updateSelect', value)
      },
      // 开启深度监听
      deep: true
    }
  },

  methods: {
    // 获取文件列表
    listFiles () {
      // 开启加载框
      this.loading = true
      listFiles().then((res) => {
        this.fileMenuList = res
        // 默认选中第一个文件分类
        if (this.fileMenuList) {
          this.changeData(this.fileMenuList[0])
        }
        // 关闭加载框
        this.loading = false
      })
    },
    // 切换文件分类名称与文件列表
    changeData (data) {
      // 更改当前选中文件分类的内容
      this.fileList = data.fileList
      // 更改当前选中文件的名称
      this.activeMenu.name = data.name
      // 更改当前文件类型的路径
      this.activeMenu.mark = data.mark
    },
    // 判断当前文件分类是否被选中
    isActive (mark) {
      return this.activeMenu.mark === mark
    },
    // 切换文件分类
    change (data) {
      // 如果当前选中标签相同则直接返回，防止多次请求
      if (this.activeMenu.mark === data.mark) {
        return
      }
      // 修改当前页面数据
      this.changeData(data)
    },
    // 设置菜单栏折叠或展开
    setCollapse () {
      this.isCollapse = !this.isCollapse
    },
    // 确认选中的文件
    confirm (data) {
      // 获取当前选中文件在已经选择的文件列表中的索引
      const index = this.selection.indexOf(data)
      // 如果当前文件已经选中，则将其从选中文件列表中删除
      if (index >= 0) {
        this.selection.splice(index, 1)
      // 判断当前选中文件列表中的文件是否超过限制
      } else {
        // 如果当前文件选择数量限制为1则进行替换
        if (this.limit === 1) {
          this.selection = [data]
        } else {
          // 如果当前选择文件数量没有超过限制则直接添加
          if (this.selection.length < this.limit) {
            // 将当前文件添加到选择文件列表中
            this.selection.push(data)
          }
        }
      }
    },
    // 清空选中的文件
    clearSelect () {
      // 将当前选中文件列表清空
      this.selection = []
    },
    // 关闭文件上传对话框
    handleClose () {
      this.uploadVisible = false
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
