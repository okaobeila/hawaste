let  em=new Vue({
    el:'#main-container',
    data:{
        appVersion:''

    },
    methods:{
        update:function () {
            axios({
                url: "/manager/app/saveOrUpdate",
                method:"POST",
                data:this.appVersion
            }).then(response=>{
                parent.layer.msg("编辑成功"); //通过父窗口layer对象弹框
                //parent.layer.msg('编辑成功');
                let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //通过父窗口layer对象执行关闭，关闭当前子窗口

            }).catch(function (error) {

                layer.msg(error);
            })
        }
    },
    created:function () {
        //在vue对象创建后  获取layer父窗口传递的对象数据
        //this.appVersion=parent.layer.appVersion;
        this.appVersion=parent.layer.appVersion;
    }
})
/*
let  em=new Vue({
    el:'#main-container',
    data:{
        appVersion:''

    },
    methods:{

        update:function () {
            axios({
                url: "/manager/app/saveOrUpdate",
                method:"POST",
                data:this.appVersion
            }).then(response=>{
                parent.layer.msg("编辑成功"); //通过父窗口layer对象弹框
                //parent.layer.msg('编辑成功');
                let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                parent.layer.close(index); //通过父窗口layer对象执行关闭，关闭当前子窗口

            }).catch(function (error) {

                layer.msg(error);
            })
        }


    },
    created:function () {  //vue生命周期：Vue的对象已经创建，data已经初始化
        this.appVersion=parent.layer.appVersion;
    }
});*/
