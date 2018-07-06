<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>
        <sitemesh:write property='title'/>
    </title>
<#include "../common/include.ftl">
    <script type="text/javascript" src="${ctxStatic}/js/cms.js"></script>
    <sitemesh:write property='head'/>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark muzi-bg">
    <div class="container-fluid" style="">
        <a class="navbar-brand" href="${ctx}/"><i class="fa fa-fw fa-snowflake fa-lg"></i>雪</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto ">

            </ul>
            <div class="row">
                <div id="personMenu" class="dropdown">
                    <a class="nav-link text-white dropdown-toggle disabled" href="#" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-fw fa-user"></i>
                    ${(SPRING_SECURITY_CONTEXT.authentication.principal.nickname)!}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a href="${ctx}/user/info" class="dropdown-item">
                            <i class="fas fa-fw fa-user"></i>个人中心
                        </a>
                        <a href="#" class="dropdown-item">
                            <i class="fas fa-fw fa-cog"></i>账号设置
                        </a>
                        <div class="dropdown-divider"></div>
                    </div>
                </div>
                <a id="btnLogOut" class="nav-link text-white" href="${ctx}/logout">
                    <i class="fas fa-fw fa-sign-out-alt"></i>退出
                </a>
            </div>

        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <div style="height: 15px;">&nbsp;</div>
            <nav class="sidebar-nav">
                <ul class="metismenu" id="leftMenu">
                <#-- 递归  宏定义 -->
                    <#macro menuTree menuInfos>
                        <#if menuInfos?? && menuInfos?size gt 0>
                            <#list menuInfos as menuInfo>
                            <li>
                                <#if menuInfo.childList?? && menuInfo.childList?size gt 0>
                                    <a id="leftMenu${(menuInfo.id)!}" class="has-arrow"
                                       href="${(menuInfo.menuHref)!}" aria-expanded="false">
                                        <i class="${(menuInfo.menuIcon)!}"></i>${(menuInfo.menuName)!}
                                    </a>
                                    <ul aria-expanded="false">
                                        <@menuTree menuInfos=menuInfo.childList />
                                    </ul>
                                <#else>
                                    <a id="leftMenu${(menuInfo.id)!}" href="${ctx+(menuInfo.menuHref)!}" target="${(menuInfo.menuTarget)!}">
                                        <i class="${(menuInfo.menuIcon)!}"></i>${(menuInfo.menuName)!}
                                    </a>
                                </#if>
                            </li>
                        </#list>
                    </#if>
                </#macro>
                <#--调用宏 生成递归树-->
                    <@menuTree menuInfos=leftMenus! />
                </ul>
            </nav>
            <script type="text/javascript">
                var id = "${clickMenuId!}";
                $("#leftMenu" + id).addClass("text-warning");
                var parentIds = "${parentIds!}";
                if (parentIds !== null && parentIds.trim() !== '' && parentIds.trim() !== '0') {
                    var ids = parentIds.split(",");
                    for (var i = 0; i < ids.length; i++) {
                        changeToActive(ids[i]);
                    }
                }
            </script>
        </div>
        <div class="col-sm-10">
            <div style="height: 15px; ">&nbsp;</div>
            <div class="col-sm-12 form-group bg-light" style="height: 100%">
                <div class="row form-group">
                </div>
                <div class="form-group">
                    <#if breadcrumbs??>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <#list breadcrumbs as breadcrumb >
                                    <li class="breadcrumb-item <#if (breadcrumb_has_next) && (breadcrumb.menuHref)! !='javascript:void(0)'>active"
                                        aria-current="page"</#if> ><a
                                        href="${breadcrumb.menuHref}">${breadcrumb.menuName}</a></li>
                                </#list>
                            </ol>
                        </nav>
                    </#if>
                </div>
                <sitemesh:write property='body'/>
            </div>
        </div>
    </div>
</div>
<#include "../common/message.ftl">
<#include "../common/hidden.ftl">
</body>
</html>
