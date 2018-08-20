<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="${ctxStatic}/js/user_list.js"></script>
</head>
<body>
<form class="form-horizontal" action="${ctx}/admin/user/list" method="get">
    <div class="form-group">
        <div class="row form-group">
            <div class="input-group col-sm-4">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">关键词：</span>
                </div>
                <input class="form-control pageListKeyWord" type="text" id="keyword" name="keyword"
                       value="${(searchInfo.keyword)!}" placeholder="账户/昵称/姓名/手机号码/邮箱" aria-label="keyword"
                       aria-describedby="basic-addon1">
            </div>
            <input type="submit" class="form-control col-sm-1 btn btn-primary " value="查询"/>
        </div>
        <div class="form-group">
            <button type="button" id="addItemBtn" class="btn btn-primary btn-sm"><i class="fas fa-user-plus"></i>
            </button>
            <button type="button" id="delItemBtn" class="btn btn-danger btn-sm" disabled="disabled"><i
                class="far fa-trash-alt"></i></button>
        </div>
        <div class="form-group">
            <table class="table table-sm table-striped table-bordered table-hover" id="myTable">
                <caption>
                    总记录数：${(page.total)!}&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;总页数：${(page.pages)!}&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;当前页：第&nbsp;${(page.pageNum)!}&nbsp;页
                </caption>
                <thead class="thead-light ">
                <tr>
                    <th scope="col" class="text-center"><input type="checkbox" value="0" <#if (page.total)! == 0 >
                                                               disabled="disabled"></#if></th>
                    <th scope="col">序号</th>
                    <th scope="col">账户&nbsp;<i class="fas fa-sort" field-name="username"></i></th>
                    <th scope="col">昵称&nbsp;<i class="fas fa-sort" field-name="nickname"></i></th>
                    <th scope="col">姓名&nbsp;<i class="fas fa-sort" field-name="realname"></i></th>
                    <th scope="col">手机&nbsp;<i class="fas fa-sort" field-name="mobile"></i></th>
                    <th scope="col">邮箱&nbsp;<i class="fas fa-sort" field-name="email"></i></th>
                    <th scope="col">注册时间&nbsp;<i class="fas fa-sort" field-name="create_date"></i></th>
                    <th scope="col">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list (page.list)! as info>
                <tr>
                    <td class="text-center">
                        <#if (SPRING_SECURITY_CONTEXT.authentication.principal.id) != (info.id)>
                            <input type="checkbox" value="${(info.id)?c}">
                        </#if>
                    </td>
                    <th scope="row">${info_index + (page.startRow)!}</th>
                    <td>${(info.username)!}</td>
                    <td>${(info.nickname)!}</td>
                    <td>${(info.realname)!}</td>
                    <td>${(info.mobile)!}</td>
                    <td>${(info.email)!}</td>
                    <td>${(info.createDate)?string('yyyy-MM-dd HH:mm:ss')}</td>
                    <td class="text-center">
                        <#if (SPRING_SECURITY_CONTEXT.authentication.principal.id) != (info.id)>
                            <a href="#" class="text-primary" title="编辑"><i class="fas fa-fw fa-edit"></i>编辑</a>
                            &nbsp;&nbsp;
                            <a href="javascript:void(0)" onclick="deleteUser('${(info.id)?c}')" class="text-danger"
                               title="删除"><i class="fas fa-fw fa-trash-alt"></i>删除</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#include "../../common/page.ftl">
    </div>
</form>
</body>
</html>
