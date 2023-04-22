<script>
export default {
  name: 'MenuItem',
  props: {
    // 菜单图标
    icon: {
      type: String,
      default: ''
    },
    // 菜单标题
    title: {
      type: String,
      default: ''
    }
  },
  // 函数组件
  functional: true,
  render (h, context) {
    // 获取到标题和图表
    const { icon, title } = context.props
    const nodes = []
    if (icon) {
      // Svg-Icon
      nodes.push(<svg-icon icon={(icon)}></svg-icon>)
    }
    if (title) {
      nodes.push(<span slot='title'>{(title)}</span>)
    }
    return nodes
  }

}
</script>
