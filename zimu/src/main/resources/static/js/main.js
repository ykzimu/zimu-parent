/*!
 * Date 2017-11-16
 * Author: 杨坤
 */


//全局退出确认框
var logOutModel = null;
//全局消息提示框
var jBoxMsgModel = null;

//全局遮罩(boot)
var globalSpinnerModal = {
    show: function () {
        $("#spinnerModal").show();
        $(".modal-backdrop").show();
        $("#spinnerModal").modal({
            backdrop: 'static',
            keyboard: false
        });
    },
    hide: function () {
        $("#spinnerModal").modal("hide");
        $("#spinnerModal").hide();
        $(".modal-backdrop").hide();
    }
};

/**
 * 默认配置
 */
$(document).ready(function () {

    $('#navMenu').metisMenu();

    $("#personMenu").hover(function () {
        $(this).children("div").addClass("show");
    }, function () {
        $(this).children("div").removeClass("show");
    });

    // 退出确认框
    logOutModel = new jBox('Confirm', {
        id: "jBoxLogoutModel",
        title: '确认退出',
        cancelButton: '取消',
        confirmButton: '确认',
        content: '你确定要退出？',
        attach: '#btnLogOut',
        closeOnEsc: true,
        closeOnClick: 'overlay',
        closeButton: 'title',
        animation: {open: 'slide:top', close: 'slide:top'},
    });

    //全局消息提示框
    jBoxMsgModel = new jBox('Modal',
        {
            id: "jBoxMsgModel",
            title: '消息提示',
            content: '',
            closeOnEsc: true,
            closeButton: 'title',
            minWidth: '360px',
            maxWidth: '500px',
            animation: {open: 'slide:top', close: 'slide:top'},
            onOpen: (function () {
                globalSpinnerModal.hide();
            }),
            onClose: (function () {
                globalSpinnerModal.hide();
            })
        });
});

function childAddTab(item) {
    var dataHref = $(item).attr("data-href");
    if (dataHref == null || dataHref === '' || dataHref === 'javascript:void(0)') {
        return;
    }
    var title = $(item).attr("title");
    var dataTabId = $(item).attr("data-tab-id");
    window.parent.document.title = title;
    window.parent.addTab(title, dataTabId, dataHref);
    window.parent.history.replaceState("", "", "#" + dataHref);//添加路由
}

function childKillTab(url) {
    window.parent.killTabByUrl(url);
}