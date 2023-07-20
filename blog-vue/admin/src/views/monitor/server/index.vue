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
                  <th class="el-table__cell is-leaf"><div class="cell">核心数</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.cpuMonitor">
                      {{ service.cpuMonitor.coreNum }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">用户使用率</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.cpuMonitor" :class="{'text-danger': service.cpuMonitor.userUseRate > 80}">
                      {{ service.cpuMonitor.userUseRate }}%
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">系统使用率</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.cpuMonitor" :class="{'text-danger': service.cpuMonitor.systemUseRate > 80}">
                      {{ service.cpuMonitor.systemUseRate }}%
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">当前空闲率</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.cpuMonitor">
                      {{ service.cpuMonitor.freeRate }}%
                    </div>
                  </td>
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
                  <th class="el-table__cell is-leaf"><div class="cell">总内存</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.memoryMonitor">
                      {{ service.memoryMonitor.total }}G
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.totalMemory }}MB
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">已用内存</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.memoryMonitor">
                      {{ service.memoryMonitor.used }}G
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.used }}MB
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">剩余内存</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.memoryMonitor">
                      {{ service.memoryMonitor.free }}G
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.freeMemory }}MB
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">使用率</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" :class="{'text-danger': service.memoryMonitor.usage > 80}" v-if="service.memoryMonitor">
                      {{ service.memoryMonitor.usage }}%
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" :class="{'text-danger': service.jvmMonitor.usage > 80}" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.usage }}%
                    </div>
                  </td>
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
                  <th class="el-table__cell is-leaf"><div class="cell">服务器名称</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.serverMonitor">
                      {{ service.serverMonitor.serverName }}
                    </div>
                  </td>
                  <th class="el-table__cell is-leaf"><div class="cell">操作系统</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.serverMonitor">
                      {{ service.serverMonitor.os }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">服务器IP</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.serverMonitor">
                      {{ service.serverMonitor.serverIp }}
                    </div>
                  </td>
                  <th class="el-table__cell is-leaf"><div class="cell">系统架构</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.serverMonitor">
                      {{ service.serverMonitor.osArch }}
                    </div>
                  </td>
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
                  <th class="el-table__cell is-leaf"><div class="cell">Java名称</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.name }}
                    </div>
                  </td>
                  <th class="el-table__cell is-leaf"><div class="cell">Java版本</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.version }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th class="el-table__cell is-leaf"><div class="cell">启动时间</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.jdkStartTime }}
                    </div>
                  </td>
                  <th class="el-table__cell is-leaf"><div class="cell">运行时长</div></th>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.jdkRunTime }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th colspan="1" class="el-table__cell is-leaf"><div class="cell">安装路径</div></th>
                  <td colspan="3" class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.home }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th colspan="1" class="el-table__cell is-leaf"><div class="cell">项目路径</div></th>
                  <td colspan="3" class="el-table__cell is-leaf">
                    <div class="cell" v-if="service.serverMonitor">
                      {{ service.serverMonitor.projectPath }}
                    </div>
                  </td>
                </tr>
                <tr>
                  <th colspan="1" class="el-table__cell is-leaf"><div class="cell">运行参数</div></th>
                  <td colspan="3" class="el-table__cell is-leaf">
                    <div class="cell"  v-if="service.jvmMonitor">
                      {{ service.jvmMonitor.inputArgs }}
                    </div>
                  </td>
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
                  <th class="el-table__cell is-leaf"><div class="cell">磁盘名称</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">磁盘路径</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">磁盘类型</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">总大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">可用大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">已用大小</div></th>
                  <th class="el-table__cell is-leaf"><div class="cell">已用百分比</div></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(disk, index) in service.diskMonitors" :key="index">
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.diskName }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.diskPath }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.diskType }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.total }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.free }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell">
                      {{ disk.used }}
                    </div>
                  </td>
                  <td class="el-table__cell is-leaf">
                    <div class="cell" :class="{'text-danger': disk.usage > 80}">
                      {{ disk.usage }}%
                    </div>
                  </td>
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
import { getMonitorInfo } from '../../../api/server'
import PageTitle from '../../../components/PageTitle/index.vue'

export default {
  name: 'Server',

  components: { PageTitle },

  data () {
    return {
      // 页面标题
      title: '',
      // 系统加载框
      loading: true,
      // 服务器信息
      service: []
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
        background: 'rgba(0, 0, 0, 0.9)'
      })
      // 获取后台数据
      getMonitorInfo().then((res) => {
        this.service = res
        setTimeout(() => {
          loading.close()
        }, 1000)
      })
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
.text-danger {
  color: red;
  font-weight: 600;
}
</style>
