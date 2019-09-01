<template>
  <el-dialog
    title="新增"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="40%">
    <el-form ref="form" :rules="rules" :model="form" label-width="100px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者"></el-input>
      </el-form-item>
      <el-form-item label="来源" prop="source">
        <el-input v-model="form.source" placeholder="请输入来源"></el-input>
      </el-form-item>
      <el-form-item label="图片1" prop="img_1_path">
        <el-input v-model="form.img_1_path" placeholder="请输入图片1"></el-input>
      </el-form-item>
      <el-form-item label="图片2" prop="img_2_path">
        <el-input v-model="form.img_2_path" placeholder="请输入图片2"></el-input>
      </el-form-item>
      <el-form-item label="图片3" prop="ing_3_path">
        <el-input v-model="form.img_3_path" placeholder="请输入图片3"></el-input>
      </el-form-item>
      <el-form-item label="文章正文" prop="content">
        <el-input v-model="form.content" placeholder="请输入文章正文（富文本）"></el-input>
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
                form: {
                    author: '',
                    source: '',
                    img_1_path: '',
                    img_2_path: '',
                    ing_3_path: '',
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
            }
        }
    }
</script>
