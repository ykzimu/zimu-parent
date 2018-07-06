<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>
        <sitemesh:write property='title'/>
    </title>
<#include "../common/include.ftl">
    <sitemesh:write property='head'/>
</head>
<body>


<nav id="topNav" class="navbar navbar-expand-sm navbar-dark muzi-bg">
    <div class="container">
        <a class="navbar-brand" href="${ctx}/"><i class="fa fa-snowflake fa-lg"></i>&nbsp;揽艾&nbsp;亲倍鲜</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

            </ul>
            <div class="row">
            <#if (SPRING_SECURITY_CONTEXT.authentication.principal.id)??>
                <a class="nav-link text-white" href="${ctx}/home">
                    <i class="fas fa-fw fa-cloud " aria-hidden="true"></i>工作台
                </a>
                <a class="nav-link text-white" href="#">
                    <i class="fas fa-fw fa-user"></i>
                    ${(SPRING_SECURITY_CONTEXT.authentication.principal.nickname)!}
                </a>
                <a id="btnLogOut" class="nav-link text-white" href="${ctx}/logout">
                    <i class="fas fa-fw fa-sign-out-alt"></i>退出
                </a>
            <#else>
                <a class="nav-link text-white" href="${ctx}/home">
                    <i class="fas fa-fw fa-sign-in-alt" aria-hidden="true"></i>登录
                </a>
            </#if>
            </div>
        </div>
    </div>
</nav>
<div class="row">xxx</div>
<div class="container">
    <nav class="topbar-nav is-hoverable">
        <ul class="metismenu" id="navMenu">
            <li>
                <a class="text-center" href="${ctx}/">
                    <i class="fa fa-home fa-lg"></i>&nbsp;首页
                </a>
            </li>
            <li>
                <a class="has-arrow text-center" href="#" aria-expanded="false">产品中心</a>
                <ul aria-expanded="false">
                    <li><a href="#">item 0.1</a></li>
                    <li><a href="#">item 0.2</a></li>
                    <li><a href="http://onokumus.com">onokumus</a></li>
                    <li><a href="#">item 0.4</a></li>
                </ul>
            </li>
            <li>
                <a class="text-center" href="#" aria-expanded="false">荣誉资质</a>
            </li>
            <li>
                <a class="text-center" href="#" aria-expanded="false">在线留言</a>
            </li>
            <li>
                <a class="text-center" href="#" aria-expanded="false">联系我们</a>
            </li>
            <li>
                <a class="text-center" href="#" aria-expanded="false">关于我们</a>
            </li>
        </ul>
    </nav>

</div>

<div>
    <sitemesh:write property='body'/>
</div>
<#include "../common/footer.ftl">
<#include "../common/message.ftl">
<#include "../common/hidden.ftl">
</body>
</html>
