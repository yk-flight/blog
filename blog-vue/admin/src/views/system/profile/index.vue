<template>
  <div class="profile-container">
    <div class="card-panel">
      <div class="card-panel-body">
        <el-row>
          <el-col :lg="10" :xs="24" class="card-panel-body-left">
            <div class="card-panel-body-avatar">
              <!-- 用户上传头像 -->
              <div class="user-info-head" @click="editAvatar">
                <el-avatar :src="avatar" :size="150"></el-avatar>
              </div>
            </div>
            <div class="card-panel-body-content">
              <div class="card-panel-body-nick">
                {{ userInfo.nickname }}
              </div>
              <div class="card-panel-body-create">
                创建时间：{{ userInfo.createTime | dateFilter }}
              </div>
            </div>
            <el-divider content-position="center">信息绑定</el-divider>

            <div class="card-panel-body-info">
              <div class="card-panel-body-info-item">
                <span>
                  手机：
                  <span v-if="userInfo.phone">15234417033</span>
                  <span v-else>暂未绑定手机</span>
                </span>

                <el-button type="success" icon="el-icon-mobile-phone" size="mini">修改手机</el-button>
              </div>
            </div>

            <el-divider></el-divider>

            <div class="card-panel-footer">
              <el-button type="danger" icon="el-icon-lock" size="small" @click="openPasswordDialog">修改密码</el-button>
            </div>

          </el-col>
          <el-col :lg="14" :xs="24" class="card-panel-body-right">
            <div class="card-panel-body-right-content">
              <el-divider content-position="center">个人信息</el-divider>
              <el-form label-width="82px">
                <!-- 用户名 -->
                <el-form-item prop="username" label="用户名：">
                  <el-input
                    placeholder="请输入用户名"
                    v-model="userInfo.username"
                    type="text"
                    size="small"
                  >
                  </el-input>
                </el-form-item>
                <!-- 角色 -->
                <el-form-item label="角色：">
                  <span>{{ userInfo.roleName }}</span>
                </el-form-item>
                <!-- 用户昵称 -->
                <el-form-item prop="nickname" label="昵称：">
                  <el-input
                    placeholder="请输入用户昵称"
                    v-model="userInfo.nickname"
                    type="text"
                    size="small"
                  >
                  </el-input>
                </el-form-item>
                <!-- 登录地址 -->
                <el-form-item label="登录地址：">
                  <span>{{ userInfo.ipSource }} （ {{ userInfo.ipAddress }} ）</span>
                </el-form-item>
                <!-- 登录设备 -->
                <el-form-item label="登录设备：">
                  <span>{{ userInfo.device  }}</span>
                </el-form-item>
                <!-- 备注 -->
                <el-form-item label="备注：">
                  <el-input
                    type="textarea"
                    rows="2"
                    placeholder="这里输入备注"
                    v-model="userInfo.remark"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div class="card-panel-content-button">
                <el-button
                  type="primary"
                  icon="el-icon-success"
                  size="small"
                  @click="updateUser"
                  :loading="saveLoading">
                  保存
                </el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <!-- 修改密码对话框 -->
    <password :id="userInfo.id"
      :username="userInfo.username"
      :visible="passwordVisible"
      @close="closePasswordDialog">
    </password>

    <file-space
      :visible="avatarVisible"
      @confirm="changeAvatar"
      :limit="1"
      :text="'只能上传 .jpeg .jpg .png .gif 格式的文件，且不超过2MB'"
      :accept="['.jpeg', '.jpg', '.png', '.gif']"
      :size="2"
      @close="closeFileSpace">
    </file-space>
  </div>
</template>

<script>
import { getUserInfo, updateUser } from '../../../api/user'
import { mapGetters } from 'vuex'
import Password from './component/Password.vue'
import FileSpace from '../../../components/FileSpace/index.vue'

export default {
  name: 'Profile',

  components: { Password, FileSpace },

  computed: {
    ...mapGetters(['avatar'])
  },

  data () {
    return {
      // 保存按钮等待框
      saveLoading: false,
      // 更新密码按钮等待框
      passwordLoading: false,
      // 更新密码对话框
      passwordVisible: false,
      // 修改头像对话框
      avatarVisible: false,
      // 用户信息
      userInfo: {
        // 用户ID
        id: undefined,
        // 用户名
        username: undefined,
        // 用户昵称
        nickname: undefined,
        // 角色名称
        roleName: undefined,
        // 登录IP
        ipAddress: undefined,
        // IP属地
        ipSource: undefined,
        // 登录设备
        device: undefined,
        // 备注
        remark: undefined,
        // 手机号码
        phone: undefined,
        // 创建时间
        createTime: undefined
      },
      // 更新用户头像表单
      avatarForm: {
        // 头像路径
        src: undefined,
        // 用户ID
        userId: undefined
      }
    }
  },

  mounted () {
    this.getUserInfo()
  },

  methods: {
    /**
     * 获取用户个人信息
     */
    getUserInfo () {
      getUserInfo().then((res) => {
        this.userInfo = res
      })
    },
    /**
     * 打开更新用户密码弹窗
     */
    openPasswordDialog () {
      this.passwordVisible = true
    },
    /**
     * 关闭更新用户密码弹窗
     */
    closePasswordDialog () {
      this.passwordVisible = false
    },
    /**
     * 更新用户个人信息
     */
    updateUser () {
      // 开启等待框
      this.saveLoading = true
      updateUser({
        id: this.userInfo.id,
        avatar: this.userInfo.avatar,
        username: this.userInfo.username,
        nickname: this.userInfo.nickname,
        phone: '',
        remark: this.userInfo.remark
      }).then((res) => {
        this.$message.success('更新成功')
        // 重新获取一次个人信息
        this.$store.dispatch('user/getUserInfo')
        // 关闭更新等待框
        this.saveLoading = false
      }).catch(() => {
        // 关闭更新等待框
        this.saveLoading = false
      })
    },
    // 打开文件空间对话框
    editAvatar () {
      this.avatarVisible = true
    },
    // 关闭文件选择框
    closeFileSpace () {
      this.avatarVisible = false
    },
    // 更新用户头像
    changeAvatar (value) {
      // 赋值当前用户ID与头像路径
      this.avatarForm.userId = this.userInfo.id
      this.avatarForm.src = value[0].src
      // 用户头像值用传来的值进行覆盖
      this.$store.dispatch('user/updateAvatar', this.avatarForm).then((res) => {
        this.$message.success('用户头像更新成功')
      }).catch(() => {})
    }
  }
}
</script>

<style lang="scss" scoped>
.profile-container{
  display: flex;
  justify-content: center;
  padding: 100px 20px;

  .el-divider__text {
    font-size: 16px;
    font-weight: 400;
    color: #303133;
  }

  .card-panel {
    width: 900px;
    height: auto;
    background: #ffffff;
    // border-radius: 15px;
    box-shadow: 3px 0 12px rgba(0, 21, 41, 0.35);
  }

  .card-panel-body {
    width: 100%;
    height: 100%;
  }

  .card-panel-body-left {
    height: 100%;
    // border-radius: 15px;
    box-shadow: 0 0 6px rgba(0, 21, 41, 0.35);
  }
  .card-panel-body-avatar {
    padding-top: 10px;
    margin: 20px 0px;
    text-align: center;
    width: 100%;
  }

  .card-panel-body-content {
    text-align: center;
    margin: 10px 0;
  }

  .card-panel-body-nick {
    color: #303133;
    font-weight: 700;
    margin-bottom: 10px;
  }

  .card-panel-body-create {
    font-size: 14px;
    color: #777;
  }

  .card-panel-footer {
    text-align: center;
    margin-bottom: 30px;

    .el-button {
      width: 80%;
    }
  }

  .card-panel-body-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
  }

  .card-panel-body-info-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 80%;
    margin: 10px 0;

    span {
      font-size: 14px;
    }
  }

  .card-panel-body-right {
    display: flex;
    justify-content: center;
  }

  .card-panel-body-right-content {
    padding: 10px 0;
    width: 85%;
    height: 100%;

    .el-form-item {
      margin-bottom: 13px;
    }
  }

  .card-panel-content-button {
    text-align: center;
    padding-top: 13px;
    margin-bottom: 17px;

    .el-button {
      width: 100%;
    }
  }

  .user-info-head {
    position: relative;
    display: inline-block;
    height: 150px;
  }

  .user-info-head:hover:after {
    content: '+';
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    color: #eee;
    background: rgba(0, 0, 0, 0.5);
    font-size: 24px;
    font-style: normal;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    cursor: pointer;
    line-height: 150px;
    border-radius: 50%;
  }
}
</style>
