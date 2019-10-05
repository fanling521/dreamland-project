<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入订单标题" style="width: 280px"
                v-model="search.obj.title"></el-input>
      <el-button @click="onSearch" class="filter-item" icon="el-icon-search" type="success">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :data="list" :header-cell-style="{background:'#F5F7FA'}" style="width: 100%" tooltip-effect="light">
      <el-table-column align="center" header-align="center" label="序号" type="index" width="50"></el-table-column>
      <el-table-column align="center" header-align="center" label="订单标题" prop="title"></el-table-column>
      <el-table-column align="center" header-align="center" label="维修人员" prop="user_name"></el-table-column>
      <el-table-column align="center" header-align="center" label="评价" prop="person_comment"></el-table-column>
      <el-table-column align="center" header-align="center" label="评价统计" prop="count"></el-table-column>
      <el-table-column align="center" fixed="right" header-align="center" label="操作" width="170">
        <template slot-scope="scope">
          <el-button @click="handleDel(scope.row.id)" size="small" type="text">删除评价</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <el-pagination
      :page-size="this.search.page_size"
      :page-sizes="[10, 20, 30, 40]"
      :total="count"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      background
      layout="total,sizes,prev, pager, next">
    </el-pagination>
  </div>
</template>
<script>
    import {list, remove} from '@/api/staffComment'

    export default {
        data() {
            return {
                search: {
                    obj: {title: ''},
                    page_size: 10,
                    page_num: 0
                },
                list: [],
                addVisible: false,
                editVisible: false,
                id: '',
                count: 0
            }
        },
        created() {
            this.initTable();
        },
        methods: {
            handleSizeChange(val) {
                this.search.page_size = val;
                this.initTable();
            },
            handleCurrentChange(val) {
                this.search.page_num = val;
                this.initTable();
            },
            initTable() {
                list(this.search).then(r => {
                    const {data, count} = r;
                    this.list = data;
                    this.count = count;
                })
            },
            onSearch() {
                this.initTable();
            },
            handleDel(val) {
                this.$confirm('此操作将永久删除次条信息以及其关联, 是否继续?', '系统提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    remove(val).then(response => {
                        const {code, msg} = response
                        if (code === 0) {
                            this.$message({
                                type: 'success',
                                message: '删除成功!',
                            });
                            this.initTable();
                        } else {
                            this.$message({
                                type: 'error',
                                message: msg,
                            });
                        }
                    });
                }).catch(() => {
                });
            }
        }
    }
</script>
