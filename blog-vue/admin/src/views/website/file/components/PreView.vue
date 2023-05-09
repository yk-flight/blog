<template>
  <div>
    <!-- 图片预览 -->
    <el-image-viewer
      v-if="showViewer"
      :on-close="closeViewer"
      :url-list="[showViewerUrl]"
    ></el-image-viewer>

    <!-- 其他类型文件预览 -->
  </div>
</template>

<script>
import ElImageViewer from 'element-ui/packages/image/src/image-viewer'

export default {
  name: 'PreView',

  components: {
    ElImageViewer
  },

  props: {
    // 是否预览当前文件
    showViewer: {
      type: Boolean,
      default: false
    },
    // 当前预览图片路径
    showViewerUrl: {
      type: String,
      default: ''
    }
  },

  data () {
    return {

    }
  },

  mounted () {},

  methods: {
    // 查看当前图片
    openPreview (data) {
      console.log('进来了')
      console.log(data)
      // 赋值当前图片预览路径
      this.showViewerUrl = data
      // 查看当前图片
      this.showViewer = true
    },
    // 关闭当前图片预览
    closeViewer () {
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped></style>
