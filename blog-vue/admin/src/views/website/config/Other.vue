<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <div class="card-desc">
        在这里编辑配置项
      </div>
    </div>

    <div class="card">
      <el-button type="primary" icon="el-icon-plus" size="small">新增配置项</el-button>
    </div>
  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'

export default {
  name: 'Other',

  components: { PageTitle },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
  },

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.card {
  margin-bottom: 20px;
}
.card-desc {
  margin-top: 60px;
}
</style>
