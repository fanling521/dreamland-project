<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入客户姓名" style="width: 280px"
                v-model="search.obj.user_name"></el-input>
      <el-button @click="onSearch" class="filter-item" icon="el-icon-search" type="success">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :data="list" :header-cell-style="{background:'#F5F7FA'}" style="width: 100%">
      <el-table-column align="center" header-align="center" label="序号" type="index" width="50"></el-table-column>
      <el-table-column align="center" header-align="center" label="标题" prop="title"></el-table-column>
      <el-table-column align="center" header-align="center" label="省市区街道" prop="region"></el-table-column>
      <el-table-column align="center" header-align="center" label="客户姓名" prop="user_name"></el-table-column>
      <el-table-column align="center" header-align="center" label="小区室号" prop="community_room"></el-table-column>
      <el-table-column align="center" header-align="center" label="小区名称" prop="community_name"></el-table-column>
      <el-table-column align="center" header-align="center" label="坐标位置" prop="position"></el-table-column>
      <el-table-column align="center" header-align="center" label="维修类型" prop="repair_type"></el-table-column>
      <el-table-column align="center" header-align="center" label="付款金额" prop="user_pay"></el-table-column>
      <el-table-column align="center" header-align="center" label="预约时间" prop="reservation_date"></el-table-column>
      <el-table-column align="center" fixed="right" header-align="center" label="操作" width="120">
        <template fixed="right" slot-scope="scope">
          <el-dropdown placement="bottom-start" trigger="click" type="primary">
            <el-button>
              更多操作
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button @click="handleEdit(scope.row.id)" icon="el-icon-edit" size="small" type="text">编辑人员
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button @click="handleDel(scope.row.id)" icon="el-icon-delete" size="small" type="text">删除人员
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
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

    import {list, remove} from "@/api/maintenance";

    export default {
        data() {
            return {
                search: {
                    obj: {
                        user_name: ''
                    },
                    pageSize: 10,
                    pageNum: 0
                },
                list: [],
                count: 0,
                id: ''
            }
        },
        created() {
            this.initTable();
        },
        methods: {
            handleSizeChange(val) {
                this.search.pageSize = val;
                this.initTable();
            },
            handleCurrentChange(val) {
                this.search.pageNum = val;
                this.initTable();
            },
            onSearch() {
                this.initTable();
            },
            initTable() {
                list(this.search).then(r => {
                    const {count, data} = r;
                    this.list = data;
                    this.count = count;
                })
            },
            handleDel(id) {
                this.$confirm('此操作将永久删除该用户的信息和权限, 是否继续?', '系统提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    remove(key).then(response => {
                        this.$message({
                            type: 'success',
                            message: '删除成功!',
                        });
                        this.initTable();
                    });
                }).catch(() => {
                });
            }
        }
    }
</script>
