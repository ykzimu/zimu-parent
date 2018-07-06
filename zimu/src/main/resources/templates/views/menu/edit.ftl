<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>编辑菜单</title>
    <script type="text/javascript" src="${ctxStatic}/js/menu_edit.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/admin/menu/update" method="POST">
    <div class="container-fluid">
        <div class="form-group">
            <div class="row form-group ">
                <label class="col-sm-2 text-right"></label>
                <label class="col-sm-3 text-center h3">编辑菜单</label>
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单名称:</label>
                <input class="form-control col-sm-3" type="text" id="menuName" name="menuName" required="required"
                       placeholder="请输入菜单名称" autofocus maxlength="32" value="${(menuInfo.menuName)!}">

            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单类型:</label>
                <input class="form-control col-sm-3" type="text" id="menuType" name="menuType"
                       required="required" placeholder="请输入菜单类型" maxlength="32" value="${(menuInfo.menuType)!}">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单描述:</label>
                <input class="form-control col-sm-3" type="text" id="menuDesc" name="menuDesc"
                       required="required" placeholder="请输入菜单描述" maxlength="32" value="${(menuInfo.menuDesc)!}">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">排序:</label>
                <input class="form-control col-sm-3" type="number" id="menuSort" name="menuSort"
                       required="required" min="0" max="99999" value="${(menuInfo.menuSort)?c}">
            </div>

            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单链接:</label>
                <div class="col-sm-3" style="padding-right: 0px;padding-left: 0px;">
                    <select class="form-control custom-select" id="menuHref" name="menuHref" required="required">
                        <option value=""></option>
                        <#list menuHrefs as info>
                            <option value="${info.id}" <#if (info.text)! == (menuInfo.menuHref)! >
                                    selected="selected" </#if>>${info.text}</option>
                        </#list>
                        <option value="javascript:void(0)" <#if (menuInfo.menuHref)! =='javascript:void(0)' >
                                selected="selected" </#if>>javascript:void(0)
                        </option>
                    </select>
                </div>
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">是否是新页面:</label>
                <div class="col-sm-3 form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="menuTargetYes" name="menuTarget"
                        <#if (menuInfo.menuTarget)! =='_blank' > checked="checked"</#if> value="_blank">
                    <label class="form-check-label" for="menuTargetYes">是</label>
                    &nbsp;&nbsp;
                    <input class="form-check-input" type="radio" id="menuTargetNo" name="menuTarget"
                        <#if (menuInfo.menuTarget)! =='' > checked="checked"</#if> value="">
                    <label class="form-check-label" for="menuTargetNo">否</label>
                </div>
                <input id="agreementHidden" type="hidden" value="0">
            </div>

            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">图标:</label>
                <input class="form-control col-sm-3" type="text" id="menuIcon" name="menuIcon" placeholder="请输入菜单图标"
                       maxlength="255" value="${(menuInfo.menuIcon)!}">
                <label class="col-sm-3 col-form-label">&nbsp;参照&nbsp;
                    <a href="https://fontawesome.com/icons?d=gallery&m=free" target="_blank">fontawesome
                    </a>
                </label>
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right"></label>
                <input type="submit" id="btnConfirm"
                       class="col-sm-3 btn btn-primary btn-lg btn-block" value="更新"/>
            </div>
            <div class="row form-group">
                <input type="hidden" name="id" value="${(menuInfo.id)!}">
            </div>
        </div>
    </div>
</form>
</body>
</html>
