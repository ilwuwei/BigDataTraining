<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 15:08:04
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 01:13:55
 * @FilePath: \bookmangement\src\components\echarts\score\comprehensive.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="myChart3" style="width: 100%; height: 300px"></div>
</template>

<script>
import { bookCom } from "@/network/api.js";

import "echarts-wordcloud";

export default {
  name: "comprehensive",
  mounted() {
    this.getCom();
  },
  methods: {
    async getCom() {
      let data = await bookCom();
      let datavalue = data.map((item) => {
        return {
          name: item.book_name,
          value: item.commented,
        };
      });
      // console.log(datavalue);
      let colorList = [
        "#71c9ce",
        "#3f72af",
        "#bad7df",
        "#ff9999",
        "#a2d5f2",
        "#e0f9b5",
        "#a2d5f2",
        "#61c0bf",
        "#0881a3",
        "#307672",
        "#34495e",
        "#a82ffc",
        "#1e56a0",
        "#35477d",
        "#e0f9b5",
      ];

      let myChart3 = this.$echarts.init(this.$refs.myChart3);
      myChart3.setOption({
        tooltip: {
          show: true,
          position: "top",
          textStyle: {
            fontSize: 30,
          },
        },
        // backgroundColor:'#fff', // 画布背景色
        series: [
          {
            type: "wordCloud",
            data: datavalue,
            shape: "cardioid", // 词云图显示排列为什么形状，默认 circle，cardioid 心形，diamond 菱形
            textstyle: {
              fontfamily: "sans-serif",
              fontweight: "bold",
              // color: "#333", // 字体颜色
              color: colorList,
              emphasis: {
                // focus: 'self',
                textstyle: {
                  shadowblur: 10,
                  shadowcolor: "#333",
                },
              },
            },
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
