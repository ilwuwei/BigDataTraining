<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-17 18:09:42
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 17:59:22
 * @FilePath: \bookmangement\src\views\Index.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div style="overflow:hidden;display:flex;border:1px solid #c0c0c0;">
    <!-- 顶部图表 -->
      <elRow></elRow>
          <bookList></bookList>

  </div>
</template>

<script>
import elRow from "@/components/echarts/index/elRow.vue"
import  bookList from "@/components/echarts/index/bookList.vue"
export default {
  name: "Index",
  components: {
    elRow,
    bookList
  },
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
