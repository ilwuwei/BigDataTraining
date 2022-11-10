<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-21 09:00:10
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 00:47:15
 * @FilePath: \bookmangement\src\components\echarts\author\bookComment.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div ref="myChart2" style="width: 100%; height: 600px"></div>
</template>

<script>
import { bookComment } from "@/network/api.js";
export default {
  name: "bookComment",
  mounted() {
    this.getComment();
  },
  methods: {
    async getComment() {
      let data1 = await bookComment();
      data1 = data1.slice(0, 15);
      console.log(data1);
      let x = data1.map((item) => {
        return item["book_name"];
      });

      let y = data1.map((item) => {
        return item["commented"];
      });

      // console.log(x);
      // console.log(y);

      let data = [
        {
          name: "文化",
          children: [
            {
              name: "万历十五年",
              value: 182612,
            },
            {
              name: "穆斯林的葬礼",
              value: 156079,
            },
            {
              name: "明朝那些事儿",
              value: 144189,
            },
          ],
        },
        {
          name: "文学",
          children: [
            {
              name: "小王子",
              value: 701191,
            },
            {
              name: "追风筝的人",
              value: 752525,
            },
            {
              name: "活着",
              value: 716460,
            },
          ],
        },
        {
          name: "流行",
          children: [
            {
              name: "解忧杂货铺",
              value: 726255,
            },
            {
              name: "白夜行",
              value: 497365,
            },
            {
              name: "嫌疑人X的献身",
              value: 489689,
            },
          ],
        },
        {
          name: "生活",
          children: [
            {
              name: "傲慢与偏见",
              value: 219734,
            },
            {
              name: "窗边的小豆豆",
              value: 194547,
            },
            {
              name: "杀死一只知更鸟",
              value: 124960,
            },
          ],
        },
        {
          name: "科技",
          children: [
            {
              name: "时间的秩序",
              value: 18559,
            },
            {
              name: "浪潮之巅",
              value: 31707,
            },
            {
              name: "思考，快与慢",
              value: 19515,
            },
          ],
        },
        {
          name: "经管",
          children: [
            {
              name: "富爸爸，穷爸爸",
              value: 72664,
            },
            {
              name: "小狗钱钱",
              value: 50117,
            },
            {
              name: "影响力",
              value: 48225,
            },
          ],
        },
      ];
      let myChart2 = this.$echarts.init(this.$refs.myChart2);

      myChart2.setOption({
        title: {
          text: "图书大类分布图",
          x: "center",
          y: "7px",
          textStyle: {
            // color: '#ffffff',
            fontSize: 16,
          },
          textAlign: "left",
        },
        tooltip: {
          formatter: (params) => {
            let str = "";
            params.treePathInfo.forEach((item, index) => {
              if (index > 0 && index < params.treePathInfo.length - 1) {
                str += item.name + "-";
              } else if (index !== 0) {
                str += item.name + ":" + item.value;
              }
            });
            return str;
          },
        },
        color: [
          "#1B95E6",
          "#6674C4",
          "#FF95D2",
          "#6BBEAC",
          "#FACF5B",
          "#3DC0E4",
          "#0DCB74",

          "#8368D9",
          "#AF65BE",
          "#4C6471",
          "#1C436F",
          "#E57B88",
          "#F4B246",
          "#A77C56",
        ],
        series: {
          type: "sunburst",
          data: data,
          radius: ["10%", "70%"],
          label: {
            rotate: 0,
          },
          itemStyle: {
            borderColor: "#fff",
            borderWidth: 6,
            borderRadius: 6,
          },
          emphasis: {
            focus: "ancestor",
          },
          levels: [
            {
              // 留给数据下钻点的空白配置
              itemStyle: {
                color: "#fff",
              },

              radius: ["10%", "10%"],
              itemStyle: {
                borderWidth: 3,
            },
            label: {
                rotate: 'tangential'
            }
            },
            {
              radius: ["10%", "35%"],
              label: {
                position: 'outside',
                padding: 3,
                silent: false
            },
            },
            {label: {
                position: 'outside',
                padding: 3,
                silent: false
            },
            itemStyle: {
                borderWidth: 3
            }}
          ],
        },
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
