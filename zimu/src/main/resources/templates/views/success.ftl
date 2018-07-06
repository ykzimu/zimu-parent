<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>success</title>
</head>
<body>
<form class="form-horizontal">
    <div class="container-fluid">
        <div class="form-group">
            <div class="alert alert-success text-center" role="alert">
            ${message!}
            </div>
            <div class="row form-group">
            </div>
        </div>
    </div>
</form>
</body>
</html>
