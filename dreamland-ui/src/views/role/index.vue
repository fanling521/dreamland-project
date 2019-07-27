<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input class="filter-item" style="width: 200px;" clearable v-model="search.role_name"
                placeholder="请输入角色名"></el-input>
      <el-button icon="el-icon-search" class="filter-item" type="primary" @click="onSearch">查询</el-button>
      <el-button icon="el-icon-edit" class="filter-item" size="medium" type="primary" @click="handleAdd">新增
      </el-button>
    </div>

    <el-table fit :data="list" style="width: 100%">
      <el-table-column align="center" header-align="center" type="index" label="序号" width="50"></el-table-column>
      <el-table-column header-align="center" prop="role_name" label="角色名称"></el-table-column>
      <el-table-column header-align="center" prop="role_key" label="角色权限"></el-table-column>
      <el-table-column header-align="center" prop="role_status" label="角色状态"></el-table-column>
      <el-table-column header-align="center" prop="create_time" label="创建时间"></el-table-column>
      <el-table-column header-align="center" prop="update_time" label="更新时间"></el-table-column>

      <el-table-column header-align="center" label="操作" fixed="right" width="150">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row.role_id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.role_id)">删除</el-button>
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

    <add-role :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"></add-role>

    <edit-role :roleId="roleId" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"></edit-role>

  </div>
</template>
<script>

  import {roleList, removeRole} from "@/api/role";
  import {AddRole, EditRole} from './components'

  export default {
    components: {AddRole, EditRole},
    data() {
      return {
        search: {
          role_name: '',
          page_size: 10,
          page_num: 0
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
