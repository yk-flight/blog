<template>
  <ul class="context-menu-container">
    <li @click="onRefreshClick">
      <i class="el-icon-refresh-right"></i>
      刷新
    </li>
    <li @click="onCloseLeftClick">
      <i class="el-icon-d-arrow-left"></i>
      <span>关闭左侧</span>
    </li>
    <li @click="onCloseRightClick">
      <i class="el-icon-d-arrow-right"></i>
      <span>关闭右侧</span>
    </li>
    <li @click="onCloseOtherClick">
      <i class="el-icon-remove-outline"></i>
      <span>关闭其他</span>
    </li>
    <li @click="closeAll">
      <i class="el-icon-circle-close"></i>
      <span>关闭全部</span>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'ContextMenu',

  props: {
    // 选中页的索引
    index: {
      type: Number,
      require: true
    }
  },

  data () {
    return {

    }
  },

  mounted () {

  },

  methods: {
    // 关闭全部标签事件
    closeAll () {
      this.$store.commit('app/CLOSE_ALL_TAGS')
      // 跳转到工作台页面
      this.$router.push('/home')
    },
    // 刷新当前页面
    onRefreshClick () {
      this.$router.go(0)
    },
    // 关闭右侧
    onCloseRightClick () {
      this.$store.commit('app/REMOVE_TAGS_VIEW', {
        type: 'right',
        index: this.index
      })
      // 退回到当前标签页
      this.$router.push(
        this.$store.getters.tagsViewList[this.$store.getters.tagsViewList.length - 1].path
      )
    },
    // 关闭其他
    onCloseOtherClick () {
      this.$store.commit('app/REMOVE_TAGS_VIEW', {
        type: 'other',
        index: this.index
      })
      // 退回到当前标签页
      this.$router.push(
        this.$store.getters.tagsViewList[this.$store.getters.tagsViewList.length - 1].path
      )
    },
    // 关闭左侧
    onCloseLeftClick () {
      this.$store.commit('app/REMOVE_TAGS_VIEW', {
        type: 'left',
        index: this.index
      })
      // 退回到当前标签页
      this.$router.push(
        this.$store.getters.tagsViewList[this.$store.getters.tagsViewList.length - 1].path
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.context-menu-container {
  position: fixed;
  background: #fff;
  z-index: 3000;
  list-style-type: none;
  padding: 5px 0;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 400;
  color: #333;
  box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.3);
  li {
    display: flex;
    align-items: center;
    margin: 0;
    padding: 7px 16px;
    cursor: pointer;

    i {
      margin-right: 5px;
    }
    &:hover {
      background: #eee;
    }
  }
}
</style>
