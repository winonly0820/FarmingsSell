<!--我很好奇这个this.他在好多地方都用到了，但是我不明白，为什么这个非要使用this.，他到底有什么作用，是指的是调用吗？还是说为了强调，后来我发现，这个this.的作用可能是-->
<!--为了强调，这个后面的变量或者方法等，是在这个文件中定义的，避免与其他文件的变量方法混淆，如果直接理解成这个文件中定义的也对，因为在全局变量main文件中挂载的东西是可以直接使用的-->
<!--但是从其他文件中导入的变量是无法使用的，这就是一些小小的区别-->


<script>
export default {
  name: "login",
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          //对于提交username时候，创建了一个限制条件，第一个代表这个输入框必须有输入内容，第二个是如果出错提示的信息，第三个代表着移除焦点时候触发校验
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],


      }
    }
  },
  methods: {
    login() {
      this.$refs['loginRef'].validate((valid) => {
        if (valid) {
          this.$request.post('/user/login', this.user).then(res => {
            if (res.code === 200) {
              localStorage.setItem("User-login", JSON.stringify(res.data));
              this.$router.push('/')
              this.$message.success('登录成功')
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })

    }
  }

}
</script>

<template>
  <!--  通过下面写了两个css样式，使得这个背景图片占据整个视图的100%；并且让这个背景图置于网页的最底层，使得后面新加入的元素不会被图片锁覆盖-->
  <div class="container">
    <div class="bg-image">
    </div>
    <el-form ref="loginRef" :model="user"
             style="width: 600px;height: 350px;border-radius: 10px;margin-left: 34%;margin-top: 15%;display: flex">
      <el-col>
        <div style="font-size: 30px;font-weight: bold;text-align: center;margin-bottom: 30px">登录农贸大卖场</div>
        <el-form-item prop="username">
          <div style="display: flex">
            <div style="margin-left: 100px;font-size: 25px">账号：</div>
            <el-input v-model="user.username" prefix-icon="el-icon-user" placeholder="请输入你的账号"
                      style="width: 350px"></el-input>
          </div>
        </el-form-item>
        <el-form-item prop="password">
          <div style="display: flex;margin-top: 15px">
            <div style="margin-left: 100px;font-size: 25px">密码：</div>
            <el-input v-model="user.password" prefix-icon="el-icon-lock" placeholder="请输入你的登录密码"
                      style="width: 350px" show-password></el-input>
          </div>
        </el-form-item>
        <div style="display: flex;margin-left: 350px">还没有账号？现在去
          <div style="color: blue;cursor: pointer;" @click="$router.push('/')">
            注册
          </div>
          <div>一个</div>
        </div>
        <div>
          <el-button type="primary" style="margin-top: 30px;width:150px;height: 60px" @click="login">登录</el-button>
        </div>
      </el-col>

    </el-form>


  </div>


  <!--<div style="height:98vh;display: flex;overflow: hidden">-->
  <!--  alt字样的意思是在你的图片无法加载时候显示的字样-->
  <!--  <img src="@/assets/login.jpg" alt="加载失败" style="width:100%;height: 100%;z-index: -1">-->
  <!--<div style="background-color: gray;width:250px;height: 300px;margin-top: 250px;margin-left: 400px"></div>-->
  <!--</div>-->
</template>

<style scoped>
.container {
  display: flex;
  position: relative;
  width: 100%;
  height: 100vh;
}

.bg-image {
  display: flex;
  position: absolute;
  width: 100%;
  height: 100vh;
  background-image: url("@/assets/login.jpg");
  background-size: cover;
  background-position: center;
  z-index: -1;
}

</style>