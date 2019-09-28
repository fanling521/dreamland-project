<template>
  <el-dialog
    title="修改"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="40%">
    <el-form ref="form" :rules="rules" :model="form" label-width="100px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="作者" prop="author">
            <el-input v-model="form.author" placeholder="请输入作者"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="来源" prop="source">
            <el-input v-model="form.source" placeholder="请输入来源"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="文章图片">
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="文章正文" prop="content">
        <el-input type="textarea" v-model="form.content" placeholder="请输入文章正文（富文本）"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改保存</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
    import {edit, get} from "@/api/article";

    export default {
        name: 'EditArticle',
        props: {
            editVisible: false,
            id: ''
        },
        data() {
            return {
                form: {
                    author:'',
                    source:'',
                    img_path:'',
                    content:'',
                    title:'',
                    id: this.id
                },
                rules: {
                    author: [
                        {required: true, message: '请输入作者', trigger: 'blur'}
                    ],
                    source: [
                        {required: true, message: '请输入来源', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入文章正文（富文本）', trigger: 'blur'}
                    ],
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'}
                    ],
                },
                show: this.editVisible,
            }
        },
        created() {
            this.getInitInfo()
        },
        methods: {
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
