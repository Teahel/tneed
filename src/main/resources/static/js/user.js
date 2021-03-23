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
            fullscreenLoading: false,
            addServerDialogVisible:false, //新增服务信息显示控制dialog
            server:{
                serverName:'',
                location:'',
                serverLink:'',
                remark:'',
                image:''
            },
            addRechargeDialogVisible:false,
            disabledAlreadyPay:false,
            imageItems:[
              "http://182.254.140.133:9088/pay_one.jpg",
                "http://182.254.140.133:9088/pay_three.jpg"
            ]
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
           var email = null;

           var authentication = $('#authentication');
           if(authentication == undefined || authentication == ""){
               return;
           }
           var token = $("meta[name='_csrf']").attr("content");
           this.token = token;
           this.username = authentication[0].innerText;
           var tableData = this.tableData;
           let self = this;
           if(!roles.includes("ROLE_ADMIN")){
               addServerButtonVisible = false;
               email = this.username;
           }

           /**
            * 查询用户信息
            */
           axios.post('/tneed/user/select', {
               username:email
           },{headers: {
                   'X-XSRF-TOKEN': token
               }
           }).then(function (response) {
               if(200 == response.status){
                   if(response.data.code == 0){
                       self.tableData= response.data.data;
                   }
               }

           }).catch(function (error) {
               console.log(error);
           });

           /**
            * 查询服务信息
            */
           axios.post('/tneed/serverInfo/select', {
               username:email
           },{headers: {
                   'X-XSRF-TOKEN': token
               }
           }).then(function (response) {
               if(200 == response.status){
                   if(response.data.code == 0){
                       self.server= response.data.data;
                   }
               }
           }).catch(function (error) {
               console.log(error);
           });

       },
        tableRowClassName:function ({row, rowIndex}) {
            if (rowIndex === 1) {
                return 'warning-row';
            } else if (rowIndex === 3) {
                return 'success-row';
            }
            return '';
        },
        /**
         * 修改密码
         * @param data
         */
       updatePassword:function (data) {
          this.dialogUserVisible = false;
          let self = this;
          axios.post('/tneed/user/update', {
                username:this.username,
                password:this.ruleForm.checkPass,
                oldPassword:this.ruleForm.oldPassword
          },{headers: {
                   'X-XSRF-TOKEN': this.token
               }
          }).then(function (response) {
               if(200 == response.status){
                   axios.post('/tneed/logout', {headers: {
                           'X-XSRF-TOKEN': this.token
                       }
                   }).then(function (response) {
                       if(200 == response.status){
                           self.fullscreenLoading = true;
                           setTimeout(() => {
                               self.fullscreenLoading = false;
                               self.$message({
                                   duration:3000,
                                   message: '恭喜你，这是一条成功消息',
                                   type: 'success'
                               });
                              window.location = "/tneed/login"
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
        addServerInfoDialog:function (){
            this.addServerDialogVisible = true;
        },
        addRechargeDialog:function(){
            this.addRechargeDialogVisible = true;
        },
        resetServerInfoForm:function () {
            this.server='';
        },
        submitServerSubmit:function (data) {
            let self = this;
            var imagefile = document.querySelector('#file');
            this.server.image = imagefile.files[0];
            var data = new FormData();
            data.append("username",this.username);
            data.append("serverName",this.server.serverName);
            data.append("location",this.server.location);
            data.append("serverLink",this.server.serverLink);
            data.append("image",this.server.image);
            data.append("_csrf",this.token);
            $.ajax({
                url: '/tneed/serverInfo/save',
                type: 'POST',
                data,
                cache: false, //设置是否第二次是否从缓存中读取
                processData: false,//将数据类型序列换成application/x-www-form-urlencoded ，fasle表示关闭
                contentType: false,//默认值为application/x-www-form-urlencoded，false表示关闭
                success:function(data){
                    if(data.code==0){
                        self.addServerDialogVisible = false;
                        self.$message({
                            message: '操作成功',
                            type: 'success'
                        });
                        self.resetServerInfoForm()
                    } else {
                        self.addServerDialogVisible = false;
                        self.$message.error('操作失败');
                        self.resetServerInfoForm()
                    }
                }
            });
        },
        alreadyPay:function () {
          // this.disabledAlreadyPay = true;
            let self = this;
            $.post("/tneed/pay",
                {
                    username:this.username,
                    _csrf:this.token
                },
                function(data,message){
                    if(data.code==0){
                       console.log(data)
                    } else {
                       console.log(data)
                    }
                });
        }
    }
})