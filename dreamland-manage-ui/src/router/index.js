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
    path: '/system',
    component: Layout,
    redirect: '/system/app-user',
    meta: {title: '用户管理', icon: 'example', affix: true},
    children: [
      {
        path: 'app-user',
        component: () => import('@/views/system/app-user/index'),
        name: 'AppUser',
        meta: {title: '注册会员管理', icon: 'table', affix: true}
      },
/*      {
        path: 'staff',
        component: () => import('@/views/system/app-user/index'),
        name: 'AppStaff',
        meta: {title: '维修人员管理', icon: 'table', affix: true}
      },*/
      {
        path: 'manage',
        component: () => import('@/views/system/manage-user/index'),
        name: 'AppManage',
        meta: {title: '后台人员管理', icon: 'table', affix: true}
      },
      {
        path: 'app-device',
        component: () => import('@/views/system/app-device/index'),
        name: 'AppDevice',
        meta: {title: '注册移动设备管理', icon: 'table', affix: true}
      },
      {
        path: 'app-id-card',
        component: () => import('@/views/system/app-id-card/index'),
        name: 'appIdCard',
        meta: {title: '注册实名信息管理', icon: 'table', affix: true}
      }
    ]
  },
  {
    path: '/basic',
    component: Layout,
    redirect: '/basic/advertisement',
    meta: {title: '系统管理', icon: 'example', affix: true},
    children: [
      {
        path: 'file',
        component: () => import('@/views/basic/file/index'),
        name: 'File',
        meta: {title: '系统文件管理', icon: 'table', affix: true}
      },
      {
        path: 'article',
        component: () => import('@/views/basic/article/index'),
        name: 'Article',
        meta: {title: '营销文章管理', icon: 'table', affix: true}
      },
      {
        path: 'marketing',
        component: () => import('@/views/basic/marketing/index'),
        name: 'Marketing',
        meta: {title: '营销广告管理', icon: 'table', affix: true}
      }
    ]
  },
  {
    path: '/wbs',
    component: Layout,
    redirect: '/wbs/service-item',
    meta: {title: '维保设管理', icon: 'example', affix: true},
    children: [
      {
        path: 'service-item',
        component: () => import('@/views/wbs/serviceitem/index'),
        name: 'AfterSale',
        meta: {title: '维保设条目管理', icon: 'table', affix: true}
      },
      {
        path: 'comment',
        component: () => import('@/views/wbs/comment/index'),
        name: 'Comment',
        meta: {title: '维保设条目评价', icon: 'table', affix: true}
      },
      {
        path: 'payment',
        component: () => import('@/views/wbs/payment/index'),
        name: 'Payment',
        meta: {title: '服务支付管理', icon: 'table', affix: true}
      },
      {
        path: 'person-comment',
        component: () => import('@/views/wbs/person-comment/index'),
        name: 'PersonComment',
        meta: {title: '维保设人员评价', icon: 'table', affix: true}
      },
      {
        path: 'after-sale',
        component: () => import('@/views/wbs/after-sale/index'),
        name: 'AfterSale',
        meta: {title: '维保设售后服务', icon: 'table', affix: true}
      }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

const createRouter = () => new Router({
  base: '/manage/',
  //mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
