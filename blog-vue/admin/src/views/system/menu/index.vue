<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <div>
        <el-button type="primary" icon="el-icon-plus" size="small">
          新增
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index'

export default {
  name: 'Menu',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: ''
    }
  },

  mounted () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
</style>
