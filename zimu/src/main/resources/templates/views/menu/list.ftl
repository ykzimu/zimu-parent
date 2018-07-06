<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>菜单管理</title>
    <script type="text/javascript" src="${ctxStatic}/js/menu_list.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/admin/menu/list" method="POST">
    <div class="form-group">
        <a class="btn btn-success btn-sm" href="${ctx}/admin/menu/add?id=0" role="button"><i class="fas fa-fw fa-plus-square"></i>添加</a>
    </div>
    <div class="form-group text-sm-left">
            <table class="table table-sm table-striped table-bordered table-hover">
                <thead class="thead-light ">
                <tr>
                    <th scope="col">菜单名称</th>
                    <th scope="col">菜单类型</th>
                    <th scope="col">级别</th>
                    <th scope="col">图标</th>
                    <th scope="col">描述</th>
                    <th scope="col">链接</th>
                    <th scope="col">排序</th>
                    <th scope="col">操作</th>
                    <th scope="col">添加/编辑</th>
                </tr>
                </thead>
                <tbody>
                    <#list (page.list)! as info>
                    <tr>
                        <td scope="row">
                                <#list 1..(info.menuLevel)! as cnt >&emsp;</#list>
                            <#if (info.remarks)?number gt 0><a href="#" style="text-decoration:none"
                                                               child-deep="${(info.remarks)!}">&nbsp;<i
                                class="fas fa-angle-down"></i>&nbsp;</a><#else>&emsp;&nbsp;</#if>
                        ${(info.menuName)!}
                        </td>
                        <td>${(info.menuType)!}</td>
                        <td>${(info.menuLevel)!}</td>
                        <td><i class="${(info.menuIcon)!}"></i></td>
                        <td>${(info.menuDesc)!}</td>
                        <td>${(info.menuHref)!}</td>
                        <td style="width: 70px;">
                            <input type="hidden" value="${(info.id)!}">
                            <input type="number" style="width: 65px;" class="form-control-sm"
                                   value="${(info.menuSort)?c}">
                        </td>
                        <td class="text-center">
                            <input type="hidden" id="idHidden${(info.id)}" value="${(info.id)!}">

                            <a href="javascript:void(0)" onclick="changeMenuStatus('${(info.id)!}',null,true)"
                                <#if (info.isShow)! == 0>
                               class="text-success" title="显示">  <i class="fas fa-fw fa-eye"></i>
                            <#else >
                                class="text-danger" title="隐藏">
                                <i class="fas fa-fw fa-eye-slash"></i>
                            </#if>
                            </a>
                            &nbsp;
                            <a href="javascript:void(0)" onclick="changeMenuStatus('${(info.id)!}',true,null)"
                               name="btnDelete"
                                <#if (info.delFlag)! == 1>
                               class="text-success" title="启用"> <i class="fas fa-fw fa-play-circle"></i>
                            <#else >
                                class="text-danger" title="删除"> <i class="fas fa-fw fa-trash-alt"></i>
                            </#if>
                            </a>
                        </td>
                        <td class="text-center">
                            <a href="${ctx}/admin/menu/add?id=${(info.id)!}" class="text-success" title="添加子菜单">
                                <i class="fas fa-fw fa-plus-square"></i></a>
                            &nbsp;
                            <a href="${ctx}/admin/menu/edit?id=${(info.id)!}" class="text-primary" title="编辑">
                                <i class="fas fa-fw fa-edit"></i></a>
                        </td>
                    </tr>

                    </#list>
                <tr>
                    <td colspan="7"></td>
                    <td class="text-center">
                        <button id="btnSave" class="btn btn-primary btn-sm" type="button" onclick="saveSort()">
                            <i class="fas fa-fw fa-save"></i>保存
                        </button>
                    </td>
                    <td colspan="3"></td>
                </tr>
                </tbody>
            </table>
    </div>
</form>
</body>
</html>
