<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-14 15:10:41
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-21 16:37:17
 * @FilePath: \bookmangement\src\views\Books.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-14 15:10:41
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-21 11:14:51
 * @FilePath: \bookmangement\src\views\books.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <!-- 顶部图表 -->
    <el-row :gutter="10">
      <el-col :lg="6" :xs="24"><div class="grid-content bg-purple">
        <bookCategory></bookCategory>
      </div>
      </el-col>
      <el-col :lg="9" :xs="24"><div class="grid-content bg-purple-light">
        <bookTime></bookTime>
      </div>
      </el-col>
      <el-col :lg="9" :xs="24"><div class="grid-content bg-purple-light">
        <bookPrice></bookPrice>
      </div>
      </el-col>
    </el-row>

    <!-- 下面图表 -->
    <el-row :gutter="10">
      <el-col :lg="24" :xs="24"><div class="grid-content bg-purple-light">
        <!-- <h1>下面1</h1> -->
        <categoryTime></categoryTime>
      </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import bookCategory from "../components/echarts/book/bookCategory.vue";
import categoryTime from "../components/echarts/book/categoryTime.vue";
import bookTime from "../components/echarts/book/bookTime.vue"
import bookPrice from "../components/echarts/book/bookPrice.vue"
export default {
    name: "Books",
    components: { bookCategory,categoryTime,bookTime,bookPrice}
};
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