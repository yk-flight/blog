import dayjs from 'dayjs'
import rt from 'dayjs/plugin/relativeTime'
import 'dayjs/locale/zh-cn'

// 具体时间
const dateFilter = (val, format = 'YYYY-MM-DD HH:mm:ss') => {
  // 如果当前的 val 可以被转换为一个数字
  if (!isNaN(val)) {
    val = parseInt(val)
  }
  return dayjs(val).format(format)
}

dayjs.extend(rt)
// 相对时间
function relativeTime (val) {
  // 如果当前的 val 可以被转换为一个数字
  if (!isNaN(val)) {
    val = parseInt(val)
  }
  return dayjs().locale('zh-cn').to(dayjs(val))
}

// 格式化文件大小，从字节转换为对应单位
const fileSizeFilter = (bytes) => {
  // 如果文件大小为0则直接返回0字节
  if (bytes === 0) return '0B'
  // 1k为1024字节
  const k = 1024
  // 定义数组存储不同文件大小单位的后缀（B、KB、MB等），以及相应的指数值
  const sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB']
  // 当前文件大小在哪个单位之内，并向下取整到最接近的整数
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  // 计算文件大小，并使用toPrecision方法将其保留3位有效数字
  const size = (bytes / Math.pow(k, i)).toPrecision(3)
  const unit = sizes[i]
  // 返回带有文件大小和单位后缀的字符串
  return `${size} ${unit}`
}

// 文件类型过滤器
const fileTypeFilter = (type) => {
  // 将字符串转为小写
  type = type.toLowerCase()
  // 图片类型
  const picture = ['.jpg', '.jpeg', '.png', '.gif']
  if (picture.includes(type)) {
    return '图片'
  }
  const office = ['.doc', '.docx', '.xls', '.xlsx', '.ppt', '.pptx']
  if (office.includes(type)) {
    return '文档'
  }
  return '未知'
}

// 时间戳显示过滤器
export default {
  dateFilter,
  relativeTime,
  fileSizeFilter,
  fileTypeFilter
}
