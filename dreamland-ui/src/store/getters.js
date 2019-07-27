const getters = {
  sidebar: state => state.settings.sidebar,
  device: state => state.settings.device,
  title: state => state.settings.title,
  token: state => state.user.token,
  uid: state => state.user.uid,
  name: state => state.user.name,
  roles: state => state.user.roles,
  permission_routes: state => state.permission.routes,
}
export default getters
