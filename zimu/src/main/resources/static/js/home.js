var tabpanel;
var search = "";
var initFlag = false;
$(document).ready(function () {

    tabpanel = new TabPanel({
        renderTo: 'mainTab',
        //border:'none',
        active: 0,
        autoResizable:true,
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
        var html = '<iframe id="' + dataTabId + '-frame" src="' + dataHref + '" width="100%" height="100%" frameborder="0"></iframe>';
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


});
