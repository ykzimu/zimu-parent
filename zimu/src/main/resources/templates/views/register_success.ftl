<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>注册成功</title>
<#include "../common/include.ftl">

    <script type="text/javascript" src="${ctxStatic}/jquery-countdown/2.2.0/jquery.countdown.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/register_success.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/" method="get">
    <div class="container">
        <div class="row form-group justify-content-center">
            <div class="row form-group col-sm-8 justify-content-center">
                <div class="row form-group col-sm-12" style="height: 80px;">
                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <label class="h4 text-success">注册成功！</label>
                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <span id="countSpan" class="text-success"></span>&nbsp;秒后自动跳转至&nbsp;<a href="${ctx}/">首页</a>
                </div>
            </div>
        </div>
    </div>
</form>
<#include "../common/hidden.ftl">
</body>
</html>
