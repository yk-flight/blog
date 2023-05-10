<template>
  <div class="item">
    <div class="image-file_wrap">
      <div class="item-file" @click="select(file)">
        <!-- 文件 -->
        <el-image :src="file.preview" fit="contain"></el-image>
        <!-- 图片预览 -->
        <pre-view
          :showViewer="showViewer"
          :showViewerUrl="showViewerUrl"
          @close="closeShower"
        >
        </pre-view>
        <!-- 文件类型 -->
        <span
          class="item-file__type"
          style="background-color: rgb(103, 194, 58)"
        >
          {{ file.type }}
        </span>
        <!-- 文件选项 -->
        <div class="item-file__actions">
          <el-button
            type="text"
            class="el-icon-zoom-in"
            @click.stop="openPreview(file)"
          ></el-button>
          <el-button
            type="text"
            class="el-icon-edit-outline"
            @click.stop="openInfo(file)"
          ></el-button>
          <el-button
            type="text"
            class="el-icon-delete"
            @click.stop="deleteFile(file)"
          ></el-button>
        </div>
        <!-- 文件选中标识 -->
        <div v-if="isSelect(file)" class="item-file__index">
          <span>
            <i class="el-icon-check"></i>
          </span>
        </div>
      </div>
    </div>

    <el-dialog
      title="图片信息"
      :before-close="closeInfo"
      :visible="showInfo"
      :append-to-body="true"
    >
      图片信息
    </el-dialog>
  </div>
</template>

<script>
import PreView from './PreView.vue'

export default {
  name: 'FileItem',

  props: {
    file: {
      type: Object,
      require: true
    }
  },

  components: {
    PreView
  },

  data () {
    return {
      // 是否预览文件
      showViewer: false,
      // 文件预览路径
      showViewerUrl: '',
      // 显示图片信息
      showInfo: false
    }
  },

  methods: {
    // 选中对应图片
    select (file) {
      // 通知父组件选中了当前文件
      this.$emit('confirm', file)
    },
    // 判断当前文件分类是否被选中
    isActive (path) {
      return this.path === path
    },
    // 是否选中图片
    isSelect (file) {
      return this.$parent.selection.includes(file)
    },
    // 查看当前图片
    openPreview (file) {
      // 赋值当前文件预览到组件文件预览路径中
      this.showViewerUrl = file.preview
      // 查看当前图片
      this.showViewer = true
    },
    // 关闭预览文件
    closeShower () {
      this.showViewer = false
    },
    // 打开图片信息对话框
    openInfo () {
      this.showInfo = true
    },
    // 关闭图片信息对话框
    closeInfo () {
      this.showInfo = false
    },
    // 删除文件
    deleteFile () {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        .catch(() => {})
    }
  }
}
</script>

<style lang="scss" scoped>
.item {
  display: flex;
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
  opacity: 1;
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
</style>
