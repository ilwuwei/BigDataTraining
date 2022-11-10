<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 21:02:54
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 23:54:37
 * @FilePath: \bookmangement\src\components\echarts\group\technological.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix" >
        <span>项目流程图</span>  
      </div>
      <div class="text item" style="width: 100%; height: 310px;">
        <img src="@/assets/imgs/work.jpg" style="width: 100%; height: 100%;"></img>
      </div>
      
    </el-card>
  </div>
</template>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
