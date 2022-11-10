<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-11 15:32:47
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 13:37:28
 * @FilePath: \bookmangement\src\views\Test.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="app">
    <el-container class="el-container">
      <!-- 侧边栏 -->
      <div class="aside">
        <el-aside
          class="app-side app-side-left"
          width="178px"
          style="overflow: hidden"
          :class="isCollapse ? 1 :3"
        >
          <!-- login 展示 -->
          <div class="app-side-logo">
            <img
              src="@/assets/logo.png"
              :width="isCollapse ? '60' : '60'"
              height="60"
            />
          </div>

          <!-- 展开与收缩 -->

          <el-menu
            default-active="1-3-1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            :collapse="isCollapse"
            style="height: 100%,width=initial,display: none"
            text-color="#ffffff"
          >
            <!-- 图表详情 -->
            <el-submenu
              :index="item.label"
              v-for="(item, index) in router_list"
              :key="item.path + index"
              @click="router_tz(item.path)"
            >
              <template slot="title">
                <i class="el-icon-s-data"></i>
                <span slot="title">{{ item.label }}</span>
              </template>
              <el-menu-item-group
                style="background-color: #324157 !important"
                v-if="item.children"
              >
                <el-menu-item
                  :index="item.path"
                  v-for="(item, index) in item.children"
                  :key="item.path"
                  @click="router_tz(item.path)"
                  >{{ item.label }}</el-menu-item
                >
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
      </div>

      <!-- 头部和主体部分 -->
      <el-container>
        <!-- 头部 -->
        <el-header class="app-header">
          <div 
            style="width: 60px; cursor: pointer;float: left;"
            @click.prevent="toggleSideBar"
          >
            <i v-show="!isCollapse" class="el-icon-d-arrow-left"></i>
            <i v-show="isCollapse" class="el-icon-d-arrow-right"></i>
          </div>
          <div>
            <h1 class="app-header-title">图书管理系统</h1>
          </div>

          <!-- 登录状态 -->
          <div class="app-header-userinfo">
            <el-dropdown trigger="hover" :hide-on-click="false">
              <span class="el-dropdown-link" style="color: #ffff">
                {{ username }}
                <i class="el-icon-arrow-down el-icon--right"></i>
              </span>

              <!-- 设置登录 -->
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>我的消息</el-dropdown-item>
                <el-dropdown-item>设置</el-dropdown-item>
                <el-dropdown-item divided @click.native="logout"
                  >退出登录</el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-header>

        <!-- 主体部分 -->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Container",
  data() {
    return {
      username: "",
      isCollapse: false,
      // 定义路由
      router_list: [
        {
          label: "首页",
          // path: "/index",
          children: [
            {
              path: "/group",
              label: "项目介绍",
            },
            {
              path: "/index",
              label: "总览",
            },
          ],
        },
        {
          label: "详情",
          children: [
            {
              path: "/authors",
              label: "作者",
            },
            {
              path: "/books",
              label: "图书",
            },
            {
              path: "/scores",
              label: "评分",
            },
          ],
        },
      ],
    };
  },
  methods: {
    // 编程式路由导航实现路由跳转
    router_tz(path) {
      this.$router.push({
        path,
      });
    },

    toggleSideBar() {
      this.isCollapse = !this.isCollapse;
    },
    logout: function () {
      this.$confirm("确认退出?", "提示", {})
        .then(() => {
          sessionStorage.removeItem("user");
          this.$router.push("/");
        })
        .catch(() => {});
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
<style>
/* 头部样式 */
.el-header {
  background-color: #324157;
  color: #333;
  line-height: 60px;
}
/* 侧边栏样式 */
.el-aside {
  color: #333;
}
/* 主体布局样式 */
.el-main {
  height: 480px;
  background-color: #eeeeee50;
  color: #fafafa;
  border: 5px solid #ccc;
}

.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #5591e6;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

/* 设置图表 */
.ec-tem {
  height: 100px;
  width: 60px;
  /* background-color: aqua; */
}
.max-el-tem {
  height: 400px;
  width: 100%;
}
</style>
