<template>
  <div class="login">
    <vue-particles color="#dedede" :particleOpacity="0.9" :particlesNumber="150" shapeType="circle" :particleSize="4"
                   linesColor="#dedede" :linesWidth="1" :lineLinked="false" :lineOpacity="0.2" :linesDistance="120"
                   :moveSpeed="5" :hoverEffect="false" hoverMode="repulse" :clickEffect="false" clickMode="push">
    </vue-particles>
    <div class="login-img">
      <img src="@/assets/galaxy.png" draggable="false">
    </div>
    <div class="login-con">
      <el-card class="box-card" shadow="never">
        <div slot="header" class="clearfix">
          <span>冷暖设备维修后台管理</span>
        </div>
        <el-form ref="loginForm" :model="loginForm" :rules="rules">
          <el-form-item prop="loginName">
            <el-input v-model="loginForm.loginName" placeholder="请输入账号" name="username" type="text"
                      auto-complete="off"/>
          </el-form-item>

          <el-form-item prop="password">
            <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" name="password"
                      @keyup.enter.native="handleLogin"/>
          </el-form-item>
          <el-form-item>
            <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                       @click.native.prevent="handleLogin">登录
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {
        name: 'Login',
        data() {
            return {
                loginForm: {
                    loginName: 'admin',
                    password: ''
                },
                rules: {
                    loginName: [{required: true, trigger: 'blur', message: '请输入账号'}],
                    password: [{required: true, trigger: 'blur', message: '请输入密码'}]
                },
                loading: false,
            }
        },
        computed: {
            ...mapGetters([
                'title'
            ]),
        },
        methods: {
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.loading = true
                        this.$store.dispatch('user/login', this.loginForm).then(() => {
                            this.$router.push({path: this.redirect || '/'})
                            this.loading = false
                        }).catch(() => {
                            this.loading = false
                        })
                    } else {
                        return false
                    }
                })
            }
        }
    }
</script>

<style lang="scss" scoped>
  .login {
    width: 100%;
    height: 100%;
    background-color: #000000;
    position: relative;
    overflow: hidden;

    &-img {
      position: absolute;
      bottom: -2px;
    }

    &-con {
      position: absolute;
      right: 160px;
      top: 50%;
      transform: translateY(-60%);
      width: 300px;

      &-header {
        font-size: 16px;
        font-weight: 300;
        text-align: center;
        padding: 30px 0;
      }

      .form-con {
        padding: 10px 0 0;
      }

      .login-tip {
        font-size: 10px;
        text-align: center;
        color: #c3c3c3;
      }
    }
  }
</style>
