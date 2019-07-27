import Layout from '@/layout'

const systemRouter = {
  path: '/system',
  component: Layout,
  name: 'System',
  meta: {title: '系统管理', icon: 'example'},
  children: [
    {
      path: 'user',
      component: () => import('@/views/user/index'),
      name: 'User',
      meta: {title: '用户管理', icon: 'people', noCache: true},
      roles: ['admin']
    },
    {
      path: 'role',
      component: () => import('@/views/role/index'),
      name: 'Role',
      meta: {title: '角色管理', icon: 'peoples', noCache: true},
      roles: ['admin']
    }
  ]
}
export default systemRouter
