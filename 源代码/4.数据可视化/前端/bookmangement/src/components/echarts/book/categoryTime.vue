<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-12 19:23:15
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-21 17:48:34
 * @FilePath: \bookmangement\src\components\echarts\book\categoryTime.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-12 19:23:15
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-19 10:31:55
 * @FilePath: \bookmangement\src\components\A.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="main" style="width: 100%; height: 300px">
  </div>
</template>

<script>
import {categoryTime} from "@/network/api.js"
export default {
  name: "categoryTime",
  mounted() {
    this.getData();
  },

  //数据接口
  methods: {

    async getData() {
        let data =await categoryTime();

        // 横坐标轴数据项
        let t = [];
        data
          .map((item) => {
            return item["year"];
          })
          .forEach((item) => {
            if (!t.includes(item)) {
              t.push(item);
            }
          });

        // 定义函数分组数据
        function groupBy(data, f) {
          let groups = {};
          data.forEach(function (o) {
            let group = JSON.stringify(f(o));
            groups[group] = groups[group] || [];
            groups[group].push(o);
          });
          return Object.keys(groups).map(function (group) {
            return groups[group];
          });
        }

        // 分组数据
        let s = groupBy(data, function (item) {
          return item.name;
        });

        console.log(s);

        //构造series数据项
        let series_list = [];
        s.forEach((item) => {
          series_list.push({
            name: item[0].name,
            type: "line",
            // stack: 'Total',
            data: item.sort((a, b) => a.yarn - b.yarn).map((item) => item.num),
          });
        });
        console.log(series_list);

        let myChart = this.$echarts.init(this.$refs.main);
        myChart.setOption({
          title: {
            text: "图书大类时间序列",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["文学", "科技", "经管", "流行", "文化", "生活"],
          },
          grid: {
            left: "3%",
            right: "4%",
            bottom: "3%",
            containLabel: true,
          },
          toolbox: {
            feature: {
              dataZoom: {
                yAxisIndex: "none",
              },
              restore: {},
              saveAsImage: {},
            },
          },
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: t.sort(),
          },
          yAxis: {
            type: "value",
          },
          dataZoom: [
            {
              type: "inside",
              start: 0,
              end: 10,
            },
            {
              start: 0,
              end: 10,
            },
          ],
          series: series_list,
        });
        window.addEventListener("resize", () => {
          myChart.resize();
        });
        window.onresize = () => {
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {}, 300);
        };
    },
  },
};
</script>
