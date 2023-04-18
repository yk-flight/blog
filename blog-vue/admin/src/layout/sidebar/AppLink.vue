<template>
  <component :is="type" v-bind="linkProps(to)">
    <slot />
  </component>
</template>

<script>
import { isExternal } from '../../utils/validate'

export default {
  name: 'AppLink',

  props: {
    // 路由跳转位置
    to: {
      type: String,
      require: true
    }
  },

  computed: {
    // 校验传来的路径是否为外部链接
    isExternal () {
      // 传来的链接进行校验
      return isExternal(this.to)
    },
    // 根据类型来判断链接方式
    type () {
      if (this.isExternal) {
        return 'a'
      }
      return 'router-link'
    }
  },

  methods: {
    /**
     * 判断当前是否为外部链接
     *
     * @param {*} to 路径
     */
    linkProps (to) {
      if (this.isExternal) {
        return {
          // 跳转路径
          href: to,
          // 空白页面打开
          target: '_blank'
        }
      }
      return {
        to: to
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
