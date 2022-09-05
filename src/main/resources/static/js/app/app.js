new Vue({
    el:'#main-container',
    data:{
        pageInfo:{
            current:1,
            size:5
        },
        app:{
          platform:0,
          forceUpdate:0
        },
        active:false
    },
    methods:{
        select:function (pageNum,pageSize) {
        axios({
           url:'/manager/app/select',
           params:{
               current:pageNum,
               size:pageSize
           }
        }).then(response=>{
            //箭头函数会把上下文的vue的this对象传递过来
            this.pageInfo=response.data.data;
        }).catch(function (error) {
            console.log(error);
        });
        },
        save:function () {
            axios({
                url: '/manager/app/saveOrUpdate',
                method:'post',
                data: this.app
            }).then(response=>{
                if(response.data.code==200){
                    this.select(this.pageInfo.current,this.pageInfo.size);
                    this.app={
                        platform: 0,
                        forceUpdate: 0
                    }
                }
                //弹提示消息窗
                layer.msg(response.data.msg);
            });
        },
        toUpdate:function (app_id) {
            axios({
                url:'/manager/app/selectOne',
                params: {id:app_id}
            }).then(response=>{
                if (response.data.code!==200){
                    layer.msg(response.data.msg);
                    return;
                }
                layer.appVersion = response.data.data;
                console.log(layer);
                //打开layer的所在的页面
                let index = layer.open({
                    type:2, //类型：0信息框  1 页面层  2 iframe层  3 加载层  4 tips层
                    title:'更新app',
                    content:'/manager/app/app-update.html',
                    area:['60%','80%'],
                    end:()=>{
                        this.select(this.pageInfo.current,this.pageInfo.size);
                    }
                });
            })
        },
        /*doDelete:function (appid) {
            layer.msg('是否删除？',{
                time:0, //无自动消失计时
                btn:['是','否'],
                yes:index=>{
                    axios({
                        url:'/manager/app/delete',
                        params:{id:appid}
                    }).then(response=>{
                        layer.close(index); //关闭当前msg窗口
                        layer.msg(response.data.msg);
                        //成功后刷新数据列表
                        if (response.data.code == 200){
                            this.select(this.pageInfo.current,this.pageInfo.size);
                        }
                    });
                }
            });
        }*/
        doDelete:function (appid) {
            layer.msg('是否删除？',{
                time:0,  //无自动消失计时
                btn:['是','否'],
                yes:index=>{  // index是当前消息框的索引
                    axios({
                        url:'/manager/app/delete',
                        params:{
                            id:appid
                        }
                    }).then(response=>{
                        layer.close(index);  //关闭当前msg窗口
                        layer.msg(response.data.msg);
                        //成功后刷新数据列表
                        if(response.data.code==200){
                            this.select(this.pageInfo.current,this.pageInfo.size);
                        }
                    });
                }
            });
        }
    },

    created:function () {
    this.select(this.pageInfo.current,this.pageInfo.size)
}
})
/*new Vue({
    el:'#main-container',
    data:{
        pageInfo:{
            current:1,
            size:5
        },
        app:{
            platform:0,
            forceUpdate:0
        },
        active:false
    },
    methods:{
        select:function (pageNum,pageSize) {
            axios({
                url:'/manager/app/select',
                params:{
                    current:pageNum,
                    size:pageSize
                }
            }).then(response=>{
                //console.log(response);
                //箭头函数会将上下文中的this(vue对象)传递过来
                this.pageInfo = response.data.data;  // response.data-->ResultBean<Page>  response.data.data-->Page对象
            }).catch(function (error) {
                console.log(error);
            });
        },
        save:function () {
            axios({
                url:'/manager/app/saveOrUpdate',
                method:'post',
                data:this.app
            }).then(response=>{
                if(response.data.code==200){
                    this.active=false;  //切换到列表的div
                    //更新数据列表
                    this.select(this.pageInfo.current,this.pageInfo.size);
                    this.app={  // 清空模型对象的属性值
                        platform:0,
                        forceUpdate:0   //设置原来的默认值
                    }
                }
                layer.msg(response.data.msg);  //弹出提示框
            })
        },
        toUpdate:function (app_id) {
            axios({
                url:'/manager/app/selectOne',
                params:{id:app_id}
            }).then(response=>{
                if (response.data.code!=200){
                    layer.msg(response.data.msg);
                    return;
                }
                layer.appVersion = response.data.data;
                console.log(layer);
                //打开layer的所在的页面
                let index = layer.open({
                    type:2, //类型：0信息框  1 页面层  2 iframe层  3 加载层  4 tips层
                    title:'更新app',
                    content:'/manager/app/app-update.html',
                    area:['60%','80%'],
                    end:()=>{
                        this.select(this.pageInfo.current,this.pageInfo.size);
                    }
                });
            });
        },
        doDelete:function (appid) {
            layer.msg('是否删除？',{
                time:0,  //无自动消失计时
                btn:['是','否'],
                yes:index=>{  // index是当前消息框的索引
                    axios({
                        url:'/manager/app/delete',
                        params:{
                            id:appid
                        }
                    }).then(response=>{
                        layer.close(index);  //关闭当前msg窗口
                        layer.msg(response.data.msg);
                        //成功后刷新数据列表
                        if(response.data.code==200){
                            this.select(this.pageInfo.current,this.pageInfo.size);
                        }
                    });
                }
            });
        }
    },
    created:function () {
        this.select(this.pageInfo.current,this.pageInfo.size);
    }
})*/

