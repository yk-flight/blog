<template>
  <div class="page-container">
    <div class="card card-title">
      <page-title :title="title"></page-title>
    </div>

    <el-row :gutter="20">
      <el-col :md="8" :sm="24">
        <div class="card card-height">
          <span><i class="el-icon-collection"></i>缓存分类</span>
          <el-button
            @click="refreshRedisKeyTypes()"
            style="float: right; padding: 3px 0"
            type="text" icon="el-icon-refresh-right"></el-button>
          <el-divider></el-divider>
          <!-- data="tableData" -->
          <el-table
            :data="redisKeyTypes"
            v-loading="typeLoading"
            element-loading-text="正在加载缓存分类"
            :height="tableHeight"
            :header-cell-style="{ background: '#fff' }" border>
            <template slot="empty">
              <el-empty :image-size="200"></el-empty>
            </template>
            <el-table-column label="序号" width="50" type="index"  align="center"></el-table-column>
            <el-table-column prop="name" label="缓存名称"  align="center"></el-table-column>
            <el-table-column prop="remark" label="备注"  align="center"></el-table-column>
            <el-table-column label="操作"  align="center" width="80">
              <!-- slot-scope="scope" -->
              <template>
                <el-button type="text" size="small" icon="el-icon-delete"></el-button>
              </template>
              </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :md="8" :sm="24">
        <div class="card card-height">
          <span><i class="el-icon-key"></i>缓存列表</span>
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-refresh-right"></el-button>
          <el-divider></el-divider>
          <el-table
            v-loading="loading"
            element-loading-text="正在加载缓存分类"
            :height="tableHeight"
            :header-cell-style="{ background: '#fff' }" border>
            <template slot="empty">
              <el-empty :image-size="200"></el-empty>
            </template>
            <el-table-column label="序号" type="index"  align="center" width="50"></el-table-column>
            <el-table-column label="缓存键名"  align="center"></el-table-column>
            <el-table-column label="操作"  align="center" width="80">
              <!-- slot-scope="scope" -->
              <template>
                <el-button type="text" size="small" icon="el-icon-delete"></el-button>
              </template>
              </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :md="8" :sm="24">
        <div class="card card-height">
          <span><i class="el-icon-document"></i>缓存内容</span>
          <el-button style="float: right; padding: 3px 0" type="text" icon="el-icon-refresh-right"></el-button>
          <el-divider></el-divider>
          <div>
            <span>测试</span>
          </div>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import { listRedisKeyType } from '../../../api/cache'

export default {
  name: 'Cache',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示Redis缓存键加载框
      typeLoading: false,
      // 表格高度
      tableHeight: window.innerHeight - 330,
      // Redis缓存键类型
      redisKeyTypes: []
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 获取表格内容
    this.getRedisKeyType()
  },

  methods: {
    // 获取表格内容
    getRedisKeyType () {
      // 打开加载框
      this.typeLoading = true
      listRedisKeyType().then((res) => {
        this.redisKeyTypes = res
        // 关闭加载框
        this.typeLoading = false
      }).catch(() => {
        // 关闭加载框
        this.typeLoading = false
      })
    },
    // 刷新Redis缓存键类型
    refreshRedisKeyTypes () {
      // 重新获取缓存类型
      this.getRedisKeyType()
      // 输出提示信息
      this.$message.success('缓存类型刷新成功')
    }
  }
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-top: 20px;
}
.el-table {
  height: 100%;
}
.card-title {
  height: 40px;
}
.card {
  margin-bottom: 20px;

  i {
    margin-right: 5px;
  }
}
.el-divider {
  margin: 10px 0;
  width: 100%;
}
.card-height {
  height: calc(100vh - 285px);
}
</style>
