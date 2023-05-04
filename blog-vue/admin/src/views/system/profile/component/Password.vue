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
      我们将要向
      <span style="color: #409eff">{{ username }}</span>
      发送一封邮件，请您查看邮件中的验证码
    </p>
    <el-form>
      <el-form-item>
        <el-row type="flex" :gutter="20" justify="center">
          <el-col :span="16">
            <el-input
              placeholder="请输入邮箱中的验证码"
              prefix-icon="el-icon-circle-check"
              v-model="code"
            ></el-input>
          </el-col>
          <el-col :span="8">
            <el-button icon="el-icon-s-promotion" type="success"
              >发送邮件</el-button
            >
          </el-col>
        </el-row>
      </el-form-item>

      <el-form-item>
        <el-input
          placeholder="请输入新密码"
          type="password"
          v-model="password"
        ></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer">
      <div class="button-container">
        <el-button type="primary">确认修改</el-button>
      </div>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'Password',

  props: {
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
      password: ''
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
    }
  }
}
</script>

<style lang="scss" scoped>
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
