var tabpanel;
$(document).ready(function () {

    tabpanel = new TabPanel({
        renderTo: 'mainTab',
        //border:'none',
        active: 0,
        //maxLength : 10,
        items: [
            {id: 'tabpanel-home', title: '首页', html: '首页', closable: false}
        ]
    });

    $("nav li a").click(function () {
        var dataHref = $(this).attr("data-href");
        if (dataHref == null || dataHref === '' || dataHref === 'javascript:void(0)') {
            return;
        }
        addTabPanel($(this));
        window.history.pushState("","","#"+dataHref);//添加路由
    })


});

function addTabPanel(item) {
    var title = item.attr("title");
    var dataTabId=item.attr("data-tab-id");
    var dataHref = item.attr("data-href");
    var html='<iframe id="'+dataTabId+'-frame" src="'+dataHref+'" width="100%" height="100%" frameborder="0"></iframe>';
    var tabitem={id: dataTabId, title: title, html: html, closable: true};
    tabpanel.addTab(tabitem);
}