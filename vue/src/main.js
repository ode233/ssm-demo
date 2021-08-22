// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import VueCookies from 'vue-cookies'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

/* eslint-disable no-new */

Vue.use(ElementUI)
Vue.use(VueCookies)


new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

router.beforeEach(function (to, from, next) {
  if (to.matched.some(record => record.meta.requireAuth)) {
    if (!VueCookies.get('name')) {
      next({
        path: '/login',
        // query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

