<template>
  <el-dialog
    title="新增"
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
        <el-upload
          action="http://139.186.30.39/microservice/attachment-api/app/file/attachment/upload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :limit="1"
          :on-exceed="handleExceed"
          :file-list="fileList">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="广告位置" prop="position">
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
        <el-col :span="12">
          <el-form-item label="广告状态" prop="adv_status">
            <el-select v-model="form.adv_status" placeholder="请选择广告状态">
              <el-option label="启用" value="1"></el-option>
              <el-option label="下线" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
    import {add} from '@/api/advertisement'
    import {service_type,adv_status} from '@/utils/dict'
    export default {
        name: 'AddAdvertisement',
        props: {
            addVisible: false
        },
        data() {
            return {
                position_options:service_type,
                adv_status_options:adv_status,
                form: {
                    title:'',
                    url: '',
                    img_path: '',
                    position: '',
                    adv_status: '',
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
                show: this.addVisible,
            }
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            },
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
