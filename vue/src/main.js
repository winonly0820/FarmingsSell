import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import request from "@/utils/request";

Vue.config.productionTip = false
Vue.use(ElementUI)
//下面这行代码将我刚刚导入的第六行的代码request文件进行挂载，而request文件中里面配置了axios文件，所以在这里相互配合使得axios在全局可用
Vue.prototype.$request=request


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
