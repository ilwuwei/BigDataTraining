<template>
  <div
    ref="myChart3"
    style="width: 100%; height: 300px; display: inline-block"
  ></div>
</template>

<script>
import { bookAuthor } from "@/network/api.js";
export default {
  name: "bookAuthor",
  mounted() {
    this.getAut();
  },
  methods: {
    async getAut() {
      let data = await bookAuthor();
      data = data.slice(0, 15);
      console.log(data);
      let x = data.map((item) => {
        return item["author"];
      });

      let y = data.map((item) => {
        return item["num"];
      });

      // console.log(x);
      // console.log(y);
      let myChart3 = this.$echarts.init(this.$refs.myChart3);
      myChart3.setOption({
        title: {
						text: '作者出书数量',
						x: 'center',
						y: '7px',
						textStyle: {
							// color: '#ffffff',
							fontSize: 16
						},
						textAlign: 'left'
					},
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
            formatter: "{a} <br/>{b}: {c}",
          },
          confine: true,
        },
        grid: {
          left: "5%",
          top: "20%",
          bottom: "15%",
          right: "5%",
        },
        xAxis: {
          axisTick: {
            show: false,
          },
          axisLine: {
            lineStyle: {
              color: "rgba(255,255,255,0.45)",
              width: 1, // 这里是为了突出显示加上的
            },
          },
          axisLabel: {
            color: "#6A93B9",
            fontSize: 10,
            interval: 0,
            rotate: 40,
          },
          data: x,
        },
        yAxis: {
          type: "value",
          name: "图书数量",
          min: 0,
          minInterval: 1,
          nameTextStyle: {
            align: "center",
          },
          splitLine: {
            lineStyle: {
              color: "rgba(255, 255, 255, 0.15)",
              // type: 'dashed', // dotted 虚线
            },
          },
          splitArea: { show: false },
          axisLine: {
            show: false,
          },
          axisTick: {
            show: false,
          },
          axisLabel: {
            fontSize: 12,
            color: "#6A93B9",
            fontFamily: "Bebas",
          },
        },
        series: [
          {
            name: "hill",
            type: "pictorialBar",
            barCategoryGap: "-20px",
            symbol:
              "path://M12.000,-0.000 C12.000,-0.000 16.074,60.121 22.731,60.121 C26.173,60.121 -3.234,60.121 0.511,60.121 C7.072,60.121 12.000,-0.000 12.000,-0.000 Z",
            label: {
              show: true,
              position: "top",
              distance: 5,
              color: "#fff",
              // fontWeight: 'bolder',
              fontSize: 12,
              fontFamily: "Bebas",
            },
            itemStyle: {
              normal: {
                color(params) {
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
                  return colorList[params.dataIndex];
                },
              },
              emphasis: {
                opacity: 1,
              },
            },
            data: y,
            // z: 10,
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
