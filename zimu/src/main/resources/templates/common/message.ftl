<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<#--<!-- Modal &ndash;&gt;
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">确认退出</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">你确定要退出？</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-info">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bd-example-modal-sm" id="mySmallModal" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
        <div class="modal-content ">
            <div class="modal-header">
                <h5 class="modal-title" id="mySmallModalLabel">消息提示</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                请使用 globalMsgModel.setContent("内容")设置内容使用 globalMsgModel.setContent("内容")设置内容使用
                globalMsgModel.setContent("内容")设置内容使用 globalMsgModel.setContent("内容")设置内容使用
                globalMsgModel.setContent("内容")设置内容
            </div>
        </div>
    </div>
</div>-->

<!-- Modal -->
<div class="modal fade" id="spinnerModal" tabindex="-1" role="dialog" aria-labelledby="spinnerModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered justify-content-center" role="document">
        <div class="text-light">
            <i class="fa fa-spinner fa-pulse fa-4x fa-fw" aria-hidden="true"></i>
        </div>
    </div>
</div>