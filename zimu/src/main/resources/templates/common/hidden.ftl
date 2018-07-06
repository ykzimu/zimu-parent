<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<#-- 隐藏域 -->
<div style="display: none">
    <input type="hidden" id="ctx" value="${ctx}"/>
    <input type="hidden" id="ctxStatic" value="${ctxStatic}"/>
</div>