<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 13:33:18
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 13:49:01
 * @FilePath: \bookmangement\src\components\echarts\score\press.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div ref="myChart2" style="width: 100%; height: 300px"></div>
  </template>
  
  <script>
  import { press } from "@/network/api.js";
  export default {
    name: "press",
    mounted() {
      this.getPub();
    },
    methods: {
      async getPub() {
        let data = await press();
        console.log(data);
        let datavalue = data.map((item) => {
          return{
            name: item.publisher,
            value:item.num,
          }
        });

        // console.log(x);
        // console.log(z);
        console.log(datavalue)
  
        let myChart2 = this.$echarts.init(this.$refs.myChart2);
        myChart2.setOption({
          title: {
            left: "center",
            top: 10,
          },
          //指定图表的配置项和数据
          //   backgroundColor: 'rgba(128, 128, 128, 0.1)'
          tooltip: { trigger: "item" },
        
          series: [
            {
              name: "数量",
              type: "pie",
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
              data: datavalue,
            },
          ],
        });
        window.addEventListener("resize", () => {
          myChart2.resize();
        });
        window.onresize = () => {
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {}, 300);
        };
      },
    },
  };
  </script>