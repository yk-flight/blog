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
            @click="refreshCacheTypes()"
            style="float: right; padding: 3px 0"
            type="text" icon="el-icon-refresh-right"></el-button>
          <el-divider></el-divider>
          <!-- data="tableData" -->
          <el-table
            :data="cacheTypes"
            v-loading="typeLoading"
            element-loading-text="正在加载缓存分类"
            @row-click="getCacheKeys"
            :height="tableHeight"
            highlight-current-row
            :header-cell-style="{ background: '#fff' }" border>
            <template slot="empty">
              <el-empty :image-size="200"></el-empty>
            </template>
            <el-table-column label="序号" width="50" type="index"  align="center"></el-table-column>
            <el-table-column prop="name" label="缓存名称"  align="center" :formatter="nameFormatter"></el-table-column>
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
          <el-button
            @click="refreshCacheKeys"
            style="float: right; padding: 3px 0"
            type="text"
            icon="el-icon-refresh-right"></el-button>
          <el-divider></el-divider>
          <el-table
            :data="cacheKeys"
            v-loading="keyLoading"
            @row-click="getCacheInfoByKey"
            element-loading-text="正在加载缓存列表"
            :height="tableHeight"
            highlight-current-row
            :header-cell-style="{ background: '#fff' }" border>
            <template slot="empty">
              <el-empty :image-size="200"></el-empty>
            </template>
            <el-table-column label="序号" type="index"  align="center" width="50"></el-table-column>
            <el-table-column label="缓存键名"  align="center" prop="showKey" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作"  align="center" width="80">
              <template slot-scope="scope">
                <el-button type="text" size="small" icon="el-icon-delete" @click="deleteCacheKey(scope.row)"></el-button>
              </template>
              </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :md="8" :sm="24">
        <div class="card card-height">
          <span><i class="el-icon-document"></i>缓存内容</span>
          <el-divider></el-divider>
          <el-form :model="cacheForm" v-loading="cacheLoading" element-loading-text="正在加载缓存信息">
            <el-row :gutter="32">
              <el-col>
                <el-form-item label="缓存名称:" prop="cacheName">
                  <el-input v-model="cacheForm.cacheName" :readOnly="true" />
                </el-form-item>
              </el-col>
              <el-col>
                <el-form-item label="缓存键名:" prop="cacheKey">
                  <el-input v-model="cacheForm.cacheKey" :readOnly="true" />
                </el-form-item>
              </el-col>
              <el-col>
                <el-form-item label="缓存内容:" prop="cacheValue">
                  <el-input
                    v-model="cacheForm.cacheValue"
                    type="textarea"
                    :rows="9"
                    :readOnly="true"
                  />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'
import { listCacheType, listCacheKeys, getCacheInfoByKey } from '../../../api/cache'

export default {
  name: 'Cache',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: '',
      // 是否展示缓存键类型加载框
      typeLoading: false,
      // 是否展示缓存键加载框
      keyLoading: false,
      // 缓存信息等待框
      cacheLoading: false,
      // 表格高度
      tableHeight: window.innerHeight - 330,
      // 缓存键类型
      cacheTypes: [],
      // 缓存键
      cacheKeys: [],
      // 缓存信息
      cacheForm: {
        // 缓存名称
        cacheName: undefined,
        // 缓存键
        cacheKey: undefined,
        // 缓存值
        cacheValue: undefined
      },
      // 当前选中缓存名称
      cacheName: undefined
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 获取表格内容
    this.listCacheType()
  },

  methods: {
    // 获取表格内容
    listCacheType () {
      // 打开加载框
      this.typeLoading = true
      listCacheType().then((res) => {
        this.cacheTypes = res
        // 关闭加载框
        this.typeLoading = false
      }).catch(() => {
        // 关闭加载框
        this.typeLoading = false
      })
    },
    // 刷新Redis缓存键类型
    refreshCacheTypes () {
      // 重新获取缓存名称
      this.listCacheType()
      // 输出提示信息
      this.$message.success('缓存名称刷新成功')
    },
    // 刷新Redis缓存键
    refreshCacheKeys () {
      // 重新获取缓存名称
      this.getCacheKeys()
      // 输出提示信息
      this.$message.success('缓存键刷新成功')
    },
    // 获取对应缓存键列表
    getCacheKeys (row) {
      // 获取当前选中类型
      const cacheName = row !== undefined ? row.name : this.cacheName
      // 加载框
      this.keyLoading = true
      listCacheKeys(cacheName).then((res) => {
        this.cacheKeys = res
        // 赋值当前选中缓存名称
        this.cacheName = cacheName
        // 关闭加载框
        this.keyLoading = false
      }).catch(() => {
        // 确保加载框关闭
        this.keyLoading = false
      })
    },
    // 获取指定缓存信息
    getCacheInfoByKey (row) {
      // 加载框
      this.cacheLoading = true
      getCacheInfoByKey(row.key).then((res) => {
        this.cacheForm = res
        // 关闭加载框
        this.cacheLoading = false
      }).catch(() => {
        // 确保加载框关闭
        this.cacheLoading = false
      })
    },
    deleteCacheKey (row) {
      // 删除指定Key
      console.log(row)
    },
    // 去除掉缓存键名后的冒号
    nameFormatter (row) {
      return row.name.replace(':', '')
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
