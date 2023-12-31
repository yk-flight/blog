<template>
  <div class="page-container">
    <div class="card">
    <page-title :title="title"></page-title>
      <el-row class="search-container" type="flex">
        <el-col :span="24" :xs="24">
          <el-form size="small" :inline="true" v-show="showSearch" label-width="68px" :model="queryParams" ref="queryForm">
            <#list fieldList as field>
              <#if !field.isNull>

            <el-form-item label="${field.comment}">
              <el-input v-model="queryParams.${field.javaName}" class="search-item" placeholder="请输入${field.comment}" size="small" clearable></el-input>
            </el-form-item>
              </#if>
            </#list>
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
        <#list fieldList as field>
        <!-- ${field.comment} -->
        <el-table-column prop="${field.javaName}" label="${field.comment}" align="center" v-if="columns[${field_index}].visible"></el-table-column>
        </#list>
        <el-table-column prop="createTime" label="创建时间" align="center" v-if="columns[${fieldList?size}].visible">
          <template slot-scope="scope">
            <span>{{ scope.row.createTime | dateFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateTime" label="更新时间" align="center" v-if="columns[${fieldList?size + 1}].visible">
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

    <!-- ${objectName}信息对话框 -->
    <el-dialog
      :title="${entityLowerName}Title"
      width="500px"
      :modal-append-to-body="true"
      :append-to-body="true"
      :close-on-click-modal="false"
      :visible="${entityLowerName}Visible"
      :before-close="handleClose">
      <div class="${entityLowerName}-wrapper" v-loading="${entityLowerName}Loading" element-loading-text="正在加载${objectName}信息">
        <el-form ref="${entityLowerName}Form" :model="formData" :rules="rules" label-width="80px" label-position="right">
          <el-row :gutter="15">
            <#list fieldList as field>
            <!-- ${field.comment} -->
            <el-form-item label="${field.comment}" prop="${field.javaName}">
              <el-input v-model="formData.${field.javaName}" placeholder="请输入${field.comment}" clearable></el-input>
            </el-form-item>
            </#list>
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
import { list${entityName}s, save${entityName}, get${entityName}ById, delete${entityName} } from '../../../api/${entityLowerName}'

export default {
  name: '${entityName}',

  components: { PageTitle, Pagination, RightToolbar },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示搜索内容
      showSearch: true,
      // 数据总条数
      total: 0,
      // ${objectName}对话框是否显示
      ${entityLowerName}Visible: false,
      // 数据表格等待框
      loading: false,
      // 查询参数
      queryParams: {
        // 页数
        currentPage: 1,
        // 页面大小
        pageSize: 10,
        <#list fieldList as field>
          <#if !field.isNull>
        // ${field.comment}
        ${field.javaName}: undefined,
          </#if>
        </#list>
        // 时间范围
        dataRange: []
      },
      // ${objectName}表单对象
      formData: {
        <#list fieldList as field>
        // ${field.comment}
        ${field.javaName}: undefined,
        </#list>
      },
      // ${objectName}表单校验规则
      rules: {
        <#list fieldList as field>
          <#if !field.isNull>
        // ${field.comment}
        ${field.javaName}: [{ required: true, message: '请输入${field.comment}', trigger: 'blur' }],
          </#if>
        </#list>
      },
      // ${objectName}对话框等待框
      ${entityLowerName}Loading: false,
      // 对话框按钮等待框
      buttonLoading: false,
      // 列信息
      columns: [
        <#list fieldList as field>
        // ${field.comment}
        { key: ${field_index}, label: '${field.comment}', visible: true },
        </#list>
        { key: ${fieldList?size}, label: '创建时间', visible: true },
        { key: ${fieldList?size + 1}, label: '更新时间', visible: true }
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
      ${entityLowerName}Title: ''
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
      list${entityName}s(this.queryParams).then((res) => {
        // 赋值数据参数
        this.tableData = res.list
        this.total = res.total
        // 关闭等待框
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
      <#list fieldList as field>
        <#if !field.isNull>
      // ${field.comment}
      this.queryParams.${field.javaName} = ''
        </#if>
      </#list>
      this.queryParams.dataRange = []
    },
    // 打开${objectName}信息对话框
    handleOpen () {
      // 清除表单数据
      this.resetForm()
      this.${entityLowerName}Visible = true
    },
    // 关闭${objectName}对话框表单
    handleClose () {
      this.resetForm()
      this.${entityLowerName}Visible = false
    },
    // 点击新增按钮
    handleAdd () {
      this.${entityLowerName}Title = '新增${objectName}'
      this.handleOpen()
    },
    // 点击编辑按钮
    handleUpdate (row) {
      // 修改对话框标题
      this.${entityLowerName}Title = '更新${objectName}'
      // 获取到传来的ID
      const ${entityLowerName}Id = row.id || this.ids
      // 打开加载框
      this.${entityLowerName}Loading = true
      // 根据${objectName}ID获取对应的数据
      get${entityName}ById(${entityLowerName}Id).then((res) => {
        // 赋值当前的${objectName}数据
        this.formData = res
        // 打开编辑${objectName}对话框
        this.${entityLowerName}Visible = true
        // 关闭加载框
        this.${entityLowerName}Loading = false
      })
    },
    // 点击删除事件
    handleDelete (row) {
      let ${entityLowerName}Ids = []
      if (row.id) {
        ${entityLowerName}Ids.push(row.id)
      } else {
        ${entityLowerName}Ids = this.ids
      }
      console.log(${entityLowerName}Ids)
      this.$confirm('是否确认删除选中的${objectName}数据？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delete${entityName}(${entityLowerName}Ids)
      }).then(() => {
        this.getTableData()
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    // 提交表单
    submitForm () {
      const that = this
      this.$refs.${entityLowerName}Form.validate(valid => {
        // 校验未通过则直接返回
        if (!valid) return
        // 开启加载框
        that.buttonLoading = true
        that.${entityLowerName}Loading = true
        // 提交表单
        save${entityName}(that.formData).then((res) => {
          // 根据是否存在ID输出对应消息
          if (that.formData.id) {
            // 输出更新成功信息
            that.$message.success('${objectName}信息更新成功')
          } else {
            // 输出添加成功消息
            that.$message.success('${objectName}添加成功')
          }
          // 刷新表单数据并关闭对话框
          that.handleClose()
          // 刷新${objectName}数据
          that.getTableData()
          // 关闭加载框
          that.buttonLoading = false
          that.${entityLowerName}Loading = false
        }).catch(() => {
          // 关闭加载框
          that.buttonLoading = false
          that.${entityLowerName}Loading = false
        })
      })
    },
    // 重置表单
    resetForm () {
      // 清除校验条件
      this.formData = {
        // ${objectName}ID
        id: undefined,
        <#list fieldList as field>
          <#if !field.isNull>
        // ${field.comment}
        ${field.javaName}: undefined,
          </#if>
        </#list>
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
.${entityLowerName}-wrapper {
  padding: 10px 30px;
}
.search-item {
  width: 240px;
}
</style>
