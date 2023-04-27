<template>
  <div class="analysis-container">
    <el-row style="margin: 20px 0px;">
      <el-col :lg="6" :sm="12" :xs="24">
        <div class="card-panel">
          <div class="card-panel-icon-wrapper icon-artcile">
            <svg-icon icon="article-create"></svg-icon>
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">文章数量</div>
            <span class="card-panel-num">10</span>
          </div>
        </div>
      </el-col>
      <el-col :lg="6" :sm="12" :xs="24">
        <div class="card-panel">
          <el-row>
            <div class="card-panel-icon-wrapper icon-user">
              <svg-icon icon="peoples"></svg-icon>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">用户数量</div>
              <span class="card-panel-num">10</span>
            </div>
          </el-row>
        </div>
      </el-col>
      <el-col :lg="6" :sm="12" :xs="24">
        <div class="card-panel">
          <el-row>
            <div class="card-panel-icon-wrapper icon-comments">
              <svg-icon icon="comments"></svg-icon>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">评论数量</div>
              <span class="card-panel-num">10</span>
            </div>
          </el-row>
        </div>
      </el-col>
      <el-col :lg="6" :sm="12" :xs="24">
        <div class="card-panel">
          <el-row>
            <div class="card-panel-icon-wrapper icon-messages">
              <svg-icon icon="message"></svg-icon>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">留言数量</div>
              <span class="card-panel-num">10</span>
            </div>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <!-- 文章一周访问数量 -->
    <el-row>
      <div class="card-panel" style="padding: 20px;">
        <div id="visits" style="width: 100%; height:400px;"></div>
      </div>
    </el-row>

    <!-- 访问区域和标签 -->
    <el-row>
      <el-col :lg="16" :sm="24">
        <div class="card-panel">
          游客访问区域
        </div>
      </el-col>
      <el-col :lg="8" :sm="24">
        <div class="card-panel">
          文章标签
        </div>
      </el-col>
    </el-row>

    <!-- 文章访问量排行和分类 -->
    <el-row>
      <el-col :lg="14" :sm="24">
        <div class="card-panel">
          文章访问量排行
        </div>
      </el-col>
      <el-col :lg="10" :sm="24">
        <div class="card-panel">
          文章分类
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'Analys',

  data () {
    return {
      // 一周访问量
      visits: {}
    }
  },

  mounted () {
    this.loadData()
  },

  methods: {
    loadData () {
      // 基于准备好的dom，初始化echarts实例
      this.visits = echarts.init(document.getElementById('visits'))
      // 绘制图表
      this.visits.setOption({
        title: {
          text: '本周访问量'
        },
        tooltip: {
          trigger: 'axis',
          // 坐标轴指示器，坐标轴触发有效
          axisPointer: {
            // 默认为直线，可选为：'line' | 'shadow'
            type: 'shadow'
          }
        },
        legend: {
          data: [{ name: '访问量' }]
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          top: '17%',
          containLabel: true // grid区域是否包含坐标轴的刻度标签
        },
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            // x轴的坐标文字
            show: true,
            textStyle: {
              fontSize: 13
            }
          }
        },
        series: [
          {
            name: '访问量',
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line',
            smooth: true
          }
        ]
      })
      const that = this
      // 如果页面大小发生变化图表自适应
      window.onresize = function () {
        // 图表动态改变并渲染
        that.visits.resize()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.analysis-container {
  padding: 0px 25px;

  .el-card {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .card-panel {
    padding: 10px;
    position: relative;
    vertical-align: middle;
    overflow: hidden;
    color: #666;
    background: #fff;
    margin: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .card-panel-icon-wrapper {
    cursor: pointer;
    float: left;
    margin: 14px 0 0 14px;
    padding: 16px;
    -webkit-transition: all .38s ease-out;
    transition: all .38s ease-out;
    border-radius: 6px;

  }

  .icon-artcile {
    .svg-icon {
      color: #409EFF;
      width: 48px;
      height: 48px;
    }
    &:hover {
      background: #409EFF;
      .svg-icon {
        color: #fff;
      }
    }
  }

  .icon-user {
    .svg-icon {
      color: #67C23A;
      width: 48px;
      height: 48px;
    }
    &:hover {
      background: #67C23A;
      .svg-icon {
        color: #fff;
      }
    }
  }

  .icon-comments {
    .svg-icon {
      color: #F56C6C;
      width: 48px;
      height: 48px;
    }
    &:hover {
      background: #F56C6C;
      .svg-icon {
        color: #fff;
      }
    }
  }

  .icon-messages {
    .svg-icon {
      color: #E6A23C;
      width: 48px;
      height: 48px;
    }
    &:hover {
      background: #E6A23C;
      .svg-icon {
        color: #fff;
      }
    }
  }

  .card-panel-description {
    float: right;
    font-weight: 700;
    margin: 26px;
    margin-left: 0;

    .card-panel-text {
      line-height: 18px;
      color: rgba(0,0,0,.45);
      font-size: 16px;
      margin-bottom: 12px;
    }

    .card-panel-num {
      font-size: 20px;
      color: #666;
    }
  }
}
</style>
