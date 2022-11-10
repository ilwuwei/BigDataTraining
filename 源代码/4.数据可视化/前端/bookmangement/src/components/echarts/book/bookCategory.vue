<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-19 10:35:55
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 03:36:13
 * @FilePath: \bookmangement\src\components\echarts\book\bookCategory.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="myChart" style="width: 100%; height: 300px"></div>
</template>

<script>
import {bookCategory} from "@/network/api.js"
export default {
  name: "bookCategory",
  data() {
    return {};
  },
  // 接口调用
  mounted() {
    this.getData();
  },
  methods: {
    async getData() {
      
        let data = await bookCategory();
        console.log(data);
        let datalist = data.map((item) => {
          return {
            name: item.category,
            value: item.num,
          };
        });
        // console.log(datalist);

        let myChart1 = this.$echarts.init(this.$refs.myChart);
        myChart1.setOption({
          title: {
            text: '图书大类统计占比',
            left: "center",
            top: 18,
          },
          tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b}: {c} ({d}%)",
          },

          series: [
            {
              name: "Access From",
              type: "pie",
              radius: ["40%", "70%"],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: "center",
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: "40",
                  fontWeight: "bold",
                },
              },
              labelLine: {
                show: false,
              },
              // clockWise: true,
              data: datalist,
            },
          ],
        });

        window.addEventListener("resize", () => {
          myChart1.resize();
        });
        window.onresize = () => {
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {}, 300);
        };
      
    },
  },
};
</script>

<style type="scss"></style>
