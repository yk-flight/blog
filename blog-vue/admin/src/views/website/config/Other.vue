<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <div class="card-desc">
        在这里编辑配置项
      </div>
    </div>

    <div class="card">
      <el-row>
        <el-button type="primary" icon="el-icon-plus" size="small">新增配置</el-button>
        <el-button type="danger" icon="el-icon-delete" size="small">删除配置</el-button>
      </el-row>
      <div class="config-container">
        <el-row>
          <el-col :md="{span: 14, offset: 5}" :sm="{span: 20, offset: 2}" :xs="24">
            <el-form label-width="80">
              <!-- 用户头像 -->
              <el-form-item label="用户头像：">
                <el-image>这里是头像</el-image>
              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置1：">
                <el-input size="small">网站配置1</el-input>
              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置2：">
                <el-radio-group>
                  <el-radio label="线上品牌商赞助"></el-radio>
                  <el-radio label="线下场地免费"></el-radio>
                </el-radio-group>
              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置3：">
                <el-input type="textarea" size="small">网站配置2</el-input>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>
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
.config-container {
  margin-top: 30px;
  margin-bottom: 10px;
}
</style>
