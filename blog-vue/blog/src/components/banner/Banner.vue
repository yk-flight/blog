<template>
  <div
    class="banner-container"
    style="background: url('https://rmt.dogedoge.com/fetch/fluid/storage/bg/vdysjx.png?w=1920&fmt=webp') center center / cover no-repeat;"
  >
  <!-- https://www.static.banq.ink/photos/fa74451429d178e2682a3cf6e833c019.png -->

    <!-- 遮罩层 -->
    <div class="banner-shade"></div>
    <!-- 每日一言 -->
    <div class="banner-body">
      <h1>雅康的个人博客</h1>
      <vue-typed-js
        v-if="typingTexts.length > 0"
        :strings="typingTexts"
        :loop="false"
        class="blog-word"
        :startDelay="100"
        :typeSpeed="100"
        :backSpeed="100"
      >
        <span class="typing"></span>
      </vue-typed-js>

    </div>
  </div>
</template>

<script>
import Wave from './components/Wave.vue';

export default {
  name: "Banner",

  components: {
    Wave
  },

  data() {
    return {
      // 每日一言数组
      typingTexts: [],
    };
  },

  created() {
    this.getWords();
  },

  methods: {
    // 获取每日一言
    getWords() {
      this.$axios
        // .get("https://api.fghrsh.net/hitokoto/rand/?encode=jsc&uid=3335")
        .get("https://v1.hitokoto.cn?c=i")
        .then((result) => {
          this.typingTexts.push(result.data.hitokoto);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.banner-container {
  position: absolute;
  top: -64px;
  width: 100%;
  height: calc(80vh);

  .banner-shade {
    height: 100%;
    background: #000;
    opacity: 0.3;
  }
  .banner-body {
    height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    // margin-left: auto;
    // margin-right: auto;
  }
  .blog-word {
    color: white;
    font-weight: 400;
    font-size: 1.5rem;
    display: -webkit-box;
    word-break: break-word;
    -webkit-box-orient: vertical;
  }

  @media (max-width: 760px) {
    .typing {
      font-size: 1.5rem;
    }

    .blog-word {
      width: 80%;
      display: -webkit-box;
      word-break: break-word;
      -webkit-box-orient: vertical;
      text-align: center;
    }
  }
}
</style>
