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
        this.find();
    },
    methods:{
       find:function (){
          console.log("dfdfdfdfdfdfd");
       }
    }
})