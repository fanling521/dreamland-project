<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入真实姓名" style="width: 280px"
                v-model="search.obj.real_name"></el-input>
      <el-input @keyup.enter.native="onSearch" class="filter-item" clearable placeholder="请输入身份证号码"
                style="width: 280px" v-model="search.obj.id_card_num"></el-input>
      <el-button @click="onSearch" class="filter-item" icon="el-icon-search" type="primary">查询</el-button>
    </div>
    <el-divider></el-divider>
    <el-table :data="list" :header-cell-style="{background:'#F5F7FA'}" style="width: 100%" tooltip-effect="light">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form class="demo-table-expand" inline label-position="left">
            <el-form-item label="真实姓名">
              <span>{{ props.row.real_name }}</span>
            </el-form-item>
            <el-form-item label="身份证号码">
              <span>{{ props.row.id_card_num }}</span>
            </el-form-item>
            <el-form-item label="身份证正面图片路径">
              <span>{{ props.row.id_card_photo_path1 }}</span>
            </el-form-item>
            <el-form-item label="手持身份证">
              <span>{{ props.row.id_card_photo_path3 }}</span>
            </el-form-item>
            <el-form-item label="验证状态">
              <span>{{ props.row.status }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" header-align="center" label="序号" type="index" width="50"></el-table-column>
      <el-table-column align="center" header-align="center" label="真实姓名" prop="real_name"></el-table-column>
      <el-table-column align="center" header-align="center" label="身份证号码" prop="id_card_num"></el-table-column>
      <el-table-column align="center" header-align="center" label="身份证正面" prop="id_card_photo_path1"></el-table-column>
      <el-table-column align="center" header-align="center" label="身份证反面"
                       prop="id_card_photo_path2"></el-table-column>
      <el-table-column align="center" header-align="center" label="手持身份证" prop="id_card_photo_path3"></el-table-column>
      <el-table-column align="center" header-align="center" label="验证状态" prop="status"></el-table-column>
      <el-table-column align="center" fixed="right" header-align="center" label="操作" width="170">
        <template slot-scope="scope">
          <el-button @click="handleDel(scope.row.id)" size="small" type="text">删除</el-button>
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
  </div>
</template>
<script>
    import {list, remove} from '@/api/appIdCard'

    export default {
        data() {
            return {
                search: {
                    obj: {
                        real_name: '',
                        id_card_num: ''
                    },
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
