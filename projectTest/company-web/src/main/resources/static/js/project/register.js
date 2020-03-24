layui.use(['form'], function () {
    var form = layui.form,
        layer = layui.layer;

    // 粒子线条背景
    $(document).ready(function(){
        $('.layui-container').particleground({
            dotColor:'#5cbdaa',
            lineColor:'#5cbdaa'
        });
    });
    $('#register').click(function(){
        if ($("#account").val() === '') {
            layer.msg('账号不能为空');
            return false;
        }

        if ($("#password").val() === '') {
            layer.msg('密码不能为空');
            return false;
        }

        if ($("#password2").val() === '') {
            layer.msg('再次输入密码不能为空');
            return false;
        }

        if ($("#companyName").val() === 0){
            layer.msg('请输入公司名字');
            return false;
        }

        if ($("#companyNature").val() === 0){
            layer.msg('请选择公司类型');
            return false;
        }

        if ($("#password2").val() !== $("#password").val()){
            layer.msg('两次密码输入不一致');
            return false;
        }
        //进行注册操作
        $.ajax({
            url:"register/registerAdmin",
            data:"{\"account\":\"" + $("#account").val() + "\",\"password\":\"" + $("#password").val() + "\",\"companyName\":\"" + $("#companyName").val() + "\",\"companyNature\":\"" + $("#companyNature").val() +"\"}",
            type:"Post",
            contentType:"application/json",
            dataType:"json",
            success:function (result) {
                if (result.data === true){
                    layer.msg(result.message);
                }else {
                    layer.msg(result.message);
                    return false;
                }
            },
            error:function(data){
                layer.msg('出错误啦');
            }
        })
    })
});