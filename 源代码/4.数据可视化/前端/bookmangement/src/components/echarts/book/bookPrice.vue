<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-21 00:03:32
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 13:33:38
 * @FilePath: \bookmangement\src\components\echarts\book\bookPrice.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="myChart3" style="width: 100%; height: 300px"></div>
</template>

<script>
import { bookPrice } from '@/network/api.js'

export default {
  name: "bookPrice",
  mounted() {
    this.getPtice();
  },
  methods: {
    async getPtice() {
      
        let data = await bookPrice();
        console.log(data);
        let x = data.map((item) => {
          return item["partitionsplit"];
        });
        let y = data.map((item) => {
          return item["num"];
        });
        // console.log(x);
        // console.log(y);


        let myChart3 = this.$echarts.init(this.$refs.myChart3);
        myChart3.setOption({
          title: {
            //配置标题组件
            text: "图书价格统计",
            left: "center",
            top: 18,
          },

          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b}: {c} ",
          },

          legend: {
            //配置图例组件
            data: ["得分区间"],
          },
          xAxis: {
            //配置x轴坐标系
            data: x,
            axisLabel: {
              show: true,
              interval: 0,
              rotate: 40,
              textStyle: {
                color: "#333",
              },
            },
          },
          yAxis: {
            min: 2000,
            max: 2500,
          },
          series: [
            //配置系列列表，每个系列通过type控制该系列图表类型
            {
              name: "数量",
              type: "bar",
              color:"#0881a3",
              data: y,
            },
          ],
        });
        window.addEventListener("resize", () => {
        myChart3.resize();
      });
      window.onresize = () => {
        clearTimeout(this.timer);
        this.timer = setTimeout(() => {}, 300);
      };
    },
  },
};
</script>
