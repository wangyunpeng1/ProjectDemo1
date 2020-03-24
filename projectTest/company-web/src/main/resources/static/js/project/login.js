layui.use(['form'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    // 登录过期的时候，跳出ifram框架
    if (top.location != self.location) top.location = self.location;

    // 粒子线条背景
    $(document).ready(function(){
        $('.layui-container').particleground({
            dotColor:'#5cbdaa',
            lineColor:'#5cbdaa'
        });
    });

    // 进行登录操作
    $('#login').click(function(){
        if ($("#account").val() === '') {
            layer.msg('账号不能为空');
            return false;
        }

        if ($("#password").val() === '') {
            layer.msg('密码不能为空');
            return false;
        }

        $.ajax({
            url:"login/login",
            data:"{\"account\":\"" + $("#account").val() + "\",\"password\":\""+ $("#password").val() +"\"}",
            type:"Post",
            contentType:"application/json",
            dataType:"json",
            success:function (result) {
                if (result.data === true){
                    layer.msg(result.message);
                     window.location = 'company'
                }else {
                    layer.msg(result.message)
                }
            },
            error:function(data){
                layer.msg('出错误啦');
            }

        })
    })

});