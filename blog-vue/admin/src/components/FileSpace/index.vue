<template>
  <el-dialog
    title="文件空间"
    :visible="visible"
    width="900px"
    :before-close="closeFileSpace"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
  >
    <!-- 文件空间 -->
    <space
      ref="space"
      class="space-component"
      :limit="limit"
      :text="text"
      :accept="accept"
      :size="size"
      @updateSelect="updateSelect"
      ></space>

    <span slot="footer">
      <div style="margin-top: 10px">
        <el-button @click="closeFileSpace">取消</el-button>
        <el-button
          type="success"
          :loading="loading"
          @click="confirm()">
          选择 {{ selection.length }}/{{ limit }}
        </el-button>
      </div>
    </span>
  </el-dialog>
</template>

<script>
import Space from '../../views/website/file/Space.vue'

export default {
  name: 'FileSpace',

  props: {
    // 定义是否展示
    visible: {
      type: Boolean,
      default: false
    },
    // 接受文件上传类型
    accept: {
      type: Array,
      default: () => []
    },
    // 接受上传文件的大小
    size: {
      type: Number,
      default: undefined
    },
    // 可以选择文件的数量
    limit: {
      type: Number,
      default: 9
    },
    // 文件上传描述
    text: {
      type: String,
      require: true
    }
  },

  components: {
    Space
  },

  data () {
    return {
      loading: false,
      // 选中文件
      selection: []
    }
  },

  mounted () {},

  methods: {
    // 关闭文件空间
    closeFileSpace () {
      // 调用外部组件绑定的close方法
      this.$emit('close')
    },
    // 更新选择文件内容
    updateSelect (data) {
      // 使用子组件中传来的值覆盖掉当前组件中选中文件的值
      this.selection = data
    },
    // 选中当前文件
    confirm () {
      // 选中文件才进行传值操作
      if (this.selection.length > 0) {
        // 将选中的文件发送给父组件
        this.$emit('confirm', this.selection)
        // 清空选择数据
        this.selection = []
        // 清空子组件的选择数据
        this.$refs.space.clearSelect()
      }
      // 关闭对话框
      this.closeFileSpace()
    }
  }
}
</script>

<style lang="scss" scoped>
.space-component {
  height: 500px !important;
  box-shadow: none;
  border-bottom: 1px solid #dcdfe6;
  border-top: 1px solid #dcdfe6;
  border-radius: 0;
}
</style>
