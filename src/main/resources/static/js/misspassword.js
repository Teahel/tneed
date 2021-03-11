new Vue({
    el: '#reset',
    data: function() {
        return {
            email:'',
            token:'',
            username:'',
            dialogVisible:true
        }
    },
    methods:{

        resetPassword:function () {
            /**
             * 获取角色
             * @type {string}
             */

            var token = $("meta[name='_csrf']").attr("content");
            this.token = token;
            self = this;
            /**
             * 查询用户信息
             */

            $.post("/tneed/userinfo/resetPassword",
                {
                    email:this.email,
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

        },
        goback:function (){
            window.location = "/tneed/login"
        }
   }
})