<template>
  <div class="editor">
    <vue-editor
      :customModules="customModulesForEditor"
      :editorOptions="editorSettings"
      :editorToolbar="customToolbar"
      useCustomImageHandler
      @image-added="handleImageAdded"
      @blur="onEditorBlur"
      v-model="editorHtml"/>
  </div>
</template>

<script>
import { addImage } from '../../api/file'
// 引入vue2wditor
import { VueEditor } from 'vue2-editor'
// 导入图片操作相关插件
import { ImageDrop } from 'quill-image-drop-module'
import ImageResize from 'quill-image-resize-module'

export default {
  name: 'RichText',
  components: { VueEditor },
  props: {
    defaultText: { type: String, default: '' },
    richText: { type: String, default: '' }
  },
  watch: {
    // 监听默认值回调
    defaultText (nv, ov) {
      if (nv !== '') {
        this.editorHtml = nv
        this.$emit('update:rich-text', nv)
      }
    }
  },
  data () {
    return {
      editorHtml: '',
      // 菜单栏
      customToolbar: [
        [{ header: [false, 1, 2, 3, 4, 5, 6] }],
        ['bold', 'underline'],
        [{ align: '' }, { align: 'center' }, { align: 'right' }, { align: 'justify' }],
        [{ list: 'ordered' }, { list: 'bullet' }, { list: 'check' }],
        [{ indent: '-1' }, { indent: '+1' }],
        ['color', 'background'],
        ['link', 'image', 'video']
      ],
      // 调整图片大小和位置
      customModulesForEditor: [
        { alias: 'imageDrop', module: ImageDrop },
        { alias: 'imageResize', module: ImageResize }
      ],
      // 设置编辑器图片可拖拽
      editorSettings: {
        modules: { imageDrop: true, imageResize: {} }
      }
    }
  },
  mounted () {},
  methods: {
    // 图片上传方法
    handleImageAdded (file, Editor, cursorLocation, resetUploader) {
      // 定义文件上传对象
      let formData = new FormData()
      formData.append('fileTypeId', '1656676089927303169')
      formData.append('file', file)

      addImage(formData).then((res) => {
        const url = res
        formData = undefined
        // 将图片插入到编辑器的文本中
        Editor.insertEmbed(cursorLocation, 'image', url)
        // 重置上传器状态
        resetUploader()
      }).catch(() => {
        this.$message.error('上传失败')
      })
    },
    // 失去焦点
    onEditorBlur (quill) {
      this.$emit('update:rich-text', this.editorHtml)
    }
  }
}
</script>

<style lang="scss" scoped>
/* 处理添加视频链接标签位置 */
.editor .ql-snow .ql-tooltip {
  top: 0 !important;
  left: 40% !important;
}
</style>
