<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" icon="el-icon-search" type="primary" @click="onSearch">查询</el-button>
    </div>
    <el-button class="filter-item" icon="el-icon-edit" type="primary" @click="handleAdd">新增</el-button>
    <el-divider></el-divider>
    <el-table :header-cell-style="{background:'#F5F7FA'}" :data="list" tooltip-effect="light" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="用户标识">
              <span>{{ props.row.user_id }}</span>
            </el-form-item>
            <el-form-item label="维修人员标识">
              <span>{{ props.row.p_user_id }}</span>
            </el-form-item>
            <el-form-item label="商品标识">
              <span>{{ props.row.goods_id }}</span>
            </el-form-item>
            <el-form-item label="订单标识">
              <span>{{ props.row.order_id }}</span>
            </el-form-item>
            <el-form-item label="投诉原因">
              <span>{{ props.row.complaint }}</span>
            </el-form-item>
            <el-form-item label="问题解决">
              <span>{{ props.row.reply }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" header-align="center" type="index" width="50" label="序号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="user_id" label="用户标识"></el-table-column>
      <el-table-column header-align="center" align="center" prop="p_user_id" label="维修人员标识"></el-table-column>
      <el-table-column header-align="center" align="center" prop="goods_id" label="商品标识"></el-table-column>
      <el-table-column header-align="center" align="center" prop="order_id" label="订单标识"></el-table-column>
      <el-table-column header-align="center" align="center" prop="complaint" label="投诉原因"></el-table-column>
      <el-table-column header-align="center" align="center" prop="reply" label="问题解决"></el-table-column>
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
    import {list, remove} from '@/api/afterSale'

    export default {
        data() {
            return {
                search: {
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
