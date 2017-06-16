<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <title>项目详细信息</title>
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="css/detailProject.css">
</head>
<body>
<!--头部区域-->
<header>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="javascript:;">网络仿真测试平台</a>
        </div>
    </nav>
</header>

<div class="contains">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <!--面包屑导航-->
            <div class="crumbNav">
                <a href="#">首页</a>
                <font>&gt;</font>
                选择
            </div>
            <!--商品分类-->
            <div class="wareSort clearfix">
                <ul id="sort1"></ul>
                <ul id="sort2" style="display: none;"></ul>
                <ul id="sort3" style="display: none;"></ul>
            </div>
            <div class="selectedSort"><b>您当前选择的类别是：</b><i id="selectedSort"></i></div>
            <div class="wareSortBtn">
                <input id="releaseBtn" type="button" class="btn btn-default" value="进入编辑器" disabled="disabled" />
                <input id="addProject" type="button" class="btn btn-default" value="新建工程"/>
                <input id="" type="button" class="btn btn-danger" value="删除"/>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <!--<div class="panel panel-info">-->
                <!--<div class="panel-heading">-->
                    <!--<h3 class="panel-title">工程属性</h3>-->
                <!--</div>-->
                <!--<div class="panel-body">-->
                    <!--<form class="form-horizontal" role="form">-->
                        <!--<div class="form-group">-->
                            <!--<label class="col-sm-2 control-label">工程名</label>-->
                            <!--<div class="col-sm-10">-->
                                <!--<input type="text" class="form-control"  placeholder="工程1" id="projectName">-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--<div class="form-group">-->
                            <!--<label class="col-sm-2 control-label">工程编号</label>-->
                            <!--<div class="col-sm-10">-->
                                <!--<input type="text" class="form-control"  placeholder="0~65535" id="projectID" disabled>-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--<div class="form-group">-->
                            <!--<label class="col-sm-2 control-label">工程类型</label>-->
                            <!--<div class="col-sm-10">-->
                                <!--<select class="form-control">-->
                                    <!--<option value="0">xxx</option>-->
                                    <!--<option value="1">XXX</option>-->
                                <!--</select>-->
                            <!--</div>-->
                        <!--</div>-->
                    <!--</form>-->
                <!--</div>-->
            <!--</div>-->
            <!--<button type="button" class="btn btn-primary ">新建</button>-->
            <!--<button type="button" class="btn btn-info ">编辑</button>-->
            <!--<button type="button" class="btn btn-danger ">删除</button>-->
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">新建工程</h4>
            </div>
        <form action="${pagecontext.request.getcontextpath}/NetworkSimulation/creatProject.action" class="form-horizontal" role="form">
            <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">工程名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="projectName" placeholder="请输入工程名" id="weixingName">
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="sumbit" class="btn btn-primary" id="addNode">提交</button>
            </div>
         </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript" src="lib/jquery/jquery.js"></script>
<script type="text/javascript" src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/detailProject.js"></script>
</body>
</html>