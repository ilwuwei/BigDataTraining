<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 09:51:19
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 15:46:47
 * @FilePath: \bookmangement\src\components\echarts\group\swiperCom.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div id="swipercom">
    <div class="swiper-container" id="swiperIndex">
      <div class="swiper-wrapper">
        <div class="swiper-slide" v-for="(item, i) in imgs" :key="i">
          <img :src="item.pic" alt="" />
        </div>
      </div>
      //换页器
      <div class="swiper-pagination"></div>
      //前进后退
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
      //滚动条
      <div class="swiper-scrollbar"></div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/swiper.css";
import Swiper from "swiper"; //引入swiper
export default {
  name: "Swiper",
  data() {
    return {
      imgs: [
        { pic: require("@/assets/imgs/first.jpg") },
        { pic: require("@/assets/imgs/two.jpg") },
        { pic: require("@/assets/imgs/three.jpg") },
        { pic: require("@/assets/imgs/four.jpg") },
      ],
    };
  },
  mounted() {
    var mySwiper = new Swiper("#swiperIndex", {
      //配置分页器内容

      loop: true, // 循环模式选项
      pagination: {
        el: ".swiper-pagination", //换页器与哪个标签关联
        clickable: true, //分页器是否可以点击
      },
      // 如果需要前进后退按钮
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
      },
      //如果需要滚动条
      scrollbar: {
        el: ".swiper-scrollbar",
      },
      // 设置自滚动
      autoplay: {
        delay: 1500,
      },
      autoHeight: true, //高度随内容变化
      // 轮播样式
      effect: "coverflow",
      centeredSlides: true,
      // 网格布局
      slidesPerView: 2,
      grid: {
        fill: "column",
        rows: 1,
      },
    });
  },
};
</script>

<style lang="less">
#swipercom {
  width: 100%;
  #swiperIndex.swiper-container {
    width: 100%;
    height: 300px;
    border-radius: 0.1rem;

    .swiper-slide img {
      width: 100%;
      height: 100%;
    }
    .swiper-pagination-bullet-active {
      background-color: orangered;
    }
  }
}
</style>
