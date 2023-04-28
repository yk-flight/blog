// 使用svg图标时的路径处理
const path = require('path')
function resolve (dir) {
  return path.join(__dirname, dir)
}
// 网站标题
const name = process.env.VUE_APP_TITLE || '后台管理系统'

module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,
    proxy: {
      // 所有带'/api'请求的都要进行代理转发
      '/api': {
        // websocket
        ws: false,
        // 代理的目标地址
        target: process.env.VUE_APP_BASE_URL,
        // 配置跨域
        changeOrigin: true,
        // 将请求中的'/api'替换为空
        pathRewrite: { '^/api': '' }
      }
    }
  },
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
      })
      .end()
  },
  // 配置网站标题
  configureWebpack: {
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  }
}
