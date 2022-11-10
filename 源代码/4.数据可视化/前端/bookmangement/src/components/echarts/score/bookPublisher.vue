<template>
  <div ref="myChart1" style="width: 100%; height: 300px"></div>
</template>

<script>
import { bookPublisher } from "@/network/api.js";
export default {
  name: "bookPublisher",
  mounted() {
    this.getPub();
  },
  methods: {
    async getPub() {
      let data = await bookPublisher();
      console.log(data);
      let x = data.map((item) => {
        return item["publisher"];
      });

      let y = data.map((item) => {
        return item["avgscore"];
      });

      let z = data.map((item) => {
        return item["num"];
      });
      // console.log(x);
      // console.log(y);
      // console.log(z);

      let myChart1 = this.$echarts.init(this.$refs.myChart1);
      myChart1.setOption({
        title: {
          text: "出版社平均得分及图书数量统计图",
          left: "center",
          top: 10,
        },
        //指定图表的配置项和数据
        //   backgroundColor: 'rgba(128, 128, 128, 0.1)'
        tooltip: { trigger: "axis" },
        legend: { data: ["数量", "评分"], left: "center", top: 36 },
        xAxis: [
          {
            type: "category",
            data: x,
            axisLabel: {
              // color: "#6A93B9",
              fontSize: 10,
              interval: 0,
              rotate: 30,
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
          {
            //设置两个Y轴之2：蒸发量
            type: "value",
            name: "评分",
            min: 7.5,
            max: 8.5,
            interval: 0.1,
            position: "right", //设置y轴安置的位置
            offset: 0, //设置向右偏移的距离
            splitLine: { show: false }, //设置y轴线条是否显示
            axisLine: { lineStyle: { color: "red" } },
            axisLabel: {
              formatter: "{value} ",
              fontSize: 10,
              interval: 0,
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
            data: z,
          },
          {
            name: "评分",
            type: "line",
            smooth: true,
            showAllSymbol: true,
            symbol: "emptyCircle",
            symbolSize: 8,
            yAxisIndex: 1,
            itemStyle: {
              normal: {
                color: "#F02FC2"
              }
            }, //设置折线颜色
            data: y,
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
