$(document).ready(function () {

    $('#myTable').DataTable({
        serverSide: true,
        processing: true,
        searching: true,
        pagingType: "full_numbers",
        ajax: {
            url: contextPath + "/admin/dict/listData",
            type: "POST"
        },
        columns: [
            {},
            {},
            {name: "username", data: "username"},
            {name: "nickname", data: "nickname"},
            {name: "realname", data: "realname"},
            {name: "email", data: "email"},
            {name: "mobile", data: "mobile"},
            {}
        ],
        columnDefs: [
            {
                orderable: false,
                //className: 'select-checkbox',
                className: ' text-center',
                targets: 0,
                title: '<input type="checkbox">',
                defaultContent: '<input type="checkbox">'
            },
            {
                orderable: false,
                targets: 1,
                title: '序号',
                className: ' text-center',
                render: function (data, type, row, meta) {
                    return meta.row + 1 + meta.settings._iDisplayStart;
                }
            }, {
                targets: 2,
                title: '账户'
            }, {
                targets: 3,
                title: '昵称'
            },
            {
                targets: 4,
                title: '姓名'
            },
            {
                targets: 5,
                title: '邮箱'
            },
            {
                targets: 6,
                title: '手机'
            },
            {
                orderable: false,
                targets: -1,
                title: '操作',
                class: 'text-center',
                defaultContent: '<a href="#" class="text-primary" title="编辑"><i class="fas fa-fw fa-edit"></i>编辑</a>'
            }
        ],
        /*      select: {
                  style: 'multi',
                  selector: 'td:first-child'
              },*/

        order: [[2, 'asc']],
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
        initComplete: function () {

        },
        drawCallback: function (settings) {
            $("#myTable thead tr th:first-child input:checkbox").prop("checked", false);
            var len = settings.aoData.length;
            if (len === 0) {
                $("#myTable thead tr th:first-child input:checkbox").attr("disabled", true);
            } else {
                $("#myTable thead tr th:first-child input:checkbox").attr("disabled", false);
            }

            /*   var api = this.api();
               api.column(1).nodes().each(function(cell, i) {
                   cell.innerHTML = i + 1;
               });*/
        }
    });


    $(document).on('change', '#myTable thead tr th:first-child input:checkbox', function () {
        var isChecked = $(this).is(":checked");

        if (isChecked) {
            $("#myTable tbody tr td:first-child input:checkbox").prop("checked", true);
            $("#myTable tbody tr").addClass("table-success");
        } else {
            $("#myTable tbody tr td:first-child input:checkbox").prop("checked", false);
            $("#myTable tbody tr").removeClass("table-success")
        }
    });

    $(document).on('click', '#myTable tbody tr td:first-child input:checkbox', function () {

        var isChecked = $(this).is(":checked");
        if (isChecked) {
            $(this).parents("tr").addClass("table-success");
        } else {
            $(this).parents("tr").removeClass("table-success");
        }

        var lenAll = $("#myTable tbody tr td:first-child input:checkbox").length;
        var lenChecked = $("#myTable tbody tr td:first-child input:checkbox:checked").length;
        if (lenAll === lenChecked) {
            $("#myTable thead tr th:first-child input:checkbox").prop("checked", true);
        } else {
            $("#myTable thead tr th:first-child input:checkbox").prop("checked", false);
        }
    });

    $(document).on('click', '#myTable tbody tr td:first-child', function () {
       //alert("a");
    });

});