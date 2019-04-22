$(document).ready(function () {

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: false,
        pagingType: false,
        paging: false,
        ordering: false,
        ajax: {
            url: contextPath + "/quartz/listData",
            type: "POST"
        },
        columns: [
            {name: "name", data: "name"},
            {name: "beanName", data: "beanName"},
            {name: "beanClass", data: "beanClass"},
            {name: "triggerStateDesc", data: "triggerStateDesc"},
            {name: "cron", data: "cron"},
            {name: "startTime", data: "startTime"},
            {name: "endTime", data: "endTime"},
            {name: "previousFireTime", data: "previousFireTime"},
            {name: "nextFireTime", data: "nextFireTime"}
        ],
        language: {
            zeroRecords: '抱歉,没有检索到数据',
            loadingRecords: '加载中......',
            processing: '加载中......',
            search: '查询：',  // 将英文search改为中文
            searchPlaceholder: '请输入用户名手机号',//搜索框提示功能
            lengthMenu: '每页&nbsp;_MENU_&nbsp;条',
            info: '_START_-_END_条记录，共_TOTAL_条',
            paginate: {
                next: '下一页',
                previous: '上一页',
                first: '首页',
                last: '末页'
            },
            infoEmpty: '没有数据!',
            infoFiltered: "(从_MAX_条数据检索)"
        },
        autoWidth: false,//关闭自动调节，与父iframe有冲突
        scrollCollapse: false
    });

});