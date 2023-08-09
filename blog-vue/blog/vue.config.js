// 使用svg图标时的路径处理
const path = require('path')
function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  // 配置使用svg图标
  chainWebpack (config) {
    config.module.rule('svg').exclude.add(resolve('src/icons')).end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      }).end()
  }
}
