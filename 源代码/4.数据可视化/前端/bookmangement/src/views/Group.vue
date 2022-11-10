<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-14 15:32:44
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 21:17:21
 * @FilePath: \bookmangement\src\views\Group.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div>
    <el-row>
      <el-col :span="24"
        ><div class="grid-content bg-purple-dark" id="home">
          <swiperCom></swiperCom>
          </div>
        </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :lg="14" :xs="24"
        ><div class="grid-content bg-purple-light">
          <Divisionlabor></Divisionlabor>
        </div>
      </el-col>
      <el-col :lg="10" :xs="24"
        ><div class="grid-content bg-purple-light">
          <technological></technological>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import swiperCom from "@/components/echarts/group/swiperCom.vue";
import Divisionlabor from "@/components/echarts/group/Divisionlabor.vue";
import technological from "@/components/echarts/group/technological.vue";
export default {
  name: "Group",
  components: {
    swiperCom,
    Divisionlabor,
    technological,
  },
};
</script>
<style>
#home {
  width: 100%;
  height: 300px;
}
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
