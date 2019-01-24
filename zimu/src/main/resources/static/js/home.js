var tabpanel;
var search = "";
var initFlag = false;
$(document).ready(function () {

    /**
     * 请勿删除，与jq.resize.js结合使用
     */
    $("aside").resize(function () {

    });

    initFlag = true;

    //默认首页
    var dashboardTitle = '仪表盘';
    var dashboardDataTabId = 'tabpanel-25';
    var dashboardDataHref = contextPath + '/admin/dashboard/index';
    var dashboardHtml = '<iframe id="' + dashboardDataTabId + 'Frame" src="' + dashboardDataHref + '" width="100%" height="100%" frameborder="0" onload="iframeOnload(null)"></iframe>';
    tabpanel = new TabPanel({
        renderTo: 'mainTab',
        //border:'none',
        active: 0,
        autoResizable: true,
        scrolled: true,
        //maxLength : 10,
        items: [
            {id: dashboardDataTabId, title: dashboardTitle, html: dashboardHtml, closable: false}
        ]
    });

    $("nav li a").click(function () {
        var dataHref = $(this).attr("data-href");
        if (dataHref == null || dataHref === '' || dataHref === 'javascript:void(0)') {
            return;
        }
        var title = $(this).attr("title");
        var dataTabId = $(this).attr("data-tab-id");
        addTab(title, dataTabId, dataHref);
    });

    initWithHash();

    $("#fullScreen").click(function () {
        if (screenfull.enabled) {
            screenfull.toggle();
        }
    });

    $.contextMenu({
        // define which elements trigger this menu
        selector: "#mainTab ul li",
        // define the elements of the menu
        zIndex: 1000000,
        height:'100%',
        items: {
            refresh: {
                name: "刷新标签", callback: function (e, currentMenuData, key) {
                    var id = key.$trigger[0].id;
                    refresh(id);
                }
            },
            closeOther: {
                name: "关闭其他", callback: function (e, currentMenuData, key) {
                    var id = key.$trigger[0].id;
                    closeOther(id);
                }
            },
            closeLeft: {
                name: "关闭左侧", callback: function (e, currentMenuData, key) {
                    var id = key.$trigger[0].id;
                    closeLeft(id);
                }
            },
            closeRight: {
                name: "关闭右侧", callback: function (e, currentMenuData, key) {
                    var id = key.$trigger[0].id;
                    closeRight(id);
                }
            }
            , closeAll: {
                name: "关闭全部", callback: function (e, currentMenuData, key) {
                    closeAll();
                }
            }
        }
        // there's more, have a look at the demos and docs...
    });

});

/**
 *  刷新标签
 * @param id
 */
function refresh(id) {
    var posision = tabpanel.getTabPosision(id);
    tabpanel.refresh(posision);
    tabpanel.show(posision, false);
}

/**
 * 关闭其他标签
 * @param id
 */
function closeOther(id) {
    var posision = tabpanel.getTabPosision(id);
    var tabsCount = tabpanel.getTabsCount();
    for (var i = tabsCount - 1; i >= 0; i--) {
        if (i !== posision) {
            var closable = tabpanel.getClosable(i);
            if (closable) {
                tabpanel.kill(i);
            }
        }
    }
}

/**
 * 关闭左侧标签
 * @param id
 */
function closeLeft(id) {
    var posision = tabpanel.getTabPosision(id);
    for (var i = posision - 1; i >= 0; i--) {
        var closable = tabpanel.getClosable(i);
        if (closable) {
            tabpanel.kill(i);
        }
    }
}

/**
 * 关闭右侧标签
 * @param id
 */
function closeRight(id) {
    var posision = tabpanel.getTabPosision(id);
    var tabsCount = tabpanel.getTabsCount();
    for (var i = tabsCount - 1; i > posision; i--) {
        var closable = tabpanel.getClosable(i);
        if (closable) {
            tabpanel.kill(i);
        }
    }
}

/**
 * 关闭全部标签
 */
function closeAll() {
    var tabsCount = tabpanel.getTabsCount();
    for (var i = tabsCount - 1; i >= 0; i--) {
        var closable = tabpanel.getClosable(i);
        if (closable) {
            tabpanel.kill(i);
        }
    }
}

/**
 * 首次加载iframe时会触发此方法
 * @param item
 */
function iframeOnload(item) {
    var htmlH = $("html").height();
    var headerH = $(".wrapper .main-header").outerHeight();
    var minH = $("#mainContent").css("min-height");
    if (minH === '0px') {
        $("#mainContent").height(htmlH - headerH);
        tabpanel.resize();
    }
}

/**
 * 首次进入时，带有hash
 */
function initWithHash() {
    var hash = location.hash;
    if (hash != null && hash !== '' && hash !== '#') {
        var w = hash.indexOf('?');
        if (w !== -1) {
            hash = hash.substring(1, w);
        } else {
            hash = hash.substring(1);
        }
        hash = hash.split("#")[0];
        var a = $("[data-href='" + hash + "']");
        if (a != null && a.length > 0) {
            $(a[0]).click();
        }
    }
    initFlag = false;
}

function addTab(title, dataTabId, dataHref) {
    var html = '<iframe id="' + dataTabId + 'Frame" src="' + dataHref + '" width="100%" height="100%" frameborder="0" onload="iframeOnload(null)"></iframe>';
    var tabitem = {id: dataTabId, title: title, html: html, closable: true};
    tabpanel.addTab(tabitem);
}

function killTabByUrl(url) {

    var id = $("iframe[src='" + url + "']").attr("id");
    if (id != null) {
        var len = id.lastIndexOf("Frame");
        var tabId = id.substring(0, len);
        var posision = tabpanel.getTabPosision(tabId);
        if (posision != null) {
            tabpanel.kill(posision);
        }
    }

}

function showIndex(posision) {
    tabpanel.show(posision, false);
}