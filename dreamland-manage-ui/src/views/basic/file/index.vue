<template>
  <div class="app-container">
    <el-table :header-cell-style="{background:'#F5F7FA'}" :data="list" tooltip-effect="light" style="width: 100%">
      <el-table-column align="center" header-align="center" type="index" width="50" label="序号"></el-table-column>
      <el-table-column header-align="center" align="center" prop="file_name" label="文件名称"></el-table-column>
      <el-table-column header-align="center" align="center" prop="file_path" label="文件路径"></el-table-column>
      <el-table-column header-align="center" align="center" prop="file_size" label="文件大小(KB)"></el-table-column>
      <el-table-column header-align="center" align="center" prop="create_time" label="创建时间"></el-table-column>
      <el-table-column align="center" header-align="center" fixed="right" label="操作" width="170">
        <template slot-scope="scope">
          <el-dropdown placement="bottom-start" trigger="click" type="primary">
            <el-button>
              更多操作
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button icon="el-icon-edit" type="text" size="small"
                           @click="handleView(scope.row.file_path,scope.row.file_extension)">查看图片
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button icon="el-icon-delete" type="text" size="small" @click="handleDownload(scope.row.id)">下载文件
                </el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button icon="el-icon-delete" type="text" size="small" @click="handleDel(scope.row.id)">删除文件
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
    <el-dialog title="查看图片" style="text-align: center" :visible.sync="dialogVisible" width="40%"
               :before-close="handleClose">
      <el-image
        style="width: 40%; height: 40%;"
        :src="pathImg"
        fit="scale-down"></el-image>
    </el-dialog>
  </div>
</template>
<script>
    import {list, remove} from '@/api/file'

    export default {
        data() {
            return {
                search: {
                    page_size: 10,
                    page_num: 0
                },
                list: [],
                baseURL: 'http://139.186.30.39/',
                dialogVisible: false,
                id: '',
                pathImg: '',
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
            },
            handleView(path, extension) {
                if (extension === '.jpg' || extension === '.png') {
                    this.dialogVisible = true
                    this.pathImg = this.baseURL + path
                } else {
                    this.$message({
                        type: 'warning',
                        message: '只支持预览图片！',
                    });
                }
            },
            handleDownload(path) {

            },
            handleClose() {
                this.dialogVisible = false
            }
        }
    }
</script>
