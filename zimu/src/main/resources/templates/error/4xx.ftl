<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">
<!DOCTYPE html>
<html>
<head>
    <title>${status}</title>
<#include "../common/include.ftl">

    <script type="text/javascript" src="${ctxStatic}/jquery-countdown/2.2.0/jquery.countdown.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/error.js"></script>
</head>
<body>
<form class="form-horizontal">
    <div class="container">
        <div class="row form-group justify-content-center text-white">
            <div class="row form-group col-sm-4 justify-content-center">
                <div class="row form-group col-sm-12" style="height: 50px;">
                </div>
                <div class="row form-group col-sm-12">
                    <label class="col text-center text-danger h1">${status}</label>
                </div>
                <div class="row form-group col-sm-12 justify-content-center">
                    <span id="countSpan" class="text-warning"></span>&nbsp;秒后自动跳转至&nbsp;<a href="${ctx}/home">首页</a>
                </div>
            </div>
        </div>
    </div>
</form>
<#include "../common/hidden.ftl">
</body>
</html>
