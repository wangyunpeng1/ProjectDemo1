layui.use(['layer', 'table', 'upload', 'element'], function () {
    var layer = layui.layer //弹层
        , table = layui.table //表格
        , upload = layui.upload //上传
        , element = layui.element //元素操作
        , form = layui.form;

    //监听Tab切换
    element.on('tab(demo)', function (data) {
        layer.tips('切换了 ' + data.index + '：' + this.innerHTML, this, {
            tips: 1
        });
    });

    //执行一个 table 实例
    table.render({
        elem: '#demo'
        , height: 420
        , url: 'company/query' //数据接口
        , title: '员工表'
        , page: true //开启分页
        , toolbar: '#toolbarDemo'
        , cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'staffName', title: '员工名', width: 130, align: 'center'}
            , {field: 'account', title: '账号', width: 200, align: 'center'}
            , {field: 'updatePower', title: '修改权限', templet: '#switchTpl1', width: 130, align: 'center'}
            , {field: 'insertPower', title: '增加权限', templet: '#switchTpl2', width: 130, align: 'center'}
            , {field: 'deletePower', title: '删除权限', templet: '#switchTpl3', width: 130, align: 'center'}
            , {field: 'createTime', title: '加入时间', width: 260, align: 'center'}
            , {fixed: 'right', width: 165, align: 'center', toolbar: '#barDemo'}
        ]]
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        switch (obj.event) {
            case 'addStaff':
                layer.open({
                    type: 1,
                    title: '员工注册',
                    area: ['350px', '350px'],
                    content: ' <div class="layui-form-item">' +
                        '<br><br><br>' +
                        '<div class="layui-inline">' +
                        '                    <label class="layui-form-label">员工名</label>\n' +
                        '<div class="layui-input-inline">' +
                        '                    <input type="text" id="staffName" name="staffName" lay-verify="staffName" placeholder="员工名" autocomplete="off" class="layui-input" >\n' +
                        '</div>' +
                        '</div>' +
                        '<br><br>' +
                        '<div class="layui-inline">' +
                        '                    <label class="layui-form-label">账号</label>\n' +
                        '<div class="layui-input-inline">' +
                        '                    <input type="text" id="staffAccount" name="account" lay-verify="account" placeholder="账号" autocomplete="off" class="layui-input" >\n' +
                        '</div>' +
                        '</div>' +
                        '<br><br>' +
                        '<div class="layui-inline">' +
                        '                    <label class="layui-form-label">密码</label>\n' +
                        '<div class="layui-input-inline">' +
                        '                    <input type="password" id="staffPassword" name="password" lay-verify="password" placeholder="密码" autocomplete="off" class="layui-input" >\n' +
                        '</div>' +
                        '</div>' +
                        '</div>',
                    btn: ['注册'],
                    yes:function () {
                        var staffName = $("#staffName").val();
                        var account = $("#staffAccount").val();
                        var password = $("#staffPassword").val();
                        if (staffName === '') {
                            layer.msg('员工名字不能为空');
                            return false;
                        }
                        if (account === '') {
                            layer.msg('账号不能为空');
                            return false;
                        }
                        if (password === '') {
                            layer.msg('密码不能为空');
                            return false;
                        }
                        $.ajax({
                            url:"register/registerStaff",
                            data:"{\"account\":\"" + account + "\",\"password\":\"" + password + "\",\"staffName\":\"" + staffName + "\"}",
                            type:"Post",
                            contentType:"application/json",
                            dataType:"json",
                            success:function (result) {
                                if (result.data === true){
                                    window.location = "staff";
                                }else {
                                    layer.msg(result.message);
                                    return false;
                                }
                            },
                            error:function(data){
                                layer.msg('出错误啦');
                            }
                        })
                    }
                });
                break;
            case 'importStaff':
                layer.msg('导入员工');
                break;
            case 'downLoadTemplate':
                layer.msg('模板下载');
                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'detail') {
            console.log(data)
            layer.msg('员工姓名:'+ data.staffName);
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url:"company/deleteStaff",
                        data:"{\"account\":\"" + data.account + "\",\"staffId\":\"" + data.staffId + "\"}",
                        type:"Post",
                        contentType:"application/json",
                        dataType:"json",
                        success:function (result) {
                            if (result.data === true){
                                window.location = "staff";
                            }else {
                                layer.msg(result.message);
                                return false;
                            }
                        },
                        error:function(data){
                            layer.msg('出错误啦');
                        }
                    })
                });
        }
        // else if (layEvent === 'edit') {
        //
        //     layer.msg('编辑操作');
        // }
    });

    //上传
    upload.render({
        elem: '#uploadDemo'
        , url: 'https://httpbin.org/post' //改成您自己的上传接口
        , done: function (res) {
            layer.msg('上传成功');
            layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.files.file);
            console.log(res)
        }
    });

    // $('#updatePower').click(function (obj) {
    //     layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    // });
    // $('#insertPower').click(function (obj) {
    //     layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    // });
    // $('#deletePower').click(function (obj) {
    //     layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    // });
    form.on('switch(updatePower)', function(obj){
        var staffInfo = this.value;
        var data = staffInfo.split(",");
        $.ajax({
            url:"company/updatePower",
            data:"{\"staffId\":\"" + data[0] + "\",\"updatePower\":\"" + data[1] + "\"}",
            type:"Post",
            contentType:"application/json",
            dataType:"json",
            success:function (result) {
                if (result.data === true){
                    window.location = "staff";
                }else {
                    layer.msg(result.message);
                    return false;
                }
            },
            error:function(data){
                layer.msg('出错误啦');
            }
        })

    });
    form.on('switch(insertPower)', function(obj){
        var staffInfo = this.value;
        var data = staffInfo.split(",");
        $.ajax({
            url:"company/insertPower",
            data:"{\"staffId\":\"" + data[0] + "\",\"insertPower\":\"" + data[1] + "\"}",
            type:"Post",
            contentType:"application/json",
            dataType:"json",
            success:function (result) {
                if (result.data === true){
                    window.location = "staff";
                }else {
                    layer.msg(result.message);
                    return false;
                }
            },
            error:function(data){
                layer.msg('出错误啦');
            }
        })
    });
    form.on('switch(deletePower)', function(obj){
        var staffInfo = this.value;
        var data = staffInfo.split(",");
        $.ajax({
            url:"company/deletePower",
            data:"{\"staffId\":\"" + data[0] + "\",\"deletePower\":\"" + data[1] + "\"}",
            type:"Post",
            contentType:"application/json",
            dataType:"json",
            success:function (result) {
                if (result.data === true){
                    window.location = "staff";
                }else {
                    layer.msg(result.message);
                    return false;
                }
            },
            error:function(data){
                layer.msg('出错误啦');
            }
        })
    });

});