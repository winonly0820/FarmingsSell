<script>
import router from "@/router";

export default {
  name: "home",
  data(){
    return{
      fruitsData:[],
      vegetablesData:[],
      // 在这里直接把本地存储中的json数据给转化一下，后续可以直接调用使用
      user:JSON.parse(localStorage.getItem('User-login')||'{}'),

    }
  },
  // created的作用是一进入网页就进行加载这个东西，就是操作调用某个方法
created() {
    this.getFruitsData()
    this.getVegetablesData()
},
  mounted() {

  },
  methods:{
    router() {
      return router
    },
    getFruitsData(){
      this.$request.get('/show/fruit').then(res=>{
        this.fruitsData=res.data;
        // 这里的fruitsData就是在表格中绑定的数据，将返回的数据赋值给这个数据
        // 这里可以使用res.data和你的main.js中的文件没有一点关系，这里是用到了axios
      })},
    getVegetablesData(){
      this.$request.get('/show/vegetable').then(res=>{
        this.vegetablesData=res.data;
      })},
  //   下面的方法是用来固定数据的格式的
  formatPrice(row, column, cellValue) {
    // cellValue 是当前行的price值
    if (cellValue === null || cellValue === undefined) return '';
    // 转换为数字后保留两位小数（toFixed会自动补0）
    return Number(cellValue).toFixed(2);
  }
  }

//     load(){
//             this.$request.get('/').then(res=>{
//             this.fruitdata=res.data
// })
//   }


}


</script>

<template>

<el-container>
  <el-header style="height: 10vh;background: bisque;padding: 0">
<el-row>
  <el-col :span="18">
    <el-button style="">此处是菜单目录栏</el-button>
  </el-col>
  <el-col :span="6">

<!--    当用户已经登陆，从本地获取token了-->
<!--<el-dropdown v-if="user.token" >-->
<!-- 图片的显示逻辑，如果存在user.token则从本地存储中提取头像并且显示，如果没有token则显示默认的头像-->
    <img src="@/assets/logo.png" alt="" @click="$router.push('/manager')" v-if="user.token"
    style="overflow: hidden;width:25%;height:70%;margin-left: 100px;cursor: pointer;margin-bottom: 20px"
    >
<!--    <el-dropdown-menu slot="dropdown">-->
<!--&lt;!&ndash;      <el-dropdown-item @click.native="$router.push('/login')">个人中心</el-dropdown-item>&ndash;&gt;-->
<!--      <el-dropdown-item @click.native="$router.push('/profile')">个人主页</el-dropdown-item>-->
<!--      <el-dropdown-item>配送过程</el-dropdown-item>-->
<!--      <el-dropdown-item>我是大神1</el-dropdown-item>-->
<!--    </el-dropdown-menu>-->
<!--</el-dropdown>-->



<!--    当用户尚未登陆时候显示的默认页面-->
      <!-- 图片的显示逻辑，如果存在user.token则从本地存储中提取头像并且显示，如果没有token则显示默认的头像-->


<!--    这里直接写v-if是不行的，你就算data中拿到了数据，你也没有进行调用，你得写一个方法，等页面加载出来的第一时间，你就得让他再调用一次，让他从本地调用出你的数据-->
      <img v-if="!user.token"  @click="$router.push('/login')" src="@/assets/Unlogin.png" alt="" style="
      overflow: hidden;width:30%;height:70%;margin-top: 30px;margin-left: 200px;cursor: pointer">




  </el-col>
</el-row>

  </el-header>
  <el-main style="height: 90vh;padding: 0;background: #42b983;padding: 10px">
    <el-row :gutter="20">
      <el-col :span="8" >
        <div style="background: white; height: 70vh;">
          <div style="height: 15%;background: chocolate">

          </div>
          <el-table :data="vegetablesData" stripe height="50vh">
            <el-table-column prop="name" label="蔬菜"></el-table-column>
            <el-table-column prop="price" label="价格" :formatter="formatPrice"></el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="8">
        <div style="background: white; height: 70vh;">
          <div style="height: 15%;background: chocolate">

          </div>
          <el-table :data="fruitsData" stripe height="50vh">
            <el-table-column prop="name" label="水果"></el-table-column>
            <el-table-column prop="price" label="价格" :formatter="formatPrice" ></el-table-column>
          </el-table>
        </div> <!-- 统一背景色 -->
      </el-col>
      <el-col :span="8">
        <div style="background: chocolate; height: 70vh;">

            <el-row>
              <el-col :span="18"></el-col>
              <div style="background-color: blue"></div>
              <el-col :span="6" style="background-color: blue"></el-col>
            </el-row>


        </div> <!-- 统一背景色 -->
      </el-col>
    </el-row>




  </el-main>
</el-container>

</template>

<style scoped>

.el-main{
  height: 82vh;
}

</style>