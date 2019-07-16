<template>
  <el-dialog
    title="新增角色"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="30%">

    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="form.roleName"></el-input>
      </el-form-item>
      <el-form-item label="角色权限" prop="roleKey">
        <el-input v-model="form.roleKey"></el-input>
      </el-form-item>
      <el-form-item label="角色排序" prop="roleSort">
        <el-input v-model="form.roleSort"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">新增</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
  import {insertRole} from '@/api/system/role'

  export default {
    name: 'AddRole',
    props: {
      addVisible: false
    },
    data() {
      return {
        form: {
          roleName: '',
          roleKey: '',
          roleSort: '',
        },
        show: this.addVisible,
        rules: {
          roleName: [
            {required: true, message: '请输入角色名称', trigger: 'blur'},
            {max: 10, message: '长度在 10 个字符以内', trigger: 'blur'}
          ],
          roleKey: [
            {required: true, message: '请输入角色权限', trigger: 'blur'},
            {max: 20, message: '长度在 20 个字符以内', trigger: 'blur'}
          ],
          roleSort: [
            {required: true, message: '请输入角色排序', trigger: 'blur'}
          ]
        },
      }
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            insertRole(this.form).then(() => {
              this.$alert("新增角色成功", '系统提示', {
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
        //调用父组件方法
        this.$emit('callback');
      }
    }
  }
</script>
