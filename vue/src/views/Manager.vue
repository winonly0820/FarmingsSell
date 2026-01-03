<script>
// 建议删除对内部 arrayMethods 的导入，除非你有特殊用途
// import {arrayMethods} from "vue/src/core/observer/array";

export default {
  name: "manager",
  methods: {
    Logout() {
      localStorage.removeItem('User-login');
      this.$message({
        message: '账号已登出，返回登录页中...',
        type: 'success'
      });
      this.$router.push('/');
    }
  }
}
</script>

<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
<!--    下面使用的居中属性中，作用的对象是，容器中的所有元素，而不是作用于自身-->
    <el-header style="background-color: #B0C4DE;height: 10vh;display: flex;align-items: center;justify-content: center">
      <el-col style="display: flex" :span="6">
        <img src="@/assets/茶叶.jpg" alt="商城logo" style="height: 80px;border-radius: 50%;margin-left: 30px">
      </el-col>
      <el-col style="display: flex" :span="18">
        <div style="align-items: center;justify-content: center">欢迎进入农贸大市场管理系统</div>
      </el-col>
      <el-col>
        <el-button @click="Logout">
          登出
        </el-button>
      </el-col>


    </el-header>

    <el-container>
      <el-aside width="200px" style="background-color: #E6E6FA;">
        <div style="display: flex;justify-content: center;align-items: center">
          <el-col>
            <el-menu style="display: flex">
              <el-col>
                <el-menu-item style="height: 80px;display: flex;align-items: center;justify-content: center">
                  <div style="">个人信息</div>
                </el-menu-item>
              <el-menu-item style="height: 80px;display: flex;align-items: center;justify-content: center">
                  <div style="">用户管理</div>
              </el-menu-item>
                <el-menu-item style="height: 80px;display: flex;align-items: center;justify-content: center">
                  <div style="">库存查询</div>
                </el-menu-item>
                <el-menu-item style="height: 80px;display: flex;align-items: center;justify-content: center">
                  <div style="">订单查询</div>
                </el-menu-item>

              </el-col>
            </el-menu>
          </el-col>
        </div>
      </el-aside>

      <el-main style="background-color: #F0F8FF;">
        Main
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>

</style>