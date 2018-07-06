$(document).ready(function () {
    //批量删除
    $("#delItemBtn").click(function () {
        var ids = getCheckedIds();
        deleteUser(ids);
    });
});

function deleteUser(id) {
    var url = $("#ctx").val() + "/admin/user/delete";
    deleteConfirm(id, url);
}

