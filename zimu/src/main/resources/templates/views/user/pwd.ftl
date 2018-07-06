<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <script type="text/javascript" src="${ctxStatic}/js/user_pwd.js"></script>

</head>
<body>
<form class="form-horizontal needs-validation" action="${ctx}/" method="get" novalidate>
    <div class="container-fluid">
        <div id="successDiv" class="form-group" style="display: none;">
            <div class="alert alert-success text-center" role="alert">
                密码修改成功！
            </div>
            <div class="row form-group">
            </div>
        </div>
        <div id="formDiv" class="form-group">
            <div class="row form-group">
                <label class="col-sm-2 text-right"></label>
                <label class="col-sm-4 text-center h3">修改密码</label>
            </div>
            <div class="row form-group mb-3">
                <label class="col-sm-2 col-form-label text-right">原密码:</label>
                <input class="form-control col-sm-4" type="password" id="password" name="password" required="required"
                       minlength="6" maxlength="16" placeholder="请输入原密码" autofocus>
                &nbsp;
                <i id="passwordDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="passwordSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="passwordMsg" class="text-danger" style="display: none"></small>
                <input id="passwordHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">新密码:</label>
                <input class="form-control col-sm-4" type="password" id="newPassword" name="newPassword"
                       required="required" minlength="6" maxlength="16" placeholder="6-16位字母、数字或符号">
                &nbsp;
                <i id="newPasswordDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="newPasswordSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="newPasswordMsg" class="text-danger" style="display: none"></small>
                <input id="newPasswordHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">确认新密码:</label>
                <input class="form-control col-sm-4" type="password" id="confirmPassword" name="confirmPassword"
                       required="required" minlength="6" maxlength="16" placeholder="再次输入您的新密码">
                &nbsp;
                <i id="confirmPasswordDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="confirmPasswordSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="confirmPasswordMsg" class="text-danger" style="display: none"></small>
                <input id="confirmPasswordHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right"></label>
                <input type="button" id="btnConfirm" class="col-sm-4 btn btn-primary btn-lg btn-block "
                       disabled="disabled" value="确认修改"/>
            </div>
            <div class="row form-group">
            </div>
        </div>
    </div>
</form>
</body>
</html>
