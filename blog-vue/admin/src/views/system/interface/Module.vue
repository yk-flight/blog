<template>
  <div>
    模块管理 {{ id }}
  </div>
</template>

<script>
export default {
  name: 'Module',

  data () {
    return {
      id: undefined
    }
  },

  created () {
    // 从当前路由中截取出文章ID
    this.id = this.$route.params.id
  },

  methods: {

  }
}
</script>

<style lang="scss" scoped>

</style>
