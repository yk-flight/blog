<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="请求模块">
              <el-select v-model="queryParams.moduleId" placeholder="请选择请求模块" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in moduleOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="请求类型">
              <el-select v-model="queryParams.type" placeholder="请选择请求类型" size="small" class="search-item" clearable>
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

            <el-form-item label="请求用户">
              <el-select v-model="queryParams.userId" placeholder="请选择请求用户" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in userOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="请求状态">
              <el-select v-model="queryParams.status" placeholder="请选择请求状态" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <!-- 请求时间 -->
            <el-form-item label="请求时间" size="small">
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
        <!-- 请求模块 -->
        <el-table-column prop="moduleName" label="请求模块" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 请求类型 -->
        <el-table-column prop="type" label="请求类型" align="center" v-if="columns[1].visible">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.type === 1">新增</el-tag>
            <el-tag type="primary" v-else-if="scope.row.type == 2">修改</el-tag>
            <el-tag type="danger" v-else-if="scope.row.type === 3">删除</el-tag>
            <el-tag type="warning" v-else-if="scope.row.type === 4">查询</el-tag>
            <el-tag type="info" v-else>其他请求</el-tag>
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
        <!-- 请求用户 -->
        <el-table-column prop="nickname" label="请求用户" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 请求IP -->
        <el-table-column prop="operateIp" label="请求IP" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 请求地址 -->
        <el-table-column prop="operateLocation" label="请求地址" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 请求状态 -->
        <el-table-column prop="status" label="请求状态" align="center" v-if="columns[6].visible">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.status">成功</el-tag>
            <el-tag type="danger" v-else>失败</el-tag>
          </template>
        </el-table-column>
        <!-- 请求消耗时间 -->
        <el-table-column prop="costTime" label="请求耗时" align="center" v-if="columns[7].visible" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.costTime }}ms</span>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="请求时间" align="center" v-if="columns[8].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="请求"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-view" @click="handleView(scope.row)">详细</el-button>
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

      <!-- 请求详情对话框 -->
      <el-dialog
        title="请求详情"
        :visible="operateLogVisible"
        :before-close="handleClose"
        :close-on-click-modal="false"
        width="700px">

        <div class="dialog-content">
          <el-form size="mini" :model="formData" label-width="100px" label-position="left">
            <el-row>
              <el-col :span="12">
                <el-form-item label="日志编号：">{{ formData.id }}</el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请求用户：">{{ formData.nickname }}</el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请求模块：">{{ formData.moduleName }}</el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请求地址：">{{ formData.operateIp }} / {{ formData.operateLocation }}</el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="请求方法：">{{ formData.methodName }}</el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="请求参数：">{{ formData.operateParam }}</el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="返回参数：">{{ formData.operateResult }}</el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请求时间：">
                  {{ formData.createTime | dateFilter }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="请求耗时：">
                  {{ formData.costTime }}ms
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="请求结果：">
                  <el-tag type="success" v-if="formData.status">成功</el-tag>
                  <el-tag type="danger" v-else>失败</el-tag>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="请求方式：">
                  <el-tag type="primary" v-if="formData.requestMethod === 'POST'">POST</el-tag>
                  <el-tag type="success" v-else-if="formData.requestMethod === 'GET'">GET</el-tag>
                  <el-tag type="warning" v-else-if="formData.requestMethod === 'PUT'">PUT</el-tag>
                  <el-tag type="danger" v-else>DELETE</el-tag>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="请求类型：">
                  <el-tag type="success" v-if="formData.type === 1">新增</el-tag>
                  <el-tag type="primary" v-else-if="formData.type == 2">修改</el-tag>
                  <el-tag type="danger" v-else-if="formData.type === 3">删除</el-tag>
                  <el-tag type="warning" v-else-if="formData.type === 4">查询</el-tag>
                  <el-tag type="info" v-else>其他请求</el-tag>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <div slot="footer">
          <el-button @click="handleClose">关 闭</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import Pagination from '../../../components/Pagination/index.vue'
import RightToolbar from '../../../components/RightToolbar/index.vue'
import { listOperateLogs, deleteOperateLog, clearOperateLogs } from '../../../api/operateLog'
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
      // 请求日志对话框是否显示
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
        // 请求类型
        type: undefined,
        // 请求方式
        requestMethod: undefined,
        // 用户ID
        userId: undefined,
        // 请求状态
        status: undefined,
        // 时间范围
        dataRange: []
      },
      // 详细参数
      formData: {},
      // 请求日志对话框等待框
      operateLogLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 模块ID
        { key: 0, label: '请求模块', visible: true },
        // 请求类型
        { key: 1, label: '请求类型', visible: true },
        // 请求方式
        { key: 2, label: '请求方式', visible: true },
        // 用户ID
        { key: 3, label: '请求用户', visible: true },
        // 请求IP
        { key: 4, label: '请求IP', visible: true },
        // 请求地址
        { key: 5, label: '请求地址', visible: true },
        // 请求状态
        { key: 6, label: '请求状态', visible: true },
        // 请求消耗时间
        { key: 7, label: '请求耗时', visible: true },
        { key: 8, label: '请求时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 表头选中的数据
      ids: [],
      // 多数据禁用
      multiple: true,
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
      // 请求方式选项
      typeOptions: [
        {
          value: 0,
          label: '其他请求'
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
      // 清除当前模块选中的数据
      this.ids = []
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
    // 点击清空按钮
    handleClear () {
      this.$confirm('此操作将永久删除所有操作日志数据项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 执行清空操作
        clearOperateLogs().then((res) => {
          this.$message({ type: 'success', message: '清空成功' })
          // 刷新表格
          this.getTableData()
        })
      }).catch(() => {})
    },
    // 点击重置按钮
    handleReset () {
      // 模块ID
      this.queryParams.moduleId = ''
      // 请求类型
      this.queryParams.type = ''
      // 请求方式
      this.queryParams.requestMethod = ''
      // 用户ID
      this.queryParams.userId = ''
      // 请求状态
      this.queryParams.status = ''
      // 时间范围
      this.queryParams.dataRange = []
    },
    // 打开请求日志信息对话框
    handleView (row) {
      this.formData = row
      this.operateLogVisible = true
    },
    // 关闭请求日志对话框表单
    handleClose () {
      this.operateLogVisible = false
    },
    // 点击删除事件
    handleDelete () {
      const operateLogIds = this.ids
      this.$confirm('是否确认删除选中的请求日志数据？', '提示', {
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
.operateLog-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
.dialog-content {
  position: relative;
  width: calc(100% - 60px);
  height: 100%;
  margin: 30px 30px;
}
</style>
