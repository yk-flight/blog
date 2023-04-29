<template>
  <div class="profile-container">
    <div class="card-panel">
      <div class="card-panel-body">
        <el-row>
          <el-col :lg="10" :xs="24" class="card-panel-body-left">
            <div class="card-panel-body-avatar">
              <el-avatar :src="avatar" :size="150"></el-avatar>
            </div>
            <div class="card-panel-body-content">
              <div class="card-panel-body-nick">
                世纪末的架构师
              </div>
              <div class="card-panel-body-create">
                创建时间：2023-03-07 16:52:16
              </div>
            </div>
            <el-divider content-position="center">信息绑定</el-divider>

            <div class="card-panel-body-info">
              <div class="card-panel-body-info-item">
                <span>手机：15234417033</span>
                <el-button type="success" icon="el-icon-mobile-phone" size="mini">修改手机</el-button>
              </div>
            </div>

            <el-divider></el-divider>

            <div class="card-panel-footer">
              <el-button type="danger" icon="el-icon-lock" size="small">修改密码</el-button>
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
                <el-button type="primary" icon="el-icon-success" size="small">保存</el-button>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>

    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Profile',

  computed: {
    ...mapGetters(['avatar'])
  },

  data () {
    return {
      // 用户信息
      userInfo: {
        // 用户名
        username: '1072876976@qq.com',
        // 用户昵称
        nickname: '世纪末的架构师',
        // 角色名称
        roleName: '超级管理员',
        // 登录IP
        ipAddress: '114.218.92.101',
        // IP属地
        ipSource: '江苏省苏州市',
        // 登录设备
        device: 'Mac OS X CHROME11',
        // 备注
        remark: '自古英雄出炼狱，破马长枪定乾坤。'
      }
    }
  },

  mounted () {

  },

  methods: {

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
}
</style>
