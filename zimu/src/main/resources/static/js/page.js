$(document).ready(function () {

    //初期化排序的信息
    initSort();

    $("table thead tr th :checkbox").change(function () {
        var flag = $(this).is(':checked');
        if (flag) {
            $("table tbody tr td input:checkbox").prop("checked", true);
        } else {
            $("table tbody tr td input:checkbox").prop("checked", false);
        }
        changeDelItemBtn();
    });

    $("table tbody tr td :checkbox").change(function () {
        changeDelItemBtn();
    });


    //以下操作，会改变查询结果值，回首页
    $('.pageListKeyWord').change(function () {
        $("#pageNumChange").val("1");
        $("#pageNum").val("1");
    });

    $("table thead tr th").click(function () {
        var clazz = $(this).children("i").attr("class");
        if (clazz === undefined) {
            return;
        }
        var fieldName = $(this).children("i").attr("field-name");
        $("#fieldName").val(fieldName);

        $("#pageNumChange").val("1");
        $("#pageNum").val("1");
        $(this).parent().find("i").attr("class", "fas fa-sort");
        $(this).parent().children("th").attr("class", "text-dark");
        $(this).attr("class", "text-primary");
        if (clazz === 'fas fa-sort' || clazz === 'fas fa-sort-down') {
            $(this).children("i").attr("class", "fas fa-sort-up");
            $("#sortType").val("ASC");
        } else {
            $(this).children("i").attr("class", "fas fa-sort-down");
            $("#sortType").val("DESC");
        }
        $(this).closest("form").submit();
    });
});

//跳转页
function goToPage(pageNum) {
    //查询条件改变标记，跳回首页
    var pageNumChange = $("#pageNumChange").val();
    if (pageNumChange === '1') {
        $("#pageNum").val("1");
    } else {
        $("#pageNum").val(pageNum);
    }
    $("#pageNum").closest("form").submit();
}

//初始化排序的样式及值
function initSort() {
    //默认初始化
    var fieldName = $("#fieldName").val();
    var sortType = $("#sortType").val();
    if (fieldName != undefined && fieldName != null && fieldName != '') {
        var items = $("table thead tr th i");
        for (var i = 0; i < items.length; i++) {
            var item = $(items[i]);
            if (item.attr("field-name") === fieldName) {
                if (sortType === 'DESC') {
                    item.attr("class", "fas fa-sort-down");
                } else {
                    item.attr("class", "fas fa-sort-up");
                }
                item.parent().attr("class", "text-primary");
                break;
            }
        }
    }
}

function deleteConfirm(ids, url) {
    new jBox('Confirm', {
        title: '确认删除',
        cancelButton: '取消',
        confirmButton: '确认',
        content: '你确认要删除？',
        closeOnEsc: true,
        closeOnClick: 'overlay',
        closeButton: 'title',
        animation: {open: 'slide:top', close: 'slide:top'},
        confirm: function () {
            deleteByIds(ids, url);
        }
    }).open();
}

function deleteByIds(ids, url) {
    $.getJSON(url, {ids: ids}, function (data) {
        var message = "删除成功！";
        if (data.msg.code != '0000') {
            message = data.msg.message;
        } else {
            deleteTableTrByIds(ids);
        }
        new jBox('Notice', {
            content: message,
            color: 'red',
            autoClose: '2000'
        });
    });
}

function deleteTableTrByIds(ids) {
    var idss = ids.split(",");
    for (var i = 0; i < idss.length; i++) {
        deleteTableTrById(idss[i]);
    }
    changeDelItemBtn();

};

function deleteTableTrById(id) {
    var items = $("table tbody tr td :checkbox");
    for (var i = 0; i < items.length; i++) {
        var item = $(items[i]);
        if (item.val() == id) {
            item.parent().parent().remove();
            return;
        }
    }
}

function changeDelItemBtn() {
    var total = 0;
    var items = $("table tbody tr td :checkbox");
    for (var i = 0; i < items.length; i++) {
        var item = $(items[i]);
        var flag = item.is(':checked');
        if (flag) {
            total++;
        }
    }
    if (total != 0) {
        $("#delItemBtn").attr("disabled", false);
    } else {
        $("#delItemBtn").attr("disabled", true);
    }

    if (total != items.length || items.length == 0) {
        $("table thead tr th :checkbox").prop("checked", false);
        if (items.length == 0) {
            $("table thead tr th :checkbox").attr("disabled", true);
        }
    } else {
        $("table thead tr th :checkbox").prop("checked", true);
    }
}

function getCheckedIds() {
    var ids = "";
    var items = $("table tbody tr td :checkbox");
    for (var i = 0; i < items.length; i++) {
        var item = $(items[i]);
        var flag = item.is(':checked');
        if (flag) {
            ids = ids + "," + item.val();
        }
    }
    return ids.substr(1, ids.length - 1);
}