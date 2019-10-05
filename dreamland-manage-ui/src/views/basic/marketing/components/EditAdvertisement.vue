<template>
  <el-dialog
    title="修改"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="50%">
    <el-form ref="form" :rules="rules" :model="form" label-width="100px">
      <el-form-item label="广告标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入广告标题"></el-input>
      </el-form-item>
      <el-form-item label="广告地址" prop="url">
        <el-input v-model="form.url" placeholder="请输入广告地址"></el-input>
      </el-form-item>
      <el-form-item label="图片路径">
        <el-input v-model="form.img_path" placeholder="请输入图片路径"></el-input>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="广告位置" prop="position">
            <el-select placeholder="请选择" v-model="form.position">
              <el-option
                :key="item.value"
                :label="item.label"
                :value="item.value"
                v-for="item in position_options">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="广告状态" prop="adv_status">
            <el-select placeholder="请选择" v-model="form.adv_status">
              <el-option
                :key="item.value"
                :label="item.label"
                :value="item.value"
                v-for="item in adv_status_options">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改保存</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
    import {edit, get} from "@/api/advertisement";
    import {service_type,adv_status} from '@/utils/dict'
    export default {
        name: 'EditAdvertisement',
        props: {
            editVisible: false,
            id: ''
        },
        data() {
            return {
                position_options:service_type,
                adv_status_options:adv_status,
                form: {
                    title:'',
                    url:'',
                    img_path:'',
                    position:'',
                    adv_status:'',
                    id: this.id
                },
                rules: {
                    title: [
                        {required: true, message: '请输入广告标题', trigger: 'blur'}
                    ],
                    url: [
                        {required: true, message: '请输入广告地址', trigger: 'blur'}
                    ],
                    position: [
                        {required: true, message: '请选择广告位置', trigger: 'blur'}
                    ],
                    adv_status: [
                        {required: true, message: '请选择广告状态', trigger: 'blur'}
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
