<template>
  <div class="page-container">
    <div class="card card-title">
      <page-title :title="title"></page-title>
    </div>
    <!-- CPU监控 / 内存监控 -->
    <el-row :gutter="20" class="item-container">
      <el-col :md="12" :sm="24">
        <div class="card">
          <div class="header">
            <i class="el-icon-cpu"></i>
            <span>CPU监控</span>
          </div>
          <el-divider></el-divider>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">属性</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">值</div></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">核心数</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">cpuNum</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">用户使用率</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">used%</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">系统使用率</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">sys%</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">当前空闲率</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">free%</div></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </el-col>
      <el-col :md="12" :sm="24">
        <div class="card">
          <div class="header">
            <i class="el-icon-tickets"></i>
            <span>内存监控</span>
          </div>
          <el-divider></el-divider>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">属性</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">内存</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">JVM</div></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">总内存</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">total G</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">total M</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">已用内存</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">usedG</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">usedM</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">剩余内存</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">freeG</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">freeM</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">使用率</div></td>
                  <!-- :class="{'text-danger': server.mem.usage > 80}" -->
                  <td class="el-table__cell is-leaf"><div class="cell">usage%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">usage%</div></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- 服务器信息 -->
    <el-row>
      <el-col :span="24">
        <div class="card">
          <div class="header">
            <i class="el-icon-monitor"></i>
            <span>服务器信息</span>
          </div>
          <el-divider></el-divider>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <tbody>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">服务器名称</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">computerName</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">操作系统</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">osName</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">服务器IP</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">computerIp</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">系统架构</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">osArch</div></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- Java虚拟机信息 -->
    <el-row>
      <el-col :span="24">
        <div class="card">
          <div class="header">
            <i class="el-icon-coffee-cup"></i>
            <span>Java虚拟机信息</span>
          </div>
          <el-divider></el-divider>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;table-layout:fixed;">
              <tbody>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">Java名称</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">name</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">Java版本</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">version</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">启动时间</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">startTime</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">运行时长</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">runTime</div></td>
                </tr>
                <tr>
                  <td colspan="1" class="el-table__cell is-leaf"><div class="cell">安装路径</div></td>
                  <td colspan="3" class="el-table__cell is-leaf"><div class="cell">home</div></td>
                </tr>
                <tr>
                  <td colspan="1" class="el-table__cell is-leaf"><div class="cell">项目路径</div></td>
                  <td colspan="3" class="el-table__cell is-leaf"><div class="cell">userDir</div></td>
                </tr>
                <tr>
                  <td colspan="1" class="el-table__cell is-leaf"><div class="cell">运行参数</div></td>
                  <td colspan="3" class="el-table__cell is-leaf"><div class="cell">inputArgs</div></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </el-col>
    </el-row>
    <!-- 磁盘状态 -->
    <el-row>
      <el-col :span="24">
        <div class="card">
          <div class="header">
            <i class="el-icon-receiving"></i>
            <span>磁盘状态</span>
          </div>
          <el-divider></el-divider>
          <div class="el-table el-table--enable-row-hover el-table--medium">
            <table cellspacing="0" style="width: 100%;">
              <thead>
                <tr>
                  <th class="el-table__cell el-table__cell is-leaf"><div class="cell">盘符路径</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">文件系统</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">盘符类型</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">总大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">可用大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">已用大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">已用百分比</div></th>
                </tr>
              </thead>
              <tbody>
                <!-- v-for="(sysFile, index) in server.sysFiles" :key="index" -->
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">dirName</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">sysTypeName</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">typeName</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">total</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">free</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">used</div></td>
                  <!-- :class="{'text-danger': sysFile.usage > 80}" -->
                  <td class="el-table__cell is-leaf"><div class="cell">usage%</div></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PageTitle from '../../../components/PageTitle/index.vue'

export default {
  name: 'Server',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: '',
      // 系统加载框
      loading: true
    }
  },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 打开加载框
    this.openLoading()
  },

  methods: {
    // 打开数据加载框
    openLoading () {
      const loading = this.$loading({
        lock: true,
        text: '正在加载服务监控数据...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.8)'
      })
      setTimeout(() => {
        loading.close()
      }, 3000)
    }
  }
}
</script>

<style lang="scss" scoped>
.el-col {
  margin-bottom: 20px;
}

.el-divider {
  margin: 8px 0;
  width: 100%;
}

.header {
  span {
    margin-left: 10px;
  }
}

.card-title {
  height: 40px;
}

.item-container {
  margin-top: 20px;
}
</style>
