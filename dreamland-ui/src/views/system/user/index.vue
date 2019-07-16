<template>
  <div class="app-container">
    <div class="tool-bar">
      <el-button size="medium" type="primary" @click="handleAdd">新增用户</el-button>
    </div>
    <div class="filter-container">
      <el-form :inline="true" :model="search" class="demo-form-inline">
        <el-form-item label="用户名">
          <el-input clearable v-model="search.userName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="登录名">
          <el-input clearable v-model="search.loginName" placeholder="请输入登录名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card class="box-card">
      <el-table :data="list" style="width: 100%">
        <el-table-column type="index" label="序号" width="50"></el-table-column>
        <el-table-column prop="loginName" label="登录名" width="180"></el-table-column>
        <el-table-column prop="userName" label="用户名" width="180"></el-table-column>
        <el-table-column prop="phone" label="手机号码"></el-table-column>
        <el-table-column prop="status" label="账户状态"></el-table-column>
        <el-table-column prop="loginIp" label="上次登录ip"></el-table-column>
        <el-table-column prop="loginDate" label="上次登录时间"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope" fixed="right" width="150">
            <el-button
              size="mini"
              @click="handleEdit(scope.row.userId)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row.userId)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        background
        :page-sizes="[10, 20, 30, 40]"
        :page-size="this.search.pageSize"
        layout="total,sizes,prev, pager, next"
        :total="count">
      </el-pagination>
    </el-card>
    <add-user :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"></add-user>

    <edit-user :userId="userId" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"></edit-user>

  </div>
</template>
<script>

  import {userList, removeUser} from "@/api/system/user";
  import {AddUser, EditUser} from './components'

  export default {
    components: {AddUser, EditUser},
    data() {
      return {
        search: {
          userName: '',
          loginName: '',
          status: '',
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
