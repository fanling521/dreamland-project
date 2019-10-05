<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button @click="handleAdd" class="filter-item" icon="el-icon-edit" size="medium" type="primary">新增</el-button>
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入手机号" style="width: 280px"
                v-model="search.obj.user_phone"></el-input>
      <el-button @click="onSearch" class="filter-item" icon="el-icon-search" type="success">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :data="list" :header-cell-style="{background:'#F5F7FA'}" style="width: 100%" tooltip-effect="light">
      <el-table-column align="center" header-align="center" label="序号" type="index" width="50"></el-table-column>
      <el-table-column align="center" header-align="center" label="登录名" prop="user_phone"></el-table-column>
      <el-table-column align="center" header-align="center" label="用户名" prop="user_name"></el-table-column>
      <el-table-column align="center" header-align="center" label="性别" prop="gender"></el-table-column>
      <el-table-column align="center" header-align="center" label="上次登录时间" prop="last_login_date"></el-table-column>
      <el-table-column align="center" header-align="center" label="上次登录ip" prop="last_login_ip"></el-table-column>
      <el-table-column align="center" fixed="right" header-align="center" label="操作" width="170">
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
    <add-user :addVisible.sync="addVisible" @callback="initTable" v-if="addVisible"></add-user>
    <edit-user :editVisible.sync="editVisible" :id="id" @callback="initTable" v-if="editVisible"></edit-user>
  </div>
</template>
<script>
    import {list, remove} from '@/api/user'
    import AddUser from './components/AddUser'
    import EditUser from './components/EditUser'
    export default {
        components: {AddUser, EditUser},
        data() {
            return {
                search: {
                    obj: {user_phone: ''},
                    page_size: 10,
                    page_num: 0
                },
                list: [],
                id: '',
                count: 0,
                addVisible: false,
                editVisible: false
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
            handleEdit(key) {
                this.id = key;
                this.editVisible = true;
            },
            handleAdd() {
                this.addVisible = true;
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
