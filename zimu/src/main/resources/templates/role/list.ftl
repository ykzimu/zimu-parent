<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>角色管理</title>
    <script type="text/javascript" src="${ctxStatic}/js/role_list.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/admin/role/list" method="POST">
    <div class="form-group">
        <a class="btn btn-success btn-sm" href="${ctx}/admin/role/add?id=0" role="button"><i
            class="fas fa-fw fa-plus-square"></i>添加</a>
    </div>
    <div class="form-group">
        <table class="table table-sm table-striped table-bordered table-hover">
            <thead class="thead-light ">
            <tr>
                <th scope="col">角色名称</th>
                <th scope="col">角色标识</th>
                <th scope="col">角色描述</th>
                <th scope="col">状态</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
                <#list (page.list)! as info>
                <tr>
                    <td>${(info.roleName)!}</td>
                    <td>${(info.roleCode)!}</td>
                    <td>${(info.roleDesc)!}</td>
                    <td><#if (info.delFlag)! == 1>已删除<#else>正常</#if></td>
                    <td class="text-center">
                        <a href="${ctx}/admin/role/edit?id=${(info.id)!}" class="text-primary" title="编辑">
                            <i class="fas fa-fw fa-edit"></i></a>&nbsp;
                        <a href="${ctx}/admin/role/delete?id=${(info.id)!}" class="text-danger" title="删除">
                            <i class="fas fa-fw fa-trash-alt"></i></a>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>
