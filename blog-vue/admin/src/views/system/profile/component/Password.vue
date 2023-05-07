<template>
  <el-dialog
    title="修改密码"
    width="500px"
    :before-close="handleDialogClose"
    :visible="passwordVisible"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
  >
    <p>
      点击下面的按钮即可向
      <span style="color: #409eff">{{ username }}</span>
      发送一封邮件，我们将通过邮件中的验证码来确定您的身份
    </p>
    <el-form>
      <el-form-item>
        <el-row type="flex" :gutter="10" justify="center">
          <el-col :span="14">
            <el-input
              placeholder="请输入邮箱中的验证码"
              prefix-icon="el-icon-circle-check"
              v-model="code"
            ></el-input>
          </el-col>
          <el-col :span="10">
            <el-button :disabled="!show" icon="el-icon-s-promotion" type="success" @click="getEmailCode">
              {{ show ? '发送邮件' : '请 ' + count + ' 秒后重新获取' }}

            </el-button>
          </el-col>
        </el-row>
      </el-form-item>

      <el-form-item>
        <el-input
          prefix-icon="el-icon-lock"
          placeholder="请输入新密码"
          type="password"
          v-model="password"
        ></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer">
      <div class="button-container">
        <el-button type="primary" @click="updateUserPassword" :loading="passwordLoading">确认修改</el-button>
      </div>
    </span>
  </el-dialog>
</template>

<script>
import { sendEmailCode, updatePassword } from '../../../../api/user'

export default {
  name: 'Password',

  props: {
    // 用户ID
    id: {
      type: Number,
      require: true
    },
    // 用户账号（用于发送验证码）
    username: {
      type: String,
      default: ''
    },
    // 定义是否展示
    passwordVisible: {
      type: Boolean,
      require: true,
      default: false
    }
  },

  data () {
    return {
      // 是否显示对话框
      passwordVisibled: this.passwordVisible,
      // 验证码
      code: '',
      // 用户输入密码
      password: '',
      // 是否显示发送邮件按钮
      show: true,
      // 发送邮件倒计时
      count: 60,
      // 定时器
      intervalButton: {},
      // 修改密码按钮加载框
      passwordLoading: false
    }
  },

  watch: {
    passwordVisible (val) {
      // 新增visble的监听变更并同步
      this.passwordVisibled = val
    }
  },

  methods: {
    // 关闭对话框
    handleDialogClose () {
      this.passwordVisibled = false
      // 组件内变更后向外部发送事件通知
      this.$emit('func', this.passwordVisibled)
    },
    // 发送邮箱验证码
    getEmailCode () {
      // 切换按钮状态
      this.show = false
      const TIME_COUNT = 60
      sendEmailCode().then((res) => {
        this.$message.success('邮件发送成功，请注意查看邮箱')
      }).catch(() => {})
      // 开始60秒倒计时
      this.intervalButton = setInterval(() => {
        if (this.count > 0 && this.count <= TIME_COUNT) {
          this.count--
        } else {
          // 切换按钮状态
          this.show = true
          // 清除计时器
          clearInterval(this.intervalButton)
          // 重置计时器
          this.count = 60
        }
      }, 1000)
    },
    // 更新密码
    updateUserPassword () {
      // 开启加载密码等待框
      this.passwordLoading = true
      updatePassword({
        id: this.id,
        username: this.username,
        code: this.code,
        password: this.password
      }).then((res) => {
        // 关闭加载密码等待框
        this.passwordLoading = false
        // 提示重新登录
        this.$notify({
          title: '更新成功',
          message: '3秒后将自动退出系统，请重新登录',
          type: 'success'
        })
        // 防止出现this指代不明的情况
        const that = this
        // 3秒后执行退出登录方法
        setTimeout(function () {
          // 清除当前用户信息
          that.$store.dispatch('user/logout')
          // 刷新当前页面即可
          location.reload()
        }, 3000)
      }).catch(() => {
        // 关闭加载密码等待框
        this.passwordLoading = false
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.el-dialog__body {
  padding: 0 20px;
}
.button-container {
  width: 100%;
  height: 100%;
  text-align: center;

  .el-button {
    width: 100%;
    border-radius: 10px;
  }
}
</style>
