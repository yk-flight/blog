<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            <!-- 请求时间 -->
            <el-form-item label="登录时间" size="small">
              <el-date-picker
                v-model="queryParams.dataRange"
                class="search-item"
                value-format="yyyy-MM-dd"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item>
            <!-- 搜索按钮 -->
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'

export default {
  name: 'Online',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 时间范围
        dataRange: []
      }
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
  },

  methods: {
    // 点击查询按钮
    handleQuery () {
    },
    // 点击重置按钮
    handleReset () {
      // 时间范围
      this.queryParams.dataRange = []
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
