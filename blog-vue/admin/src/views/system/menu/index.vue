<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="菜单名称">
              <el-input v-model="queryParams.name" class="search-item" placeholder="请输入菜单名称名称" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="菜单状态">
              <el-select v-model="queryParams.status" placeholder="请选择菜单状态" size="small" class="search-item" clearable>
                <el-option
                  v-for="item in statusOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
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
          <el-button type="info" icon="el-icon-sort" size="mini" @click="toggleExpandAll">
            展开/折叠
          </el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" :columns="columns" @getTableData="getTableData"></right-toolbar>
      </el-row>

      <el-table
        v-if="refreshTable"
        :default-expand-all="isExpandAll"
        v-loading="loading"
        element-loading-text="正在加载表格数据"
        :data="tableData"
        row-key="id"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
        <template slot="empty">
          <el-empty :image-size="200"></el-empty>
        </template>
        <!-- 菜单名称 -->
        <el-table-column prop="name" label="菜单名称" align="center" width="150" v-if="columns[0].visible"></el-table-column>
        <!-- 图标 -->
        <el-table-column prop="icon" label="图标" align="center" width="80" v-if="columns[1].visible">
          <template slot-scope="scope">
            <svg-icon :icon="scope.row.icon"></svg-icon>
          </template>
        </el-table-column>
        <!-- 排序 -->
        <el-table-column prop="order" label="排序" align="center" width="80" v-if="columns[2].visible"></el-table-column>
        <!-- 访问路径 -->
        <el-table-column prop="path" label="访问路径" align="center" v-if="columns[3].visible"></el-table-column>
        <!-- 组件路径 -->
        <el-table-column prop="component" label="组件路径" align="center" v-if="columns[4].visible"></el-table-column>
        <!-- 是否缓存 -->
        <el-table-column prop="status" label="状态" align="center" v-if="columns[5].visible">
          <template slot-scope="scope">
            <el-tag type="primary" v-if="scope.row.status">正常</el-tag>
            <el-tag type="danger" v-else>禁用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[6].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[7].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" icon="el-icon-plus" @click="handleAdd(scope.row)" v-if="scope.row.parentId === '0'">新增</el-button>
            <el-button type="text" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button type="text" size="small" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 菜单信息对话框 -->
    <el-dialog
      :title="menuTitle"
      width="680px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="menuVisible"
      :before-close="handleClose">
      <div class="menu-wrapper" v-loading="menuLoading" element-loading-text="正在加载菜单信息">
        <el-form ref="menuForm" :model="formData" :rules="rules" label-width="100px" label-position="right">
          <el-row :gutter="15">
            <el-col :span="24">
              <el-form-item label="上级菜单" prop="parentId">
                <!-- <treeselect
                  v-model="formData.parentId"
                  :options="menuOptions"
                  :normalizer="normalizer"
                  :show-count="true"
                  placeholder="选择上级菜单"
                /> -->
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="菜单类型" prop="type">
                <el-radio-group v-model="formData.type">
                  <el-radio label="D">目录</el-radio>
                  <el-radio label="P">菜单</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="formData.type != 'F'">
              <el-form-item label="菜单图标" prop="icon">
                <el-popover
                  placement="bottom-start"
                  width="460"
                  trigger="click"
                  @show="$refs['iconSelect'].reset()"
                >
                  <IconSelect ref="iconSelect" @selectIcon="selectIcon" :active-icon="formData.icon" />
                  <el-input slot="reference" v-model="formData.icon" size="small" placeholder="点击选择图标" readonly>
                    <svg-icon
                      v-if="formData.icon"
                      slot="prefix"
                      :icon="formData.icon"
                      style="width: 25px;"
                    />
                    <i v-else slot="prefix" class="el-icon-search el-input__icon"></i>
                  </el-input>
                </el-popover>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="菜单名称" prop="name">
                <el-input v-model="formData.name" size="small" placeholder="请输入菜单名称" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="显示排序" prop="order">
                <el-input-number v-model="formData.order" size="small" controls-position="right" :min="0" />
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="formData.type === 'P'">
              <el-form-item prop="component">
                <span slot="label">
                  <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                    <i class="el-icon-question"></i>
                  </el-tooltip>
                  组件路径
                </span>
                <el-input v-model="formData.component" placeholder="请输入组件路径" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="path">
                <span slot="label">
                  <el-tooltip content="访问的路由地址，如：`user`，如外网地址需内链访问则以`http(s)://`开头" placement="top">
                    <i class="el-icon-question"></i>
                  </el-tooltip>
                  路由地址
                </span>
                <el-input v-model="formData.path" placeholder="请输入路由地址" size="small" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="isFrame">
                <span slot="label">
                  <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                  <i class="el-icon-question"></i>
                  </el-tooltip>
                  是否外链
                </span>
                <el-radio-group v-model="formData.isLink">
                  <el-radio :label="1">是</el-radio>
                  <el-radio :label="0">否</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="formData.type === 'P'">
              <el-form-item prop="isCache">
                <span slot="label">
                  <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                  <i class="el-icon-question"></i>
                  </el-tooltip>
                  是否缓存
                </span>
                <el-radio-group v-model="formData.isCache">
                  <el-radio :label="0">不缓存</el-radio>
                  <el-radio :label="1">缓存</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="visible">
                <span slot="label">
                  <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                  <i class="el-icon-question"></i>
                  </el-tooltip>
                  显示状态
                </span>
                <el-radio-group v-model="formData.visible">
                  <el-radio :label="0">显示</el-radio>
                  <el-radio :label="1">隐藏</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="status">
                <span slot="label">
                  <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                  <i class="el-icon-question"></i>
                  </el-tooltip>
                  菜单状态
                </span>
                <el-radio-group v-model="formData.status">
                  <el-radio :label="0">禁用</el-radio>
                  <el-radio :label="1">正常</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
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
import RightToolbar from '../../../components/RightToolbar/index.vue'
import IconSelect from '../../../components/IconSelect/index.vue'
import { listMenus, saveMenu, getMenuById, deleteMenu } from '../../../api/menu'

export default {
  name: 'Menu',

  components: { PageTitle, RightToolbar, IconSelect },

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
        // 菜单名称
        name: undefined,
        // 菜单状态
        status: undefined,
        // 时间范围
        dataRange: []
      },
      // 是否展开，默认全部折叠
      isExpandAll: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 菜单表单对象
      formData: {
        // 菜单ID
        id: undefined,
        // 菜单名称
        name: undefined,
        // 父菜单
        parentId: undefined,
        // 访问路径
        path: undefined,
        // 菜单类型
        type: 'D',
        // 组件路径
        component: undefined,
        // 是否缓存
        isCache: 0,
        // 是否外链
        isLink: 0,
        // 是否隐藏
        visible: 0,
        // 图标
        icon: undefined,
        // 排序
        order: undefined,
        // 菜单状态
        status: 1
      },
      // 菜单表单校验规则
      rules: {
        // 菜单名称
        name: [{ required: true, message: '菜单名称不能为空', trigger: 'blur' }],
        // 排序
        order: [{ required: true, message: '菜单顺序不能为空', trigger: 'blur' }],
        // 访问路径
        path: [{ required: true, message: '路由地址不能为空', trigger: 'blur' }]
      },
      // 菜单对话框等待框
      menuLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        // 菜单名称
        { key: 0, label: '菜单名称', visible: true },
        // 图标
        { key: 1, label: '图标', visible: true },
        // 排序
        { key: 2, label: '排序', visible: true },
        // 访问路径
        { key: 3, label: '访问路径', visible: true },
        // 组件路径
        { key: 4, label: '组件路径', visible: true },
        // 菜单状态
        { key: 5, label: '菜单状态', visible: true },
        // 是否隐藏
        { key: 6, label: '创建时间', visible: true },
        { key: 7, label: '更新时间', visible: true }
      ],
      // 表格数据
      tableData: [],
      // 对话框标题
      menuTitle: '',
      // 菜单状态选项
      statusOptions: [
        {
          value: 1,
          label: '正常'
        },
        {
          value: 0,
          label: '禁用'
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
      listMenus(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res
        // 关闭等待框
        this.loading = false
      }).catch(() => {
        // 确保加载框可以关闭
        this.loading = false
      })
    },
    // 展开/折叠
    toggleExpandAll () {
      this.refreshTable = false
      this.isExpandAll = !this.isExpandAll
      this.$nextTick(() => {
        this.refreshTable = true
      })
    },
    // 点击查询按钮
    handleQuery () {
      this.queryParams.currentPage = 1
      this.getTableData()
    },
    // 点击重置按钮
    handleReset () {
      this.queryParams = {
        // 菜单名称
        name: undefined,
        // 菜单状态
        status: undefined,
        // 时间范围
        dataRange: []
      }
    },
    // 打开菜单信息对话框
    handleOpen () {
      // 清除表单数据
      this.reset()
      this.menuVisible = true
    },
    // 关闭菜单对话框表单
    handleClose () {
      this.reset()
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
    reset () {
      // 清除校验条件
      this.formData = {
        // 菜单ID
        id: undefined,
        // 菜单名称
        name: undefined,
        // 父菜单
        parentId: undefined,
        // 访问路径
        path: undefined,
        // 菜单类型
        type: 'D',
        // 组件路径
        component: undefined,
        // 是否缓存
        isCache: 0,
        // 是否外链
        isLink: 0,
        // 是否隐藏
        visible: 0,
        // 图标
        icon: undefined,
        // 排序
        order: undefined,
        // 菜单状态
        status: 1
      }
      // 清除表单校验规则
      this.resetForm('menuForm')
    },
    // 选择图标
    selectIcon (name) {
      this.formData.icon = name
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
.card {
  min-height: calc(100vh - 165px);
}
</style>
