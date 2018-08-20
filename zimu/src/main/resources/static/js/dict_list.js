$(document).ready(function () {


    $('#myTable').DataTable({
        processing: true,
        loadingRecords: "Loading...",
        ajax: {
            url: "/admin/dict/listData",
            type: "GET",
            dataSrc: function (data) {

                return data.data.list; //获取服务器返回的数据

            }
        },
        columns: [
            {data: "username"},
            {data: "username"},
            {data: "username"},
            {data: "username"},
            {data: "username"},
            {data: "username"},
            {data: "username"}
        ],
        language: {
            zeroRecords: '抱歉,没有检索到数据',
            search: '查询',  // 将英文search改为中文
            searchPlaceholder: '请输入用户名手机号',//搜索框提示功能
            lengthMenu: '每页显示_MENU_条记录',
            info: '显示第_START_到第_END_条记录，共_TOTAL_条',
            paginate: {'next': '下页', 'previous': '下页', 'first': '第一页', 'last': '最后一页'},
            infoEmpty: '没有数据!',
            infoFiltered: "(从_MAX_条数据检索)"
        },
        aoColumnDefs: [{
            targets: 0,
            searchable: false,
            orderable: false,
            className: 'dt-body-center',
            render: function (data, type, row) {
                return '<input class="checkchild" type="checkbox"/>';
            }
        }]
    });

});