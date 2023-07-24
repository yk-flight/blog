<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <div class="card-desc">
        在这里编辑系统基本配置项，定制您的个性化设置
      </div>
    </div>

    <div class="card" v-loading="loading" element-loading-text="正在加载系统基本配置数据">
      <div class="config-container">
        <el-row>
          <el-col :md="{span: 14, offset: 5}" :sm="{span: 20, offset: 2}" :xs="24">
            <el-form label-width="80">
              <!-- 用户头像 -->
              <el-form-item label="用户默认头像：">
                <div class="image-info" @click="editAvatar">
                  <el-image :src="configForm.avatar"></el-image>
                </div>

              </el-form-item>
              <!-- 上传策略 -->
              <el-form-item label="上传策略：">
                <el-radio-group v-model="configForm.upload">
                  <el-radio label="local">本地策略</el-radio>
                  <el-radio label="oss">OSS策略</el-radio>
                </el-radio-group>
              </el-form-item>
              <!-- 系统通知 -->
              <label class="item_label">系统通知：</label>
              <!-- <vue-editor v-model="configForm.notice" /> -->
              <rich-text :value="configForm.notice"></rich-text>
            </el-form>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" style="margin-top: 30px;">
          <el-button
            type="primary"
            icon="el-icon-circle-check"
            size="small"
            plain
            :loading="buttonLoading"
            @click="saveConfig">
            保存配置
          </el-button>
          <el-button
            type="danger"
            icon="el-icon-close"
            size="small"
            plain
            :loading="buttonLoading"
            @click="cancel">
            取消编辑
          </el-button>
        </el-row>
      </div>
    </div>

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
import PageTitle from '../../../components/PageTitle/index.vue'
import FileSpace from '../../../components/FileSpace/index.vue'
import RichText from '../../../components/RichText/index.vue'
import { getConfig, saveConfig } from '../../../api/system'

export default {
  name: 'Other',

  components: { PageTitle, FileSpace, RichText },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 获取系统配置
    this.getConfig()
  },

  activated () {
    // 组件被激活时动态获取系统配置
    this.getConfig()
  },

  data () {
    return {
      // 文件空间对话框
      avatarVisible: false,
      // 系统配置表单
      configForm: {
        // 用户默认头像
        avatar: undefined,
        // 系统通知
        notice: undefined,
        // 上传策略
        upload: undefined
      },
      // 等待框
      loading: false,
      // 按钮加载框
      buttonLoading: false
    }
  },

  methods: {
    // 获取系统基本配置信息
    getConfig () {
      // 等待框
      this.loading = true
      this.buttonLoading = true
      getConfig().then((res) => {
        this.configForm = res
        // 关闭等待框
        this.loading = false
        this.buttonLoading = false
      }).catch(() => {
        // 关闭等待框
        this.loading = false
        this.buttonLoading = false
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
    // 修改默认用户头像
    changeAvatar (value) {
      // 修改当前头像值
      this.configForm.avatar = value[0].src
    },
    // 保存配置信息
    saveConfig () {
      // 开启加载框
      this.loading = true
      saveConfig(this.configForm).then((res) => {
        this.$message.success('保存成功')
        this.loading = false
        this.buttonLoading = false
      }).catch(() => {
        this.loading = false
        this.buttonLoading = false
      })
    },
    // 取消编辑
    cancel () {
      this.getConfig()
    }
  }
}
</script>

<style lang="scss" scoped>
.el-image {
  border-radius: 10px;
}
.card {
  margin-bottom: 20px;
}
.card-desc {
  margin-top: 60px;
}
.config-container {
  margin-top: 30px;
  margin-bottom: 10px;
}
.dialog-wrapper {
  padding: 20px;
}
.image-info {
    position: relative;
    display: inline-block;
    height: 150px;
    border-radius: 10px;
    width: 150px;
  }

.image-info:hover:after {
  content: '+';
  position: absolute;
  text-align: center;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  color: #eee;
  background: rgba(0, 0, 0, 0.4);
  font-size: 24px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  cursor: pointer;
  line-height: 150px;
  border-radius: 10px;
}

.item_label {
  text-align: right;
  vertical-align: middle;
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}
</style>
