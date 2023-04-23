<template>
  <div>
    <router-view />
  </div>
</template>

<script>
import { isTags } from '../utils/tags'

export default {
  name: 'AppMain',

  data () {
    return {

    }
  },

  watch: {
    $route (to, from) {
      this.addTagsViewList(to)
    }
  },

  methods: {
    // 添加到标签栏
    addTagsViewList (to) {
      // 并不是所有的路由都不需要保存
      if (!isTags(to.path)) return
      // 从 to 中解构出想要的属性
      const { fullPath, meta, name, params, path, query } = to
      this.$store.commit('ADD_TAGS_VIEW', {
        fullPath,
        meta,
        name,
        params,
        path,
        query,
        title: this.$route.name
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
