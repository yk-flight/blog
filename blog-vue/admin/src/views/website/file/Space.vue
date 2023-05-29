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
          @click="handleShow">
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
      <div class="upload-container" v-loading="uploadLoading" element-loading-text="正在加载文件上传资源...">
        <div class="select-item">
          <span>上传模式：</span>
          <el-select v-model="upload.mode" placeholder="请选择上传方式" size="small" style="width: 290px;">
            <el-option
              v-for="item in uploadMode"
              :key="item.mark"
              :label="item.mode"
              :value="item.mark">
              <span style="float: left">{{ item.mode }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">
                {{ item.mark }}
              </span>
            </el-option>
          </el-select>
        </div>

        <div class="select-item">
          <span>文件分类：</span>
          <el-select v-model="upload.fileTypeId" placeholder="请选择文件分类" size="small" style="width: 290px;">
            <el-option
              v-for="item in fileMenuList"
              :key="item.mark"
              :label="item.name"
              :value="item.id">
              <span style="float: left">{{ item.name }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">
                {{ item.mark }}
              </span>
            </el-option>
          </el-select>
        </div>

        <div class="upload-item">
          <el-upload
            style="width: 360px;"
            list-type="picture"
            ref="upload"
            drag
            action="#"
            multiple
            :auto-upload="false"
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">{{ text }}</div>
          </el-upload>
        </div>
      </div>

      <div slot="footer">
        <el-button size="small" type="danger" @click="uploadVisible = false">取 消</el-button>
        <el-button size="small" type="primary" icon="el-icon-upload" @click="submitUpload">确定上传</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import FileItem from './components/FileItem.vue'
import { listFiles, listModes, upload } from '../../../api/file'

export default {
  name: 'Space',

  props: {
    // 可以选择文件的数量
    limit: {
      type: Number,
      default: 9
    },
    // 接受上传文件大小
    size: {
      type: Number,
      default: undefined
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
      // 文件上传对话框加载
      uploadLoading: false,
      // 文件上传对话框
      uploadVisible: false,
      // 文件上传模式数据
      uploadMode: [],
      // 文件上传对象
      upload: {
        // 文件上传模式
        mode: '',
        // 文件所属分类
        fileTypeId: '',
        // 文件内容
        file: {}
      },
      // 上传进度百分比
      progressPercent: 0
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
    // 打开文件上传对话框
    handleShow () {
      // 打开对话框
      this.uploadVisible = true
      // 显示加载框
      this.uploadLoading = true
      // 获取文件上传模式
      listModes().then((res) => {
        this.uploadMode = res
      })
      // 关闭加载框
      this.uploadLoading = false
    },
    // 关闭文件上传对话框
    handleClose () {
      this.uploadVisible = false
    },
    // 文件上传前校验
    beforeUpload (file) {
      console.log('进入校验')
      // 参数校验
      if (this.upload.mode === '') {
        this.$message.error('文件上传模式不能为空')
        return false
      }
      if (this.upload.type === '') {
        this.$message.error('文件分类不能为空')
        return false
      }
      // 校验图片格式和大小
      if (this.accept.length > 0) {
        // 获取文件后缀
        const fileType = file.name.slice(file.name.lastIndexOf('.')).toLowerCase()
        // 校验文件格式
        if (!this.accept.includes(fileType)) {
          // 拼接允许上传文件格式提示信息
          var acceptType = ''
          for (var i = 0; i < this.accept.length; i++) {
            acceptType = acceptType + this.accept[i] + ' '
          }
          this.$message.error('请上传 ' + acceptType + '格式的文件')
          return false
        }
        // 校验文件大小
        if (this.size) {
          console.log(file.size / 1024 / 1024 < this.size)
          // 判断文件大小是否符合
          if (file.size / 1024 / 1024 > this.size) {
            this.$message.error('文件大小不得超过 ' + this.size + ' MB')
            return false
          }
        }
      } else {
        // 如果没有图片格式限制则直接上传
        const isJPG = file.type === 'image/jpeg'
        console.log('isAccept: ', isJPG)
      }
      return true
    },
    // 文件上传事件
    submitUpload () {
      console.log(typeof (this.upload.fileTypeId))
      console.log(this.upload.fileTypeId)
      // 获取 DOM 元素中的文件
      const fileArray = this.$refs.upload.uploadFiles
      // 如果没有选中文件
      if (fileArray.length === 0) {
        this.$message.error('请选择文件')
        return false
      }
      // 如果文件存在则获取对应文件
      const file = fileArray[0].raw
      // 校验文件通过则进行上传
      if (this.beforeUpload(file)) {
        // 定义formData方式上传
        const formData = new FormData()
        formData.append('mode', this.upload.mode)
        formData.append('fileTypeId', this.upload.fileTypeId)
        formData.append('file', file)

        // 开始上传
        upload(formData).then((res) => {
          console.log(res)
        })
        console.log('开始上传')
      }
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

  .select-item {
    .el-select {
      width: 290px;
    }
  }
}
</style>
