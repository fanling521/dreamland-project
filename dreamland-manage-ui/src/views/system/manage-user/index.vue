<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input clearable style="width: 280px" class="filter-item" v-model="search.user_phone" placeholder="请输入手机号" @keyup.enter.native="onSearch"></el-input>
      <el-button class="filter-item" icon="el-icon-search" type="primary" @click="onSearch">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :header-cell-style="{background:'#F5F7FA'}" :data="list" tooltip-effect="light" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="密码">
              <span>{{ props.row.password }}</span>
            </el-form-item>
            <el-form-item label="盐">
              <span>{{ props.row.slat }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" header-align="center" type="index" width="50" label="序号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="user_phone" label="手机号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="real_name" label="真实姓名"></el-table-column>
      <el-table-column header-align="center" align="center" prop="user_name" label="用户名"></el-table-column>
      <el-table-column header-align="center" align="center" prop="gender" label="性别"></el-table-column>
      <el-table-column header-align="center" align="center" prop="last_login_date" label="上次登录时间"></el-table-column>
      <el-table-column header-align="center" align="center" prop="last_login_ip" label="上次登录ip"></el-table-column>
      <el-table-column align="center" header-align="center" fixed="right" label="操作" width="170">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="handleDel(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <el-pagination
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      background
      :page-sizes="[10, 20, 30, 40]"
      :page-size="this.search.page_size"
      layout="total,sizes,prev, pager, next"
      :total="count">
    </el-pagination>
  </div>
</template>
<script>
    import {list, remove} from '@/api/appUser'

    export default {
        data() {
            return {
                search: {
                    user_phone:'',
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
