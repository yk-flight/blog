<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="操作模块">
              <el-select v-model="queryParams.moduleId" placeholder="请选择操作模块" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in moduleOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="操作类型">
              <el-select v-model="queryParams.type" placeholder="请选择操作类型" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in typeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="请求方式">
              <el-select v-model="queryParams.requestMethod" placeholder="请选择请求方式" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in requestOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="操作用户">
              <el-select v-model="queryParams.userId" placeholder="请选择操作用户" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in userOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="操作状态">
              <el-select v-model="queryParams.status" placeholder="请选择操作状态" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <!-- 操作时间 -->
            <el-form-item label="操作时间" size="small">
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
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" @selection-change="handleSelectionChange" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="selection" width="50" align="center" />
        <!-- 操作模块 -->
        <el-table-column prop="moduleName" label="操作模块" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 操作类型 -->
        <el-table-column prop="type" label="操作类型" align="center" v-if="columns[1].visible">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.type === 1">新增</el-tag>
            <el-tag type="primary" v-else-if="scope.row.type == 2">修改</el-tag>
            <el-tag type="danger" v-else-if="scope.row.type === 3">删除</el-tag>
            <el-tag type="warning" v-else-if="scope.row.type === 4">查询</el-tag>
            <el-tag type="info" v-else>其他操作</el-tag>
          </template>
        </el-table-column>
        <!-- 请求方式 -->
        <el-table-column prop="requestMethod" label="请求方式" align="center" v-if="columns[2].visible">
          <template slot-scope="scope">
            <el-tag type="primary" v-if="scope.row.requestMethod === 'POST'">POST</el-tag>
            <el-tag type="success" v-else-if="scope.row.requestMethod === 'GET'">GET</el-tag>
            <el-tag type="warning" v-else-if="scope.row.requestMethod === 'PUT'">PUT</el-tag>
            <el-tag type="danger" v-else>DELETE</el-tag>
          </template>
        </el-table-column>
        <!-- 操作用户 -->
        <el-table-column prop="nickname" label="操作用户" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 操作IP -->
        <el-table-column prop="operateIp" label="操作IP" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 操作地址 -->
        <el-table-column prop="operateLocation" label="操作地址" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 操作状态 -->
        <el-table-column prop="status" label="操作状态" align="center" v-if="columns[6].visible">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status">成功</el-tag>
            <el-tag type="danger" v-else>失败</el-tag>
          </template>
        </el-table-column>
        <!-- 操作消耗时间 -->
        <el-table-column prop="costTime" label="操作耗时" align="center" v-if="columns[7].visible"></el-table-column>
        <el-table-column prop="createTime" label="操作时间" align="center" v-if="columns[8].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-view" @click="handleView(scope.row)">查看</el-button>
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
  </div>
</template>
<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import Pagination from '../../../components/Pagination/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'
import { listOperateLogs, deleteOperateLog } from '../../../api/operateLog'
import { listModuleOptions } from '../../../api/module'
import { listUserOptions } from '../../../api/user'

export default {
  name: 'OperateLog',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 操作日志对话框是否显示
      operateLogVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 模块ID
        moduleId: undefined,
        // 操作类型
        type: undefined,
        // 请求方式
        requestMethod: undefined,
        // 用户ID
        userId: undefined,
        // 操作状态
        status: undefined,
        // 时间范围
        dataRange: []
      },
      // 操作日志对话框等待框
      operateLogLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 模块ID
        { key: 0, label: '操作模块', visible: true },
        // 操作类型
        { key: 1, label: '操作类型', visible: true },
        // 请求方式
        { key: 2, label: '请求方式', visible: true },
        // 用户ID
        { key: 3, label: '操作用户', visible: true },
        // 操作IP
        { key: 4, label: '操作IP', visible: true },
        // 操作地址
        { key: 5, label: '操作地址', visible: true },
        // 操作状态
        { key: 6, label: '操作状态', visible: true },
        // 操作消耗时间
        { key: 7, label: '操作耗时', visible: true },
        { key: 8, label: '操作时间', visible: true }
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
      operateLogTitle: '',
      // 操作状态选项
      statusOptions: [
        {
          value: 1,
          label: '成功'
        },
        {
          value: 0,
          label: '失败'
        }
      ],
      // 请求方式选项
      requestOptions: [
        {
          value: 'POST',
          label: 'POST'
        },
        {
          value: 'GET',
          label: 'GET'
        },
        {
          value: 'PUT',
          label: 'PUT'
        },
        {
          value: 'DELETE',
          label: 'DELETE'
        }
      ],
      // 操作方式选项
      typeOptions: [
        {
          value: 0,
          label: '其他操作'
        },
        {
          value: 1,
          label: '新增'
        },
        {
          value: 2,
          label: '修改'
        },
        {
          value: 3,
          label: '删除'
        },
        {
          value: 4,
          label: '查询'
        }
      ],
      // 模块选项数据
      moduleOptions: [],
      // 用户选项数据
      userOptions: []
    }
  },

  computed: {},

  watch: {},

  created () {},

  mounted () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 查询模块选项
    this.listOptions()
    // 查询表格数据
    this.getTableData()
  },

  methods: {
    // 获取表格数据
    getTableData () {
      // 开启加载框
      this.loading = true
      listOperateLogs(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res.list
        this.total = res.total
        // 关闭等待框
        this.loading = false
      })
    },
    // 查询模块选项数据
    listOptions () {
      // 调用后端接口
      listModuleOptions().then((res) => {
        this.moduleOptions = res
      })
      listUserOptions().then((res) => {
        this.userOptions = res
      })
    },
    // 点击查询按钮
    handleQuery () {
      this.getTableData()
    },
    // 点击重置按钮
    handleReset () {
      // 模块ID
      this.queryParams.moduleId = ''
      // 操作类型
      this.queryParams.type = ''
      // 请求方式
      this.queryParams.requestMethod = ''
      // 用户ID
      this.queryParams.userId = ''
      // 操作状态
      this.queryParams.status = ''
      // 时间范围
      this.queryParams.dataRange = []
    },
    // 打开操作日志信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.operateLogVisible = true
    },
    // 关闭操作日志对话框表单
    handleClose () {
      this.resetForm()
      this.operateLogVisible = false
    },
    // 点击删除事件
    handleDelete (row) {
      let operateLogIds = []
      if (row.id) {
        operateLogIds.push(row.id)
      } else {
        operateLogIds = this.ids
      }
      this.$confirm('是否确认删除选中的操作日志数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return deleteOperateLog(operateLogIds)
      }).then(() => {
        this.getTableData()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 操作日志ID
        id: undefined,
        // 模块ID
        moduleId: undefined,
        // 操作类型
        type: undefined,
        // 请求方式
        requestMethod: undefined,
        // 用户ID
        userId: undefined,
        // 操作状态
        status: undefined
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
.operateLog-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
