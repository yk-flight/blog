import router from './router'
import NProgress from 'nprogress'
import store from './store'
import { Message } from 'element-ui'
import { getItem } from './utils/cookie'

// 调整进度条的速度
NProgress.configure({ easing: 'ease', speed: 500 })

// to：当前位置，from：从哪来
router.beforeEach(async (to, from, next) => {
  // 开启页面进度条
  NProgress.start()
  // 如果本地已经缓存token
  if (getItem('token')) {
    // 修改网站的标题
    window.document.title = to.meta.title
    // 如果用户已登录，则不允许进入登录页面
    if (to.path === '/login') {
      // 跳回首页
      next('/home')
    } else {
      // 判断用户信息是否存在，如果不存在则获取用户信息
      if (store.getters.roles.length === 0) {
        store.dispatch('user/getUserInfo').then(res => {
          // 调用生成菜单的方法
          store.dispatch('permission/generateRoutes').then((accessRoutes) => {
            for (const item of accessRoutes) {
              router.addRoute(item)
            }
            // hack方法 确保路由生成已完成
            next({ ...to, replace: true })
          })
        }).catch(error => {
          Message.error(error)
        })
      }
      next()
    }
  } else {
    // 放行登录页面
    if (to.path === '/login') {
      next()
    } else {
      // 否则在没有登录之前的一切路由都重定向到登录页面
      next('/login')
    }
  }
})

router.afterEach((to, from) => {
  // 关闭进度条
  NProgress.done()
})
