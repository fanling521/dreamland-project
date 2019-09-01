<template>
  <el-dialog
    title="新增"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="35%">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="宽度" prop="width">
        <el-input v-model="form.width" placeholder="请输入宽度"></el-input>
      </el-form-item>
      <el-form-item label="高度" prop="height">
        <el-input v-model="form.height" placeholder="请输入高度"></el-input>
      </el-form-item>
      <el-form-item label="图片路径" prop="img_path">
        <el-input v-model="form.img_path" placeholder="请输入图片路径"></el-input>
      </el-form-item>
      <el-form-item label="广告位置" prop="position">
        <el-input v-model="form.position" placeholder="请输入广告位置"></el-input>
      </el-form-item>
      <el-form-item label="广告状态" prop="adv_status">
        <el-input v-model="form.adv_status" placeholder="请输入广告状态"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
    import {add} from '@/api/advertisement'

    export default {
        name: 'AddAdvertisement',
        props: {
            addVisible: false
        },
        data() {
            return {
                form: {
                    width: '',
                    height: '',
                    img_path: '',
                    position: '',
                    adv_status: '',
                },
                rules: {
                    width: [
                        {required: true, message: '请输入宽度', trigger: 'blur'}
                    ],
                    height: [
                        {required: true, message: '请输入高度', trigger: 'blur'}
                    ],
                    img_path: [
                        {required: true, message: '请输入图片路径', trigger: 'blur'}
                    ],
                    position: [
                        {required: true, message: '请输入广告位置', trigger: 'blur'}
                    ],
                    adv_status: [
                        {required: true, message: '请输入广告状态', trigger: 'blur'}
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
