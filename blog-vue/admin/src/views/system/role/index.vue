<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            <!-- 角色名称 -->
            <el-form-item label="角色名称">
              <el-input v-model="queryParams.name" class="search-item" placeholder="请输入角色名称" size="small" clearable></el-input>
            </el-form-item>
            <!-- 角色标识 -->
            <el-form-item label="角色标识">
              <el-input v-model="queryParams.mark" class="search-item" placeholder="请输入角色标识" size="small" clearable></el-input>
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
              <!-- @click="handleQuery" -->
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <!-- @click="resetQuery" -->
              <el-button icon="el-icon-refresh" size="mini" @click="handleReset">重置</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <el-row :gutter="10" class="button-container">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" @click="handleOpen()" size="mini">
            新增
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single">编辑</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column prop="name" label="角色名称" align="center" v-if="columns[0].visible"></el-table-column>
        <el-table-column prop="mark" label="角色标识" align="center" v-if="columns[1].visible"></el-table-column>
        <el-table-column prop="description" label="角色描述" align="center" v-if="columns[2].visible" width="300"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[3].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[4].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope" v-if="scope.row.mark !== 'ROLE_ADMIN'">
            <!-- @click="handleClick(scope.row)" -->
            <el-button type="text" size="small" icon="el-icon-edit">编辑</el-button>
            <el-button type="text" size="small" icon="el-icon-delete">删除</el-button>
            <el-dropdown size="small" @command="(command) => handleCommand(command, scope.row)" style="margin-left: 10px;">
              <el-button size="small" type="text" icon="el-icon-d-arrow-right">更多</el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="handleDataScope" icon="el-icon-document">页面权限</el-dropdown-item>
                <el-dropdown-item command="handleAuthUser" icon="el-icon-folder-opened">资源权限</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
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

    <!-- 角色信息对话框 -->
    <el-dialog
      title="角色信息"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="roleVisible"
      :before-close="handleClose">
      <div class="role-wrapper" v-loading="roleLoading" element-loading-text="正在加载角色信息">
        <el-form ref="roleForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <el-form-item label="角色名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入角色名称" clearable>
              </el-input>
            </el-form-item>
            <el-form-item label="角色标识" prop="mark">
              <el-input v-model="formData.mark" placeholder="请输入角色标识" clearable>
                <template slot="prepend">ROLE_</template>
              </el-input>
            </el-form-item>
            <el-form-item label="角色描述">
              <el-input v-model="formData.description" type="textarea" placeholder="请输入角色描述" :autosize="{minRows: 4, maxRows: 4}"></el-input>
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
import { listRoles } from '../../../api/role'

export default {
  name: 'Role',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 角色信息对话框是否显示
      roleVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 角色名称
        name: '',
        // 角色标识
        mark: '',
        // 时间范围
        dataRange: []
      },
      // 角色表单对象
      formData: {
        // 角色ID
        id: undefined,
        // 角色名称
        name: undefined,
        // 角色标识
        mark: undefined,
        // 角色描述
        description: undefined
      },
      // 角色表单校验规则
      rules: {
        // 角色名称
        name: [{ required: true, message: '请输入角色名称', trigger: 'blur' }],
        // 角色标识
        mark: [{ required: true, message: '请输入角色标识', trigger: 'blur' }]
      },
      // 角色对话框等待框
      roleLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        { key: 0, label: '角色名称', visible: true },
        { key: 1, label: '角色标识', visible: true },
        { key: 2, label: '角色描述', visible: true },
        { key: 3, label: '创建时间', visible: true },
        { key: 4, label: '更新时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 多数据禁用
      single: true,
      // 单数据禁用
      multiple: true
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
      listRoles(this.queryParams).then((res) => {
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
      this.queryParams.name = ''
      this.queryParams.mark = ''
      this.queryParams.dataRange = []
    },
    // 打开角色信息对话框
    handleOpen () {
      this.roleVisible = true
    },
    // 关闭角色对话框表单
    handleClose () {
      this.resetForm()
      this.roleVisible = false
    },
    // 提交表单
    submitForm () {
      this.$refs.roleForm.validate(valid => {
        // if (!valid) return
        // TODO 提交表单
      })
    },
    // 更多操作触发
    handleCommand (command, row) {
      switch (command) {
        case 'handleDataScope':
          // this.handleDataScope(row);
          console.log('页面权限')
          break
        case 'handleAuthUser':
          console.log('资源权限')
          // this.handleAuthUser(row);
          break
        default: break
      }
    },
    // 重置表单
    resetForm () {
      this.$refs.roleForm.resetFields()
    }
  }
}

</script>
<style>
</style>

<style lang="scss" scoped>
.role-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
