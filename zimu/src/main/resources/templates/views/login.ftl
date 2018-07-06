<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
<#include "../common/include.ftl">
    <script type="text/javascript" src="${ctxStatic}/js/login.js"></script>
    <style>
        a {
            color: gold;
        }

        a:hover,
        a:active {
            color: yellow;
        }
    </style>
</head>
<body>
<form class="form-horizontal" action="${ctx}/login" method="post">
    <div class="container">
        <div class="row form-group justify-content-center text-white">
            <div class="row form-group col-sm-4 justify-content-center">
                <div class="row form-group col-sm-12" style="height: 50px;">
                </div>
                <div class="row form-group col-sm-12">
                    <label class="col text-center"><h4>雪</h4></label>
                </div>
                <div class="row form-group col-sm-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa fa-user"
                                                              aria-hidden="true"></i></span>
                        </div>
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="用户名/邮箱/手机号" maxlength="20" required="required" autofocus>
                    </div>
                </div>
                <div class="row form-group col-sm-12">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa fa-lock"
                                                              aria-hidden="true"></i></span>
                        </div>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="密码" required="required" maxlength="16">
                    </div>
                </div>
                <div class="row form-group col-sm-12">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="remember-me" name="remember-me">
                        <label class="form-check-label" for="remember-me">记住密码</label>
                    </div>

                </div>
                <div class="row form-group col-sm-12">
                    <div class="input-group">
                        <button id="btnLogin" name="btnLogin" type="button" class="btn btn-primary btn-lg btn-block">登录
                        </button>
                    </div>
                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <label><a href="javascript:void(0)">忘记密码</a>&nbsp;&nbsp;<a
                        href="${ctx}/register">立即注册</a></label>
                </div>
                <br>
                <div class="row form-group col-sm-12">
                    <div class="input-group">
                        <label class="col-sm-3" style="padding-left: 0px;padding-right: 0px;">
                            <hr/>
                        </label>
                        <label class="col-sm-6 text-center"
                               style="padding-left: 0px;padding-right: 0px;">其他方式登录</label>
                        <label class="col-sm-3" style="padding-left: 0px;padding-right: 0px;">
                            <hr/>
                        </label>
                    </div>

                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <label>
                        <a href="javascript:void(0)" onclick="oauth2Login('github')" title="github">
                            <i class="fab fa-github fa-2x" aria-hidden="true"></i>
                        </a>
                        &nbsp;&nbsp;
                        <a href="javascript:void(0);" title="QQ" onclick="oauth2Login('qq')">
                            <i class=" fab fa-qq fa-2x" aria-hidden="true"></i>
                        </a>
                    </label>
                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <label><i class="fa fa-copyright" aria-hidden="true"></i>2017-2018 All Rights Reserved</label>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="loginSpinnerModal" tabindex="-1" role="dialog" aria-labelledby="loginSpinnerModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered justify-content-center" role="document">
            <div class="text-light">
                <i class="fa fa-spinner fa-pulse fa-4x fa-fw" aria-hidden="true"></i>
            </div>
        </div>
    </div>
</form>
<#include "../common/message.ftl">
<#include "../common/hidden.ftl">
</body>
</html>
