$(document).ready(function () {

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: true,
        pagingType: "full_numbers",
        order: [[1, 'asc']],
        ajax: {
            url: contextPath + "/user/listData",
            type: "POST"
        },
        columnDefs: [
            {
                targets: [0, 6, 7],
                searchable: false,
                orderable: false
            },
            {
                title: '账户',
                targets: 1,
            },
            {
                title: '昵称',
                targets: 2,
            },
            {
                title: '姓名',
                targets: 3,
            },
            {
                title: '邮箱',
                targets: 4,
            },
            {
                title: '手机',
                targets: 5,
            },
            {
                title: '锁定状态',
                targets: 6,
                className: "text-center"
            },
            {
                title: '操作',
                targets: 7,
                className: "text-center"
            }
        ],
        columns: [
            //{data: '<input class="checkchild" type="checkbox"/>'},
            {name: "id", data: "id"},
            {name: "username", data: "username"},
            {name: "nickname", data: "nickname"},
            {name: "realname", data: "realname"},
            {name: "email", data: "email"},
            {name: "mobile", data: "mobile"},
            {
                data: function (item) {
                    return '<input type="checkbox" name="lock-switch" checked>';
                }
            },
            {
                data: function (item) {
                    return '<a class="btn btn-outline-danger btn-sm" href="#" role="button" title="删除"><i class="fas fa-trash-alt"></i></a>' +
                        '&nbsp;&nbsp;<button type="button" class="btn btn-outline-primary btn-sm" title="编辑"><i class="fas fa-edit"></i></button>' +
                        '&nbsp;&nbsp;<button type="button" class="btn btn-outline-primary btn-sm" title="编辑"><i class="fas fa-lock"></i></button>' +
                        '';
                }
            }
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
        scrollCollapse: false,
        drawCallback: function (settings) {
            lockSwitchDraw();
        }
    });


});

function lockSwitchDraw() {
    $("[name='lock-switch']").bootstrapSwitch({
        onText: "启动",
        offText: "停止",
        onColor: "success",
        offColor: "info",
        size: "small"
    });
}