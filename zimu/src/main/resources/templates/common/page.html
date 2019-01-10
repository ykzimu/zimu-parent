<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<div class="row form-group">
    <script type="text/javascript" src="${ctxStatic}/js/page.js"></script>
    <div class="row col-sm-3">
        <label class="col-sm-5 form-control-sm col-form-label text-right">每页</label>
        <select class="form-control-sm col-sm-4" onchange="goToPage('1')" id="pageSize" name="pageSize">
        <#list pageSizeInfos as pageSizeInfo>
            <option value="${pageSizeInfo.id}"
                    <#if (page.pageSize == (pageSizeInfo.id)?number) || (page.pageSize==0 && pageSizeInfo.id=='10' ) >selected="selected"</#if>>${pageSizeInfo.text}</option>
        </#list>
        </select>
        <label class="col-sm-3 form-control-sm col-form-label">条</label>
    </div>

    <div class="col-sm-9">
        <nav aria-label="Page navigation example">
            <ul class="pagination pagination-sm">
                <li class="page-item <#if (page.isFirstPage)!>disabled</#if>">
                    <a class="page-link " href="javascript:void(0)" onclick="goToPage('1')">首页</a>
                </li>
                <li class="page-item <#if (page.isFirstPage)!>disabled</#if>">
                    <a class="page-link " href="javascript:void(0)"
                       onclick="goToPage(<#if (page.isFirstPage)!>'1'<#else>'${(page.prePage)!}'</#if> )">上一页</a>
                </li>
            <#if (page.navigatepageNums)?? && ((page.navigatepageNums)?size gt 0) && ((page.navigatepageNums[0])! gt 1)>
                <li class="page-item ">
                    <a class="page-link" href="javascript:void(0)" onclick="goToPage('1')">1</a>
                </li>
                <#if (page.navigatepageNums[0])! gt 2>
                    <li class="page-item disabled"><a class="page-link">...</a></li>
                </#if>
            </#if>
            <#list (page.navigatepageNums)! as navigatepageNum>
                <#if (page.pageNum)! == navigatepageNum>
                    <li class="page-item active">
                        <a class="page-link">${navigatepageNum}<span class="sr-only">(current)</span></a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="javascript:void(0)"
                           onclick="goToPage('${navigatepageNum}')">${navigatepageNum}</a>
                    </li>
                </#if>
            </#list>
            <#if (page.pages - page.pageNum) gt 3>
                <#if (page.pages - page.pageNum) gt 4>
                    <li class="page-item disabled"><a class="page-link">...</a></li>
                </#if>
                <li class="page-item">
                    <a class="page-link" href="javascript:void(0)"
                       onclick="goToPage(${(page.pages)!})">${(page.pages)!}</a>
                </li>
            </#if>
                <li class="page-item <#if (page.isLastPage)!>disabled</#if>">
                    <a class="page-link " href="javascript:void(0)"
                       onclick="goToPage(<#if (page.isLastPage)!>'${(page.pages)!}'<#else>'${(page.nextPage)!}'</#if>)">下一页</a>
                </li>
                <li class="page-item <#if (page.isLastPage)!>disabled</#if>">
                    <a class="page-link" href="javascript:void(0)" onclick="goToPage('${(page.pages)!}')">末页</a>
                </li>

            </ul>
        </nav>
    </div>
    <input type="hidden" id="pageNum" name="pageNum" value="${(page.pageNum)!}"/>
    <input type="hidden" id="pageNumChange" value="0"/>
    <input type="hidden" id="fieldName" name="fieldName" value="${(searchInfo.fieldName)!}"/>
    <input type="hidden" id="sortType" name="sortType" value="${(searchInfo.sortType)!}"/>
</div>