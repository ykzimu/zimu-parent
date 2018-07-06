<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>添加菜单</title>
    <script type="text/javascript" src="${ctxStatic}/js/menu_add.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/admin/menu/save" method="POST">
    <div class="container-fluid">
        <div class="form-group">
            <div class="row form-group ">
                <label class="col-sm-2 text-right"></label>
                <label class="col-sm-3 text-center h3">添加菜单</label>
            </div>
            <#if (menuInfo.id)??>
                <div class="row form-group">
                    <label class="col-sm-2 col-form-label text-right">父菜单:</label>
                    <input class="form-control-plaintext col-sm-3" type="text" value="${(menuInfo.menuName)!}"
                           readonly>

                </div>
            </#if>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单名称:</label>
                <input class="form-control col-sm-3" type="text" id="menuName" name="menuName" required="required"
                       placeholder="请输入菜单名称" autofocus maxlength="32">

            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单类型:</label>
                <input class="form-control col-sm-3" type="text" id="menuType" name="menuType"
                       required="required" placeholder="请输入菜单类型" maxlength="32">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单描述:</label>
                <input class="form-control col-sm-3" type="text" id="menuDesc" name="menuDesc"
                       required="required" placeholder="请输入菜单描述" maxlength="32">
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">排序:</label>
                <input class="form-control col-sm-3" type="number" id="menuSort" name="menuSort"
                       required="required" min="0" max="99999">
            </div>

            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">菜单链接:</label>
                <div class="col-sm-3" style="padding-right: 0px;padding-left: 0px;">
                    <select class="form-control custom-select" id="menuHref" name="menuHref" required="required">
                        <option value=""></option>
                        <#list menuHrefs as info>
                            <option value="${info.id}">${info.text}</option>
                        </#list>
                        <option value="javascript:void(0)">javascript:void(0)</option>
                    </select>
                </div>
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">是否是新页面:</label>
                <div class="col-sm-3 form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="menuTargetYes" name="menuTarget"
                           value="_blank">
                    <label class="form-check-label" for="menuTargetYes">是</label>
                    &nbsp;&nbsp;
                    <input class="form-check-input" type="radio" id="menuTargetNo" name="menuTarget"
                           checked="checked"
                           value="">
                    <label class="form-check-label" for="menuTargetNo">否</label>
                </div>
                <input id="agreementHidden" type="hidden" value="0">
            </div>

            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right">图标:</label>
                <input class="form-control col-sm-3" type="text" id="menuIcon" name="menuIcon" placeholder="请输入菜单图标" maxlength="255">
                <label class="col-sm-3 col-form-label">&nbsp;参照&nbsp;
                    <a href="https://fontawesome.com/icons?d=gallery&m=free" target="_blank">fontawesome
                    </a>
                </label>
            </div>
            <div class="row form-group">
                <label class="col-sm-2 col-form-label text-right"></label>
                <input type="submit" id="btnConfirm"
                       class="col-sm-3 btn btn-primary btn-lg btn-block" value="添加"/>
            </div>
            <#if (menuInfo.id)??>
                <div class="row form-group">
                    <input type="hidden" name="parentId" value="${(menuInfo.id)!}">
                </div>
            </#if>
        </div>
    </div>
</form>
</body>
</html>
