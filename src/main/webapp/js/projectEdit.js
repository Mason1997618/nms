/**
 * Created by sjm on 2017/6/8.
 */
//新建场景
$("#add").click(function () {
   $("#myModal").modal();
});

//编辑场景
$("#editScenario").click(function () {
   window.open("index3.html?scenarioName=" + $("#selectScenario").val());
});

//预读
$(document).ready(function(){
    $.ajax({
        url: '/NetworkSimulation/selectScenarioList',
        data: {
            p_id : $.getUrlParam("projectId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
           // alert(msg);
        },
        error: function () {

        }
    });

    initScenarioList();
});

var scenarioList = ["场景1","场景2","场景3","场景4","场景5"];

function initScenarioList() {
    areaCont = "";
    for (var i = 0; i < scenarioList.length; i++){
        areaCont += '<option onClick="selectP(' + i + ');">' + scenarioList[i] + '</option>';
    }
    $("#selectScenario").html(areaCont);
}

function selectP(i) {
    $("#editScenario").removeAttr("disabled");
    $("#delScenatio").removeAttr("disabled");
}

//编辑工程属性提交
$("#editProject").click(function () {
    $.ajax({
        url: 'project/editProject',
        data: {
            projectId : $.getUrlParam("projectId"),
            projectName : $("#projectName").val()
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
            //刷新当前页面
            window.location.reload();
            // opener.location.reload()刷新父窗口对象（用于单开窗口）
        },
        error: function () {

        }
    });
});

//添加场景提交
$("#addScenario").click(function () {
    $.ajax({
        url: '/NetworkSimulation/createScenario',
        data: {
            scenarioName : $("#scenarioName").val(),
            scenarioType : $("#scenarioType").val(),
            p_id : $.getUrlParam("projectId")
        },
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
            //刷新当前页面
            window.location.reload();
            // opener.location.reload()刷新父窗口对象（用于单开窗口）
        },
        error: function () {

        }
    });
});

//用场景名删除场景
$("#delScenatio").click(function () {
    $.ajax({
        url: 'project/delScenario',
        data: {
            projectId : $.getUrlParam("projectId"),
            scenarioName : $("#selectScenario").val()
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
            //刷新当前页面
            window.location.reload();
            // opener.location.reload()刷新父窗口对象（用于单开窗口）
        },
        error: function () {

        }
    });
});

//解析url参数的函数
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);