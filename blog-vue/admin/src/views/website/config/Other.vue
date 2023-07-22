<template>
  <div class="page-container">
    <div class="card">
      <page-title :title="title"></page-title>
      <div class="card-desc">
        在这里编辑配置项
      </div>
    </div>

    <div class="card">
      <div class="config-container">
        <el-row>
          <el-col :md="{span: 14, offset: 5}" :sm="{span: 20, offset: 2}" :xs="24">
            <el-form label-width="80">
              <!-- 用户头像 -->
              <el-form-item label="用户头像：">
                <div class="image-info" @click="editAvatar">
                  <el-image :src="configForm.avatar">这里是头像</el-image>
                </div>

              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置1：">
                <el-input size="small">网站配置1</el-input>
              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置2：">
                <el-radio-group>
                  <el-radio label="线上品牌商赞助"></el-radio>
                  <el-radio label="线下场地免费"></el-radio>
                </el-radio-group>
              </el-form-item>
              <!-- 网站配置 -->
              <el-form-item label="网站配置3：">
                <el-input type="textarea" size="small">网站配置2</el-input>
              </el-form-item>
            </el-form>
          </el-col>
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
import { getConfig } from '../../../api/system'

export default {
  name: 'Other',

  components: { PageTitle, FileSpace },

  created () {
    // 赋值当前页面内容
    this.title = this.$route.meta.title
    // 获取系统配置
    this.getConfig()
  },

  data () {
    return {
      // 文件空间对话框
      avatarVisible: false,
      // 系统配置表单
      configForm: {
        // 用户默认头像
        avatar: undefined
      }
    }
  },

  methods: {
    // 获取系统基本配置信息
    getConfig () {
      getConfig().then((res) => {
        this.configForm = res
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
    }
  }
}
</script>

<style lang="scss" scoped>
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
</style>
