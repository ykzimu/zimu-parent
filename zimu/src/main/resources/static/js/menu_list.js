$(document).ready(function () {

    $("#addBtn").click(function () {
        childAddTab(this);
    });


    //点击左侧菜单
    $("table tbody tr td:first-child a").click(function () {
        var clazz = $(this).children("i").attr("class");
        var cnt = parseInt($(this).attr("child-deep"));
        if (clazz.indexOf("fa-angle-down") != -1) {
            $(this).children("i").removeClass("fa-angle-down");
            $(this).children("i").addClass("fa-angle-right");
            hideTr($(this).parent().parent(), cnt);
        } else {
            $(this).children("i").removeClass("fa-angle-right");
            $(this).children("i").addClass("fa-angle-down");
            showIcon($(this).parent().parent(), cnt);
            showTr($(this).parent().parent(), cnt);
        }
    });

    //保存菜单排序
    new jBox('Confirm', {
        title: '确认保存',
        cancelButton: '取消',
        confirmButton: '确认',
        content: '你确认要保存？',
        attach: '#btnSave',
        closeOnEsc: true,
        closeOnClick: 'overlay',
        closeButton: 'title',
        animation: {open: 'slide:top', close: 'slide:top'}
    });

    //切换显示状态
    new jBox('Confirm', {
        title: '确认删除',
        cancelButton: '取消',
        confirmButton: '确认',
        content: '你确认要删除？',
        attach: 'a[name="btnDelete"]',
        closeOnEsc: true,
        closeOnClick: 'overlay',
        closeButton: 'title',
        animation: {open: 'slide:top', close: 'slide:top'}
    });

});

function saveSort() {
    var value = "";
    var items = $("table tbody tr td:nth-child(7) :hidden");
    for (var i = 0; i < items.length; i++) {

        //隐藏域不处理
        var flag = $(items[i]).parents("tr").is(":hidden");
        if (flag) {
            continue;
        }
        var id = $(items[i]).val();
        var sort = $(items[i]).next().val();
        value = value + id + ":" + sort + ",";
    }
    if (value == '') {
        return;
    }

    //加载动画
    globalSpinnerModal.show();

    //排序
    var url = $("#ctx").val() + "/admin/menu/sort";
    $.post(url, {value: value}, function (data) {

        var message = "保存成功！";
        if (data.msg.code != '0000') {
            message = data.msg.message;
        }
        message = message + "刷新页面可查看。";
        jBoxMsgModel.setContent(message);
        jBoxMsgModel.open();
    }, "json");

}

function hideTr(item, cnt) {
    for (var i = 0; i < cnt; i++) {
        item = item.next();
    }
    for (var i = 0; i < cnt; i++) {
        item.hide();
        item = item.prev();
    }
}

function showTr(item, cnt) {
    for (var i = 0; i < cnt; i++) {
        item = item.next().show();
    }
}

function showIcon(item, cnt) {
    for (var i = 0; i < cnt; i++) {
        item = item.next();
        item.find("td a i").removeClass("fa-angle-right");
        item.find("td a i").addClass("fa-angle-down");
    }
}

function changeMenuStatus(id, delFlag, isShow) {
    //加载动画
    globalSpinnerModal.show();
    //排序
    var url = $("#ctx").val() + "/admin/menu/change";
    $.post(url, {id: id, delFlag: delFlag, isShow: isShow}, function (data) {
        var message = "操作成功！";
        if (data.msg.code == '0000') {
            chageIcon(id, delFlag, isShow);
        }
        jBoxMsgModel.setContent(message);
        jBoxMsgModel.open();
    }, "json");
}

function chageIcon(id, delFlag, isShow) {
    var item = $("#idHidden" + id);
    if (isShow != null) {
        item = item.next();
        var clazz = item.attr("class");
        if ("text-success" == clazz.trim()) {
            item.removeClass("text-success");
            item.addClass("text-danger");
            item.attr("title", "隐藏");
            item.children("i").removeClass("fa-eye");
            item.children("i").addClass("fa-eye-slash");
        } else {
            item.removeClass("text-danger");
            item.addClass("text-success");
            item.attr("title", "显示");
            item.children("i").removeClass("fa-eye-slash");
            item.children("i").addClass("fa-eye");
        }
    }

    if (delFlag != null) {
        item = item.next().next();
        var clazz = item.attr("class");
        if ("text-success" == clazz.trim()) {
            item.removeClass("text-success");
            item.addClass("text-danger");
            item.attr("title", "删除");
            item.children("i").removeClass("fa-play-circle");
            item.children("i").addClass("fa-trash-alt");
        } else {
            item.removeClass("text-danger");
            item.addClass("text-success");
            item.attr("title", "启用");
            item.children("i").removeClass("fa-trash-alt");
            item.children("i").addClass("fa-play-circle");
        }
    }
}