<template>
  <el-dialog
    title="新增"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="40%">
    <el-form ref="form" :rules="rules" :model="form" label-width="110px">
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
        <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
    import {add} from '@/api/article'

    export default {
        name: 'AddArticle',
        props: {
            addVisible: false
        },
        data() {
            return {
                dialogImageUrl: '',
                dialogVisible: false,
                form: {
                    author: '',
                    source: '',
                    img_path: '',
                    content: '',
                    title: '',
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
                show: this.addVisible,
            }
        },
        methods: {
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        add(this.form).then(response => {
                            const {code, msg} = response
                            if (code === 0) {
                                this.$alert("新增成功", '系统提示', {
                                    type: 'success',
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        this.resetForm(formName)
                                    }
                                });
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
                this.$emit('update:addVisible', false);
                //调用父组件方法
                this.$emit('callback');
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            }
        }
    }
</script>
