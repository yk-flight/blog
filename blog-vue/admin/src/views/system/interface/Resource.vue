<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="资源名称">
              <el-input v-model="queryParams.name" class="search-item" placeholder="请输入资源名称" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="请求方式">
              <el-select v-model="queryParams.method" placeholder="请选择请求类型" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in requestOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="请求路径">
              <el-input v-model="queryParams.url" class="search-item" placeholder="请求路径" size="small" clearable></el-input>
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
          <el-button type="warning" icon="el-icon-download" size="mini">导出接口</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column label="序号" type="index" align="center" />
        <!-- 资源名称 -->
        <el-table-column prop="name" label="资源名称" align="center" v-if="columns[0].visible" show-overflow-tooltip></el-table-column>
        <!-- 资源描述 -->
        <el-table-column prop="description" label="资源描述" align="center" v-if="columns[1].visible" show-overflow-tooltip></el-table-column>
        <!-- 资源请求方式 -->
        <el-table-column prop="method" label="请求方式" align="center" v-if="columns[2].visible">
          <template slot-scope="scope">
            <el-tag type="primary" v-if="scope.row.method === 'POST'">POST</el-tag>
            <el-tag type="success" v-else-if="scope.row.method === 'GET'">GET</el-tag>
            <el-tag type="warning" v-else-if="scope.row.method === 'PUT'">PUT</el-tag>
            <el-tag type="danger" v-else>DELETE</el-tag>
          </template>
        </el-table-column>
        <!-- 资源请求路径 -->
        <el-table-column prop="url" label="请求路径" align="center" v-if="columns[3].visible"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[4].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[5].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
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

    <!-- 资源信息对话框 -->
    <el-dialog
      title="编辑资源"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="resourceVisible"
      :before-close="handleClose">
      <div class="resource-wrapper" v-loading="resourceLoading" element-loading-text="正在加载资源信息">
        <el-form ref="resourceForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <!-- 资源名称 -->
            <el-form-item label="资源名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入资源名称" clearable></el-input>
            </el-form-item>
            <!-- 资源描述 -->
            <el-form-item label="资源描述" prop="description">
              <el-input type="textarea" v-model="formData.description" placeholder="请输入资源描述" clearable></el-input>
            </el-form-item>
            <!-- 资源请求方式 -->
            <el-form-item label="请求方式" prop="method">
              <el-input v-model="formData.method" disabled></el-input>
            </el-form-item>
            <!-- 资源请求路径 -->
            <el-form-item label="请求路径" prop="url">
              <el-input v-model="formData.url" disabled></el-input>
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
import { listResources, saveResource, getResourceById } from '../../../api/resource'

export default {
  name: 'Resource',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 资源对话框是否显示
      resourceVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 资源名称
        name: undefined,
        // 资源请求方式
        method: undefined,
        // 资源请求路径
        url: undefined,
        // 时间范围
        dataRange: []
      },
      // 资源表单对象
      formData: {
        // 资源名称
        name: undefined,
        // 资源描述
        description: undefined,
        // 资源请求方式
        method: undefined,
        // 资源请求路径
        url: undefined
      },
      // 资源表单校验规则
      rules: {
        // 资源名称
        name: [{ required: true, message: '请输入资源名称', trigger: 'blur' }],
        // 资源描述
        description: [{ required: true, message: '请输入资源描述', trigger: 'blur' }]
      },
      // 资源对话框等待框
      resourceLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 资源名称
        { key: 0, label: '资源名称', visible: true },
        // 资源描述
        { key: 1, label: '资源描述', visible: true },
        // 资源请求方式
        { key: 2, label: '请求方式', visible: true },
        // 资源请求路径
        { key: 3, label: '请求路径', visible: true },
        { key: 4, label: '创建时间', visible: true },
        { key: 5, label: '更新时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 请求类型选项
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
      // 开启加载框
      this.loading = true
      listResources(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res.list
        this.total = res.total
        // 关闭等待框
        this.loading = false
      }).catch(() => {
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
      // 资源名称
      this.queryParams.name = ''
      // 资源请求方式
      this.queryParams.method = ''
      // 资源请求路径
      this.queryParams.url = ''
      this.queryParams.dataRange = []
    },
    // 关闭资源对话框表单
    handleClose () {
      this.resetForm()
      this.resourceVisible = false
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 清除表单数据
      this.resetForm()
      // 获取到传来的ID
      const resourceId = row.id
      // 打开加载框
      this.resourceLoading = true
      // 打开编辑资源对话框
      this.resourceVisible = true
      // 根据资源ID获取对应的数据
      getResourceById(resourceId).then((res) => {
        // 赋值当前的资源数据
        this.formData = res
        // 关闭加载框
        this.resourceLoading = false
      }).catch(() => {
        // 打开编辑资源对话框
        this.resourceVisible = true
        // 关闭加载框
        this.resourceLoading = false
      })
    },
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.resourceForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.resourceLoading = true
        // 提交表单
        saveResource(that.formData).then((res) => {
          // 输出更新成功信息
          that.$message.success('资源信息更新成功')
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新资源数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.resourceLoading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.resourceLoading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 资源ID
        id: undefined,
        // 资源名称
        name: undefined,
        // 资源请求方式
        method: undefined,
        // 资源请求路径
        url: undefined
      }
    }
  }
}

</script>

  <style lang="scss" scoped>
  .resource-wrapper {
    padding: 10px 30px;
  }
  .search-item {
    width: 240px;
  }
  </style>
