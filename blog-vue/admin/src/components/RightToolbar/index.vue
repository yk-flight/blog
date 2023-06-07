<template>
  <div class="top-right-btn" :style="style">
    <el-row>
      <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top" v-if="search">
        <el-button size="mini" circle icon="el-icon-search" @click="toggleSearch()" />
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="刷新" placement="top">
        <el-button size="mini" circle icon="el-icon-refresh" @click="refresh()" />
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="筛选列" placement="top" v-if="columns">
        <el-button size="mini" circle icon="el-icon-menu" @click="showColumn()" />
      </el-tooltip>
    </el-row>
    <el-dialog :title="title" :visible.sync="open" append-to-body width="622px">
      <el-transfer
        :titles="['显示列', '隐藏列']"
        v-model="value"
        :data="columns"
        @change="dataChange"
      ></el-transfer>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'RightToolbar',
  data () {
    return {
      // 显隐数据
      value: [],
      // 弹出层标题
      title: '筛选列',
      // 是否显示弹出层
      open: false
    }
  },
  props: {
    showSearch: {
      type: Boolean,
      default: true
    },
    // 筛选列
    columns: {
      type: Array
    },
    // 是否展示搜索
    search: {
      type: Boolean,
      default: true
    },
    gutter: {
      type: Number,
      default: 10
    }
  },
  computed: {
    style () {
      const ret = {}
      if (this.gutter) {
        ret.marginRight = `${this.gutter / 2}px`
      }
      return ret
    }
  },
  created () {
    // 显隐列初始默认隐藏列
    for (const item in this.columns) {
      if (this.columns[item].visible === false) {
        this.value.push(parseInt(item))
      }
    }
  },
  methods: {
    // 搜索
    toggleSearch () {
      this.$emit('update:showSearch', !this.showSearch)
    },
    // 刷新
    refresh () {
      this.$emit('getTableData')
    },
    // 右侧列表元素变化
    dataChange (data) {
      for (const item in this.columns) {
        const key = this.columns[item].key
        this.columns[item].visible = !data.includes(key)
      }
    },
    // 打开显隐列dialog
    showColumn () {
      this.open = true
    }
  }
}
</script>
<style lang="scss" scoped>
.el-transfer {
  padding: 20px !important;
}
.el-transfer__buttons {
  border-radius: 50%;
  padding: 12px;
  display: block;
  margin-left: 0px;
}
.el-transfer__buttons:first-child {
  margin-bottom: 10px !important;
}
</style>
