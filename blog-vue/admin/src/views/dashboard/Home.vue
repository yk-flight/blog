<template>
  <div class="home-container">
    <!-- Github角标 -->
    <github-corner class="github-corner" />
    <!-- 工作台顶部描述 -->
    <el-card shadow="never">
      <el-row>
        <el-col :xs="24" :sm="20" :md="21">
          <el-row type="flex" justify="space-between">
            <div class="home-avatar">
              <el-avatar :src="avatar" alt="" class="avatar-image"></el-avatar>
            </div>
            <el-col style="padding: 0px 18px;">
              <span class="font-title">你好，{{ nickname }}，今天又是充满活力的一天</span>
              <p class="font-content">须知少时凌云志，曾许人间第一流</p>
            </el-col>
          </el-row>
        </el-col>
        <el-col :xs="0" :sm="4" :md="3">
          项目数
        </el-col>
      </el-row>
    </el-card>

    <el-row :gutter="24">
      <el-col :lg="15" :md="24">
        <!-- 项目主要技术栈 -->
        <el-card>
          <el-row type="flex" justify="space-between">
            <span class="card-item-title">项目主要技术栈</span>
            <a href="https://www.zrkizzy.com" target="__blank">更多技术栈</a>
          </el-row>
          <!-- 技术栈内容 -->
          <el-row style="margin: 20px 0;">
            <!-- Vue -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="vue"></svg-icon>
                  <a href="https://cn.vuejs.org/" target="__blank" class="content-header">Vue</a>
                </el-row>
                <span class="content-body">一套用于构建用户界面的渐进式框架。</span>
              </div>
            </el-col>
            <!-- SpringBoot -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="spring-boot" style="color: #238E23;"></svg-icon>
                  <a href="https://spring.io/projects/spring-boot" target="__blank" class="content-header">SpringBoot</a>
                </el-row>
                <span class="content-body">
                  Spring Boot通过自动配置功能，降低了复杂性，同时支持基于JVM的多种开源框架，可以缩短开发时间，使开发更加简单和高效。
                </span>
              </div>
            </el-col>
            <!-- ElementUI -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="element"></svg-icon>
                  <a href="https://element.eleme.cn/#/zh-CN" target="__blank" class="content-header">ElementUI</a>
                </el-row>
                <span class="content-body">
                  Element，一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的桌面端组件库。
                </span>
              </div>
            </el-col>
            <!-- MyBatisPlus -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="mybatis-plus"></svg-icon>
                  <a href="https://baomidou.com/" target="__blank" class="content-header">MyBatisPlus</a>
                </el-row>
                <span class="content-body">
                  🚀为简化开发而生
                </span>
              </div>
            </el-col>
            <!-- Redis -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="redis" style="color: #dc382c;"></svg-icon>
                  <a href="https://redis.io/" target="__blank" class="content-header">Redis</a>
                </el-row>
                <span class="content-body">
                  Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。
                </span>
              </div>
            </el-col>
            <!-- RabbitMQ -->
            <el-col :lg="8" :md="8" :sm="12" :xs="24">
              <div class="card-item-content">
                <el-row type="flex" align="middle">
                  <svg-icon icon="rabbitmq" style="color: #FF6600;"></svg-icon>
                  <a href="https://www.rabbitmq.com/" target="__blank" class="content-header">RabbitMQ</a>
                </el-row>
                <span class="content-body">
                  RabbitMQ是实现了高级消息队列协议（AMQP）的开源消息代理软件。
                </span>
              </div>
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
      <el-col :lg="9" :md="24">
        <el-card>
          <span class="card-item-title">快捷操作</span>
          <!-- 快捷操作内容 -->
          <el-row style="margin: 20px 0;">
            <el-col :md="8" :sm="12">
              <!-- 发布文章 -->
              <div class="operate-container">
                <svg-icon icon="article-create" style="color: #409EFF;"></svg-icon>
                <span>发布文章</span>
              </div>
            </el-col>
            <el-col :md="8" :sm="12">
              <div class="operate-container">
                <svg-icon icon="comments" style="color: #67C23A;"></svg-icon>
                <span>评论管理</span>
              </div>
            </el-col>
            <el-col :md="8" :sm="12">
              <div class="operate-container">
                <svg-icon icon="user" style="color: #F56C6C;"></svg-icon>
                <span>用户管理</span>
              </div>
            </el-col>
            <el-col :md="8" :sm="12">
              <div class="operate-container">
                <svg-icon icon="article-ranking" style="color: #E6A23C;"></svg-icon>
                <span>文件管理</span>
              </div>
            </el-col>
            <el-col :md="8" :sm="12">
              <div class="operate-container">
                <svg-icon icon="email" style="color: #7238D1;"></svg-icon>
                <span>公告管理</span>
              </div>
            </el-col>
            <el-col :md="8" :sm="12">
              <div class="operate-container">
                <svg-icon icon="personnel" style="color: #909399;"></svg-icon>
                <span>友链管理</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
        <el-card>
          <span class="card-item-title">创意</span>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog
      center
      title="系统通知"
      :visible.sync="homeDialogShow"
      width="680px"
      :close-on-press-escape="false"
      :close-on-click-modal="false"
      :before-close="handleClose">
      <b>尊敬的用户：</b><br>
      <p>欢迎来到我的个人博客! 我是Dream_飞翔，非常感谢您的来访。这里是我分享自己生活、学习和工作中的一些体验与见解的地方。您可以随意浏览我的文章以及后台的所有页面，也可以在评论区分享您的想法和反馈。如果您有任何问题或建议，请与我联系，祝您生活愉快！</p>
      <b>其他：</b>
    </el-dialog>
  </div>
</template>

<script>
import GithubCorner from '../../components/GithubCorner/index.vue'

export default {
  name: 'Home',

  components: {
    GithubCorner
  },

  mounted () {

  },

  data () {
    return {

    }
  },

  computed: {
    // 登录用户头像
    avatar () {
      return this.$store.getters.avatar
    },
    // 登录用户昵称
    nickname () {
      return this.$store.getters.nickname
    },
    // 首页通知框
    homeDialogShow () {
      return this.$store.getters.homeDialogShow
    }
  },

  methods: {
    // 关闭对话框
    handleClose () {
      this.$store.commit('app/SET_HOME_DIALOG')
    }
  }
}
</script>

<style lang="scss" scoped>
.home-container {
  position: relative;
  padding: 0px 20px;

  .github-corner {
    position: absolute;
    top: -24px;
    border: 0;
    right: 0;
  }

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
    display: flex;
    align-items: left;
    flex-direction: column;
    height: 90px;
    padding: 24px;
    transition: all 0.3s;

    .svg-icon {
      font-size: 40px;
    }

    &:hover {
      box-shadow: darkgrey 1px 1px 3px 1px;
    }

    .content-header {
      margin-left: 0.5rem;
      font-weight: 600;
      font-size: 20px;
      color: #000;
    }

    .content-body {
      margin-top: 5px;
      letter-spacing: 1px;
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      color: #909399;
      font-size: 14px;
    }
  }

  .operate-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 6rem;
    border: 1px solid #dcdfe6;
    margin: 1px;
    transition: all .3s;

    .svg-icon {
      font-size: 35px;
    }

    span {
      font-size: 14px;
      padding-top: 10px;
      color: #303133;
      font-weight: 500;
    }

    &:hover {
      cursor: pointer;
      box-shadow: darkgrey 1px 1px 3px 1px;
    }
  }
}
</style>
