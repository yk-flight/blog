<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            <!-- 用户名 -->
            <el-form-item label="用户名">
              <el-input v-model="queryParams.username" class="search-item" placeholder="请输入用户名" size="small" clearable></el-input>
            </el-form-item>
            <!-- 用户昵称 -->
            <el-form-item label="用户昵称">
              <el-input v-model="queryParams.nickname" class="search-item" placeholder="请输入用户昵称" size="small" clearable></el-input>
            </el-form-item>
            <!-- 搜索按钮 -->
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

      <el-row :gutter="10" class="button-container">
        <!-- :columns="columns" @getTableData="getTableData" -->
        <right-toolbar :showSearch.sync="showSearch"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <!-- 登录编号 -->
        <el-table-column prop="track" label="登录编号" align="center"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'

export default {
  name: 'Online',

  components: { PageTitle, RightToolbar },

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
        // 用户名
        username: undefined,
        // 用户昵称
        nickname: undefined
      },
      // 数据表格等待框
      loading: false,
      // 表格数据
      tableData: []
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
  },

  methods: {
    // 点击查询按钮
    handleQuery () {
      this.queryParams.currentPage = 1
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
