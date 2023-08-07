<template>
  <div class="home-container">
    <!-- 首页遮罩层 -->
    <div class="shaw-box">
      <!-- 导入Banner组件 -->
      <Banner></Banner>
    </div>

    <!-- 首页主体内容 -->
    <div class="home-body">
      <!-- 文章列表 -->
      <v-card
        class="article-body"
        justify="center"
      >
          <!-- 占位内容 -->
          <div style="height: 3rem"></div>
          <!-- 文章列表 -->
          <v-row justify="center" style="margin: 0;">
            <v-col cols="12" xs="12" md="10">
              <!-- 单个文章样式 -->
              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/hello-fluid/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <v-icon>fas fa-arrow-up</v-icon>
                    <span class="info-title">Hello Fluid</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    这是一款 Material Design 风格的 Hexo
                    主题，以简约的设计帮助你专注于写作，本篇文章可预览主题的样式及功能。
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2020-04-22
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        主题示例
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #示例
                      </router-link>
                      <router-link to="/" class="info-span">
                        #Fluid
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://blog.17lai.site/medias_webp/cover/hexo.webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title">Vercel部署高级用法教程</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    Vercel使用有不少需要仔细配置的地方，可惜的是，自定义Vercel缓存时间，Vercel重定向等使用方法略复杂，不是开箱即用，有一定使用门槛，故这里单独列一篇文章详细说明Vercel的一些高级使用方法！
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2022-10-10
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        实用技巧
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #vercel
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #blog
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://blog.17lai.site/medias_webp/cover/hexo.webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title"
                      >Hexo 使用 Docker 来写文章和调试博客</span
                    >
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    Docker
                    是一种轻量级的虚拟机环境，可以隔离主机的运行环境，内核公用主机的，运行库和环境是
                    Docker 私有的。运行 Docker
                    程序只比主机直接运行程序性能损失微乎其微。使用 Docker
                    你可以同时运行各种各样运行库环境而不用担心搞乱你的主机运行库环境！
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2022-10-10
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        实用技巧
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #用户经验
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.ladydaily.com/fetch/fluid/storage/coding-imagehost/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title">Coding 免费图床方案</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    通过 Coding 持续集成同步 GitHub 仓库，实现自建图床功能
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2022-03-11
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link to="/" class="info-span">
                        #用户经验
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/fluid-adsense/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title">在 Fluid 主题中加入谷歌广告</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">几行代码把自己博客的流量轻松变现</div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2022-02-20
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Fluid
                      </router-link>
                      <router-link to="/" class="info-span">
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.ladydaily.com/fetch/fluid/storage/hexo-netlify/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title"
                      >Hexo Netlify CMS 在线编辑博客</span
                    >
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    通过 Netlify CMS 实现在线编辑文章和修改主题配置
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2021-04-20
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #用户经验
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/hexo-injector/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title"
                      >通过 Hexo 5 注入器为主题添加新功能</span
                    >
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    想增加一些花里胡哨的功能又不想修改主题源代码？本文介绍 Hexo
                    官方支持的一种无侵入式的修改方式，适用于所有主题。
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2020-10-17
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #用户经验
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/fluid-footer-custom/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title">Fluid 页脚增加网站运行时长</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    Fluid 1.8.4
                    版本支持自定义页脚内容了，群友常改的网站运行时长，这次无需再修改源代码。
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2020-10-17
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Fluid
                      </router-link>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/hexo-echarts/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title"
                      >使用 ECharts 插件绘制炫酷图表</span
                    >
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    ECharts 是一个使用 JavaScript
                    实现的开源可视化库，涵盖各行业图表，满足各种需求，本文介绍如何在
                    Hexo 博客中使用 ECharts 插件。
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2020-06-20
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link to="/" class="info-span">
                        #用户经验
                      </router-link>
                      <router-link to="/" class="info-span">
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>

              <v-row justify="center" class="article-content">
                <!-- 文章图片 -->
                <v-col cols="12" md="4">
                  <router-link to="/">
                    <img
                      class="article-image"
                      src="https://rmt.dogedoge.com/fetch/fluid/storage/hexo-nodeppt/cover.png?w=480&fmt=webp"
                    />
                  </router-link>
                </v-col>
                <!-- 文章信息 -->
                <v-col class="article-info" cols="12" md="8">
                  <h1 class="info-header">
                    <span class="info-title">给博客文章嵌入 PPT 演示</span>
                  </h1>
                  <!-- 文章摘要 -->
                  <div class="info-body">
                    在 Hexo 博客使用 NodePPT 插件实现演示功能
                  </div>
                  <!-- 文章日期、分类、标签 -->
                  <div class="info-footer">
                    <!-- 文章日期 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-calendar-alt</v-icon>
                      2020-06-09
                    </div>
                    <!-- 文章分类 -->
                    <div class="info-item">
                      <v-icon size="14">fas fa-th-large</v-icon>
                      <router-link to="/" class="info-span">
                        功能增强
                      </router-link>
                    </div>
                    <!-- 文章标签 -->
                    <div class="info-item">
                      <v-icon>fas fa-tags</v-icon>
                      <router-link
                        to="/"
                        class="info-span"
                        style="margin-right: 0.3rem"
                      >
                        #Hexo
                      </router-link>
                      <router-link to="/" class="info-span">
                        #用户经验
                      </router-link>
                      <router-link to="/" class="info-span">
                        #花里胡哨
                      </router-link>
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
          <!-- 分页内容 -->
          <v-pagination class="my-4" v-model="page" :length="6"></v-pagination>
          <!-- 占位内容 -->
          <div style="height: 5rem"></div>

      </v-card>
    </div>
  </div>
</template>

<script>
import Banner from "../../components/banner/Banner";

export default {
  name: "Home",
  components: {
    Banner,
  },
  data() {
    return {
      // Body元素实际移动的情况
      boxY: 0,
      // 移动速度
      scrollDirection: 0,
      // 鼠标滚动校验位置
      i: 0,
      page: 1,
    };
  },
  mounted() {
  },
  methods: {
    // 监听当前页面的滚动事件
  },
};
</script>

<style lang="less" scoped>
.home-container {
  height: auto;
  width: 100%;
  background: #eee;

  .shaw-box {
    position: relative;
    z-index: 998;
    width: 100vw;
    height: calc(100vh - 64px);
  }

  .home-body {
    height: auto;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .article-body {
    width: 66vw;
    margin: 0;
    background: #fff;
    border-radius: 0.5rem;
    box-shadow: 0 12px 15px 0 rgba(0, 0, 0, 0.24),
      0 17px 50px 0 rgba(0, 0, 0, 0.19);
    z-index: 1000;
  }

  .article-list {
    width: 84%;
    margin: 48px 0;
    padding: 0 15px;
  }

  .article-content {
    margin: 0;
    margin-bottom: 2.5rem;
    display: flex;
  }

  .article-image {
    display: block;
    width: 100%;
    height: 10rem;
    object-fit: cover;
    box-shadow: 0 5px 11px 0 rgba(0, 0, 0, 0.18),
      0 4px 15px 0 rgba(0, 0, 0, 0.15);
    border-radius: 0.25rem;
    background-color: transparent;
    cursor: pointer;
  }

  .article-info {
    display: flex;
    flex-direction: column;
    align-items: left;
    padding: 1rem 1rem;
  }

  .info-header {
    color: #3c4858;
    font-size: 1.6rem;
    font-weight: bold;
    line-height: 1.4;
    margin-bottom: 0.25rem;
    display: flex;

    .v-icon {
      color: #3c4858;
      margin-right: 0.5rem;
    }

    .info-title {
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 1;
      -webkit-box-orient: vertical;
      cursor: pointer;
    }
  }

  .info-body {
    color: #718096;
    margin: 0.5rem 0 0.5rem 0;
    height: calc(1.4rem * 3);
    overflow: hidden;
    line-height: 1.4rem;
    word-break: break-word;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    cursor: pointer;
  }

  .info-title:hover {
    transition: 0.8s;
    color: #30a9de;
  }

  .info-body:hover {
    transition: 0.8s;
    color: #30a9de;
  }

  .info-footer {
    display: flex;
    flex-wrap: wrap;
    color: #718096;
  }

  .info-item {
    display: flex;
    align-items: center;
    margin-right: 1rem !important;

    .v-icon {
      color: #718096;
      font-size: 0.9rem;
      margin-right: 0.5rem;
    }
  }

  .article-image:hover {
    transition: 0.8s;
    transform: scale(1.1);
  }

  .info-span {
    color: #718096;
    font-size: 1rem;
    text-decoration: none;
  }

  .info-span:hover {
    transition: 0.8s;
    color: #30a9de;
  }

  @media (max-width: 575px) {
    .article-body {
      width: 100vw;
    }

    .article-image {
      width: 100%;
    }
  }
}
</style>
