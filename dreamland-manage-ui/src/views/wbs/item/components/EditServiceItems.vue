<template>
  <el-dialog
    title="修改"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="40%">
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
      <el-form-item label="条目图标">
        <el-input v-model="form.item_icon" placeholder="请输入条目图标"></el-input>
      </el-form-item>
      <el-form-item label="服务费用" prop="price">
        <el-input v-model="form.price" placeholder="请输入服务费用"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改保存</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
    import {edit, get} from "@/api/serviceItems";

    export default {
        name: 'EditServiceItems',
        props: {
            editVisible: false,
            id: ''
        },
        data() {
            return {
                form: {
                    top: '',
                    sub_no: '',
                    item_name: '',
                    top_name: '',
                    item_icon:'',
                    price:'',
                    id: this.id
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
                    price: [
                        {required: true, message: '请输入服务费用', trigger: 'blur'}
                    ]
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
