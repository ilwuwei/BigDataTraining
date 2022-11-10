<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 14:32:43
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 00:45:36
 * @FilePath: \bookmangement\src\components\echarts\score\bookScore.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div ref="myChart4" style="width: 100%; height: 300px"></div>
  </template>
  
  <script>
  import { bookScore } from "@/network/api.js";
  export default {
    name: "bookScore",
    mounted() {
      this.getPub();
    },
    methods: {
      async getPub() {
        let data = await bookScore();
        console.log(data);
        let x = data.map((item) => {
          return item["partitionsplit"];
        });
  
  
        let y = data.map((item) => {
          return item["num"];
        });
        // console.log(x);
        // console.log(y);
  
        let myChart4 = this.$echarts.init(this.$refs.myChart4);
        myChart4.setOption({
          title: {
            text: "图书评分分布图",
            left: "center",
            top: 10,
          },
          //指定图表的配置项和数据
          //   backgroundColor: 'rgba(128, 128, 128, 0.1)'
          tooltip: { trigger: "axis" },
          legend: { data: "数量",  left: "center", top: 36 },
          xAxis: [
            {
              type: "category",
              data: x,
              axisLabel: {
                // color: "#6A93B9",
                fontSize: 10,
                interval: 0,
                // rotate: 30,
              },
            },
          ],
          yAxis: [
            {
              //设置两个Y轴之1：降水量
              type: "value",
              name: "数量",
              splitNumber: 6,
              min: 250,
              max: 850,
              interval: 50,
              axisLabel: {
                formatter: "{value} ",
                fontSize: 10,
                interval: 0,
                rotate: 30,
              },
            },
          ],
          series: [
            {
              name: "数量",
              type: "bar",
              // 	itemStyle: {  //设置柱状图颜色
              // 		normal: {
              // 			color: function (params) {
              // 				var colorList = [  //build a color map as your need
              // 					'#fe9f4f', '#fead33', '#feca2b', '#fed728', '#c5ee4a',
              // 					'#87ee4a', '#46eda9', '#47e4ed', '#4bbbee', '#4f8fa8',
              // 					'#4586d8', '#4f68d8', '#F4E001', '#F0805A', '#26C0C0'];
              // 				return colorList[params.dataIndex]
              // 			},
              // 		}
              // 	},
              colorBy: "data",
              data: y,
            },
          ],
        });
        window.addEventListener("resize", () => {
          myChart4.resize();
        });
        window.onresize = () => {
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {}, 300);
        };
      },
    },
  };
  </script>