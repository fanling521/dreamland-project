<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input clearable style="width: 280px" class="filter-item" v-model="search.title" placeholder="请输入标题"
                @keyup.enter.native="onSearch"></el-input>
      <el-button class="filter-item" icon="el-icon-search" type="primary" @click="onSearch">查询</el-button>
    </div>
    <el-button class="filter-item" icon="el-icon-edit" type="primary" @click="handleAdd">新增</el-button>
    <el-divider></el-divider>
    <el-table :header-cell-style="{background:'#F5F7FA'}" :data="list" tooltip-effect="light" style="width: 100%">
      <el-table-column align="center" header-align="center" type="index" width="50" label="序号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="title" label="标题"></el-table-column>
      <el-table-column header-align="center" align="url" prop="width" label="广告地址"></el-table-column>
      <el-table-column header-align="center" align="center" prop="img_path" label="图片路径"></el-table-column>
      <el-table-column header-align="center" align="center" prop="position" label="广告位置"></el-table-column>
      <el-table-column header-align="center" align="center" prop="adv_status" label="广告状态"></el-table-column>
      <el-table-column align="center" header-align="center" fixed="right" label="操作" width="170">
        <template slot-scope="scope">
          <el-dropdown placement="bottom-start" trigger="click" type="primary">
            <el-button>
              更多操作
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button icon="el-icon-edit" type="text" size="small" @click="handleEdit(scope.row.id)">编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button icon="el-icon-delete" type="text" size="small" @click="handleDel(scope.row.id)">删除</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <el-pagination @current-change="handleCurrentChange" @size-change="handleSizeChange" background :page-sizes="[10, 20, 30, 40]" :page-size="this.search.page_size" layout="total,sizes,prev, pager, next" :total="count"></el-pagination>
    <add-advertisement :addVisible.sync="addVisible" v-if="addVisible" @callback="initTable"/>
    <edit-advertisement :id="id" :editVisible.sync="editVisible" v-if="editVisible" @callback="initTable"/>
  </div>
</template>
<script>
    import {AddAdvertisement, EditAdvertisement} from './components'
    import {list, remove} from '@/api/advertisement'

    export default {
        components: {AddAdvertisement, EditAdvertisement},
        data() {
            return {
                search: {
                    title:'',
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
