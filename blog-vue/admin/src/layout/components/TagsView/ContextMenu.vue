<template>
  <ul class="context-menu-container">
    <!-- @click="onRefreshClick" -->
    <li>
      <i class="el-icon-refresh-right"></i>
      刷新
    </li>
    <li>
      <i class="el-icon-d-arrow-left"></i>
      <span>关闭左侧</span>
    </li>
    <!-- @click="onCloseRightClick" -->
    <li>
      <i class="el-icon-d-arrow-right"></i>
      <span>关闭右侧</span>
    </li>
    <!-- @click="onCloseOtherClick" -->
    <li>
      <i class="el-icon-remove-outline"></i>
      <span>关闭其他</span>
    </li>
    <li>
      <i class="el-icon-circle-close"></i>
      <span>关闭全部</span>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'ContextMenu',

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
