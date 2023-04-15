import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
// ========================= 导入外部组件 =========================
import axios from "axios";
import animate from "animate.css";
import NProgress from "nprogress";
import VueTypedJs from "vue-typed-js";
// ========================= 导入CSS样式 =========================
import './style/css/index.css'
import "nprogress/nprogress.css";

Vue.prototype.$axios = axios;

Vue.use(NProgress)
Vue.use(animate)
Vue.use(VueTypedJs)

// 调整进度条的速度
NProgress.configure({ easing: "ease", speed: 400 });

// 路由导航守卫
// to：当前位置，from：从哪来
router.beforeEach(async (to, from, next) => {
  // 开启页面进度条
  NProgress.start();
  // 修改网站的标题
  // if (to.meta.title) {
  //   window.document.title = to.meta.title;
  // }
  // 放行
  next();
});

router.afterEach((to, from) => {
  // 切换路由时返回到顶部
  window.scrollTo({
    top: 0,
    behavior: "instant",
  });
  // 关闭进度条
  NProgress.done();
});

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
