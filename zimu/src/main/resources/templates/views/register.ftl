<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>注册账户</title>
    <script type="text/javascript" src="${ctxStatic}/js/register.js"></script>
</head>
<body>
<form id="registerForm" class="form-horizontal" action="${ctx}/register" method="post">
    <div class="container ">
        <div class="text-white">
            <div class="row form-group" style="height: 20px;">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 text-right"></label>
                <label class="h2 col-sm-4 text-center">注册</label>
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">用户名:</label>
                <input class="form-control col-sm-4" type="text" id="username" name="username"
                       value="${(user.username)!}" required="required" minlength="6" maxlength="20"
                       placeholder="6-20位字母、数字或“_”，字母开头" autofocus>
                &nbsp;
                <i id="usernameDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="usernameSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="usernameMsg" class="text-danger" style="display: none"></small>
                <input id="usernameHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">密码:</label>
                <input class="form-control col-sm-4" type="password" id="password" name="password" required="required"
                       minlength="6" maxlength="16" placeholder="6-16位字母、数字或符号">
                &nbsp;
                <i id="passwordDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="passwordSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="passwordMsg" class="text-danger" style="display: none"></small>
                <input id="passwordHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">确认密码:</label>
                <input class="form-control col-sm-4" type="password" id="confirmPassword" name="confirmPassword"
                       required="required" minlength="6" maxlength="16" placeholder="再次输入您的登录密码">
                &nbsp;
                <i id="confirmPasswordDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="confirmPasswordSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="confirmPasswordMsg" class="text-danger" style="display: none"></small>
                <input id="confirmPasswordHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">昵称:</label>
                <input class="form-control col-sm-4" type="text" id="nickname" name="nickname"
                       value="${(user.nickname)!}" required="required" maxlength="10" placeholder="请输入昵称">
                &nbsp;
                <i id="nicknameDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="nicknameSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="nicknameMsg" class="text-danger" style="display: none"></small>
                <input id="nicknameHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">姓名:</label>
                <input class="form-control col-sm-4" type="text" id="realname" name="realname"
                       value="${(user.realname)!}" required="required" maxlength="10" placeholder="请输入姓名">
                &nbsp;
                <i id="realnameDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="realnameSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="realnameMsg" class="text-danger" style="display: none"></small>
                <input id="realnameHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">邮箱:</label>
                <input class="form-control col-sm-4" type="text" id="email" name="email" value="${(user.email)!}"
                       required="required" maxlength="20" placeholder="请正确填写邮箱地址">
                &nbsp;
                <i id="emailDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="emailSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="emailMsg" class="text-danger" style="display: none"></small>
                <input id="emailHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">手机号码:</label>
                <input class="form-control col-sm-4" type="text" id="mobile" name="mobile" value="${(user.mobile)!}"
                       required="required" minlength="11" maxlength="11" placeholder="请输入您的手机号码">
                &nbsp;
                <i id="mobileDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="mobileSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="mobileMsg" class="text-danger" style="display: none"></small>
                <input id="mobileHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right">验证码:</label>
                <div class="input-group col-sm-4" style="padding-left: 0px;padding-right: 0px;">
                    <input type="text" class="form-control" id="validateCode" name="validateCode" minlength="4"
                           maxlength="4" placeholder="请输入验证码">
                    <div class="input-group-append">
                        <span class="input-group-text">
                            <img id="validateImg" src="${ctx}/public/getCodeImage" title="点击刷新"/>
                        </span>
                    </div>
                </div>
                &nbsp;
                <i id="validateCodeDanger" class="fa fa-times fa-2x text-danger" style="display: none"></i>
                <i id="validateCodeSuccess" class="fa fa-check fa-2x text-success" style="display: none"></i>
                &nbsp;
                <small id="validateCodeMsg" class="text-danger" style="display: none"></small>
                <input id="validateCodeHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 col-form-label text-right"></label>
                <div class="col-sm-4 form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="agreement" name="agreement">
                    <label class="form-check-label" for="agreement">阅读并接受
                        <a href="" data-toggle="modal" data-target="#agreementModalLong">《用户协议》</a>
                    </label>
                </div>
                <input id="agreementHidden" type="hidden" value="0">
            </div>
            <div class="row form-group">
                <label class="col-sm-4 text-right"></label>
                <input type="submit" id="btnRegister" class="col-sm-4 btn btn-primary btn-lg btn-block "
                       disabled="disabled"
                       value="提交"/>
            </div>
        </div>
    </div>
</form>
    <!-- Modal -->
<div class="modal fade" id="agreementModalLong" tabindex="-1" role="dialog" aria-labelledby="agreementModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">协议</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <iframe width="100%" height="500px" frameborder="0"
                        src="https://passport.baidu.com/static/passpc-account/html/protocal.html"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
