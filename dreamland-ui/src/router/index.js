import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)
/* Router Modules */

export const constantRoutes = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: {title: '首页', icon: 'dashboard', affix: true}
      }
    ]
  },
  {
    path: '/auth',
    component: Layout,
    redirect: '/auth/appuser',
    meta: {title: '用户管理', icon: 'example', affix: true},
    children: [
      {
        path: 'appuser',
        component: () => import('@/views/auth/appuser/index'),
        name: 'Appuser',
        meta: {title: '注册会员管理', icon: 'table', affix: true}
      },
      {
        path: 'appdevice',
        component: () => import('@/views/auth/appdevice/index'),
        name: 'Appdevice',
        meta: {title: '移动设备管理', icon: 'table', affix: true}
      },
      {
        path: 'appidcard',
        component: () => import('@/views/auth/appidcard/index'),
        name: 'Appidcard',
        meta: {title: '实名信息管理', icon: 'table', affix: true}
      }
    ]
  },
  {
    path: '/basic',
    component: Layout,
    redirect: '/basic/advertisement',
    meta: {title: '营销管理', icon: 'example', affix: true},
    children: [
      {
        path: 'file',
        component: () => import('@/views/basic/file/index'),
        name: 'File',
        meta: {title: '文件管理', icon: 'table', affix: true}
      },
      {
        path: 'article',
        component: () => import('@/views/basic/article/index'),
        name: 'Article',
        meta: {title: '文章管理', icon: 'table', affix: true}
      }
    ]
  },
  {
    path: '/evaluation',
    component: Layout,
    redirect: '/evaluation/after-sale',
    meta: {title: '评价管理', icon: 'example', affix: true},
    children: [
      {
        path: 'after-sale',
        component: () => import('@/views/evaluation/after-sale/index'),
        name: 'AfterSale',
        meta: {title: '售后服务', icon: 'table', affix: true}
      },
      {
        path: 'comment',
        component: () => import('@/views/evaluation/comment/index'),
        name: 'Comment',
        meta: {title: '商品评价', icon: 'table', affix: true}
      },
      {
        path: 'person-comment',
        component: () => import('@/views/evaluation/person-comment/index'),
        name: 'PersonComment',
        meta: {title: '维修人员评价', icon: 'table', affix: true}
      }
    ]
  },
  {
    path: '/service',
    component: Layout,
    redirect: '/service/reservation',
    meta: {title: '服务系统', icon: 'example', affix: true},
    children: [
      {
        path: 'reservation',
        component: () => import('@/views/service/reservation/index'),
        name: 'Reservation',
        meta: {title: '服务预约管理', icon: 'table', affix: true}
      },
      {
        path: 'payment',
        component: () => import('@/views/service/payment/index'),
        name: 'Payment',
        meta: {title: '服务支付管理', icon: 'table', affix: true}
      }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  base: '/manager/',
  mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
