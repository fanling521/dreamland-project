<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入手机号" style="width: 280px"
                v-model="search.obj.user_phone"></el-input>
      <el-button @click="onSearch" class="filter-item" icon="el-icon-search" type="primary">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :data="list" :header-cell-style="{background:'#F5F7FA'}" style="width: 100%" tooltip-effect="light">
      <el-table-column align="center" header-align="center" label="序号" type="index" width="50"></el-table-column>
      <el-table-column align="center" header-align="center" label="用户名" prop="user_id"></el-table-column>
      <el-table-column align="center" header-align="center" label="手机号码" prop="user_phone"></el-table-column>
      <el-table-column align="center" header-align="center" label="操作系统版本" prop="phone_version"></el-table-column>
      <el-table-column align="center" header-align="center" label="手机操作系统" prop="phone_type"></el-table-column>
      <el-table-column align="center" fixed="right" header-align="center" label="操作" width="170">
        <template slot-scope="scope">
          <el-button @click="handleDel(scope.row.id)" size="small" type="text">删除</el-button>
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
    import {list, remove} from '@/api/deviceInfo'

    export default {
        data() {
            return {
                search: {
                    obj: {user_phone: ''},
                    page_size: 10,
                    page_num: 0
                },
                list: [],
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
