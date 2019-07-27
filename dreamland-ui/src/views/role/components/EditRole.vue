<template>
  <el-dialog
    title="新增角色"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="30%">

    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="角色名称" prop="role_name">
        <el-input v-model="form.role_name"></el-input>
      </el-form-item>
      <el-form-item label="角色权限" prop="role_key">
        <el-input v-model="form.role_key"></el-input>
      </el-form-item>
      <el-form-item label="角色排序" prop="role_sort">
        <el-input v-model="form.role_sort"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
  import {saveRole, getRole} from '@/api/role'

  export default {
    name: 'EditRole',
    props: {
      editVisible: false,
      roleId: ''
    },
    data() {
      return {
        form: {
          role_id: this.roleId,
          role_name: '',
          role_key: '',
          role_sort: ''
        },
        show: this.editVisible,
        rules: {
          role_name: [
            {required: true, message: '请输入角色名称', trigger: 'blur'},
            {max: 10, message: '长度在 10 个字符以内', trigger: 'blur'}
          ],
          role_key: [
            {required: true, message: '请输入角色权限', trigger: 'blur'},
            {max: 20, message: '长度在 20 个字符以内', trigger: 'blur'}
          ],
          role_sort: [
            {required: true, message: '请输入角色排序', trigger: 'blur'}
          ]
        }
      }
    },
    created() {
      this.getRole()
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            saveRole(this.form).then(() => {
              this.$alert("修改成功", '系统提示', {
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
        this.$emit('update:editVisible', false);
        //调用父组件方法
        this.$emit('callback');
      },
      getRole() {
        getRole(this.roleId).then(response => {
          const {data} = response;
          this.form.role_name = data.role_name;
          this.form.role_key = data.role_key;
          this.form.role_sort = data.role_sort;
        })
      }
    }
  }
</script>
