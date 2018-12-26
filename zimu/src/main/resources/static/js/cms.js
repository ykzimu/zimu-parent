$(document).ready(function () {

    $('#leftMenu').metisMenu({toggle: false});

    $("#leftMenu a").click(function () {
        var href = $(this).attr("href");
        var clazz = $(this).attr("class");
        if (href != '' && href != 'javascript:void(0)' && clazz.indexOf("has-arrow") == -1) {
            globalSpinnerModal.show();
        }
    });

});

function changeToActive(id) {
    var item = "#leftMenu" + id;
    $(item).parent().addClass("active");
    $(item).attr("aria-expanded", true);
    var nextUl = $(item).next("ul");
    nextUl.attr("aria-expanded", true);
    nextUl.removeClass("collapse");
    nextUl.addClass("collapse in");
    nextUl.attr("style", "");
}