<script>
export default {
  name: "home",
  data(){
    return{
      fruitsData:[],
      vegetablesData:[],
    }
  },
  // created的作用是一进入网页就进行加载这个东西，就是操作调用某个方法
created() {
    this.getFruitsData()
    this.getVegetablesData()
},
  mounted() {
    this.getFruitsData()
    this.getVegetablesData()
  },
  methods:{
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
  <el-header style="height: 15vh;background: bisque;padding: 0">
<el-row>
  <el-col :span="18">
    <el-button style="">此处是菜单目录栏</el-button>
  </el-col>
  <el-col :span="6">
<el-dropdown>

<!-- 图片的显示逻辑，如果存在user.token则从本地存储中提取头像并且显示，如果没有token则显示默认的头像-->
    <img src="@/assets/logo.png" alt="" style="border-radius: 50%;overflow: hidden;width: 60px;height:100%">
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click="$router.push('/login')">去登录</el-dropdown-item>
      <el-dropdown-item>我是大神2</el-dropdown-item>
      <el-dropdown-item>我是大神1</el-dropdown-item>
      <el-dropdown-item>我是大神1</el-dropdown-item>
    </el-dropdown-menu>
</el-dropdown>

  </el-col>
</el-row>

  </el-header>
  <el-main style="height: 80vh;padding: 0;background: #42b983">
    <el-row :gutter="20">
      <el-col :span="8" >
        <div style="background: white; height: 70vh;margin-left: 30px">
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
        </div> <!-- 统一背景色 -->
      </el-col>
    </el-row>




  </el-main>
</el-container>

</template>

<style scoped>

</style>