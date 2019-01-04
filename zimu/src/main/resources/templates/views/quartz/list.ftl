<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="${ctxStatic}/js/quartz_list.js?v9"></script>
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <div class="form-group">
            <table class="table table-sm table-striped table-bordered table-hover" id="myTable">
                <thead class="thead-light ">
                <tr>
                <#-- <th scope="col" class="text-center"><input type="checkbox" value="0"></th>-->
                    <th scope="col">序号</th>
                    <th scope="col">账户</th>
                    <th scope="col">昵称</th>
                    <th scope="col">姓名</th>
                    <th scope="col">邮箱</th>
                    <th scope="col">手机</th>
                </tr>
                </thead>
            </table>
        </div>
    <#--<#include "../../common/page.ftl">-->
    </div>
</form>
</body>
</html>
