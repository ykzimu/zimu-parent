$(document).ready(function () {

    //默认下拉框使用select2
    $("#menuHref").select2({
        placeholder: "--请选择或者输入--",
        tags: true,
        language: "zh-CN",  //设置 提示语言
        width: "100%"//设置下拉框的宽度
    });

    //保存菜单排序
    new jBox('Confirm', {
        title: '确认保存',
        cancelButton: '取消',
        confirmButton: '确认',
        content: '你确认要保存？',
        //attach: '#btnConfirm',
        closeOnEsc: true,
        closeOnClick: 'overlay',
        closeButton: 'title',
        animation: {open: 'slide:top', close: 'slide:top'}
    });
});


function editMenu() {
    $("form").submit();
}
