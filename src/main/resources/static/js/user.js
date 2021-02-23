new Vue({
    el: '#user',
    data: function() {
        var validPassOldPassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.oldPassword !== '') {
                    this.$refs.ruleForm.validateField('passOldPassword');
                }
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            tableData: [{
                username: '',
                email: '',
                effectiveTime: ''
            }],
            dialogUserVisible: false,
            ruleForm: {
                pass: '',
                checkPass: '',
                oldPassword:''
            },
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                oldPassword:[
                    { validator: validPassOldPassword, trigger: 'blur' }
                ]
            },
            addServerButtonVisible:true,
            dialogButton:false,
            token:"",
            username:"",
            fullscreenLoading: false
        }
    },
    created:function(){
        this.findUser();
    },
    methods:{
       findUser:function (){
           /**
            * 获取角色
            * @type {string}
            */
           var roles = $("#role")[0].innerText
           if(!roles.includes("ROLE_ADMIN")){
               addServerButtonVisible = false;
           }
           var authentication = $('#authentication');
           if(authentication == undefined || authentication == ""){
               return;
           }
           var token = $("meta[name='_csrf']").attr("content");
           this.token = token;
           this.username = authentication[0].innerText;
           var tableData = this.tableData;
           let self = this;

           /**
            * 查询用户信息
            */
           axios.post('/user/select', {
               username:authentication[0].innerText,
           },{headers: {
                   'X-XSRF-TOKEN': token
               }
           }).then(function (response) {
               if(200 == response.status){
                   if(response.data.code == 0){
                       self.tableData[0].username = response.data.data.username;
                       self.tableData[0].effectiveTime = response.data.data.effectiveTime;
                   }
               }

           }).catch(function (error) {
               console.log(error);
           });

           /**
            * 查询服务信息
            */
           axios.post('/server/v2/select', {
              // username:authentication[0].innerText,
           },{headers: {
                   'X-XSRF-TOKEN': token
               }
           }).then(function (response) {
               if(200 == response.status){
                   console.log(response);
               }

           }).catch(function (error) {
               console.log(error);
           });

       },
        /**
         * 修改密码
         * @param data
         */
       updatePassword:function (data) {
          this.dialogUserVisible = false;
          let self = this;
          axios.post('/user/update', {
                username:this.username,
                password:this.ruleForm.checkPass,
                oldPassword:this.ruleForm.oldPassword
          },{headers: {
                   'X-XSRF-TOKEN': this.token
               }
          }).then(function (response) {
               if(200 == response.status){
                   axios.post('/logout', {headers: {
                           'X-XSRF-TOKEN': this.token
                       }
                   }).then(function (response) {
                       if(200 == response.status){
                           self.fullscreenLoading = true;
                           self.$message({
                               duration:3000,
                               message: '恭喜你，这是一条成功消息',
                               type: 'success'
                           });
                           setTimeout(() => {
                               self.fullscreenLoading = false;
                              window.location = "/login"
                       }, 3000);


                       }
                   }).catch(function (error) {
                       console.log(error);
                   });
               }

          }).catch(function (error) {
               console.log(error);
          });
          this.resetForm(data);

       },
        resetForm:function(formName) {
            this.$refs[formName].resetFields();
        },

       
    }
})