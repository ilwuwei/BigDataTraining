<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 20:06:30
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-23 18:11:21
 * @FilePath: \bookmangement\src\components\echarts\index\bookList.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <div class="book">
    <el-table v-fit-columns
    :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" 
    border
    :stripe="stripe"
        :current-page.sync="currentPage" style="width: 100%">
      <el-table-column   prop="book_name" label="书名" width="150"></el-table-column>
      <el-table-column prop="author" label="作者" width="150">
      </el-table-column>
      <el-table-column prop="publisher" label="出版社"  width="150"></el-table-column>
      <el-table-column prop="isbn" label="编码" width="200"></el-table-column>
      <el-table-column prop="weight" label="推荐指数" width="100"></el-table-column>
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="[5,10, 15, 20]"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>
    </div>
</div>
  </template>
  
  <script>
  import {bookList} from "@/network/api.js"
    export default {
        name:"bookList",
        mounted() {
        this.getPage();
      },
        data() {
        return {
            tableData: [],
            currentPage:1,
            pagesize:10,
            total:1000,
        }
      },
     
      methods: {
        handleClick(row) {
        console.log(row);
      },
      handleSizeChange(val) {
            this.pagesize=val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
        },
        async getPage(){
            let res = await bookList();
            if (res !=0) {
                this.tableData = res;
                console.log(res,"请求成功");
            } else {
                this.$message.error("res.msg")
            }

        }
      },
      
  
    

    }
  </script>
  <style>
  .book{
  float:left;
  flex: 1;
  margin-left:2px;
  margin-bottom: -10000px;
  padding-bottom: 10000px;


  }

</style>