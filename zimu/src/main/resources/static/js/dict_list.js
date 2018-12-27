$(document).ready(function () {

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: true,
        ajax: {
            url: "/admin/dict/listDataTable",
            type: "POST"
        },
        columns: [
            //{data: '<input class="checkchild" type="checkbox"/>'},
            {name: "id", data: "id", orderable: false},
            {name: "username", data: "username"},
            {name: "nickname", data: "nickname"},
            {name: "realname", data: "realname"},
            {name: "email", data: "email"},
            {name: "mobile", data: "mobile"}
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
        //jQueryUI
        scrollY: 410,
        scrollCollapse: true,
        jQueryUI: true
    });

});