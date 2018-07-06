<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <script type="text/javascript" src="${ctxStatic}/js/home.js"></script>
</head>
<body>
<form class="form-horizontal">
    <div class="container-fluid">
        <div id="mainChart" style="height: 400px;">

        </div>
    </div>
</form>
</body>
</html>
