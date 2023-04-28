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
      <div class="card-panel-chart" style="padding: 20px;">
        <div id="visits" style="width: 100%; height:400px;"></div>
      </div>
    </el-row>

    <!-- 访问区域和标签 -->
    <el-row>
      <el-col :lg="16" :sm="24">
        <div class="card-panel-chart">
          <div style="width: 100%; height:380px;">
            游客访问区域
          </div>
        </div>
      </el-col>
      <el-col :lg="8" :sm="24">
        <div class="card-panel-chart">
          <span class="card-panel-chart-title">文章标签</span>
          <div style="width: 100%; height:355px; display: flex; align-items: center;">
            <tag-cloud :data="tagsList" />
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 文章访问量排行和分类 -->
    <el-row>
      <el-col :lg="14" :sm="24">
        <div class="card-panel-chart">
          <div id="ranking" style="width: 100%; height:520px;" />
        </div>
      </el-col>
      <el-col :lg="10" :sm="24">
        <div class="card-panel-chart">
          <div id="category" style="width: 100%; height:520px;" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'Analys',

  components: {
  },

  data () {
    return {
      // 一周访问量
      visits: {},
      // 文章分类
      category: {},
      // 文章访问量排行
      ranking: {},
      // 标签云数据
      tagsList: [{ name: '标签1', id: '1' },
        { name: '标签2', id: '2' },
        { name: '标签3', id: '3' },
        { name: '标签4', id: '4' },
        { name: '标签5', id: '5' },
        { name: '标签6', id: '6' },
        { name: '标签7', id: '7' },
        { name: '标签8', id: '8' },
        { name: '标签9', id: '9' },
        { name: '标签10', id: '10' },
        { name: '标签11', id: '11' },
        { name: '标签12', id: '12' },
        { name: '标签13', id: '13' },
        { name: '标签14', id: '14' },
        { name: '标签15', id: '15' },
        { name: '标签16', id: '16' },
        { name: '标签17', id: '17' }]
    }
  },

  mounted () {
    this.$nextTick(() => {
      this.loadData()
    })
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
            // 默认为直线，可选为：'line' | 'shadow' | 'cross'
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

      // 文章分类饼图
      this.category = echarts.init(document.getElementById('category'))
      // 绘制图表
      this.category.setOption({
        title: {
          text: '文章分类'
        },
        legend: {
          top: 'bottom'
        },
        // Echarts工具栏
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            // 显示数据
            dataView: { show: true, readOnly: false },
            // 还原
            restore: { show: true },
            // 保存为图片
            saveAsImage: { show: true }
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        series: [
          {
            name: '文章分类',
            type: 'pie',
            // 半径
            radius: [0, 190],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              // 饼图圆角
              borderRadius: 0
            },
            data: [
              { value: 40, name: 'rose 1' },
              { value: 38, name: 'rose 2' },
              { value: 32, name: 'rose 3' },
              { value: 30, name: 'rose 4' },
              { value: 28, name: 'rose 5' },
              { value: 26, name: 'rose 6' },
              { value: 22, name: 'rose 7' },
              { value: 18, name: 'rose 8' }
            ]
          }
        ]
      })

      // 文章访问量排行
      this.ranking = echarts.init(document.getElementById('ranking'))
      // 绘制图表
      this.ranking.setOption({
        title: {
          text: '文章访问量排行'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '浏览量',
            type: 'bar',
            barWidth: '60%',
            data: [52, 200, 334, 390, 220]
          }
        ]
      })

      const that = this
      // 如果页面大小发生变化图表自适应
      window.onresize = function () {
        that.visits.resize()
        // 图表动态改变并渲染
        that.ranking.resize()
        that.category.resize()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.analysis-container {
  padding: 0px 25px 25px 25px;

  .el-card {
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

  .card-panel-chart {
    padding: 20px;
    position: relative;
    vertical-align: middle;
    overflow: hidden;
    color: #666;
    background: #fff;
    margin: 10px;
    border-radius: 8px;
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

  .card-panel-chart-title {
    height: 20px;
    font-size: 18px;
    font-weight: 500;
    color: #000;
  }
}
</style>
