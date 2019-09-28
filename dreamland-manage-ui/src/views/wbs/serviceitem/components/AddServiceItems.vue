<template>
  <el-dialog
    title="新增"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="30%">
    <el-form ref="form" :rules="rules" :model="form" label-width="100px">
      <el-form-item label="主类编号" prop="top">
        <el-input v-model="form.top" placeholder="请输入主类编号"></el-input>
      </el-form-item>
      <el-form-item label="主类名称" prop="top_name">
        <el-input v-model="form.top_name" placeholder="请输入主类名称"></el-input>
      </el-form-item>
      <el-form-item label="分类编号" prop="sub_no">
        <el-input v-model="form.sub_no" placeholder="请输入分类编号"></el-input>
      </el-form-item>
      <el-form-item label="条目名称" prop="item_name">
        <el-input v-model="form.item_name" placeholder="请输入条目名称"></el-input>
      </el-form-item>
      <el-form-item label="条目图标" prop="item_name">
        <el-input v-model="form.item_icon" placeholder="请输入条目图标"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
    import {add} from '@/api/serviceItems'

    export default {
        name: 'AddServiceItems',
        props: {
            addVisible: false
        },
        data() {
            return {
                form: {
                    top: '',
                    sub_no: '',
                    item_name: '',
                    top_name: '',
                    item_icon:''
                },
                rules: {
                    top: [
                        {required: true, message: '请输入主类编号', trigger: 'blur'}
                    ],
                    sub_no: [
                        {required: true, message: '请输入分类编号', trigger: 'blur'}
                    ],
                    item_name: [
                        {required: true, message: '请输入条目名称', trigger: 'blur'}
                    ],
                    top_name: [
                        {required: true, message: '请输入主类名称', trigger: 'blur'}
                    ],
                    item_icon: [
                        {required: true, message: '请输入条目图标', trigger: 'blur'}
                    ]
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
