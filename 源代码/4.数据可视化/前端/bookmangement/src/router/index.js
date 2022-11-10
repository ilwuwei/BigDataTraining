/*
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-11 01:10:11
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 03:30:39
 * @FilePath: \bookmangement\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */





import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/TheLogin'
import Layout from '@/views/Test.vue'


Vue.use(Router)





export default new Router({
  mode:"history",
  
  routes: [
    
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path: '/tui',
      name: 'Test',
      component: Layout,
      meta:{
        title:"渲染"
      },
      children: [
        {
          path: "/group",
          component: () => import("@/views/Group")
        },
        {
          path: "/index",
          component: () => import("@/views/Index")
        },
        
      {
        path: "/authors",
        component: () => import("@/views/Authors")
      },
      {
        path: "/books",
        component: () => import("@/views/Books")
      },
      {
        path: "/scores",
        component: () => import("@/views/Scores")
      }
      ]

    },
    
  ],
  
});


