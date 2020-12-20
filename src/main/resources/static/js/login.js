new Vue({
    el: '#app',
    data: function() {
        return {
            username: "",
            password: "",
            loading: false,
        }
    },
    methods:{
        async login () {
            var token = $("meta[name='_csrf']").attr("content");
            /* if(this.username !='' && this.password != ''){
                 this.$message.success("Login successfull");
             }*/
            /* this.$http.post("/login",{username:this.username,password:this.password},{emulateJSON:true}).then(function (res){
                 console.log("成功")
             },function (res){
                 console.log("失败")
             });*/
         /*   window.csrfToken = document.querySelector('meta[name="csrf-token"]').content;
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
             var csrfTokens = csrfToken;*/

           /* axios.post('/login', {
                '_csrf':token,
                username:this.username,
                password:this.password
            }).then(function (response) {
                console.log(response);
            })
                .catch(function (error) {
                    console.log(error);
                });*/

           /* this.$http.post("/login",{'_csrf':token,username:this.username,password:this.password},{emulateJSON:true}).then(function (res){
                console.log("成功")
            },function (res){
                console.log("失败")
            });*/
            $.ajax({type:'POST',url:"/login",async:false,data:{'_csrf':token,username:this.username,password:this.password},
                success:function(result){
                    window.location.href = "/user/index";

                }});

        }
    }
})