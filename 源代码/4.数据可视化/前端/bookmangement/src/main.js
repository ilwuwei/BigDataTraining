/*
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-10 23:36:09
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 18:07:00
 * @FilePath: \bookmangement\src\main.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import Vue from 'vue'
import App from './App.vue'
import router from './router'

// 首页样式
import '@/style/text.scss'




// 第三方包
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
Vue.use(ElementUI)


// axios文件
import axios from 'axios';
Vue.prototype.$axios = axios;

// Swipe 文件(局部引用)

//vue-seamless-scroll 自动轮播
import scroll from 'vue-seamless-scroll'
Vue.use(scroll)

// 自适应插件
import Plugin from 'v-fit-columns';
Vue.use(Plugin);


//引入echarts文件
import * as echarts from 'echarts';
Vue.prototype.$echarts = echarts

// 引入PubSub
import PubSub from 'pubsub-js'
Vue.prototype.PubSub = PubSub


//引入v-viewer
//注册
import Viewer from 'v-viewer'
// import 'viewerjs/dist/viewer.css'
// 使用 
// Vue.use(Viewer);
// Viewer.setDefaults({
//     'inline':false,//是否直接展示
//     'button':true, //右上角按钮
//     "navbar": true, //底部缩略图
//     "title": true, //当前图片标题
//     "toolbar": true, //底部工具栏
//     "tooltip": true, //显示缩放百分比
//     "movable": true, //是否可以移动
//     "zoomable": true, //是否可以缩放
//     "rotatable": true, //是否可旋转
//     "scalable": true, //是否可翻转
//     "transition": true, //使用 CSS3 过度
//     "fullscreen": true, //播放时是否全屏
//     "keyboard": true, //是否支持键盘
//     "url": "data-source",
//     debug: true,
//     defaultOptions: {
//       zIndex: 9999
//     }
// });



Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
