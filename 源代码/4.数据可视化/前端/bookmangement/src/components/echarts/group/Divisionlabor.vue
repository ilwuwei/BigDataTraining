<!--
 * @Author: error: git config user.name && git config user.email & please set dead value or install git
 * @Date: 2022-10-22 11:43:00
 * @LastEditors: error: git config user.name && git config user.email & please set dead value or install git
 * @LastEditTime: 2022-10-22 20:50:48
 * @FilePath: \bookmangement\src\components\echarts\group\Divisionlabor.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
    <el-table
      ref="filterTable"
      :data="tableData"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <!-- 姓名列 -->
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="position"
        label="职位"
        width="180">
      </el-table-column>
      <el-table-column
        prop="technology"
        label="技术"
        width="220">
      </el-table-column>
      <el-table-column
        prop="tag"
        label="标签"
        width="100"
        :filters="[{ text: '数据采集', value: '数据采集' }, { text: '数据处理', value: '数据处理' },
    { text: '数据分析', value: '数据分析' }, { text: '数据可视化', value: '数据可视化' },
    { text: '前端', value: '前端' }, { text: '后端', value: '后端' },
    { text: '环境搭建', value: '环境搭建' }, { text: '文档管理', value: '文档管理' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.tag === '数据采集'  ? 'primary' : 'success'"
            disable-transitions>{{scope.row.tag}}</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [{
            name: '彭佳欢',
            position: '技术总监',
            technology:'Scrapy Redis Mysql hadoop集群',
            tag: '数据采集',
          }, {
            name: '施俊杰',
            position: '数据分析',
            technology:'SQL Sparksql hadoop集群',
            tag: '数据分析',
          }, {
            name: '艾丽思',
            position: '前端搭建',
            technology:'Vue Element-UI Axios JavaScript',
            tag: '前端',
          }, {
            name: '袁帅',
            position: '后端搭建',
            technology:'SpringBoot Knife4j Mysql',
            tag: '后端',
          },{
            name: '柏彬彬',
            position: '数据可视化',
            technology:'Echarts Axios',
            tag: '数据可视化',
          },{
            name: '欧阳瑞頔',
            position: '数据可视化',
            technology:'Echarts Axios',
            tag: '数据可视化',
          }
        ]
        }
      },
      methods: {
        // resetDateFilter() {
        //   this.$refs.filterTable.clearFilter('date');
        // },
        // clearFilter() {
        //   this.$refs.filterTable.clearFilter();
        // },
        formatter(row, column) {
          return row.address;
        },
        filterTag(value, row) {
          return row.tag === value;
        },
        filterHandler(value, row, column) {
          const property = column['property'];
          return row[property] === value;
        },
        tableRowClassName({row, rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex == 3) {
          return 'success-row';
        }
        return '';
      }
      }
    }
  </script>
  <style>
   .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>