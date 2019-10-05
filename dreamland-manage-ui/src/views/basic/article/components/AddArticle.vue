<template>
  <el-dialog
    :close-on-click-modal="false"
    :close-on-press-escape="true" :modal="true" :visible.sync="show" @close="resetForm('form')"
    title="新增文章"
    width="50%">
    <el-form :model="form" :rules="rules" label-width="110px" ref="form">
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
        <el-button @click="onSubmit('form')" type="primary">立即创建</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
    import {add} from '@/api/article'
    import {service_type} from '@/utils/dict'

    export default {
        name: 'AddArticle',
        props: {
            addVisible: false
        },
        data() {
            return {
                source_options:service_type,
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
                show: this.addVisible,
            }
        },
        methods: {
            handlePictureCardPreview(){},
            handleRemove(){},
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
