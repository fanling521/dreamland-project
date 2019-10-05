<template>
  <el-dialog
    :close-on-click-modal="false"
    :close-on-press-escape="true" :modal="true" :visible.sync="show" @close="resetForm('form')"
    title="修改文章"
    width="50%">
    <el-form :model="form" :rules="rules" label-width="100px" ref="form">
      <el-form-item label="文章标题" prop="title">
        <el-input placeholder="请输入文章标题" v-model="form.title"></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="文章作者" prop="author">
            <el-input placeholder="请输入文章作者" v-model="form.author"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="文章来源" prop="source">
            <el-select placeholder="请选择" v-model="form.source">
              <el-option
                :key="item.value"
                :label="item.label"
                :value="item.value"
                v-for="item in source_options">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="文章图片">
        <el-upload
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
          action="http://139.186.30.39/microservice/attachment-api/app/file/attachment/upload"
          list-type="picture-card">
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="文章正文" prop="content">
        <el-input placeholder="请输入文章正文" rows="5" type="textarea" v-model="form.content"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit('form')" type="primary">修改保存</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
    import {edit, get} from "@/api/article";
    import {service_type} from '@/utils/dict'
    export default {
        name: 'EditArticle',
        props: {
            editVisible: false,
            id: ''
        },
        data() {
            return {
                source_options:service_type,
                form: {
                    author: '',
                    source: '',
                    img_path: '',
                    content: '',
                    title: '',
                    id: this.id
                },
                rules: {
                    author: [
                        {required: true, message: '请输入文章作者', trigger: 'blur'}
                    ],
                    source: [
                        {required: true, message: '请输入文章来源', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入文章正文', trigger: 'blur'}
                    ],
                    title: [
                        {required: true, message: '请输入文章标题', trigger: 'blur'}
                    ],
                },
                show: this.editVisible,
            }
        },
        created() {
            this.getInitInfo()
        },
        methods: {
            handlePictureCardPreview(){},
            handleRemove(){},
            getInitInfo() {
                get(this.form.id).then(r => {
                    const {data} = r;
                    this.form = data;
                })
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        edit(this.form).then(response => {
                            const {code, msg} = response
                            if (code === 0) {
                                this.$alert("修改成功", '系统提示', {
                                    type: 'success',
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        this.resetForm(formName)
                                    }
                                });
                                this.initTable();
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: msg,
                                });
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.$emit('update:editVisible', false);
                //调用父组件方法
                this.$emit('callback');
            }
        }
    }
</script>
