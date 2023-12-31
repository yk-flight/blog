<template>
  <div class="space-container" v-loading="loading" element-loading-text="拼命加载文件资源中">
    <!-- 左侧文件菜单类型容器 -->
    <div :class="isCollapse ? 'hideLeft' : 'space-left'">
      <!-- 文件菜单内容 -->
      <div class="left-header">
        <span class="header-title">分类</span>
        <!-- 菜单按钮组 -->
        <div class="menu-grounp">
          <!-- 新增菜单类型 -->
          <div class="menu-item" @click="handleTypeAdd()">
            <el-tooltip effect="dark" content="新增" placement="bottom">
              <svg-icon icon="add"></svg-icon>
            </el-tooltip>
          </div>
          <!-- 编辑 -->
          <div class="menu-item" @click="handleTypeInfo()">
            <el-tooltip effect="dark" content="信息" placement="bottom">
              <svg-icon icon="dict"></svg-icon>
            </el-tooltip>
          </div>
          <!-- 删除 -->
          <div class="menu-item" @click="handleTypeDelete()">
            <el-tooltip effect="dark" content="删除" placement="bottom">
              <svg-icon icon="delete"></svg-icon>
            </el-tooltip>
          </div>
        </div>
      </div>
      <el-scrollbar style="min-height: calc(100% - 50px);">
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
      <el-row class="button-group" type="flex">
        <el-col :span="24" :xs="24">
          <!-- 刷新文件 -->
          <el-button
            icon="el-icon-refresh"
            @click="listFiles()"
            size="mini">
            刷新
          </el-button>
          <!-- 上传文件 -->
          <el-button
            icon="el-icon-upload"
            type="primary"
            size="mini"
            @click="handleShow">
            上传文件
          </el-button>
          <!-- 清空选中文件 -->
          <el-button
            icon="el-icon-folder-opened"
            type="warning"
            :disabled="selection.length === 0"
            size="mini"
            @click="clearSelect()"
            >
            清空选中文件
          </el-button>
          <!-- 删除选中文件 -->
          <el-button
            icon="el-icon-delete"
            type="danger"
            :disabled="selection.length === 0"
            @click="deleteSelect()"
            size="mini">
            删除选中文件
          </el-button>
        </el-col>
      </el-row>
      <!-- 文件内容 -->
      <div class="list">
        <!-- 单个文件 -->
        <file-item
          v-for="file in fileList"
          :file="file"
          :key="file.id"
          @confirm="confirm"
          @refresh="listFiles"
          @clear="clearSelect"
          @selection="selection"
          >
        </file-item>
      </div>
    </div>

    <!-- 文件上传对话框 -->
    <el-dialog
      :visible="uploadVisible"
      :before-close="handleClose"
      :modal-append-to-body="true"
      :append-to-body="true"
      width="500px"
      title="文件上传">
      <div class="upload-container" v-loading="uploadLoading" element-loading-text="正在加载文件上传资源...">

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
        <el-button
          size="small"
          type="danger"
          icon="el-icon-error"
          @click="uploadVisible = false">
          取 消
        </el-button>
        <el-button
          size="small"
          type="primary"
          icon="el-icon-upload"
          :loading="buttonLoading"
          @click="submitUpload">
          确定上传
        </el-button>
      </div>
    </el-dialog>

    <!-- 新增分类对话框 -->
    <el-dialog
      :visible="typeVisible"
      :before-close="handleTypeClose"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :destroy-on-close="true"
      width="500px"
      :title="typeTitle">
      <div class="type-container" v-loading="typeLoading" element-loading-text="正在加载文件分类资源...">
        <el-form label-position="top" :rules="rules" :model="typeForm">
          <!-- 分类名称 -->
          <el-form-item label="分类名称" style="margin-bottom: 10px;" prop="name">
            <el-input v-model="typeForm.name" placeholder="请填写分类名称"></el-input>
          </el-form-item>
          <!-- 分类标识 -->
          <el-form-item label="分类标识" style="margin-bottom: 10px;" prop="mark">
            <el-input v-model="typeForm.mark" placeholder="请填写分类标识" :disabled="typeForm.fileList.length > 0"></el-input>
          </el-form-item>
          <!-- 分类排序 -->
          <el-form-item label="分类排序" style="margin-bottom: 10px;" prop="sort">
            <el-input-number v-model="typeForm.sort" :max="fileMenuList.length + 1" :min="1"></el-input-number>
          </el-form-item>
          <!-- 分类描述 -->
          <el-form-item label="分类描述" style="margin-bottom: 10px;">
            <el-input type="textarea" v-model="typeForm.description" placeholder="请填写分类描述" rows="4"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer">
        <el-button
          size="small"
          type="danger"
          icon="el-icon-error"
          :loading="buttonLoading"
          @click="handleTypeClose()">
          取 消
        </el-button>
        <el-button
          size="small"
          type="success"
          icon="el-icon-success"
          @click="handleTypeSave()"
          :loading="buttonLoading">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import FileItem from './components/FileItem.vue'
import { listFiles, listModes, upload, deleteBatch, getFileTypeById, save, deleteFileTypeById } from '../../../api/file'

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
        id: undefined,
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
        // 文件所属分类
        fileTypeId: ''
      },
      // 上传文件按钮等待框
      buttonLoading: false,
      // 新增分类对话框
      typeVisible: false,
      // 分类加载框
      typeLoading: false,
      // 文件分类对话框标题
      typeTitle: '',
      // 文件分类内容
      typeForm: {
        // 分类ID
        id: undefined,
        // 分类名称
        name: undefined,
        // 分类标识
        mark: undefined,
        // 分类排序
        sort: undefined,
        // 分类包含文件
        fileList: [],
        // 分类描述
        description: undefined,
        // 分类创建时间
        createTime: undefined,
        // 更新时间
        updateTime: undefined
      },
      // 新增文件分类校验规则
      rules: {
        // 分类名称
        name: [{ required: true, message: '文件分类名称不能为空', trigger: 'blur' }],
        // 分类标识
        mark: [{ required: true, message: '文件分类标识不能为空', trigger: 'blur' }],
        // 分类排序
        sort: [{ required: true, message: '请选择分类排序值', trigger: 'blur' }]
      }
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
      // 清空当前选择文件
      this.selection = []
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
      // ID
      this.activeMenu.id = data.id
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
      // 参数校验
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
          // 判断文件大小是否符合
          if (file.size / 1024 / 1024 > this.size) {
            this.$message.error('文件大小不得超过 ' + this.size + ' MB')
            return false
          }
        }
      }
      return true
    },
    // 文件上传事件
    submitUpload () {
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
        // 定义formData方式上传，因为后续涉及到清空当前对象，因此使用let定义
        let formData = new FormData()
        formData.append('fileTypeId', this.upload.fileTypeId)
        formData.append('file', file)
        // 加载等待框
        this.buttonLoading = true
        this.uploadLoading = true
        // 开始上传
        upload(formData).then((res) => {
          this.$message.success('上传成功')
          // 清空表单数据
          this.upload.mode = ''
          this.upload.fileTypeId = ''
          formData = undefined
          // 清除上传的文件
          this.$refs.upload.uploadFiles = []
          // 刷新文件列表
          this.listFiles()
          // 关闭等待框
          this.uploadLoading = false
          this.buttonLoading = false
          // 关闭对话框
          this.handleClose()
        }).catch(() => {
          // 发生异常关闭等待框
          this.uploadLoading = false
          this.buttonLoading = false
        })
      }
    },
    // 删除选中文件
    deleteSelect () {
      const that = this
      this.$confirm('此操作将永久删除选中文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 加载等待框
        this.loading = true
        // 批量删除选中文件
        deleteBatch(this.selection).then((res) => {
        // 刷新当前文件
          that.listFiles()
          // 清空当前选中文件
          that.selection = []
          that.$message.success('文件删除成功')
        }).catch(() => {
        // 关闭等待框
          this.loading = false
        })
      }).catch(() => {})
    },
    // 点击文件分类新增按钮
    handleTypeAdd () {
      // 对话框标题
      this.typeTitle = '新增分类'
      // 新增文件分类排序
      this.typeForm.sort = this.fileMenuList.length + 1
      // 打开对话框
      this.typeVisible = true
    },
    // 点击文件信息按钮
    handleTypeInfo () {
      // 对话框标题
      this.typeTitle = '编辑分类'
      // 文件分类加载框
      this.typeLoading = true
      // 根据当前选中分类查询
      getFileTypeById(this.activeMenu.id).then((res) => {
        this.typeForm = res
        this.typeForm.fileList = this.fileList
        // 关闭加载框
        this.typeLoading = false
      }).catch(() => {
        // 清空数据并关闭加载框和对话框
        this.typeLoading = false
        this.typeVisible = false
        this.resetTypeForm()
      })
      // 打开对话框
      this.typeVisible = true
    },
    // 重置文件分类对象
    resetTypeForm () {
      this.typeForm.id = undefined
      this.typeForm.name = undefined
      this.typeForm.mark = undefined
      this.typeForm.sort = undefined
      this.typeForm.description = undefined
      this.typeForm.fileList = []
      this.typeForm.createTime = undefined
      this.typeForm.updateTime = undefined
    },
    // 关闭文件分类对话框
    handleTypeClose () {
      this.typeVisible = false
      // 重置文件分类对象
      this.resetTypeForm()
    },
    // 保存文件分类
    handleTypeSave () {
      // 开启按钮加载框
      this.buttonLoading = true
      // 更新文件分类或返回对象
      save(this.typeForm).then((res) => {
        // 输出提示信息
        this.$message.success('文件分类操作成功')
        // 重新获取文件数据
        this.listFiles()
        // 关闭对话框
        this.typeVisible = false
        // 重置文件分类对象
        this.resetTypeForm()
        // 关闭按钮加载框
        this.buttonLoading = false
      }).catch(() => {
        // 关闭加载框
        this.buttonLoading = false
      })
    },
    // 删除文件分类
    handleTypeDelete () {
      const that = this
      this.$confirm('此操作将永久删除选中文件分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 加载等待框
        this.loading = true
        // 批量删除选中文件
        deleteFileTypeById(this.activeMenu.id).then((res) => {
        // 刷新当前文件
          that.listFiles()
          // 清空当前选中文件
          that.$message.success('文件分类删除成功')
        }).catch(() => {
        // 关闭等待框
          this.loading = false
        })
      }).catch(() => {})
    }
  }
}
</script>

<style lang="scss" scoped>
.space-container {
  display: flex;
  justify-content: space-between;
  min-height: 100%;
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
    transition: 3s;
    // min-height: calc(100vh - 125px);

    /* 定义展开和折叠的过渡果 */
  .sidebar-enter-active,
  .sidebar-leave-active {
    transition: width 0.8s;
  }
  }

  .hideLeft {
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
  }

  .right-header {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
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
    padding: 10px;
  }

  .select-item {
    .el-select {
      width: 290px;
    }
  }
}
</style>
