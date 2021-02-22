new Vue({
    el: '#user',
    data: function() {
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
            },
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
            },
            addServerButtonVisible:true

        }
    },
    created:function(){
        this.findUser();
    },
    methods:{
       findUser:function (){
           var roles = $("#role")[0].innerText
           if(!roles.includes("ROLE_ADMIN")){
               addServerButtonVisible = false;
           }
           var authentication = $('#authentication');
           if(authentication == undefined || authentication == ""){
               return;
           }
           var token = $("meta[name='_csrf']").attr("content");
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
       updateUser:function () {

       },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                done();
        })
        .catch(_ => {});
        },
        submitForm:function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
            return false;
        }
        });
        },
        resetForm:function(formName) {
            this.$refs[formName].resetFields();
        }
       
    }
})