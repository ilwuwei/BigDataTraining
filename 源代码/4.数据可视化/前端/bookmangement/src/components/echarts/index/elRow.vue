<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 09:24:21
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 18:15:43
 * @FilePath: \bookmangement\src\components\echarts\index\el-row.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="first" id="swipercom">
    <div class="swiper-container" id="swiperIndex">
      <div class="swiper-wrapper">
        <div class="swiper-slide" v-for="(item, i) in imgs" :key="i">
          <img :src="item.pic" alt="" />
        </div>
      </div>
      <!-- //换页器
      <div class="swiper-pagination"></div> -->
      <!-- //前进后退
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
      //滚动条
      <div class="swiper-scrollbar"></div> -->
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
        { pic: require("@/assets/imgs/all/1.jpg") },
        { pic: require("@/assets/imgs/all/2.jpg") },
        { pic: require("@/assets/imgs/all/3.jpg") },
        { pic: require("@/assets/imgs/all/4.jpg") },
        { pic: require("@/assets/imgs/all/5.jpg") },
        { pic: require("@/assets/imgs/all/6.jpg") },
        { pic: require("@/assets/imgs/all/7.jpg") },
        { pic: require("@/assets/imgs/all/8.jpg") },
        { pic: require("@/assets/imgs/all/9.jpg") },
        { pic: require("@/assets/imgs/all/10.jpg") },
        { pic: require("@/assets/imgs/all/11.jpg") },
        { pic: require("@/assets/imgs/all/12.jpg") },
        { pic: require("@/assets/imgs/all/13.jpg") },
        { pic: require("@/assets/imgs/all/14.jpg") },
        { pic: require("@/assets/imgs/all/15.jpg") },
      ],
    };
  },
  mounted() {
    var mySwiper = new Swiper("#swiperIndex", {
      //配置分页器内容
      direction: 'vertical',
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
        delay: 1800,
      },
      autoHeight: true, //高度随内容变化
      // 轮播样式
      // effect: "coverflow",
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
.first{
  display:block;
  margin-bottom: -10000px;
  padding-bottom: 10000px;
}

#swipercom {
  width: 250px;
  float: left;
  #swiperIndex.swiper-container {
    width: 100%;
    height: 100%;
    border-radius: 0.1rem;

    .swiper-slide img {
      width: 100%;
      height: 100%;
    }
    .swiper-pagination-bullet-active {
      background-color: orangered;
    }
    .swiper-slide{height:1px} /* 随意指定一个height值即可 */    

.swiper-slide-active { height:auto}
  }
}
</style>

