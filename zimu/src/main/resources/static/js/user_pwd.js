/**
 * register.js
 */

$(document).ready(function () {

    $("#password").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkPassword(itemName, value);
    });

    $("#newPassword").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkNewPassword(itemName, value);
    });

    $("#confirmPassword").bind('input propertychange', function () {
        var value = $(this).val().trim();
        $(this).val(value);
        var itemName = $(this).attr("id");
        checkConfirmPassword(itemName, value);
    });

    $("#btnConfirm").click(function () {

        globalSpinnerModal.show();
        $(this).attr("disabled", true);

        //参数
        var params = {"password": $("#password").val(), "newPassword": $("#newPassword").val()};
        //url
        var url = $("#ctx").val() + "/user/updPwd";
        $.post(url, params, function (data) {
            globalSpinnerModal.hide();
            var message = "修改成功！"
            if (data.msg.code == '0000') {
                $("#password").attr("disabled", true);
                $("#newPassword").attr("disabled", true);
                $("#confirmPassword").attr("disabled", true);
                $("#successDiv").show();
                $("#formDiv").hide();
            } else {
                message = data.msg.message;
                $(this).attr("disabled", false);
            }
            new jBox('Notice', {
                content: message,
                color: 'black',
                autoClose: '2000'
            });

        }, 'json');

    });

});


function checkPassword(itemName, value) {

    //空值校验
    if (value === '') {
        checkItem(itemName, false, "请输入原密码！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "原密码至少6位！");
        return;
    }


    var url = $("#ctx").val() + "/user/validatePwd";
    $.post(url, {"password": value}, function (data) {
        if (data.msg.code == '0000') {
            checkItem(itemName, true, "")

            $("#newPassword").focus();
            //原密码验证通过后，验证新密码
            checkNewPassword("newPassword", $("#newPassword").val().trim());
        } else {
            checkItem(itemName, false, data.msg.message);
        }

    }, 'json');

}

function checkNewPassword(itemName, value) {
    //空值校验
    if (value === '') {
        checkItem(itemName, false, "请输入新密码！");
        return;
    }

    if ($("#passwordHidden").val() === '1' && $("#password").val() === value) {
        checkItem(itemName, false, "新密码与原密码不能相同！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "新密码至少6位！");
        return;
    }

    checkItem(itemName, true, "");

    //新密码验证通过后验证确认密码
    checkConfirmPassword("confirmPassword", $("#confirmPassword").val().trim());

}


function checkConfirmPassword(itemName, value) {
    //空值校验
    if (value === '') {
        checkItem(itemName, false, "请输入确认密码！");
        return;
    }

    //长度校验
    if (value.length < 6) {
        checkItem(itemName, false, "确认密码至少6位！");
        return;
    }

    if ($("#newPassword").val() !== value) {
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
    var val1 = $("#passwordHidden").val();
    var val2 = $("#newPasswordHidden").val();
    var val3 = $("#confirmPasswordHidden").val();
    if (val1 == '1' && val2 == "1" && val3 == "1") {
        $("#btnConfirm").attr("disabled", false);
    } else {
        $("#btnConfirm").attr("disabled", true);
    }

}