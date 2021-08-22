<template>
  <div class="login">
    <h1>文件管理</h1>
    <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="0">
      <el-form-item prop="username" style="horiz-align: center">
        <el-input v-model="loginForm.username" autocomplete="off" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password" style="text-align: center">
        <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('loginForm')">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        loginForm:{
          username:"",
          password:"",
        },
        rules: {
          username: [
            { required: true, message: '请填写用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请填写密码', trigger: 'blur' }
          ],
        }
      }
    },
    methods:{
      submitForm(loginForm){
        // 为表单绑定验证功能
        this.$refs[loginForm].validate((valid) => {
          if (valid) {
            this.$cookies.set("name", this.loginForm.username)
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$router.push("/user/profile");
          } else {
            return false;
          }
        });
      }
    }
  }
</script>

<style scoped>
  .login {
    position: absolute;
    width: 500px;
    height: 500px;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    margin: auto;
  }
</style>
