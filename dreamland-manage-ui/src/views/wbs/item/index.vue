<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" icon="el-icon-edit" type="primary" @click="handleAdd">新增</el-button>
      <el-select  clearable class="filter-item" v-model="search.obj.top_name" placeholder="请选择"
                  @change="onSearch">
        <el-option label="维修" value="维修"></el-option>
        <el-option label="保养" value="保养"></el-option>
        <el-option label="设计" value="设计"></el-option>
      </el-select>
      <el-input clearable style="width: 280px" class="filter-item" v-model="search.obj.item_name" placeholder="请输入条目名称"
                @keyup.enter.native="onSearch"></el-input>
      <el-button class="filter-item" icon="el-icon-search" type="success" @click="onSearch">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :header-cell-style="{background:'#F5F7FA'}" :data="list" tooltip-effect="light" style="width: 100%">
      <el-table-column align="center" header-align="center" type="index" width="50" label="序号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="top" label="主类编号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="top_name" label="主类名称"></el-table-column>
      <el-table-column header-align="center" align="center" prop="sub_no" label="分类编号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="item_name" label="条目名称"></el-table-column>
      <el-table-column header-align="center" align="center" prop="item_icon" label="条目图标"></el-table-column>
      <el-table-column header-align="center" align="center" prop="price" label="价格(元)"></el-table-column>
      <el-table-column align="center" header-align="center" fixed="right" label="操作" width="170">
        <template slot-scope="scope">
          <el-dropdown placement="bottom-start" trigger="click" type="primary">
            <el-button>
              更多操作
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button icon="el-icon-edit" type="text" size="small" @click="handleEdit(scope.row.id)">编辑条目
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button icon="el-icon-delete" type="text" size="small" @click="handleDel(scope.row.id)">删除条目
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <el-pagination @current-change="handleCurrentChange" @size-change="handleSizeChange" background
                   :page-sizes="[10, 20, 30, 40]" :page-size="this.search.page_size"
                   layout="total,sizes,prev, pager, next" :total="count"></el-pagination>
    <add-serviceItems :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"/>
    <edit-serviceItems :id="id" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"/>
  </div>
</template>
<script>
    import AddServiceItems from './components/AddServiceItems'
    import EditServiceItems from './components/EditServiceItems'
    import {list, remove} from '@/api/serviceItems'

    export default {
        components: {AddServiceItems, EditServiceItems},
        data() {
            return {
                search: {
                    obj:{
                        item_name: '',
                        top_name: ''
                    },
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
            },
            handleEdit(val) {
                this.editVisible = true;
                this.id = val;
            }
        }
    }
</script>
