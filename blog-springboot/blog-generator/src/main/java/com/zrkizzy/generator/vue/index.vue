<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            
            <el-form-item label="模块ID">
              <el-input v-model="queryParams.moduleId" class="search-item" placeholder="请输入模块ID" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="操作类型">
              <el-input v-model="queryParams.type" class="search-item" placeholder="请输入操作类型" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="请求方式">
              <el-input v-model="queryParams.requestMethod" class="search-item" placeholder="请输入请求方式" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="操作用户ID">
              <el-input v-model="queryParams.userId" class="search-item" placeholder="请输入操作用户ID" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="操作状态">
              <el-input v-model="queryParams.status" class="search-item" placeholder="请输入操作状态" size="small" clearable></el-input>
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
        <!-- 模块ID -->
        <el-table-column prop="moduleId" label="模块ID" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 操作类型 -->
        <el-table-column prop="type" label="操作类型" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 操作方法名称 -->
        <el-table-column prop="methodName" label="操作方法名称" align="center" v-if="columns[2].visible"></el-table-column>
        <!-- 请求方式 -->
        <el-table-column prop="requestMethod" label="请求方式" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 操作用户ID -->
        <el-table-column prop="userId" label="操作用户ID" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 操作IP -->
        <el-table-column prop="operateIp" label="操作IP" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 操作地址 -->
        <el-table-column prop="operateLocation" label="操作地址" align="center" v-if="columns[6].visible"></el-table-column>
        <!-- 操作参数 -->
        <el-table-column prop="operateParam" label="操作参数" align="center" v-if="columns[7].visible"></el-table-column>
        <!-- 操作结果描述 -->
        <el-table-column prop="operateResult" label="操作结果描述" align="center" v-if="columns[8].visible"></el-table-column>
        <!-- 操作状态 -->
        <el-table-column prop="status" label="操作状态" align="center" v-if="columns[9].visible"></el-table-column>
        <!-- 操作消耗时间 -->
        <el-table-column prop="costTime" label="操作消耗时间" align="center" v-if="columns[10].visible"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[11].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[12].visible">
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

    <!-- 操作日志信息对话框 -->
    <el-dialog
      :title="operateLogTitle"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="operateLogVisible"
      :before-close="handleClose">
      <div class="operateLog-wrapper" v-loading="operateLogLoading" element-loading-text="正在加载操作日志信息">
        <el-form ref="operateLogForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <!-- 模块ID -->
            <el-form-item label="模块ID" prop="moduleId">
              <el-input v-model="formData.moduleId" placeholder="请输入模块ID" clearable></el-input>
            </el-form-item>
            <!-- 操作类型 -->
            <el-form-item label="操作类型" prop="type">
              <el-input v-model="formData.type" placeholder="请输入操作类型" clearable></el-input>
            </el-form-item>
            <!-- 操作方法名称 -->
            <el-form-item label="操作方法名称" prop="methodName">
              <el-input v-model="formData.methodName" placeholder="请输入操作方法名称" clearable></el-input>
            </el-form-item>
            <!-- 请求方式 -->
            <el-form-item label="请求方式" prop="requestMethod">
              <el-input v-model="formData.requestMethod" placeholder="请输入请求方式" clearable></el-input>
            </el-form-item>
            <!-- 操作用户ID -->
            <el-form-item label="操作用户ID" prop="userId">
              <el-input v-model="formData.userId" placeholder="请输入操作用户ID" clearable></el-input>
            </el-form-item>
            <!-- 操作IP -->
            <el-form-item label="操作IP" prop="operateIp">
              <el-input v-model="formData.operateIp" placeholder="请输入操作IP" clearable></el-input>
            </el-form-item>
            <!-- 操作地址 -->
            <el-form-item label="操作地址" prop="operateLocation">
              <el-input v-model="formData.operateLocation" placeholder="请输入操作地址" clearable></el-input>
            </el-form-item>
            <!-- 操作参数 -->
            <el-form-item label="操作参数" prop="operateParam">
              <el-input v-model="formData.operateParam" placeholder="请输入操作参数" clearable></el-input>
            </el-form-item>
            <!-- 操作结果描述 -->
            <el-form-item label="操作结果描述" prop="operateResult">
              <el-input v-model="formData.operateResult" placeholder="请输入操作结果描述" clearable></el-input>
            </el-form-item>
            <!-- 操作状态 -->
            <el-form-item label="操作状态" prop="status">
              <el-input v-model="formData.status" placeholder="请输入操作状态" clearable></el-input>
            </el-form-item>
            <!-- 操作消耗时间 -->
            <el-form-item label="操作消耗时间" prop="costTime">
              <el-input v-model="formData.costTime" placeholder="请输入操作消耗时间" clearable></el-input>
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
import { listOperateLogs, saveOperateLog, getOperateLogById, deleteOperateLog } from '../../../api/operateLog'

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
        // 操作用户ID
        userId: undefined,
        // 操作状态
        status: undefined,
        // 时间范围
        dataRange: []
      },
      // 操作日志表单对象
      formData: {
        // 模块ID
        moduleId: undefined,
        // 操作类型
        type: undefined,
        // 操作方法名称
        methodName: undefined,
        // 请求方式
        requestMethod: undefined,
        // 操作用户ID
        userId: undefined,
        // 操作IP
        operateIp: undefined,
        // 操作地址
        operateLocation: undefined,
        // 操作参数
        operateParam: undefined,
        // 操作结果描述
        operateResult: undefined,
        // 操作状态
        status: undefined,
        // 操作消耗时间
        costTime: undefined,
      },
      // 操作日志表单校验规则
      rules: {
        // 模块ID
        moduleId: [{ required: true, message: '请输入模块ID', trigger: 'blur' }],
        // 操作类型
        type: [{ required: true, message: '请输入操作类型', trigger: 'blur' }],
        // 请求方式
        requestMethod: [{ required: true, message: '请输入请求方式', trigger: 'blur' }],
        // 操作用户ID
        userId: [{ required: true, message: '请输入操作用户ID', trigger: 'blur' }],
        // 操作状态
        status: [{ required: true, message: '请输入操作状态', trigger: 'blur' }],
      },
      // 操作日志对话框等待框
      operateLogLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 模块ID
        { key: 0, label: '模块ID', visible: true },
        // 操作类型
        { key: 1, label: '操作类型', visible: true },
        // 操作方法名称
        { key: 2, label: '操作方法名称', visible: true },
        // 请求方式
        { key: 3, label: '请求方式', visible: true },
        // 操作用户ID
        { key: 4, label: '操作用户ID', visible: true },
        // 操作IP
        { key: 5, label: '操作IP', visible: true },
        // 操作地址
        { key: 6, label: '操作地址', visible: true },
        // 操作参数
        { key: 7, label: '操作参数', visible: true },
        // 操作结果描述
        { key: 8, label: '操作结果描述', visible: true },
        // 操作状态
        { key: 9, label: '操作状态', visible: true },
        // 操作消耗时间
        { key: 10, label: '操作消耗时间', visible: true },
        { key: 11, label: '创建时间', visible: true },
        { key: 12, label: '更新时间', visible: true }
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
      operateLogTitle: ''
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
      listOperateLogs(this.queryParams).then((res) => {
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
      // 模块ID
      this.queryParams.moduleId = ''
      // 操作类型
      this.queryParams.type = ''
      // 请求方式
      this.queryParams.requestMethod = ''
      // 操作用户ID
      this.queryParams.userId = ''
      // 操作状态
      this.queryParams.status = ''
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
    // 点击新增按钮
    handleAdd () {
      this.operateLogTitle = '新增操作日志'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.operateLogTitle = '更新操作日志'
      // 获取到传来的ID
      const operateLogId = row.id || this.ids
      // 打开加载框
      this.operateLogLoading = true
      // 根据操作日志ID获取对应的数据
      getOperateLogById(operateLogId).then((res) => {
        // 赋值当前的操作日志数据
        this.formData = res
        // 打开编辑操作日志对话框
        this.operateLogVisible = true
        // 关闭加载框
        this.operateLogLoading = false
      })
    },
    // 点击删除事件
    handleDelete (row) {
      let operateLogIds = []
      if (row.id) {
        operateLogIds.push(row.id)
      } else {
        operateLogIds = this.ids
      }
      console.log(operateLogIds)
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
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.operateLogForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.operateLogLoading = true
        // 提交表单
        saveOperateLog(that.formData).then((res) => {
          // 根据是否存在ID输出对应消息
          if (that.formData.id) {
            // 输出更新成功信息
            that.$message.success('操作日志信息更新成功')
          } else {
            // 输出添加成功消息
            that.$message.success('操作日志添加成功')
          }
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新操作日志数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.operateLogLoading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.operateLogLoading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 操作日志ID
        id: undefined,
        // 模块ID
        moduleId: undefined
        // 操作类型
        type: undefined
        // 请求方式
        requestMethod: undefined
        // 操作用户ID
        userId: undefined
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
