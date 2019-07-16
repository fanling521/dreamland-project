<template>
  <div class="app-container">
    <div class="tool-bar">
      <el-button size="medium" type="primary" @click="handleAdd">新增角色</el-button>
    </div>
    <div class="filter-container">
      <el-form :inline="true" :model="search" class="demo-form-inline">
        <el-form-item label="角色名">
          <el-input clearable v-model="search.roleName" placeholder="请输入角色名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card class="box-card">
      <el-table :data="list" style="width: 100%">
        <el-table-column type="index" label="序号" width="50"></el-table-column>
        <el-table-column prop="roleName" label="角色名" width="180"></el-table-column>
        <el-table-column prop="roleKey" label="角色权限" width="180"></el-table-column>
        <el-table-column prop="status" label="角色状态"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column label="操作" fixed="right" width="150">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.row.roleId)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row.roleId)">删除
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
    <add-role :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"></add-role>

    <edit-role :roleId="roleId" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"></edit-role>

  </div>
</template>
<script>

  import {roleList, removeRole} from "@/api/system/role";
  import {AddRole, EditRole} from './components'

  export default {
    components: {AddRole, EditRole},
    data() {
      return {
        search: {
          roleName: '',
          pageSize: 10,
          pageNum: 0
        },
        list: [],
        count: 0,
        addVisible: false,
        editVisible: false,
        roleId: ''
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
        roleList(this.search).then(r => {
          const {count, data} = r;
          this.list = data;
          this.count = count;
        })
      },
      handleEdit(key) {
        this.roleId = key;
        this.editVisible = true;
      },
      handleAdd() {
        this.addVisible = true;
      },
      handleDelete(key) {
        this.$confirm('此操作将永久删除角色信息, 是否继续?', '系统提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          removeRole(key).then(response => {
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
