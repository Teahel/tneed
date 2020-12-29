new Vue({
    el: '#user',
    data: function() {
        return {
            tableData: [{
                username: '2016-05-02',
                email: '王小虎',
                effectiveTime: '上海市普陀区金沙江路 1518 弄'
            }]
        }
    },
    created:function(){
        this.findUser();
    },
    methods:{
       findUser:function (){
           var token = $("meta[name='_csrf']").attr("content");
           var authentication = $('#authentication');
           if(authentication == undefined || authentication == ""){
               return;
           }
           axios.post('/userinfo/select', {
               '_csrf':token,
               username:authentication[0].innerText,
           }).then(function (response) {
               console.log(response);
           })
               .catch(function (error) {
                   console.log(error);
               });
       }
    }
})