<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">

            <el-form-item label="用户名">
              <el-input v-model="queryParams.username" class="search-item" placeholder="请输入用户名" size="small" clearable></el-input>
            </el-form-item>

            <el-form-item label="状态">
              <el-select v-model="queryParams.status" placeholder="请选择用户状态" size="small" class="search-item" clearable>
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
        <!-- 用户名 -->
        <el-table-column prop="username" label="用户名" align="center" v-if="columns[0].visible"></el-table-column>
        <!-- 昵称 -->
        <el-table-column prop="nickname" label="昵称" align="center" v-if="columns[1].visible"></el-table-column>
        <!-- 头像 -->
        <el-table-column prop="avatar" label="头像" align="center" v-if="columns[2].visible" width="100">
          <template slot-scope="scope">
            <el-image :src="scope.row.avatar">
              <div slot="error" class="image-slot-table">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </template>
        </el-table-column>
        <!-- 备注 -->
        <el-table-column prop="remark" label="备注" align="center" v-if="columns[3].visible" show-overflow-tooltip></el-table-column>
        <!-- 状态 -->
        <el-table-column prop="status" label="状态" align="center" v-if="columns[4].visible" width="100">
          <template slot-scope="scope">
            <el-switch
              v-if="scope.row.roles !== 'ROLE_ADMIN'"
              @change="handleUserStatus(scope.row.id)"
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
            <el-switch
              v-else
              disabled
              v-model="scope.row.status"
              active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[5].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[6].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.updateTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope" v-if="scope.row.roles !== 'ROLE_ADMIN'">
            <el-button type="text" size="small" icon="el-icon-edit" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button type="text" size="small" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
            <el-dropdown  @command="(command) => handleCommand(command, scope.row)" style="margin-left: 10px;">
              <el-button size="small" type="text" icon="el-icon-d-arrow-right">更多</el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="handleResetPassword" icon="el-icon-refresh-left">重置密码</el-dropdown-item>
                <el-dropdown-item command="handleAuthUser" icon="el-icon-s-custom">分配角色</el-dropdown-item>
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

    <!-- 用户信息对话框 -->
    <el-dialog
      :title="userTitle"
      width="600px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="userVisible"
      :before-close="handleClose">
      <div class="dialog-wrapper" v-loading="userLoading" element-loading-text="正在加载用户信息">
        <el-form ref="userForm" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="10">
            <el-col :span="12">
              <!-- 用户名 -->
              <el-form-item label="用户名" prop="username">
                <el-input v-model="formData.username" placeholder="请输入用户名" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <!-- 昵称 -->
              <el-form-item label="用户昵称" prop="nickname">
                <el-input v-model="formData.nickname" placeholder="请输入用户昵称" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <!-- 电话号码 -->
              <el-form-item label="电话号码" prop="phone">
                <el-input v-model="formData.phone" placeholder="请绑定电话号码" clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <!-- 状态 -->
              <el-form-item label="状态" prop="status">
                <el-radio-group v-model="formData.status">
                  <el-radio :label="true">正常</el-radio>
                  <el-radio :label="false">禁用</el-radio>
              </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <!-- 备注 -->
          <el-form-item label="备注" prop="remark">
            <el-input v-model="formData.remark" type="textarea" placeholder="请输入备注" clearable></el-input>
          </el-form-item>
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

    <!-- 分配角色对话框 -->
    <el-dialog
      title="分配角色"
      width="600px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="userRoleVisible"
      :before-close="handleRoleClose">
      <div class="dialog-wrapper" v-loading="userRoleLoading" element-loading-text="正在加载角色信息">
        <el-radio-group v-model="userRole.roleId">
          <el-radio
            v-for="radio in roleOptions"
            :key="radio.value"
            :label="radio.value">
            {{ radio.label }}
          </el-radio>
        </el-radio-group>
      </div>
      <div slot="footer">
        <el-button
          type="danger"
          :loading="buttonLoading"
          @click="handleRoleClose()"
          size="small"
          icon="el-icon-error">
          取消
        </el-button>
        <el-button
          type="success"
          :loading="buttonLoading"
          @click="handleUserRoleUpdate()"
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
import { listRoleOptions, updateUserRole } from '../../../api/role'
import { listUsers, insert, getUserInfoById, deleteUser, updateUser, updateUserStatus, resetPassword } from '../../../api/user'

export default {
  name: 'User',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // 用户对话框是否显示
      userVisible: false,
      // 分配角色对话框是否展示
      userRoleVisible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        // 用户名
        username: undefined,
        // 状态
        status: undefined,
        // 时间范围
        dataRange: []
      },
      // 用户表单对象
      formData: {
        // 用户名
        username: undefined,
        // 昵称
        nickname: undefined,
        // 电话号码
        phone: undefined,
        // 状态
        status: undefined,
        // 备注
        remark: undefined,
        // 用户角色
        roles: undefined
      },
      // 用户角色对象
      userRole: {
        // 角色ID
        roleId: undefined,
        // 用户ID
        userId: undefined
      },
      // 用户表单校验规则
      rules: {
        // 用户名
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        // 用户昵称
        nickname: [{ required: true, message: '用户昵称不能为空', trigger: 'blur' }],
        // 状态
        status: [{ required: true, message: '用户状态不能为空', trigger: 'blur' }]
      },
      // 用户对话框等待框
      userLoading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 分配角色对话框等待框
      userRoleLoading: false,
      // 列信息
      columns: [
        // 用户名
        { key: 0, label: '用户名', visible: true },
        // 昵称
        { key: 1, label: '昵称', visible: true },
        // 头像
        { key: 2, label: '头像', visible: true },
        // 备注
        { key: 3, label: '备注', visible: true },
        // 状态
        { key: 4, label: '状态', visible: true },
        { key: 5, label: '创建时间', visible: true },
        { key: 6, label: '更新时间', visible: true }
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
      userTitle: '',
      // 请求状态选项
      statusOptions: [
        {
          value: true,
          label: '正常'
        },
        {
          value: false,
          label: '禁用'
        }
      ],
      // 分配角色选项
      roleOptions: []
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
      listUsers(this.queryParams).then((res) => {
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
      // 用户名
      this.queryParams.username = ''
      // 状态
      this.queryParams.status = ''
      // 创建时间
      this.queryParams.dataRange = []
    },
    // 打开用户信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.userVisible = true
    },
    // 更多操作触发
    handleCommand (command, row) {
      switch (command) {
        case 'handleResetPassword':
          this.handleResetPassword(row)
          // console.log('重置密码')
          break
        case 'handleAuthUser':
          // console.log('分配角色')
          this.handleAuthUser(row)
          break
        default: break
      }
    },
    // 关闭用户对话框表单
    handleClose () {
      this.resetForm()
      this.userVisible = false
    },
    // 打开分配用户角色对话框
    handleAuthUser (row) {
      // 将当前选中的角色ID和用户ID
      this.userRole.roleId = row.roleId
      this.userRole.userId = row.id
      // 打开分配角色对话框
      this.userRoleVisible = true
      // 开启加载框
      this.userRoleLoading = true
      // 获取角色选项
      listRoleOptions().then((res) => {
        this.roleOptions = res
        // 关闭加载框
        this.userRoleLoading = false
      }).catch(() => {
        // 关闭加载框
        this.userRoleLoading = false
      })
    },
    // 更新用户角色
    handleUserRoleUpdate () {
      // 加载框
      this.userRoleLoading = true
      this.buttonLoading = true
      updateUserRole(this.userRole).then((res) => {
        this.$message.success('角色更新成功')
        // 关闭对话框
        this.handleRoleClose()
        // 刷新数据
        this.getTableData()
        // 关闭加载框
        this.userRoleLoading = false
        this.buttonLoading = false
      }).catch(() => {
        this.userRoleLoading = false
        this.buttonLoading = false
      })
    },
    // 关闭分配角色对话框
    handleRoleClose () {
      this.userRoleVisible = false
    },
    // 重置用户密码
    handleResetPassword (row) {
      const that = this
      this.$confirm('是否重置用户 ' + row.nickname + ' 的密码？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 执行重置用户密码方法
        resetPassword(row.id).then((res) => {
          that.$message.success('密码重置成功')
        })
      }).catch(() => {})
    },
    // 点击新增按钮
    handleAdd () {
      this.userTitle = '新增用户'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.userTitle = '更新用户'
      // 获取到传来的ID
      const userId = row.id || this.ids
      // 打开加载框
      this.userLoading = true
      // 打开编辑用户对话框
      this.userVisible = true
      // 根据用户ID获取对应的数据
      getUserInfoById(userId).then((res) => {
        // 赋值当前的用户数据
        this.formData = res
        // 关闭加载框
        this.userLoading = false
      }).catch(() => {
        this.closeLoading()
      })
    },
    // 点击删除事件
    handleDelete (row) {
      const that = this
      let userIds = []
      if (row.id) {
        userIds.push(row.id)
      } else {
        userIds = this.ids
      }
      this.$confirm('是否确认删除选中的用户数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 打开加载框
        that.loading = true
        deleteUser(userIds).then((res) => {
          that.$message.success('删除成功')
          that.getTableData()
          that.loading = false
        }).catch(() => {
          that.loading = false
        })
      }).catch(() => {})
    },
    // 更新用户状态
    handleUserStatus (id) {
      // 加载等待框
      this.userLoading = true
      updateUserStatus(id).then((res) => {
        // 输出提示信息
        this.$message.success('用户状态修改成功')
        // 关闭加载框
        this.userLoading = false
      }).catch(() => {
        // 关闭加载框
        this.userLoading = false
      })
    },
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.userForm.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.userLoading = true
        // 如果存在用户ID则进行用户更新操作
        if (that.formData.id) {
          updateUser(that.formData).then((res) => {
            // 刷新表单数据并关闭对话框
            that.handleClose()
            // 输出更新成功信息
            that.$message.success('用户更新成功')
            // 刷新用户数据
            that.getTableData()
            // 关闭等待框
            that.closeLoading()
          }).catch(() => {
            // 关闭加载框
            that.closeLoading()
          })
        } else {
          insert(that.formData).then((res) => {
            // 输出添加成功消息
            that.$message.success('用户添加成功')
            // 刷新表单数据并关闭对话框
            that.handleClose()
            // 刷新用户数据
            that.getTableData()
            // 关闭加载框
            that.closeLoading()
          }).catch(() => {
            // 关闭加载框
            that.closeLoading()
          })
        }
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // 用户ID
        id: undefined,
        // 用户名
        username: undefined,
        // 电话号码
        phone: undefined,
        // 状态
        status: undefined,
        // 备注
        remark: undefined
      }
      // 清除表单的校验
      if (this.$refs.userForm) {
        this.$refs.userForm.clearValidate()
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
    },
    // 关闭所有等待框
    closeLoading () {
      this.buttonLoading = false
      this.userLoading = false
    }
  }
}

</script>

<style lang="scss" scoped>
.dialog-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
