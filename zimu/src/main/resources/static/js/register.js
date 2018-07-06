/**
 * register.js
 */

$(document).ready(function () {


    $("#username").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkUsername(itemName, value);
        checkPassword("password", $("#password").val().trim());
    });


    $("#password").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkPassword(itemName, value);
        checkConfirmPassword("confirmPassword", $("#confirmPassword").val());
    });

    $("#confirmPassword").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkConfirmPassword(itemName, value);
    });

    $("#nickname").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");

        //空值校验
        if (value == '') {
            checkItem(itemName, false, "请输入昵称！");
            return;
        }

        checkItem(itemName, true, "")
    });

    $("#realname").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");

        //空值校验
        if (value == '') {
            checkItem(itemName, false, "请输入姓名！");
            return;
        }

        checkItem(itemName, true, "")
    });


    $("#email").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");

        //空值校验
        if (value == '') {
            checkItem(itemName, false, "请输入邮箱地址！");
            return;
        }

        //长度校验
        if (value.length < 6) {
            checkItem(itemName, false, "请输入正确的邮箱地址！");
            return;
        }

        //正则校验
        var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        var flag = pattern.test(value)
        if (!flag) {
            checkItem(itemName, false, "请输入正确的邮箱地址！");
            return;
        }

        var url = $("#ctx").val() + "/public/validateRegister";
        $.post(url, {email: value}, function (data) {
            if (data.msg.code == '0000') {
                checkItem(itemName, true, "")
            } else {
                checkItem(itemName, false, data.msg.message);
            }

        }, 'json');

    });


    $("#mobile").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");

        //空值校验
        if (value == '') {
            checkItem(itemName, false, "请输入手机号码！");
            return;
        }

        //长度校验
        if (value.length != 11) {
            checkItem(itemName, false, "请输入正确手机号码！");
            return;
        }

        //正则校验
        var pattern = /^((13[0-9])|(14[579])|(15[0-3,5-9])|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\d{8}$/;
        var flag = pattern.test(value)
        if (!flag) {
            checkItem(itemName, false, "请输入正确手机号码！")
            return;
        }

        var url = $("#ctx").val() + "/public/validateRegister";
        $.post(url, {mobile: value}, function (data) {
            if (data.msg.code == '0000') {
                checkItem(itemName, true, "")
            } else {
                checkItem(itemName, false, data.msg.message);
            }

        }, 'json');

    });


    $("#agreement").change(function () {
        var flag = $(this).is(':checked');
        var item = $("#btnRegister");
        if (flag) {
            $("#agreementHidden").val("1");
        } else {
            $("#agreementHidden").val("0");
        }

        checkBtn();
    });

    $("#validateImg").click(function () {
        var url = $("#ctx").val() + "/public/getCodeImage?" + new Date().getTime();
        $("#validateCode").val("");
        $("#validateCodeDanger").hide();
        $("#validateCodeSuccess").hide();
        $("#validateCodeHidden").val("0");
        $(this).attr("src", url);
    });

    $("#validateCode").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        if (value != '' && value.trim().length == 4) {
            var url = $("#ctx").val() + "/public/validateCode";
            $.getJSON(url, {validateCode: value}, function (data) {
                if (data.msg.code == '0000') {
                    checkItem(itemName, true, "")
                } else {
                    checkItem(itemName, false, "")
                }
            })

        } else {
            checkItem(itemName, false, "")
        }
    });

});


function checkUsername(itemName, value) {
    //空值校验
    if (value == '') {
        checkItem(itemName, false, "请输入用户名！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "用户名至少6位！");
        return;
    }

    //正则校验
    var pattern = /^[a-zA-Z][a-zA-Z0-9_-]{5,19}$/;
    var flag = pattern.test(value)
    if (!flag) {
        checkItem(itemName, false, "用户名6-20位字母、数字或“_”，字母开头！");
        return;
    }

    var url = $("#ctx").val() + "/public/validateRegister";
    $.post(url, {username: value}, function (data) {
        if (data.msg.code == '0000') {
            checkItem(itemName, true, "")
        } else {
            checkItem(itemName, false, data.msg.message);
        }

    }, 'json');
}


function checkPassword(itemName, value) {
    //空值校验
    if (value == '') {
        checkItem(itemName, false, "请输入密码！");
        return;
    }

    if ($("#username").val() === value) {
        checkItem(itemName, false, "密码与用户名不能相同！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "密码至少6位！");
        return;
    }
    checkItem(itemName, true, "")
}


function checkConfirmPassword(itemName, value) {
    //空值校验
    if (value == '') {
        checkItem(itemName, false, "请输入确认密码！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "确认密码至少6位！");
        return;
    }

    if ($("#password").val() != value) {
        checkItem(itemName, false, "两次输入的密码不一致！");
        return;
    }
    checkItem(itemName, true, "")
}

function checkItem(itemName, flag, message) {
    var item="#"+itemName;
    var nameSuccess = "#" + itemName + "Success";
    var nameDanger = "#" + itemName + "Danger";
    var nameMsg = "#" + itemName + "Msg";
    var nameHidden = "#" + itemName + "Hidden";
    $(item).removeClass("is-valid");
    $(item).removeClass("is-invalid");
    if (flag) {
        $(item).addClass("is-valid");
        $(nameMsg).html("");
        $(nameMsg).hide();
        $(nameDanger).hide();
        $(nameSuccess).show();
        $(nameHidden).val("1");
    } else {
        $(item).addClass("is-invalid");
        $(nameMsg).html(message);
        $(nameMsg).show();
        $(nameSuccess).hide();
        $(nameDanger).show();
        $(nameHidden).val("0");
    }

    checkBtn();
}

function checkBtn() {
    var val1 = $("#usernameHidden").val();
    var val2 = $("#passwordHidden").val();
    var val3 = $("#confirmPasswordHidden").val();
    var val4 = $("#nicknameHidden").val();
    var val5 = $("#realnameHidden").val();
    var val6 = $("#emailHidden").val();
    var val7 = $("#mobileHidden").val();
    var val8 = $("#validateCodeHidden").val();
    var val9 = $("#agreementHidden").val();
    if (val1 == '1' && val2 == "1" && val3 == "1" && val4 == "1" && val5 == "1" && val6 == "1" && val7 == "1" && val8 == "1" && val9 == "1") {
        $("#btnRegister").attr("disabled", false);
    } else {
        $("#btnRegister").attr("disabled", true);
    }

}