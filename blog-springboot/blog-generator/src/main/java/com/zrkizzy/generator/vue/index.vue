<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            
            <el-form-item label="友情链接名称">
              <el-input v-model="queryParams.name" class="search-item" placeholder="请输入网站名称" size="small" clearable></el-input>
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
        <!-- 网站名称 -->
        <el-table-column prop="name" label="网站名称" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 网站域名 -->
        <el-table-column prop="website" label="网站域名" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 网站Logo -->
        <el-table-column prop="logo" label="网站Logo" align="center" v-if="columns[2].visible"></el-table-column>
        <!-- 网站介绍 -->
        <el-table-column prop="introduce" label="网站介绍" align="center" v-if="columns[3].visible"></el-table-column>
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

    <!-- 友情链接信息对话框 -->
    <el-dialog
      :title="linkTitle"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="linkVisible"
      :before-close="handleClose">
      <div class="link-wrapper" v-loading="linkLoading" element-loading-text="正在加载友情链接信息">
        <el-form ref="linkForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <!-- 网站名称 -->
            <el-form-item label="网站名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入网站名称" clearable></el-input>
            </el-form-item>
            <!-- 网站域名 -->
            <el-form-item label="网站域名" prop="website">
              <el-input v-model="formData.website" placeholder="请输入网站域名" clearable></el-input>
            </el-form-item>
            <!-- 网站Logo -->
            <el-form-item label="网站Logo" prop="logo">
              <el-input v-model="formData.logo" placeholder="请输入网站Logo" clearable></el-input>
            </el-form-item>
            <!-- 网站介绍 -->
            <el-form-item label="网站介绍" prop="introduce">
              <el-input v-model="formData.introduce" placeholder="请输入网站介绍" clearable></el-input>
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
import { listLinks, saveLink, getLinkById, deleteLink } from '../../../api/link'

export default {
  name: 'Link',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 友情链接对话框是否显示
      linkVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 网站名称
        name: undefined,
        // 时间范围
        dataRange: []
      },
      // 友情链接表单对象
      formData: {
        // 网站名称
        name: undefined,
        // 网站域名
        website: undefined,
        // 网站Logo
        logo: undefined,
        // 网站介绍
        introduce: undefined,
      },
      // 友情链接表单校验规则
      rules: {
        // 网站名称
        name: [{ required: true, message: '请输入网站名称', trigger: 'blur' }],
      },
      // 友情链接对话框等待框
      linkLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 网站名称
        { key: 0, label: '网站名称', visible: true },
        // 网站域名
        { key: 1, label: '网站域名', visible: true },
        // 网站Logo
        { key: 2, label: '网站Logo', visible: true },
        // 网站介绍
        { key: 3, label: '网站介绍', visible: true },
        { key: 4, label: '创建时间', visible: true },
        { key: 5, label: '更新时间', visible: true }
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
      linkTitle: ''
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
      listLinks(this.queryParams).then((res) => {
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
      // 网站名称
      this.queryParams.name = ''
      this.queryParams.dataRange = []
    },
    // 打开友情链接信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.linkVisible = true
    },
    // 关闭友情链接对话框表单
    handleClose () {
      this.resetForm()
      this.linkVisible = false
    },
    // 点击新增按钮
    handleAdd () {
      this.linkTitle = '新增友情链接'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.linkTitle = '更新友情链接'
      // 获取到传来的ID
      const linkId = row.id || this.ids
      // 打开加载框
      this.linkLoading = true
      // 根据友情链接ID获取对应的数据
      getLinkById(linkId).then((res) => {
        // 赋值当前的友情链接数据
        this.formData = res
        // 打开编辑友情链接对话框
        this.linkVisible = true
        // 关闭加载框
        this.linkLoading = false
      })
    },
    // 点击删除事件
    handleDelete (row) {
      let linkIds = []
      if (row.id) {
        linkIds.push(row.id)
      } else {
        linkIds = this.ids
      }
      console.log(linkIds)
      this.$confirm('是否确认删除选中的友情链接数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return deleteLink(linkIds)
      }).then(() => {
        this.getTableData()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.linkForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.linkLoading = true
        // 提交表单
        saveLink(that.formData).then((res) => {
          // 根据是否存在ID输出对应消息
          if (that.formData.id) {
            // 输出更新成功信息
            that.$message.success('友情链接信息更新成功')
          } else {
            // 输出添加成功消息
            that.$message.success('友情链接添加成功')
          }
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新友情链接数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.linkLoading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.linkLoading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 友情链接ID
        id: undefined,
        // 网站名称
        name: undefined
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
.link-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>