<template>
  <div class="login-form">
    <h1>{{title}}</h1>
    <div class="container">

      <div class="agile_info">
        <div class="w3l_form">
          <img src="/static/logo_bg.jpg">
        </div>

        <div class="w3_info">
          <h2>欢迎登录</h2>
          <p>请输入管理员的手机号码进行登录</p>
          <el-form ref="loginForm" :model="loginForm" :rules="rules">
            <el-form-item prop="loginName">
              <el-input v-model="loginForm.loginName" placeholder="请输入手机号" name="username" type="text"
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
        </div>
        <div style="clear: both"></div>
      </div>
    </div>
    <div class="footer">
      <p>&copy; FANLING | {{title}} | All Rights Reserved 2019 </p>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    name: 'Login',
    data() {
      return {
        loginForm: {
          loginName: '13451585202',
          password: '123456'
        },
        rules: {
          loginName: [{required: true, trigger: 'blur', message: '请输入手机号'}],
          password: [{required: true, trigger: 'blur', message: '请输入密码'}]
        },
        loading: false,
      }
    },
    computed:{
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
  .login-form {
    min-height: 100%;
    width: 100%;
    overflow: hidden;
    padding: 0;
    margin: 0;
    background: #3eb991;
  }

  h1 {
    text-align: center;
    font-size: 40px;
    margin: 80px 0px 30px;
    color: #fff;
    font-weight: 500;
  }

  .container {
    width: 64%;
    margin: 0 auto;
    background-color: #fff;
  }

  .agile_info {
    padding: 50px 50px;
  }

  .w3l_form {
    padding: 0px;
    float: left;
  }

  .w3_info {
    padding: 0;
    float: right;
    width: 42%;

    h2 {
      display: inline-block;
      font-size: 26px;
      margin-bottom: 10px;
      color: #3eb991;
      letter-spacing: 2px;
    }

    p {
      margin: 0;
      color: #777;
      letter-spacing: 1px;
      line-height: 1.8em;
      font-size: 14px;
      font-weight: 400;
      padding-bottom: 20px;
    }
  }

  .footer {
    p {
      color: #fff;
      text-align: center;
      margin: 40px 0px 0px;

      a {
        color: #fff;

        :hover {
          color: #2d72d9;
        }
      }
    }
  }
</style>
