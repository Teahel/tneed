new Vue({
    el: '#register',
    data: function() {
        return {
            dynamicValidateForm:{
                email:'',
            },
            visibleDialog:true,
            inviteCodeError:'',
            inviteCode:'',
            disabledRegister:false,
            showClose:false,
            token:'',
        }
    },
    created:function () {
        this.token = $("meta[name='_csrf']").attr("content");
        this.inviteCodeError = $("#inviteCodeError").val();
        this.inviteCode = $("#inviteCode").val();
        this.checkInviteCode();
    },
    methods:{
       checkInviteCode:function (){
         if( this.inviteCodeError == "false"){
               this.disabledRegister = true;
               $("#username").hide();

           }
       },
        register:function (formName) {
            self.disabledRegister = true;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    self = this
                    axios.post('/tneed/happy', {
                        username:this.dynamicValidateForm.email,
                        inviteCode:this.inviteCode,
                    },{headers: {
                            'X-XSRF-TOKEN': this.token
                        }
                    }).then(function (response) {
                        if(response.data.code==0){
                            self.$message({
                                duration:6000,
                                message: '注册成功，Tneed 学习码已经发送到邮箱，即将跳转登录界面!',
                                type: 'success'
                            });
                            setTimeout(() => {
                                window.location = "/tneed/login"
                            }, 3000);

                        } else {
                            self.$message({
                                showClose: true,
                                message: response.data.message,
                                type: 'warning'
                            });
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });

                } else {
                    this.$message.error('请正确输入邮箱信息!');
                    return false;
                }
            });


        }

    }
})