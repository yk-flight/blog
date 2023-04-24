<template>
  <div class="tags-view-container">
    <el-scrollbar >
      <div class="tags-view-left">
        <router-link
          class="tags-view-item"
          v-for="(tag, index) in this.$store.getters.tagsViewList"
          :class="isActive(tag) ? 'active' : ''"
          :key="tag.fullPath"
          :to="{ path: tag.fullPath }"
          :style="{
            backgroundColor: isActive(tag) ? '#409EFF' : '',
          }"
          >{{ tag.meta.title }}
          <i
            v-show="tag.path != '/home'"
            class="el-icon-close"
            @click.prevent.stop="onCloseClick(index)"
          ></i>
        </router-link>
     </div>

    </el-scrollbar>
    <!-- 关闭全部 -->
    <div class="tags-view-right">
      <span class="tags-btn" @click="closeAll">
        关闭全部
      </span>
    </div>
  </div>
</template>

<script>

export default {
  name: 'TagsView',

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {
    /**
     * 当前标签页是否被选中
     *
     * @param {*} tag
     */
    isActive (tag) {
      return tag.path === this.$route.path
    },
    /**
     * 关闭 tag 的点击事件
     *
     * @param {*} index 当前标签页的索引
     */
    onCloseClick (index) {
      this.$store.commit('REMOVE_TAGS_VIEW', {
        type: 'index',
        index
      })
      var path = this.$store.getters.tagsViewList[
        this.$store.getters.tagsViewList.length - 1
      ].path
      if (!this.$route.path.includes(path)) {
        // 当前路径中不包括本path就执行
        this.$router.push(path)
      }
      // 如果关闭的是当前标签页则返回上一个标签页
    },
    // 关闭全部标签事件
    closeAll () {
      this.$store.commit('CLOSE_ALL_TAGS')
      if (!this.$route.path.includes('/home')) {
        // 当前路径中不包括本path就执行
        this.$router.push('/home')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.tags-view-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 34px;
  width: 100%;
  background: #fff;
  border-bottom: 1px solid #d8dce5;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.12), 0 0 3px 0 rgba(0, 0, 0, 0.04);

  .el-scrollbar {
    width: calc(100% - 100px);
  }

  .tags-view-left {
    width: 100%;
    /* 设置超出滚动 */
    overflow-x: auto;
    overflow-y: hidden;
    white-space: nowrap;
  }

  .tags-view-right {
    width: 90px;
    height: 70%;
    display: flex;
    border: #DCDFE6 solid 1px;
    border-radius: 4px;
    font-size: 12px;
    justify-content: center;
    align-items: center;
    margin-right: 10px;
    cursor: pointer;
    transition: background .3s;

    &:hover {
      background: rgba(0, 0, 0, .085);
    }
  }

  .tags-view-item {
    text-decoration: none;
    display: inline-block;
    position: relative;
    cursor: pointer;
    height: 26px;
    line-height: 26px;
    border: 1px solid #d8dce5;
    color: #495060;
    background: #fff;
    padding: 0 8px;
    font-size: 12px;
    margin-left: 5px;
    margin-top: 4px;
    margin-bottom: 2px;
    &:first-of-type {
      margin-left: 15px;
    }
    &:last-of-type {
      margin-right: 15px;
    }
    &.active {
      color: #fff;
      &::before {
        content: "";
        background: #fff;
        display: inline-block;
        width: 8px;
        height: 8px;
        border-radius: 50%;
        position: relative;
        margin-right: 4px;
      }
    }
    // close 按钮
    .el-icon-close {
      width: 16px;
      height: 16px;
      line-height: 10px;
      vertical-align: 2px;
      border-radius: 50%;
      text-align: center;
      transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
      transform-origin: 100% 50%;
      &:before {
        transform: scale(0.6);
        display: inline-block;
        vertical-align: -3px;
      }
      &:hover {
        background-color: #b4bccc;
        color: #fff;
      }
    }
  }
}
</style>
