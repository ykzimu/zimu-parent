var tabpanel;
var search = "";
var initFlag = false;
$(document).ready(function () {

    $(window).resize(function () {
        zimuOnload(null);
    });

    tabpanel = new TabPanel({
        renderTo: 'mainTab',
        //border:'none',
        active: 0,
        autoResizable: true,
        scrolled: true,
        //maxLength : 10,
        items: [
            {id: 'tabpanel-home', title: '首页', html: '首页', closable: false}
        ]
    });

    $("nav li a").click(function () {
        var dataHref = $(this).attr("data-href");
        if (dataHref == null || dataHref === '' || dataHref === 'javascript:void(0)') {
            initFlag = false;
            return;
        }

        if (initFlag) {
            var hash = location.hash;
            if (hash != null && hash !== '') {
                var w = hash.indexOf('?');
                if (w !== -1) {
                    dataHref = dataHref + hash.substring(w);
                }
            }
        }

        var title = $(this).attr("title");
        $("title").html(title);
        var dataTabId = $(this).attr("data-tab-id");
        var html = '<iframe id="' + dataTabId + 'Frame" src="' + dataHref + '" width="100%" height="100%" frameborder="0" onload="zimuOnload(this)"></iframe>';
        var tabitem = {id: dataTabId, title: title, html: html, closable: true};
        tabpanel.addTab(tabitem);
        window.history.pushState("", "", "#" + dataHref);//添加路由
        initFlag = false;

    });

    var hash = location.hash;
    if (hash != null && hash !== '') {
        var w = hash.indexOf('?');
        if (w !== -1) {
            hash = hash.substring(1, w);
        } else {
            hash = hash.substring(1);
        }

        var a = $("[data-href='" + hash + "']");
        if (a != null && a.length > 0) {
            initFlag = true;
            $(a[0]).click();
        }
    }

    $("#fullScreen").click(function () {
        if (screenfull.enabled) {
            screenfull.toggle();
        }
    });

    $("#testBtn").click(function () {
        var doc = document.getElementById("tabpanel-12Frame");
        zimuOnload(doc)
    });


    $.contextMenu({
        // define which elements trigger this menu
        selector: "#mainTab ul li",
        // define the elements of the menu
        zIndex: 1000000,
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
    tabpanel.show(posision, true);
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

function zimuOnload(item) {
    var htmlH = $("html").height();
    var headerH = $(".wrapper .main-header").outerHeight();

    var minH= $("#mainContent").css("min-height");
    if(minH=='0px'){
        $("#mainContent").height(htmlH - headerH);
        tabpanel.resize();
    }else {
       // $("#mainContent").height(minH);
    }
    tabpanel.resize();

}
