<template>
 <div ref="myChart1" style="width: 100%; height: 300px;  display: inline-block; margin-left: 0;">
    </div>
</template>

<script>
import { authorTop10 } from "@/network/api.js";
export default {
  name: "authorTop10",
  mounted() {
    this.getTop();
  },
  methods: {
    async getTop() {
      let data = await authorTop10();
      data = data.slice(0, 10);
      console.log(data);
      let x = data.map((item) => {
        return item["commenteds"];
      });

      let y = data.map((item) => {
        return item["author"];
      });

    //   console.log(x);
    //   console.log(y);
      let myChart1 = this.$echarts.init(this.$refs.myChart1);
      myChart1.setOption({
					title: {
						text: '最热图书作者top10',
						x: 'center',
						y: '7px',
						textStyle: {
							// color: '#ffffff',
							fontSize: 16
						},
						textAlign: 'left'
					},
					tooltip: {

						trigger: 'axis',
						axisPointer: {
							type: 'shadow',
							formatter: "{a} <br/>{b}: {c}"
						},
						confine: true,
					},
					xAxis: {
						name: '评论数量',
						type: 'value',
						data: y,
						axisLine: { //x轴线的颜色以及宽度

							lineStyle: {
								color: "#fff",
								width: 0,
								type: "solid"
							}
						},
						axisLabel: {
            color: "#6A93B9",
            fontSize: 10,
            interval: 0,
              rotate: 40,
          },

					},
					yAxis: {

						name: '作者',
						type: 'category',
						// axisLabel: {
						// 	interval: 0
						// } 
						data: y,
						axisLine: { //x轴线的颜色以及宽度
							show: true,
							lineStyle: {
								color: "#fff",
								width: 0,
								type: "solid"
							}
						},
						axisLabel: {
              color: "#6A93B9",
							interval: 0,
							rotate: 30, //倾斜的程度
							show: true
						},
					},
					series: [{
						data: x,
						type: 'bar',
						markPoint: {
							data: [{
									type: 'max',
									name: '最大值'
								},
								{
									type: 'min',
									name: '最小值'
								}
							]
						},
						markLine: {
							data: [{
								type: 'average',
								name: '平均值'
							}, ]
						},
						showBackground: true,
						label: {
							show: true,
							position: 'inside'
						},
						itemStyle: { // 柱状图的背景色
							normal: {
								// 每个柱子的颜色即为colorList数组里的每一项,如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
								color: function(params) {
									var index = params.dataIndex
									var colorList = [
										// 渐变颜色的色值和透明度
										// 透明度从0
										['rgba(0, 255, 127, 0.3)', 'rgba(255, 0, 255, 0.3)', 'rgba(85, 85, 255, 0.3)',
											'rgba(255, 255, 127, 1.0)', 'rgba(0, 0, 0, 0.3)', 'rgba(170, 0, 0, 0.3)', 'rgba(85, 85, 0, 0.3)',
											'rgba(13,94,208,0.3)', 'rgba(255,155,15,0)', 'rgba(253,103,96,0.3)'
										], // 到透明度1 ,如果需要不同的颜色直接修改不同颜色即可
										['rgba(0, 255, 0, 0.6)', 'rgba(255, 0, 255, 0.6)', 'rgba(85, 85, 127, 0.6)', 'rgba(255, 255, 0, 0.6)',
											'rgba(255, 255, 255, 0.6)', 'rgba(255, 255, 255, 0.6)', 'rgba(255, 255, 255, 0.6)',
											'rgba(13,94,208,0.6)', 'rgba(255,155,15,0.6)', 'rgba(253,103,96,0.6)'
										]
									]
									return {
										colorStops: [{
												offset: 0.3, // 颜色的开始位置
												color: colorList[0][index] // 0% 处的颜色
											},
											{
												offset: 0.6, // 颜色的结束位置
												color: colorList[1][index] // 100% 处的颜色
											}
										]
									}
								}
							}
						},

						backgroundStyle: {
							color: 'rgba(180, 180, 180, 0.2)'
						}
					}]
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
