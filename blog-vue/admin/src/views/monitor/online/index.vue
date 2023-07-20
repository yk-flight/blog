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
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
        <!-- 登录编号 -->
        <el-table-column prop="track" label="登录编号" align="center" v-if="columns[0].visible" show-overflow-tooltip></el-table-column>
        <!-- 用户头像 -->
        <el-table-column prop="avatar" label="用户头像" align="center" v-if="columns[1].visible" width="100">
          <template slot-scope="scope">
            <el-image :src="scope.row.avatar">
              <div slot="error" class="image-slot-table">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </template>
        </el-table-column>
        <!-- 用户名 -->
        <el-table-column prop="username" label="用户名" align="center" v-if="columns[2].visible" show-overflow-tooltip></el-table-column>
        <!-- 用户昵称 -->
        <el-table-column prop="nickname" label="用户昵称" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 登录IP -->
        <el-table-column prop="ipAddress" label="登录IP" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 登录位置 -->
        <el-table-column prop="ipLocation" label="登录位置" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 操作系统 -->
        <el-table-column prop="os" label="操作系统" align="center" v-if="columns[6].visible"></el-table-column>
        <!-- 浏览器 -->
        <el-table-column prop="browser" label="浏览器" align="center" v-if="columns[7].visible"></el-table-column>
        <!-- 登录时间 -->
        <el-table-column prop="loginTime" label="登录时间" align="center" v-if="columns[8].visible" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.loginTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-switch-button" @click="handleOffline(scope.row)">下线</el-button>
          </template>
        </el-table-column>

      </el-table>
      <!-- 分页器 -->
      <pagination
        :background="false"
        :total="total"
        :page.sync="queryParams.currentPage"
        :limit.sync="queryParams.pageSize"
        @pagination="getTableData"
      />
    </div>
  </div>
</template>

<script>
import Pagination from '../../../components/Pagination/index.vue'
import PageTitle from '../../../components/PageTitle/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'
import { listOnline, offline } from '../../../api/system'

export default {
  name: 'Online',

  components: { PageTitle, RightToolbar, Pagination },

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
      tableData: [],
      // 列信息
      columns: [
        // 登录编号
        { key: 0, label: '登录编号', visible: true },
        // 用户头像
        { key: 1, label: '用户头像', visible: true },
        // 用户昵称
        { key: 2, label: '用户名', visible: true },
        // 用户昵称
        { key: 3, label: '用户昵称', visible: true },
        // 登录IP
        { key: 4, label: '登录IP', visible: true },
        // 登录位置
        { key: 5, label: '登录位置', visible: true },
        // 操作系统
        { key: 6, label: '操作系统', visible: true },
        // 浏览器
        { key: 7, label: '浏览器', visible: true },
        // 登录时间
        { key: 8, label: '登录时间', visible: true }
      ]
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 获取表格内容
    this.getTableData()
  },

  methods: {
    getTableData () {
      // 开启加载框
      this.loading = true
      listOnline(this.queryParams).then((res) => {
        this.tableData = res.list
        this.total = res.total
        // 关闭加载框
        this.loading = false
      }).catch(() => {
        // 关闭加载框
        this.loading = false
      })
    },
    // 点击查询按钮
    handleQuery () {
      this.queryParams.currentPage = 1
      this.getTableData()
    },
    // 点击重置按钮
    handleReset () {
      // 用户名
      this.queryParams.username = undefined
      // 用户昵称
      this.queryParams.nickname = undefined
    },
    // 下线指定用户
    handleOffline (onlineUser) {
      this.$confirm('是否下线 "' + onlineUser.nickname + '(' + onlineUser.username + ')" ?', '系统提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 执行下线用户方法
        offline(onlineUser.track).then((res) => {
          this.$message({ type: 'success', message: '下线成功' })
          // 刷新数据表格
          this.getTableData()
        })
      }).catch(() => {})
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
