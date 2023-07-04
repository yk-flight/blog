<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            
            <el-form-item label="登录用户名称">
              <el-input v-model="queryParams.username" class="search-item" placeholder="请输入登录用户名称" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="登录状态：0 失败; 1 成功">
              <el-input v-model="queryParams.status" class="search-item" placeholder="请输入登录状态：0 失败; 1 成功" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="登录时间">
              <el-input v-model="queryParams.loginTime" class="search-item" placeholder="请输入登录时间" size="small" clearable></el-input>
            </el-form-item>
            <!-- 创建时间 -->
            <el-form-item label="创建时间" size="small">
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
          <el-button type="primary" icon="el-icon-plus" @click="handleAdd" size="mini">
            新增
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate">编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" @selection-change="handleSelectionChange" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="selection" width="50" align="center" />
        <!-- 登录用户名称 -->
        <el-table-column prop="username" label="登录用户名称" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 登录IP -->
        <el-table-column prop="loginIp" label="登录IP" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 登录位置 -->
        <el-table-column prop="loginLocation" label="登录位置" align="center" v-if="columns[2].visible"></el-table-column>
        <!-- 浏览器版本 -->
        <el-table-column prop="browser" label="浏览器版本" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 操作系统 -->
        <el-table-column prop="os" label="操作系统" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 登录状态：0 失败; 1 成功 -->
        <el-table-column prop="status" label="登录状态：0 失败; 1 成功" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 登录消息提示 -->
        <el-table-column prop="message" label="登录消息提示" align="center" v-if="columns[6].visible"></el-table-column>
        <!-- 登录时间 -->
        <el-table-column prop="loginTime" label="登录时间" align="center" v-if="columns[7].visible"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[8].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[9].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button type="text" size="small" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
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

    <!-- 用户登录信息信息对话框 -->
    <el-dialog
      :title="loginInfoTitle"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="loginInfoVisible"
      :before-close="handleClose">
      <div class="loginInfo-wrapper" v-loading="loginInfoLoading" element-loading-text="正在加载用户登录信息信息">
        <el-form ref="loginInfoForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <!-- 登录用户名称 -->
            <el-form-item label="登录用户名称" prop="username">
              <el-input v-model="formData.username" placeholder="请输入登录用户名称" clearable></el-input>
            </el-form-item>
            <!-- 登录IP -->
            <el-form-item label="登录IP" prop="loginIp">
              <el-input v-model="formData.loginIp" placeholder="请输入登录IP" clearable></el-input>
            </el-form-item>
            <!-- 登录位置 -->
            <el-form-item label="登录位置" prop="loginLocation">
              <el-input v-model="formData.loginLocation" placeholder="请输入登录位置" clearable></el-input>
            </el-form-item>
            <!-- 浏览器版本 -->
            <el-form-item label="浏览器版本" prop="browser">
              <el-input v-model="formData.browser" placeholder="请输入浏览器版本" clearable></el-input>
            </el-form-item>
            <!-- 操作系统 -->
            <el-form-item label="操作系统" prop="os">
              <el-input v-model="formData.os" placeholder="请输入操作系统" clearable></el-input>
            </el-form-item>
            <!-- 登录状态：0 失败; 1 成功 -->
            <el-form-item label="登录状态：0 失败; 1 成功" prop="status">
              <el-input v-model="formData.status" placeholder="请输入登录状态：0 失败; 1 成功" clearable></el-input>
            </el-form-item>
            <!-- 登录消息提示 -->
            <el-form-item label="登录消息提示" prop="message">
              <el-input v-model="formData.message" placeholder="请输入登录消息提示" clearable></el-input>
            </el-form-item>
            <!-- 登录时间 -->
            <el-form-item label="登录时间" prop="loginTime">
              <el-input v-model="formData.loginTime" placeholder="请输入登录时间" clearable></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </div>
      <div slot="footer">
        <el-button
          type="danger"
          :loading="buttonLoading"
          @click="handleClose()"
          size="small"
          icon="el-icon-error">
          取消
        </el-button>
        <el-button
          type="success"
          :loading="buttonLoading"
          @click="submitForm()"
          size="small"
          icon="el-icon-success">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import Pagination from '../../../components/Pagination/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'
import { listLoginInfos, saveLoginInfo, getLoginInfoById, deleteLoginInfo } from '../../../api/loginInfo'

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
      // 用户登录信息对话框是否显示
      loginInfoVisible: false,
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
        // 登录状态：0 失败; 1 成功
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
        // 登录状态：0 失败; 1 成功
        status: undefined,
        // 登录消息提示
        message: undefined,
        // 登录时间
        loginTime: undefined,
      },
      // 用户登录信息表单校验规则
      rules: {
        // 登录用户名称
        username: [{ required: true, message: '请输入登录用户名称', trigger: 'blur' }],
        // 登录状态：0 失败; 1 成功
        status: [{ required: true, message: '请输入登录状态：0 失败; 1 成功', trigger: 'blur' }],
        // 登录时间
        loginTime: [{ required: true, message: '请输入登录时间', trigger: 'blur' }],
      },
      // 用户登录信息对话框等待框
      loginInfoLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 登录用户名称
        { key: 0, label: '登录用户名称', visible: true },
        // 登录IP
        { key: 1, label: '登录IP', visible: true },
        // 登录位置
        { key: 2, label: '登录位置', visible: true },
        // 浏览器版本
        { key: 3, label: '浏览器版本', visible: true },
        // 操作系统
        { key: 4, label: '操作系统', visible: true },
        // 登录状态：0 失败; 1 成功
        { key: 5, label: '登录状态：0 失败; 1 成功', visible: true },
        // 登录消息提示
        { key: 6, label: '登录消息提示', visible: true },
        // 登录时间
        { key: 7, label: '登录时间', visible: true },
        { key: 8, label: '创建时间', visible: true },
        { key: 9, label: '更新时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 表头选中的数据
      ids: [],
      // 多数据禁用
      single: true,
      // 单数据禁用
      multiple: true,
      // 对话框标题
      loginInfoTitle: ''
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
    // 点击重置按钮
    handleReset () {
      // 登录用户名称
      this.queryParams.username = ''
      // 登录状态：0 失败; 1 成功
      this.queryParams.status = ''
      // 登录时间
      this.queryParams.loginTime = ''
      this.queryParams.dataRange = []
    },
    // 打开用户登录信息信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.loginInfoVisible = true
    },
    // 关闭用户登录信息对话框表单
    handleClose () {
      this.resetForm()
      this.loginInfoVisible = false
    },
    // 点击新增按钮
    handleAdd () {
      this.loginInfoTitle = '新增用户登录信息'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.loginInfoTitle = '更新用户登录信息'
      // 获取到传来的ID
      const loginInfoId = row.id || this.ids
      // 打开加载框
      this.loginInfoLoading = true
      // 根据用户登录信息ID获取对应的数据
      getLoginInfoById(loginInfoId).then((res) => {
        // 赋值当前的用户登录信息数据
        this.formData = res
        // 打开编辑用户登录信息对话框
        this.loginInfoVisible = true
        // 关闭加载框
        this.loginInfoLoading = false
      })
    },
    // 点击删除事件
    handleDelete (row) {
      let loginInfoIds = []
      if (row.id) {
        loginInfoIds.push(row.id)
      } else {
        loginInfoIds = this.ids
      }
      console.log(loginInfoIds)
      this.$confirm('是否确认删除选中的用户登录信息数据？', '提示', {
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
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.loginInfoForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.loginInfoLoading = true
        // 提交表单
        saveLoginInfo(that.formData).then((res) => {
          // 根据是否存在ID输出对应消息
          if (that.formData.id) {
            // 输出更新成功信息
            that.$message.success('用户登录信息信息更新成功')
          } else {
            // 输出添加成功消息
            that.$message.success('用户登录信息添加成功')
          }
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新用户登录信息数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.loginInfoLoading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.loginInfoLoading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 用户登录信息ID
        id: undefined,
        // 登录用户名称
        username: undefined,
        // 登录状态：0 失败; 1 成功
        status: undefined,
        // 登录时间
        loginTime: undefined,
      }
    },
    // 多选框
    handleSelectionChange (selection) {
      // 获取当前选中数组的ID
      this.ids = selection.map(item => item.id)
      // 表头的编辑是否可以点击
      this.single = selection.length !== 1
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
