<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-14 15:28:00
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 16:02:50
 * @FilePath: \bookmangement\src\views\Scores.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div>
      <!-- 顶部图表 -->
      <el-row :gutter="10">
        <el-col :lg="12" :xs="24"><div class="grid-content bg-purple">
            <bookPublisher></bookPublisher>
        </div>
        </el-col>
        <el-col :lg="12" :xs="24"><div class="grid-content bg-purple-light">
          <press></press>
        </div>
        </el-col>
      </el-row>
  
      <!-- 下面图表 -->
      <el-row :gutter="10">
        <el-col :lg="12" :xs="24"><div class="grid-content bg-purple-light">
          <comprehensive></comprehensive>
        </div>
        </el-col>
        <el-col :lg="12" :xs="24"><div class="grid-content bg-purple-light">
          <!-- <h1>下面1</h1> -->
          <bookScore></bookScore>
        </div>
        </el-col>
      </el-row>
    </div>
  </template>


<script>
import bookPublisher from "@/components/echarts/score/bookPublisher"
import press from "@/components/echarts/score/press.vue"
import comprehensive from "@/components/echarts/score/comprehensive.vue"
import bookScore from "@/components/echarts/score/bookScore.vue"
export default {
    name:"Scores",
    components :{
        bookPublisher,
        press,
        comprehensive,
        bookScore
    }
}
</script>

<style>
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #ffff;
  }
  .bg-purple {
    background: #ffff;
  }
  .bg-purple-light {
    background: #ffff;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>