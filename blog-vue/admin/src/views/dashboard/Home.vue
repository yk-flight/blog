<template>
  <div class="home-container">
    <!-- 工作台顶部描述 -->
    <el-card shadow="never">
      <el-row>
        <el-col :span="12">
          <el-row type="flex" justify="space-between">
            <div class="home-avatar">
              <img src="https://s1.ax1x.com/2023/04/26/p9K181A.jpg" alt="" class="avatar-image" />
            </div>
            <el-col style="padding-left: 18px;">
              <el-row>
                <span class="font-title">你好，世纪末的架构师，今天又是充满活力的一天</span>
              </el-row>
              <p class="font-content">须知少时凌云志，曾许人间第一流</p>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          项目数
        </el-col>
      </el-row>
    </el-card>

    <el-row :gutter="24">
      <el-col :span="15">
        <!-- 项目主要技术栈 -->
        <el-card>
          <el-row type="flex" justify="space-between">
            <span class="card-item-title">项目主要技术栈</span>
            <a href="https://www.zrkizzy.com" target="__blank">更多技术栈</a>
          </el-row>
          <!-- 技术栈内容 -->
          <el-row style="margin: 20px 0;">
            <el-col :span="8">
              <div class="card-item-content">技术栈1</div>
            </el-col>
            <el-col :span="8">
              <div class="card-item-content">技术栈1</div>
            </el-col>
            <el-col :span="8">
              <div class="card-item-content">技术栈1</div>
            </el-col>
          </el-row>
        </el-card>
        <el-card>
          <el-row type="flex" justify="space-between">
            <span class="card-item-title">动态</span>
            <a href="https://www.zrkizzy.com" target="__blank">更多动态</a>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="9">
        <el-card>
          <span class="card-item-title">快捷操作</span>
        </el-card>
        <el-card>
          <span class="card-item-title">创意</span>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Home',

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>
.home-container {
  padding: 0px 20px;

  a {
    font-size: 14px;
    color: #409EFF;
  }

  .el-card {
    border-radius: 1rem;
    padding: 0px 4px;
    box-shadow: 0px 0.5px 1px 0 rgba(0, 0, 0, 0.12);
    margin-top: 24px;
  }

  .home-avatar {
    width: 75px;
    height: 75px;
  }

  .font-title {
    font-weight: 600;
    font-size: 18px;
  }

  .font-content {
    font-size: 14px;
    color: #909399;
  }

  .avatar-image {
    height: 75px;
    width: 75px;
    border-radius: 50%;
    transition: all .5s;

    &:hover {
      transform: rotate(360deg);
    }
  }

  .card-item-title {
    font-weight: 500;
    color: #1F2225;
  }

  .card-item-content {
    height: 90px;
    padding: 24px;
    transition: all 0.3s;

    &:hover {
      box-shadow: darkgrey 1px 1px 3px 1px;
    }
  }
}
</style>
