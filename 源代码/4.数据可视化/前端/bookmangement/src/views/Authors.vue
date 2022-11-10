<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-12 18:38:07
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 00:28:04
 * @FilePath: \bookmangement\src\views\Tu2.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <!-- 顶部图表 -->
    <el-row :gutter="10">
      <el-col :lg="12" :xs="24"
        ><div class="grid-content bg-purple" style="width: 100%; height: 600px;">
          <authorTop10></authorTop10> 
          <bookAuthor></bookAuthor>
                    　　
          </div>
      </el-col>
      <el-col :lg="12" :xs="24"
        ><div class="grid-content bg-purple-light">
          <bookComment></bookComment>
        </div>
      </el-col>
    </el-row>

    <!-- 下面图表 -->
  </div>
</template>

<script>
import bookComment from "@/components/echarts/author/bookComment.vue";
import authorTop10 from "@/components/echarts/author/authorTop10.vue";
import bookAuthor from "@/components/echarts/author/bookAuthor.vue"
export default {
  name: "Authors",
  components: {
    authorTop10,
    bookComment,
    bookAuthor,
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

.shell {
  position: relative;
  color: #fff;
  height: 150px;
  width: 55px;
  display: block;
  border: 1px solid #aaa;
  filter: alpha(opacity=50);
  margin-right: 10px;
}
</style>
