<template>
  <div ref="myChart2" style="width: 100%; height: 300px"></div>
</template>

<script>
import {bookTime} from "@/network/api.js"
export default {
  mame: "bookTime",
  mounted() {
    this.getTime();
  },
  methods: {
    async getTime() {
        let data = await bookTime();
        console.log(data);
        let x = data.map((item) => {
          return item["year"];
        });

        let y = data.map((item) => {
          return item["num"];
        });

        // console.log(x);
        // console.log(y);

        let colors = [
          {
            offset: 0.25,
            color: "rgba(1, 255, 156, 0.4)",
          },
          {
            offset: 0.5,
            color: "rgba(81, 103, 249, 0.4)",
          },
          {
            offset: 0.75,
            color: "rgba(81, 103, 249, 0.4)",
          },
          {
            offset: 1,
            color: "rgba(178, 93, 255, 0.4)",
          },
        ];

        let myChart2 = this.$echarts.init(this.$refs.myChart2);
        myChart2.setOption({
          title: {
            text: "图书增长变化趋势",
          },
          tooltip: {
            trigger: "axis",
          },
            grid: {
    left: '10%',
    right: '5%',
    // containLabel: true
  },
  xAxis: [{
    // type: 'category',
    boundaryGap: false,
    axisLine: { //坐标轴轴线相关设置。数学上的x轴
      show: true,
      lineStyle: {
        color: 'rgba(101, 124, 168, 0.4)'
      },
    },
    axisLabel: {
              show: true,
              interval: 0,
              rotate: 40,
              textStyle: {
                color: "#333",
              },
            },
    splitLine: {
      show: false,
      lineStyle: {
        color: "#0881a3",
      },
    },
    data: x
  }],
  yAxis: {
            type: "value",
          },
  series: [{
    type: 'line',
    showAllSymbol: true,
    symbol: 'circle',
    symbolSize: 12,
    smooth: true,
    lineStyle: {
      normal: {
        width: 3,
        color: "#3DE0F3", // 线条颜色
      },
      type: 'solid',
      // borderColor: 'rgba(0,0,0,.4)',
    },
    itemStyle: {
      color: '#001d42',
      borderWidth: 2,
      borderColor: '#3DE0F3',
    },
    tooltip: {
      show: true
    },
    label: {
      show: false,
    },
    areaStyle: { //区域填充样式
      normal: {
        //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
        // color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        //   offset: 0,
        //   color: "rgba(61, 224, 243,.8)"


        // },
        // {
        //   offset: 1,
        //   color: "rgba(61, 224, 243, 0.1)"
        // }
        // ], false),
        shadowColor: 'rgba(25,163,223, 0.5)', //阴影颜色
        shadowBlur: 20 //shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
      }
    },
    data: y
  }]
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



