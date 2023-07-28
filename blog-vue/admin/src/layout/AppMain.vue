<template>
  <div>
    <transition name="fade-transform" mode="out-in">
      <router-view :key="$route.fullPath" />
    </transition>
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
  mounted () {
    // 如果vuex中的标签页只有首页并且当前路由不是首页
    if (this.$store.getters.tagsViewList.length === 1 && this.$route.path !== '/home') {
      this.addTagsViewList(this.$route)
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
      this.$store.commit('app/ADD_TAGS_VIEW', {
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
