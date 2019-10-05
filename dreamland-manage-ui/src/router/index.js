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
        meta: {title: '会员管理', icon: 'user', affix: true}
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
        meta: {title: '后台管理', icon: 'user', affix: true}
      },
      {
        path: 'app-device',
        component: () => import('@/views/system/app-device/index'),
        name: 'AppDevice',
        meta: {title: '设备管理', icon: 'tab', affix: true}
      },
      {
        path: 'app-id-card',
        component: () => import('@/views/system/app-id-card/index'),
        name: 'appIdCard',
        meta: {title: '实名管理', icon: 'people', affix: true}
      }
    ]
  },
  {
    path: '/basic',
    component: Layout,
    redirect: '/basic/marketing',
    meta: {title: '系统管理', icon: 'example', affix: true},
    children: [
      {
        path: 'file',
        component: () => import('@/views/basic/file/index'),
        name: 'File',
        meta: {title: '文件管理', icon: 'zip', affix: true}
      },
      {
        path: 'article',
        component: () => import('@/views/basic/article/index'),
        name: 'Article',
        meta: {title: '文章管理', icon: 'guide', affix: true}
      },
      {
        path: 'marketing',
        component: () => import('@/views/basic/marketing/index'),
        name: 'Marketing',
        meta: {title: '广告管理', icon: 'nested', affix: true}
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
        component: () => import('@/views/wbs/item/index'),
        name: 'ServiceItem',
        meta: {title: '条目管理', icon: 'tree', affix: true}
      },
      {
        path: '/order',
        component: () => import('@/views/wbs/order/index'),
        redirect: '/wbs/order/service-item',
        meta: {title: '订单管理', icon: 'example', affix: true},
        children: [
          {
            path: 'repair-order',
            component: () => import('@/views/wbs/order/repair/index'),
            name: 'RepairOrder',
            meta: {title: '维修订单', icon: 'tree'}
          },
          {
            path: 'design-order',
            component: () => import('@/views/wbs/order/design/index'),
            name: 'DesignOrder',
            meta: {title: '设计订单', icon: 'tree'}
          },
          {
            path: 'maintenance-order',
            component: () => import('@/views/wbs/order/maintenance/index'),
            name: 'MaintenanceOrder',
            meta: {title: '保养订单', icon: 'tree'}
          }
        ]
      },
      {
        path: '/comment',
        component: () => import('@/views/wbs/comment/index'),
        redirect: '/wbs/comment/item-comment',
        meta: {title: '评价管理', icon: 'example', affix: true},
        children: [
          {
            path: 'item-comment',
            component: () => import('@/views/wbs/comment/item/index'),
            name: 'ItemComment',
            meta: {title: '条目评价', icon: 'star', affix: true}
          },
          {
            path: 'staff-comment',
            component: () => import('@/views/wbs/comment/staff/index'),
            name: 'StaffComment',
            meta: {title: '人员评价', icon: 'star', affix: true}
          },
        ]
      },
      {
        path: 'payment',
        component: () => import('@/views/wbs/payment/index'),
        name: 'Payment',
        meta: {title: '支付管理', icon: 'money', affix: true}
      },
      {
        path: 'after-sale',
        component: () => import('@/views/wbs/after-sale/index'),
        name: 'AfterSale',
        meta: {title: '售后管理', icon: 'star', affix: true}
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
