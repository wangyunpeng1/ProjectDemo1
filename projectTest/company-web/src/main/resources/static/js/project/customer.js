layui.use(['layer', 'table','upload', 'element'], function(){
    var layer = layui.layer //弹层
        ,table = layui.table //表格
        ,upload = layui.upload //上传
        ,element = layui.element //元素操作

    //监听Tab切换
    element.on('tab(demo)', function(data){
        layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    table.render({
        elem: '#demo'
        ,height: 420
        ,url: 'customer/query' //数据接口
        ,title: '客户表'
        ,page: true //开启分页
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,cols: [[ //表头
            ,{field: 'customerId', title: '客户id', width:130, align:'center'}
            ,{field: 'customerName', title: '客户名', width: 200, align:'center'}
            ,{field: 'age', title: '年龄', width:1, align:'center'}
            ,{field: 'sex', title: '性别', width: 130, align:'center'}
            ,{field: 'phone', title: '手机号', width:130, align:'center'}
            ,{field: 'createTime', title: '加入时间', width: 260, align:'center'}
            ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.msg('添加');
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            layer.msg('编辑操作');
        }
    });

    //上传
    upload.render({
        elem: '#uploadDemo'
        ,url: 'https://httpbin.org/post' //改成您自己的上传接口
        ,done: function(res){
            layer.msg('上传成功');
            layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.files.file);
            console.log(res)
        }
    });
});