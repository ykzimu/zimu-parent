/**
 * error.js
 */

$(document).ready(function () {
    var utilTime = new Date().getTime() + (10 * 1000);
    countdown(new Date(utilTime));
});

function countdown(untilDateTime) {
    $("#countSpan").countdown(untilDateTime, function (event) {
        var cntSec = event.strftime('%S');
        var iSec = parseInt(cntSec);
        $(this).html(iSec);
        if (0 == iSec) {
            window.location.href = $("#ctx").val() + "/home";
        }
    });
}