new Vue({
    el: '#register',
    data: function() {
        return {
            user:{
                username:'',
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
        register:function () {
            $.post("/tneed/userinfo/resetPassword",
                {
                    username:this.user.username,
                    inviteCode:this.inviteCode,
                    _csrf:this.token
                },
                function(data,message){
                    if(data.code==0){
                        self.dialogVisible = false
                        self.$message({
                            duration:2000,
                            message: '修改成功，即将跳转界面',
                            type: 'success'
                        });
                        setTimeout(() => {
                            window.location = "/tneed/login"
                        }, 1000);

                    } else {
                        self.$message({
                            showClose: true,
                            message: data.message,
                            type: 'warning'
                        });
                    }
                });
        }

    }
})