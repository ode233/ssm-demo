import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Main from '../components/Main'
import UserProfile from '../components/main/Profile'
import DocList from '../components/main/DocList'
import NotFound from '../components/NotFound'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/',
      name: 'Main',
      component: Main,
      redirect: '/user/profile/',
      meta:{
        requireAuth:true
      },
      children: [
        {
          path: '/user/profile',
          name: 'UserProfile',
          component: UserProfile,
        },
        {
          path: '/doc/list',
          name: 'DocList',
          component: DocList
        }
      ]
    },
    {
      path: '*',
      component: NotFound
    }
  ]
})
