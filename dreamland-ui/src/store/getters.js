const getters = {
  sidebar: state => state.settings.sidebar,
  device: state => state.settings.device,
  title: state => state.settings.title,
  token: state => state.user.token,
  name: state => state.user.name,
}
export default getters
