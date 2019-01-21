/**
 * error.js
 */

$(document).ready(function () {
    var utilTime = new Date().getTime() + (10 * 1000);
    countdown(new Date(utilTime));

    $("#closePageBtn").click(function () {
        var url = GetUrlRelativePathAndParam();
        childKillTab(url);
    })
});

function countdown(untilDateTime) {
    $("#countSpan").countdown(untilDateTime, function (event) {
        var cntSec = event.strftime('%S');
        var iSec = parseInt(cntSec);
        $(this).html(iSec);
        if (0 === iSec) {
            var url = GetUrlRelativePathAndParam();
            childKillTab(url);
        }
    });
}


function GetUrlRelativePathAndParam() {
    var url = document.location.toString();
    var arrUrl = url.split("//");
    var start = arrUrl[1].indexOf("/");
    var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符
    return relUrl;
}

