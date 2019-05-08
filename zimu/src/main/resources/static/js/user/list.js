$(document).ready(function () {

    var userId;

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: true,
        pagingType: "full_numbers",
        order: [[2, 'asc']],
        ajax: {
            url: contextPath + "/user/listData",
            type: "POST",
            data: {},
            dataSrc: function (json) {
                userId = json.extendData.userId;
                return json.data;
            }
        },
        columnDefs: [
            {
                targets: [0, 6, 7],
                searchable: false,
                orderable: false,
                className: "text-center"
            }
        ],
        columns: [
            //{data: '<input class="checkchild" type="checkbox"/>'},
            {data: null, defaultContent: '', className: 'select-checkbox', width: "33px"},
            {title: '账户', name: "username", data: "username"},
            {title: '昵称', name: "nickname", data: "nickname"},
            {title: '姓名', name: "realname", data: "realname"},
            {title: '邮箱', name: "email", data: "email"},
            {title: '手机', name: "mobile", data: "mobile"},
            {
                title: '锁定状态',
                data: function (item) {

                    if (userId === item.id) {
                        return '<span class="text-danger">当前登陆用户，无法锁定！</span>';
                    }

                    var html = '<input type="checkbox"  data-id="' + item.id + '" data-username="' + item.username + '" data-realname="' + item.realname + '"';
                    if (item.isLocked !== 1) {
                        html += ' checked ';
                    }
                    html += '>';
                    return html;
                }
            },
            {
                title: '操作',
                data: function (item) {
                    if (userId === item.id) {
                        return '<span class="text-danger">当前登陆用户，无法操作！</span>';
                    }

                    return '<a class="btn btn-outline-danger btn-sm" href="#" role="button" title="删除"><i class="fas fa-trash-alt"></i></a>' +
                        '&nbsp;&nbsp;<button type="button" class="btn btn-outline-primary btn-sm" title="编辑"><i class="fas fa-edit"></i></button>';
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
            infoFiltered: "(从_MAX_条数据检索)",
            select: {
                rows: {
                    _: "选中 %d 行",
                    0: "点击选择"

                }
            }
        },
        autoWidth: false,//关闭自动调节，与父iframe有冲突
        scrollCollapse: false,
        drawCallback: function (settings) {
            lockSwitchDraw();
        },
        select: {
            style: 'multi+shift',
            selector: 'td:first-child'
        },
        dom: 'Bfrtip',
        buttons: [
            'copy', 'csv', 'excel', 'pdf'
        ]
    });


});

function lockSwitchDraw() {
    $('table tbody [type="checkbox"]').bootstrapSwitch({
        /*  onText: '<i class="fas fa-lock-open"></i>',*/
        onText: '未锁定',
        /*     offText: '<i class="fas fa-lock"></i>',*/
        offText: '已锁定',
        onColor: 'success',
        offColor: 'danger',
        size: 'mini'
    });

    $('table tbody [type="checkbox"]').on("switchChange.bootstrapSwitch", function (event, state) {

        //window.parent.globalSpinnerModal.show();

        var title = "";
        var content = "";
        if (state) {
            title = '<i class="fas fa-lock-open text-success">&nbsp;&nbsp;解锁成功！</i>';
            content = '账户：' + $(this).attr("data-username") + '&nbsp;（' + $(this).attr("data-realname") + '）已解锁！';
        } else {
            title = '<i class="fas fa-lock text-danger">&nbsp;&nbsp;锁定成功!</i>';
            content = '账户：' + $(this).attr("data-username") + '&nbsp;（' + $(this).attr("data-realname") + '）已锁定！';
        }

        jBoxMsgModel.setTitle('<i class="fas fa-spinner fa-pulse"></i>').setContent('处理中......').open();

        var url = contextPath + "/user/updateLockStatus";
        var pData = {
            userId: $(this).attr('data-id'),
            state: state
        };

        $.post(url, pData, function (data, status) {
            if (data.msg.code == '200') {
                jBoxMsgModel.setTitle(title).setContent(content);
            }
        }, 'json');
    });
}