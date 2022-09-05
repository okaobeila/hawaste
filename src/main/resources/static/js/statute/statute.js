let sm = new Vue({
    el: "#main-container",
    data:{
        pageInfo:{
            current:1,
            size:5
        },
        type:'',
        statute:'',
        ueditorConfig:{//自定义VueUeditorWrap配置项
            // ueditor.config.js 的UEDITOR_HOME_URL: "/static/ueditor/" ->
            // 覆盖为ueUeditorWrap的  UEDITOR_HOME_URL:"/static/UEditor/" (默认配置)   ->
            //覆盖为 ueditorConfig 的  UEDITOR_HOME_URL:"/ueditor/"          （自定义配置）
            UEDITOR_HOME_URL:"/ueditor/",  //前端资源默认读取路径  注意结束/不能省  是拼接
            // 服务器统一请求接口（服务器接口测试用，后修改为下面我们自己的服务器接口地址）
            // serverUrl:'http://35.201.165.105:8000/controller.php'
            serverUrl:'/ueditor/exec',
            maximumWords:500000   /*设置支持最大编辑富文本字符个数*/
        }
    },
    methods:{
        select:function (pageNum,pageSize) {
            axios({
                url: `/manager/statute/select/${pageNum}/${pageSize}`,
                params: {
                    type:this.type
                }
            }).then(response => {
                //箭头函数会把上下文的vue的this对象传递过来
                this.pageInfo = response.data.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        doDelete:function (statute_id) {
            layer.msg('是否删除',{
                time:0,
                btn:['是','否'],
                yes:index=>{
                    axios({
                        url:'/manager/statute/doDelete',
                        params:{
                            id:statute_id
                        }
                    }).then(response=>{
                        layer.close(index);  //关闭当前msg窗口
                        //layer.msg(response.data.msg);
                        //成功后刷新数据列表
                        if(response.data.code==200){
                            this.select(this.pageInfo.current,this.pageInfo.size);
                        }
                    })
                }
            })
        },
        save:function () {
            axios({
                url:'/manager/statute/saveOrUpdate',
                method:'post',
                data:this.statute
            }).then(response=>{
                if(response.data.code==200){
                    //更新表格
                    this.select(this.pageInfo.current, this.pageInfo.size);
                    this.statute={}
                }
                //弹提示消息框
                layer.msg(response.data.msg)
            })
        },
        toUpdate:function (statuteid) {
            axios({
                url:'/manager/statute/selectOne',
                params:{id:statuteid},

            }).then(response=>{
                if (response.data.code==500) {
                    layer.msg(response.data.msg)
                    return;
                }
                //返回数据绑定到当前父窗口layer的obj上，传递给子窗口
                //对一些实时性要求不高的数据更新，可以不查询数据，直接方法传参对象传递到layer子窗口
                 layer.obj = response.data.data;
                let index = layer.open({ //打开窗口
                    type:2,
                    title:'更新statute',
                    content:'/manager/statute/statute-update.html', //加载页面
                    area:['60%','80%'],
                    end:() =>{
                        this.select(this.pageInfo.current,this.pageInfo.size);
                    }
                });
            })
        }

    },

    created:function () {
        this.select(1,this.pageInfo.size)
    },/*vue组件属性，用于引入一些已经封装好的vue组件对象*/
    components:{
        VueUeditorWrap
    }

})

