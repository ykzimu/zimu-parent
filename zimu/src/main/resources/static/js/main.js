/*!
 * Date 2017-11-16
 * Author: 杨坤
 */

//全局消息提示框
var jBoxMsgModel = null;
//工程名
var contextPath = null;

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

    if (contextPath == null) {
        var metaContextPath = $("meta[name='context-path']");
        if (metaContextPath == null || metaContextPath.attr("content") == null || metaContextPath.attr("content") == undefined) {
            contextPath = "";
        } else {
            contextPath = metaContextPath.attr("content");
        }
    }

    if (jBoxMsgModel == null) {
        if (window.parent != null && window.parent.jBoxMsgModel != null) {
            jBoxMsgModel = window.parent.jBoxMsgModel;
        } else {
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
                    }),
                    onCloseComplete: (function () {
                        this.setTitle('消息提示').setContent('请使用jBoxMsgModel.setContent("xxxxx")方法设置内容');
                    })
                });
        }
    }
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