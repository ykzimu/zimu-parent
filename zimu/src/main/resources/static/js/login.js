/**
 * login.js
 */

$(document).ready(function () {
    $('#loginSpinnerModal').on('shown.bs.modal', function (e) {

        var username = $("#username").val().trim();
        //正则校验
        var pattern = /^[a-zA-Z][a-zA-Z0-9_-]{5,19}$/;
        var flag = pattern.test(username)
        if (username.length < 6 || !flag) {
            loginBtn(true);
            $("#loginSpinnerModal").modal('hide');
            new jBox('Notice', {
                content: "用户名或密码错误!",
                color: 'black',
                autoClose: '2000'
            });
            return;
        }

        var password = $("#password").val().trim();
        //正则校验
        if (password.length < 6 || username === password) {
            loginBtn(true);
            $("#loginSpinnerModal").modal('hide');
            new jBox('Notice', {
                content: "用户名或密码错误!",
                color: 'black',
                autoClose: '2000'
            });
            return;
        }

        //登录校验
        var url = contextPath + "/public/validateLogin";
        var pData = {username: $("#username").val(), password: $("#password").val()};
        $.post(url, pData, function (data, status) {
            //设置登录按钮可用
            loginBtn(true);
            $("#loginSpinnerModal").modal('hide');
            var code = data.msg.code;
            if (code != '0000') {
                new jBox('Notice', {
                    content: data.msg.message,
                    color: 'black',
                    autoClose: '2000'
                });
            } else {
                $("form").submit();
            }
        }, 'json');
    });

    $("#btnLogin").click(function () {
        var username = $("#username").val().trim();
        $("#username").val(username);
        if (username == '') {
            new jBox('Notice', {
                content: "请输入用户名!",
                color: 'black',
                autoClose: '2000'
            });
            return;
        }

        var password = $("#password").val().trim();
        $("#password").val(password);
        if (password == '') {
            new jBox('Notice', {
                content: "请输入密码!",
                color: 'black',
                autoClose: '2000'
            });
            return;
        }


        $("#loginSpinnerModal").modal({
            backdrop: 'static',
            keyboard: false
        });
        //设置登录按钮不可用
        loginBtn(false);

    });


});

function loginBtn(flag) {
    var item = $("#btnLogin");
    if (flag) {
        item.html("登录");
        item.attr("disabled", false);
    } else {
        item.html('登录中......');
        item.attr("disabled", true);
    }
};
