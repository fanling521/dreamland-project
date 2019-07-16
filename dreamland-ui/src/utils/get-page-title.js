import defaultSettings from '@/settings'

const title = defaultSettings.title || '信息管理系统V2'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
