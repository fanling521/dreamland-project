<template>
  <el-dialog
    :close-on-click-modal="false"
    :close-on-press-escape="true" :modal="true" :visible.sync="show" @close="resetForm('form')"
    title="新增用户"
    width="45%">
    <el-form :model="form" :rules="rules" label-width="80px" ref="form">
      <el-form-item label="登录名" prop="user_phone">
        <el-input v-model="form.user_phone"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="user_name">
        <el-input v-model="form.user_name"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select  v-model="form.gender" placeholder="请选择">
          <el-option label="男" value="1"></el-option>
          <el-option label="女" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit('form')" type="primary">新增</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
    import {add} from '@/api/user'

    export default {
        name: 'AddUser',
        props: {
            addVisible: false
        },
        data() {
            return {
                form: {
                    user_phone: '',
                    user_name: '',
                    gender: ''
                },
                show: this.addVisible,
                rules: {
                    user_phone: [
                        {required: true, message: '请输入登录名', trigger: 'blur'},
                        {max: 10, message: '长度在 10 个字符以内', trigger: 'blur'}
                    ],
                    user_name: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {max: 20, message: '长度在 20 个字符以内', trigger: 'blur'}
                    ],
                    gender:[
                        {required: true, message: '请输入性别', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        add(this.form).then(() => {
                            this.$alert("新增成功", '系统提示', {
                                type: 'success',
                                confirmButtonText: '确定',
                                callback: action => {
                                    this.resetForm(formName)
                                }
                            });
                        });
                    } else {
                        return false;
                    }
                });

            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.$emit('update:addVisible', false);
                this.$emit('callback');
            }
        }
    }
</script>
