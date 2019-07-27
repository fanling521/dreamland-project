<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input class="filter-item" style="width: 200px;" clearable v-model="search.phone"
                placeholder="请输入手机号"></el-input>
      <el-input class="filter-item" style="width: 200px;" clearable v-model="search.user_name"
                placeholder="请输入管理员姓名"></el-input>
      <el-button icon="el-icon-search" class="filter-item" type="primary" @click="onSearch">查询</el-button>
      <el-button icon="el-icon-edit" class="filter-item" size="medium" type="primary" @click="handleAdd">新增</el-button>
    </div>

    <el-table fit :data="list" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="创建时间">
              <span>{{ props.row.create_time }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ props.row.update_time }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" header-align="center" type="index" label="序号" width="50"></el-table-column>
      <el-table-column header-align="center" prop="user_name" label="用户名" width="180"></el-table-column>
      <el-table-column header-align="center" prop="phone" label="手机号码"></el-table-column>
      <el-table-column header-align="center" prop="email" label="邮箱地址"></el-table-column>
      <el-table-column header-align="center" prop="status" label="账户状态"></el-table-column>
      <el-table-column header-align="center" prop="login_ip" label="上次登录ip"></el-table-column>
      <el-table-column header-align="center" prop="login_date" label="上次登录时间"></el-table-column>
      <el-table-column header-align="center" label="操作">
        <template slot-scope="scope" fixed="right" width="180">
          <el-button size="mini" @click="handleEdit(scope.row.user_id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.user_id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      background
      :page-sizes="[10, 20, 30, 40]"
      :page-size="this.search.page_size"
      layout="total,sizes,prev, pager, next"
      :total="count">
    </el-pagination>
    <add-user :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"></add-user>

    <edit-user :userId="userId" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"></edit-user>

  </div>
</template>
<script>

  import {userList, removeUser} from "@/api/user";
  import {AddUser, EditUser} from './components'

  export default {
    components: {AddUser, EditUser},
    data() {
      return {
        search: {
          phone: '',
          user_name: '',
          pageSize: 10,
          pageNum: 0
        },
        list: [],
        count: 0,
        addVisible: false,
        editVisible: false,
        userId: ''
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
        userList(this.search).then(r => {
          const {count, data} = r;
          this.list = data;
          this.count = count;
        })
      },
      handleEdit(key) {
        this.userId = key;
        this.editVisible = true;
      },
      handleAdd() {
        this.addVisible = true;
      },
      handleDelete(key) {
        this.$confirm('此操作将永久删除该用户的信息和权限, 是否继续?', '系统提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          removeUser(key).then(response => {
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

<style lang="scss">
  .demo-table-expand {
    font-size: 0;

    & label {
      width: 90px;
      color: #99a9bf;
    }

    .el-form-item {
      margin-right: 0;
      margin-bottom: 0;
      width: 50%;
    }
  }
</style>
