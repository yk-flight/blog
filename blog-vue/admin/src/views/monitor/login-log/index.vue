<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="用户名称">
              <el-input v-model="queryParams.username" class="search-item" placeholder="请输入登录用户名称" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="登录状态">
              <el-select v-model="queryParams.status" placeholder="请选择登录状态" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <!-- 登录时间 -->
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
      <el-row :gutter="10" class="button-container">
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="warning" icon="el-icon-folder-opened" size="mini" @click="handleClear">清空</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" @selection-change="handleSelectionChange" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="selection" width="50" align="center" />
        <!-- 日志编号 -->
        <el-table-column prop="id" label="日志编号" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 登录用户名称 -->
        <el-table-column prop="username" label="用户名称" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 登录IP -->
        <el-table-column prop="loginIp" label="登录IP" align="center" v-if="columns[2].visible"></el-table-column>
        <!-- 登录位置 -->
        <el-table-column prop="loginLocation" label="登录位置" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 浏览器版本 -->
        <el-table-column prop="browser" label="浏览器版本" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 操作系统 -->
        <el-table-column prop="os" label="操作系统" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 登录状态 -->
        <el-table-column prop="status" label="登录状态" align="center" v-if="columns[6].visible">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status">成功</el-tag>
            <el-tag type="danger" v-else>失败</el-tag>
          </template>
        </el-table-column>
        <!-- 登录消息提示 -->
        <el-table-column prop="message" label="登录提示" align="center" v-if="columns[7].visible"></el-table-column>
        <!-- 登录时间 -->
        <el-table-column prop="loginTime" label="登录时间" width="180" align="center" v-if="columns[8].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.loginTime | dateFilter }}</span>
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
import PageTitle from '../../../components/PageTitle/index.vue'
import Pagination from '../../../components/Pagination/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'
import { listLoginInfos, deleteLoginInfo, clearLoginInfo } from '../../../api/loginInfo'

export default {
  name: 'LoginInfo',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 登录用户名称
        username: undefined,
        // 登录状态
        status: undefined,
        // 登录时间
        loginTime: undefined,
        // 时间范围
        dataRange: []
      },
      // 用户登录信息表单对象
      formData: {
        // 登录用户名称
        username: undefined,
        // 登录IP
        loginIp: undefined,
        // 登录位置
        loginLocation: undefined,
        // 浏览器版本
        browser: undefined,
        // 操作系统
        os: undefined,
        // 登录状态
        status: undefined,
        // 登录消息提示
        message: undefined,
        // 登录时间
        loginTime: undefined
      },
      // 用户登录信息表单校验规则
      rules: {
        // 登录用户名称
        username: [{ required: true, message: '请输入登录用户名称', trigger: 'blur' }],
        // 登录状态
        status: [{ required: true, message: '请输入登录状态', trigger: 'blur' }],
        // 登录时间
        loginTime: [{ required: true, message: '请输入登录时间', trigger: 'blur' }]
      },
      // 用户登录信息对话框等待框
      loginInfoLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 日志编号
        { key: 0, label: '日志编号', visible: true },
        // 登录用户名称
        { key: 1, label: '用户名称', visible: true },
        // 登录IP
        { key: 2, label: '登录IP', visible: true },
        // 登录位置
        { key: 3, label: '登录位置', visible: true },
        // 浏览器版本
        { key: 4, label: '浏览器版本', visible: true },
        // 操作系统
        { key: 5, label: '操作系统', visible: true },
        // 登录状态
        { key: 6, label: '登录状态', visible: true },
        // 登录消息提示
        { key: 7, label: '登录提示', visible: true },
        // 登录时间
        { key: 8, label: '登录时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 表头选中的数据
      ids: [],
      // 多数据禁用
      multiple: true,
      // 对话框标题
      loginInfoTitle: '',
      // 请求状态选项
      statusOptions: [
        {
          value: 1,
          label: '成功'
        },
        {
          value: 0,
          label: '失败'
        }
      ]
    }
  },

  computed: {},

  watch: {},

  created () {},

  mounted () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 查询表格数据
    this.getTableData()
  },

  methods: {
    // 获取表格数据
    getTableData () {
      // 清除多选数据
      this.ids = []
      // 开启加载框
      this.loading = true
      listLoginInfos(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res.list
        this.total = res.total
        // 关闭等待框
        this.loading = false
      })
    },
    // 点击查询按钮
    handleQuery () {
      this.getTableData()
    },
    // 点击清空按钮
    handleClear () {
      this.$confirm('此操作将永久删除所有登录日志数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 执行清空操作
        clearLoginInfo().then((res) => {
          this.$message({ type: 'success', message: '清空成功' })
          // 刷新表格
          this.getTableData()
        })
      }).catch(() => {})
    },
    // 点击重置按钮
    handleReset () {
      // 登录用户名称
      this.queryParams.username = ''
      // 登录状态
      this.queryParams.status = ''
      // 登录时间
      this.queryParams.loginTime = ''
      this.queryParams.dataRange = []
    },
    // 点击删除事件
    handleDelete () {
      const loginInfoIds = this.ids
      this.$confirm('是否确认删除选中的用户登录日志数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return deleteLoginInfo(loginInfoIds)
      }).then(() => {
        this.getTableData()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    // 多选框
    handleSelectionChange (selection) {
      // 获取当前选中数组的ID
      this.ids = selection.map(item => item.id)
      // 表头的删除是否可以点击
      this.multiple = !selection.length
    }
  }
}

</script>

<style lang="scss" scoped>
.loginInfo-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
