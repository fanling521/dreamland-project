<template>
  <el-dialog
    title="新增用户"
    :visible.sync="show" @close="resetForm('form')" :modal="true" :close-on-click-modal="false"
    :close-on-press-escape="true"
    width="30%">

    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="登录名" prop="loginName">
        <el-input v-model="form.loginName"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="form.userName"></el-input>
      </el-form-item>
      <el-form-item label="手机" prop="phone">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="角色" prop="roleIds">
        <el-checkbox-group v-model="form.roleIds">
          <el-checkbox v-for="(item,index) in roleGroup"
                       :label="item.roleId"
                       :key="index"
                       :checked="item.flag"
                       name="roleIds">{{item.roleName}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('form')">修改</el-button>
        <el-button @click="resetForm('form')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
  import {selectRoles} from '@/api/system/role'
  import {saveUser, getUser} from '@/api/system/user'

  export default {
    name: 'EditUser',
    props: {
      editVisible: false,
      userId: ''
    },
    data() {
      return {
        form: {
          userId: this.userId,
          loginName: '',
          userName: '',
          phone: '',
          roleIds: [],
        },
        show: this.editVisible,
        rules: {
          loginName: [
            {required: true, message: '请输入登录名', trigger: 'blur'},
            {max: 10, message: '长度在 10 个字符以内', trigger: 'blur'}
          ],
          userName: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {max: 20, message: '长度在 20 个字符以内', trigger: 'blur'}
          ],
          phone: [
            {required: true, message: '请输入手机', trigger: 'blur'},
            {
              pattern: /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/,
              message: '手机号格式不对',
              trigger: 'blur'
            }
          ],
          roleIds: [
            {required: true, message: '请选择角色', trigger: 'change'}
          ]
        },
        roleGroup: []
      }
    },
    created() {
      this.getUser()
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            saveUser(this.form).then(() => {
              this.$alert("修改用户成功", '系统提示', {
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
      initRoleGroup() {
        selectRoles(this.userId).then(r => {
          const {data} = r;
          this.roleGroup = data;
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.$emit('update:editVisible', false);
        //调用父组件方法
        this.$emit('callback');
      },
      getUser() {
        getUser(this.userId).then(r => {
          const {data} = r;
          this.form.loginName = data.loginName;
          this.form.userName = data.userName;
          this.form.phone = data.phone;
          this.initRoleGroup()
        })
      }
    }
  }
</script>
