<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
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
          <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" :loading="buttonLoading">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" element-loading-text="正在加载表格数据" :data="tableData" @selection-change="handleSelectionChange" border>
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <el-table-column type="selection" width="50" align="center" />
        <!-- 菜单名称 -->
        <el-table-column prop="name" label="菜单名称" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 父菜单 -->
        <el-table-column prop="parentId" label="父菜单" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 访问路径 -->
        <el-table-column prop="path" label="访问路径" align="center" v-if="columns[2].visible"></el-table-column>
        <!-- 组件 -->
        <el-table-column prop="component" label="组件" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 是否缓存 （0: 不缓存 1: 缓存） -->
        <el-table-column prop="isCache" label="是否缓存 （0: 不缓存 1: 缓存）" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 是否外链 （0: 不是外链 1: 外链） -->
        <el-table-column prop="isLink" label="是否外链 （0: 不是外链 1: 外链）" align="center" v-if="columns[5].visible"></el-table-column>
        <!-- 是否隐藏 （0: 不隐藏 1: 隐藏） -->
        <el-table-column prop="visible" label="是否隐藏 （0: 不隐藏 1: 隐藏）" align="center" v-if="columns[6].visible"></el-table-column>
        <!-- 图标 -->
        <el-table-column prop="icon" label="图标" align="center" v-if="columns[7].visible"></el-table-column>
        <!-- 菜单顺序 -->
        <el-table-column prop="order" label="菜单顺序" align="center" v-if="columns[8].visible"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[9].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[10].visible">
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

    <!-- 菜单信息对话框 -->
    <el-dialog
      :title="menuTitle"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="menuVisible"
      :before-close="handleClose">
      <div class="menu-wrapper" v-loading="menuLoading" element-loading-text="正在加载菜单信息">
        <el-form ref="menuForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <!-- 菜单名称 -->
            <el-form-item label="菜单名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入菜单名称" clearable></el-input>
            </el-form-item>
            <!-- 父菜单 -->
            <el-form-item label="父菜单" prop="parentId">
              <el-input v-model="formData.parentId" placeholder="请输入父菜单" clearable></el-input>
            </el-form-item>
            <!-- 访问路径 -->
            <el-form-item label="访问路径" prop="path">
              <el-input v-model="formData.path" placeholder="请输入访问路径" clearable></el-input>
            </el-form-item>
            <!-- 组件 -->
            <el-form-item label="组件" prop="component">
              <el-input v-model="formData.component" placeholder="请输入组件" clearable></el-input>
            </el-form-item>
            <!-- 是否缓存 （0: 不缓存 1: 缓存） -->
            <el-form-item label="是否缓存 （0: 不缓存 1: 缓存）" prop="isCache">
              <el-input v-model="formData.isCache" placeholder="请输入是否缓存 （0: 不缓存 1: 缓存）" clearable></el-input>
            </el-form-item>
            <!-- 是否外链 （0: 不是外链 1: 外链） -->
            <el-form-item label="是否外链 （0: 不是外链 1: 外链）" prop="isLink">
              <el-input v-model="formData.isLink" placeholder="请输入是否外链 （0: 不是外链 1: 外链）" clearable></el-input>
            </el-form-item>
            <!-- 是否隐藏 （0: 不隐藏 1: 隐藏） -->
            <el-form-item label="是否隐藏 （0: 不隐藏 1: 隐藏）" prop="visible">
              <el-input v-model="formData.visible" placeholder="请输入是否隐藏 （0: 不隐藏 1: 隐藏）" clearable></el-input>
            </el-form-item>
            <!-- 图标 -->
            <el-form-item label="图标" prop="icon">
              <el-input v-model="formData.icon" placeholder="请输入图标" clearable></el-input>
            </el-form-item>
            <!-- 菜单顺序 -->
            <el-form-item label="菜单顺序" prop="order">
              <el-input v-model="formData.order" placeholder="请输入菜单顺序" clearable></el-input>
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
import { listMenus, saveMenu, getMenuById, deleteMenu } from '../../../api/menu'

export default {
  name: 'Menu',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 菜单对话框是否显示
      menuVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 时间范围
        dataRange: []
      },
      // 菜单表单对象
      formData: {
        // 菜单名称
        name: undefined,
        // 父菜单
        parentId: undefined,
        // 访问路径
        path: undefined,
        // 组件
        component: undefined,
        // 是否缓存 （0: 不缓存 1: 缓存）
        isCache: undefined,
        // 是否外链 （0: 不是外链 1: 外链）
        isLink: undefined,
        // 是否隐藏 （0: 不隐藏 1: 隐藏）
        visible: undefined,
        // 图标
        icon: undefined,
        // 菜单顺序
        order: undefined,
      },
      // 菜单表单校验规则
      rules: {
      },
      // 菜单对话框等待框
      menuLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 菜单名称
        { key: 0, label: '菜单名称', visible: true },
        // 父菜单
        { key: 1, label: '父菜单', visible: true },
        // 访问路径
        { key: 2, label: '访问路径', visible: true },
        // 组件
        { key: 3, label: '组件', visible: true },
        // 是否缓存 （0: 不缓存 1: 缓存）
        { key: 4, label: '是否缓存 （0: 不缓存 1: 缓存）', visible: true },
        // 是否外链 （0: 不是外链 1: 外链）
        { key: 5, label: '是否外链 （0: 不是外链 1: 外链）', visible: true },
        // 是否隐藏 （0: 不隐藏 1: 隐藏）
        { key: 6, label: '是否隐藏 （0: 不隐藏 1: 隐藏）', visible: true },
        // 图标
        { key: 7, label: '图标', visible: true },
        // 菜单顺序
        { key: 8, label: '菜单顺序', visible: true },
        { key: 9, label: '创建时间', visible: true },
        { key: 10, label: '更新时间', visible: true }
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
      menuTitle: ''
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
      listMenus(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res.list
        this.total = res.total
        // 关闭等待框
        this.loading = false
      }).catch(() => {
        // 确保加载框可以关闭
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
      this.queryParams.dataRange = []
    },
    // 打开菜单信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.menuVisible = true
    },
    // 关闭菜单对话框表单
    handleClose () {
      this.resetForm()
      this.menuVisible = false
    },
    // 点击新增按钮
    handleAdd () {
      this.menuTitle = '新增菜单'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.menuTitle = '更新菜单'
      // 获取到传来的ID
      const menuId = row.id || this.ids
      // 打开加载框
      this.menuLoading = true
      // 根据菜单ID获取对应的数据
      getMenuById(menuId).then((res) => {
        // 赋值当前的菜单数据
        this.formData = res
        // 打开编辑菜单对话框
        this.menuVisible = true
        // 关闭加载框
        this.menuLoading = false
      })
    },
    // 点击删除事件
    handleDelete (row) {
      const that = this
      let menuIds = []
      if (row.id) {
        menuIds.push(row.id)
      } else {
        menuIds = this.ids
      }
      this.$confirm('是否确认删除选中的菜单数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        that.buttonLoading = true
        that.loading = true
        deleteMenu(menuIds).then((res) => {
          that.buttonLoading = false
          that.$message.success('删除成功')
          // 刷新数据
          that.getTableData()
        }).catch(() => {
          // 确保加载框会关闭
          that.buttonLoading = false
          that.loading = false
        })
      }).catch(() => {})
    },
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.menuForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.menuLoading = true
        // 提交表单
        saveMenu(that.formData).then((res) => {
          // 根据是否存在ID输出对应消息
          if (that.formData.id) {
            // 输出更新成功信息
            that.$message.success('菜单信息更新成功')
          } else {
            // 输出添加成功消息
            that.$message.success('菜单添加成功')
          }
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新菜单数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.menuLoading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.menuLoading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 菜单ID
        id: undefined,
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
.menu-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
