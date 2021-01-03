new Vue({
    el: '#user',
    data: function() {
        return {
            tableData: [{
                username: '',
                email: '',
                effectiveTime: ''
            }]
        }
    },
    created:function(){
        this.findUser();
    },
    methods:{
       findUser:function (){
           var authentication = $('#authentication');
           if(authentication == undefined || authentication == ""){
               return;
           }
           var token = $("meta[name='_csrf']").attr("content");
           var tableData = this.tableData;
           let self = this;
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

                       /*
                                              response.data.data.username
                       */
                   }
               }
               console.log(response);
               console.log(tableData);

           })
               .catch(function (error) {
                   console.log(error);
               });
       }
    }
})