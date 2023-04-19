<template>
  <el-row class="login-container">
    <!-- 左侧输入表单 -->
    <el-col :span="10" class="left-container">
      <div class="login-form">
        <div class="login-title">
          <h2>用户登录</h2>
          <span>Happy to see you again!</span>
        </div>
        <el-form :model="loginForm" :rules="loginRules" ref="loginForm">
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              prefix-icon="el-icon-user"
              placeholder="请输入用户名"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              prefix-icon="el-icon-lock"
              type="password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
          <el-row :gutter="10">
            <el-col :span="13">
              <el-image
                class="captcha"
                :src="captcha"
                fit="contain"
                @click="getCaptcha()"
              ></el-image>
            </el-col>
            <el-col :span="11">
              <el-form-item prop="code">
                <el-input
                  v-model="loginForm.code"
                  prefix-icon="el-icon-circle-check"
                  placeholder="点击刷新验证码"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleLogin()">
              登录
          </el-button>
        </el-form>

        <div class="login-method">
          <el-tooltip class="item" effect="dark" content="QQ登录" placement="bottom">
            <svg-icon icon="QQ"></svg-icon>
          </el-tooltip>

          <el-tooltip class="item" effect="dark" content="Git登录" placement="bottom">
            <svg-icon icon="gitee"></svg-icon>
          </el-tooltip>

          <el-tooltip class="item" effect="dark" content="GitHub登录" placement="bottom">
            <svg-icon icon="github"></svg-icon>
          </el-tooltip>
        </div>
      </div>
    </el-col>
    <!-- 右侧背景图 -->
    <el-col :span="14" class="right-container">
      <span class="footer-span"> Copyright © 2023 世纪末的架构师 </span>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'Login',

  data () {
    return {
      // 加载等待框
      loading: false,
      // 验证码
      captcha: '',
      // 用户登录表单
      loginForm: {
        // 用户名
        username: '',
        // 密码
        password: '',
        // 验证码
        code: undefined
      },
      // 登录校验规则
      loginRules: {
        // 用户名校验规则
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱格式', trigger: ['blur', 'change'] }
        ],
        // 密码校验规则
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        // 验证码校验规则
        code: [{ required: true, message: '验证码不能为空', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    // 获取验证码
    this.getCaptcha()
  },
  methods: {
    /**
     * 获取验证码
     */
    getCaptcha () {
      // 加入时间戳确保每一次请求都不重复
      this.captcha =
        process.env.VUE_APP_CAPTCHA_URL +
        '/captcha/getCaptcha?time=' +
        new Date()
    },
    /**
     * 用户登录
     */
    handleLogin () {
      this.$refs.loginForm.validate((valid) => {
        this.loading = true
        if (valid) {
          this.$store.dispatch('login', this.loginForm).then(() => {
            // 跳转到后台首页
            this.$router.replace('/home')
          }).catch(() => {
            // 关闭等待框
            this.loading = false
          })
        } else {
          // 关闭等待框
          this.loading = false
          this.$message.error('请输入所有内容')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.login-container {
  height: 100%;
  width: 100%;
}

.left-container {
  display: flex;
  height: 100%;
  padding: 0 10px;
  justify-content: center;
  align-items: center;
}

.el-button {
  width: 100%;
}

.right-container {
  background: url("../../assets/images/background.jpg") no-repeat center;
  background-size: cover;
  height: 100%;
  display: flex;
  justify-content: center;
}

.login-form {
  width: 55%;
}

.login-method {
  margin-top: 50px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

  .svg-icon {
    font-size: 30px;
    margin: 0 15px;
    cursor: pointer;
  }
}

.footer-span {
  position: absolute;
  color: #fff;
  font-weight: 500;
  font-size: 0.875rem;
  bottom: 0.5rem;
}

.login-title {
  margin-bottom: 2rem;

  h2 {
    margin-bottom: 0.875rem;
  }

  span {
    font-size: 0.937rem;
    font-weight: 300;
    color: #606266;
  }
}

.captcha {
  width: 100%;
  height: 40px;
  cursor: pointer;
}
</style>
