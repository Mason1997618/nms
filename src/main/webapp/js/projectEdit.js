/**
 * Created by sjm on 2017/6/8.
 */
var scenarioList = [];
var scenarioId = [];

//解析场景对象列表的json
function praseScenarioList(data) {
    scenarioList = [];
    scenarioId = [];
    var objs = jQuery.parseJSON(data);
    for (var i = 0; i < objs.length; i++){
        scenarioList[i] = objs[i].scenarioName;
        scenarioId[i] = objs[i].s_id;
    }
}

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
    initProject();
    $.ajax({
        url: '/NetworkSimulation/selectScenarioList',
        data: {
            p_id : $.getUrlParam("projectId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
          //  alert(msg);
            praseScenarioList(msg);
        },
        error: function () {

        }
    });
    initScenarioList();
});

//初始化工程属性
function initProject() {
    $("#projectName").val($.getUrlParam("projectName"));
    $("#projectId").val($.getUrlParam("projectId"));
}

//初始化场景列表
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
    //打开场景编辑器
    $("#editScenario").click(function () {
        window.open("index3.html?scenarioId=" + scenarioId[i]);
    });
    //删除场景
    $.ajax({
        url: 'project/deleteScenario',
        data: {
            scenarioId : scenarioId[i]
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
            //刷新当前页面
            //window.location.reload();
            // opener.location.reload()刷新父窗口对象（用于单开窗口）
        },
        error: function () {

        }
    });
}

//编辑工程属性提交
$("#editProject").click(function () {
    $.ajax({
        url: '/NetworkSimulation/editProject',
        data: {
            p_id : $.getUrlParam("projectId"),
            projectName : $("#projectName").val()
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
            //刷新当前页面
            location.href(encodeURI("projectEdit.html?projectId=" + $.getUrlParam("p_id") + "&projectName=" + $("#projectName").val()));
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
           // alert(msg);
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
            projectId : $.getUrlParam("p_id"),
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

//解析url参数的函数，包括解码
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var url = decodeURI(window.location.search);
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);