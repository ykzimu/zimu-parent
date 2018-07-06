<#assign ctx=request.getContextPath()>
<#assign ctxStatic=request.getContextPath()+"/static">

<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#btn1").click(function () {
                logOutModel.open();
            });
            $("#btn2").click(function () {
                globalMsgModel.open();
            });
        });


    </script>
</head>
<body>
<form class="form-horizontal">
    <div class="container bg-light">
        <div class="row  justify-content-center">
            <div id="demo" class="col-sm-12 carousel slide" data-ride="carousel">

                <!-- 指示符 -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                    <li data-target="#demo" data-slide-to="3"></li>
                </ul>

                <!-- 轮播图片 -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="http://static.runoob.com/images/mix/img_fjords_wide.jpg">
                        <div class="carousel-caption">
                            <p>描述文字!</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="http://static.runoob.com/images/mix/img_nature_wide.jpg">
                        <div class="carousel-caption">
                            <p>描述文字!</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="http://static.runoob.com/images/mix/img_mountains_wide.jpg">
                        <div class="carousel-caption">
                            <p class="text-danger">描述文字!</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="http://static.runoob.com/images/mix/img_mountains_wide.jpg">
                        <div class="carousel-caption">
                            <p class="text-danger">描述文字!</p>
                        </div>
                    </div>
                </div>

                <!-- 左右切换按钮 -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>

            </div>
        </div>
        <div class="row ">
            <label class="col-sm-2 col-form-label">&nbsp;</label>
        </div>
    </div>
</form>
</body>
</html>
