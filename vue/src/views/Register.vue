<template>
  <div class="container">
    <div class="bg-image"></div>

    <el-form
        :model="user"
        :rules="rules"
        ref="registerRef"
        style="width: 600px; height: 500px; border-radius: 10px; margin-left: 34%; margin-top: 8%;
             display: flex; flex-direction: column; background-color: white; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);">

      <el-col>
        <div style="font-size: 30px; font-weight: bold; text-align: center; margin-top: 40px; margin-bottom: 30px">
          用户注册
        </div>

        <el-form-item prop="username">
          <div style="display: flex; align-items: center">
            <div style="margin-left: 80px; font-size: 25px; width: 100px">账号：</div>
            <el-input
                v-model="user.username"
                prefix-icon="el-icon-user"
                placeholder="请设置你的账号"
                style="width: 350px">
            </el-input>
          </div>
        </el-form-item>

        <el-form-item prop="password">
          <div style="display: flex; align-items: center; margin-top: 10px">
            <div style="margin-left: 80px; font-size: 25px; width: 100px">密码：</div>
            <el-input
                v-model="user.password"
                prefix-icon="el-icon-lock"
                placeholder="请设置你的密码"
                style="width: 350px"
                show-password>
            </el-input>
          </div>
        </el-form-item>

        <el-form-item prop="confirm">
          <div style="display: flex; align-items: center; margin-top: 10px">
            <div style="margin-left: 80px; font-size: 25px; width: 100px">确认：</div>
            <el-input
                v-model="user.confirm"
                prefix-icon="el-icon-circle-check"
                placeholder="请再次输入密码"
                style="width: 350px"
                show-password>
            </el-input>
          </div>
        </el-form-item>

        <div style="display: flex; justify-content: flex-end; margin-right: 70px; margin-top: 10px">
          已有账号？去
          <div style="color: #409EFF; cursor: pointer; font-weight: bold" @click="$router.push('/login')">
            登录
          </div>
        </div>

        <div style="text-align: center; margin-top: 30px">
          <el-button
              type="primary"
              @click="register"
              style="width: 200px; height: 55px; font-size: 20px; border-radius: 5px">
            立即注册
          </el-button>
        </div>
      </el-col>
    </el-form>
  </div>
</template>

<script>
// 导入 RSA 加密工具和自定义的 request
import { encrypt } from "@/utils/rsaEncrypt";

export default {
  name: "Register",
  data() {
    // 校验两次密码是否一致的函数
    const validateConfirm = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.user.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      user: {
        username: '',
        password: '',
        confirm: ''
      },
      // 表单校验规则
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        confirm: [{ validator: validateConfirm, trigger: 'blur' }]
      }
    }
  },
  methods: {
    register() {
      this.$refs.registerRef.validate((valid) => {
        if (valid) {
          // 1. 深度克隆，防止修改页面上的输入框内容
          let sendData = JSON.parse(JSON.stringify(this.user));

          // 2. RSA 加密真实的密码字段
          sendData.password = encrypt(sendData.password);

          // 3. 【关键修复】删除冗余的明文字段
          // 既然前端已经校验通过，后端只需要 password，不需要 confirm
          delete sendData.confirm;

          // 4. 发送请求
          this.$request.post("/user/register", sendData).then(res => {
            if (res.code === 200) {
              this.$message.success("注册成功");
              this.$router.push("/login");
            } else {
              this.$message.error(res.msg);
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped>
/* 你的样式代码保持不变 */
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
  background-image: url("@/assets/register.jpg");
  background-size: cover;
  background-position: center;
  z-index: -1;
}
</style>