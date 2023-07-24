<template>
  <div>
    <vue-editor v-model="content"></vue-editor>
  </div>
</template>

<script>

export default {
  name: 'RichText',

  props: {
    // 对外暴露值
    value: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      // 富文本内容
      content: this.value
    }
  },
  watch: {
    // 监听外部内容的变化
    value (newValue) {
      this.content = newValue
    }
  },
  methods: {
    // updateValue () {
    //   this.$emit('input', this.content)
    // }
  }
}
</script>

<style lang="scss" scoped>

</style>
