const app = {
  state: {
    // 菜单是否折叠
    isCollapse: false,
    // 右侧面板是否显示
    rightPanelShow: false,
    // 标签页
    tagsViewList: [
      {
        fullPath: '/home',
        name: 'Home',
        path: '/home',
        meta: {
          title: '首页',
          icon: 'dashboard'
        }
      }
    ]
  },
  mutations: {
    SET_COLLAPSE (state) {
      state.isCollapse = !state.isCollapse
    },
    SET_RIGHT_PANEL (state) {
      state.rightPanelShow = !state.rightPanelShow
    },
    // 添加标签页
    ADD_TAGS_VIEW (state, tag) {
      const isFind = state.tagsViewList.find((item) => {
        return item.path === tag.path
      })
      // 处理重复
      if (!isFind) {
        // 如果不存在重复的标签页
        state.tagsViewList.push(tag)
      }
    },
    // 为指定的 tag 修改 title
    CHANGE_TAGS_VIEW (state, { index, tag }) {
      state.tagsViewList[index] = tag
    },
    /**
     * 关闭标签页
     *
     * @param { type: 'other' || 'right' || 'index' } payload 载荷
     */
    REMOVE_TAGS_VIEW (state, payload) {
      // 关闭当前页
      if (payload.type === 'index') {
        // 删除当前指定的一项标签页
        state.tagsViewList.splice(payload.index, 1)
      } else if (payload.type === 'other') {
        // 删除当前位置之后的标签页
        state.tagsViewList.splice(
          payload.index + 1,
          state.tagsViewList.length - payload.index + 1
        )
        // 删除当前位置之前的标签页
        state.tagsViewList.splice(1, payload.index - 1)
      } else if (payload.type === 'right') {
        // 删除当前位置之后的标签页
        state.tagsViewList.splice(
          payload.index + 1,
          state.tagsViewList.length - payload.index + 1
        )
      }
    },
    // 关闭全部标签页
    CLOSE_ALL_TAGS (state) {
      state.tagsViewList = [
        {
          fullPath: '/home',
          name: 'Home',
          path: '/home',
          meta: {
            title: '首页',
            icon: 'dashboard'
          }
        }
      ]
    }
  },
  // 异步处理方式
  actions: {}
}

export default app
