$(document).ready(function () {

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: false,
        pagingType: false,
        paging: false,
        ordering: false,
        ajax: {
            url: contextPath + "/admin/menu/listData",
            type: "POST"
        },
        treeGrid: {
            left: 35,
            expandIcon: '<span><i class="fas fa-angle-right"></i></span>',
            collapseIcon: '<span><i class="fas fa-angle-down"></i></span>'
        },
        columns: [
            {
                className: 'treegrid-control',
                width: "100px",
                data: function (item) {
                    if (item.children != null && item.children.length > 0) {
                        return '<span><i class="fas fa-angle-right"></i></span>';
                    }
                    return '';
                }
            },
            {
                width: "250px",
                name: "menuName",
                data: function (item) {
                    return item.menuName;
                }
            },
            {name: "menuType", data: "menuType"},
            {name: "menuLevel", data: "menuLevel"},
            {
                name: "menuIcon",
                data: function (item) {
                    if (item.menuIcon == null || item.menuIcon === '') {
                        return '';
                    }
                    return '<span><i class="' + item.menuIcon + '"></i></span>&nbsp;' + item.menuIcon;
                }
            },
            {name: "menuDesc", data: "menuDesc"},
            {name: "menuHref", data: "menuHref"},
            {name: "menuSort", data: "menuSort"},
            {name: "menuSort", data: "menuSort"},
            {name: "menuSort", data: "menuSort"}
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