//解析url参数的函数，包括解码
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var url = decodeURI(window.location.search);
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

//设置canvas画布大小
var canvas = document.getElementById('canvas');
var content = document.getElementById('content');
window.onload = window.onresize = function () {
    canvas.width = content.offsetWidth;
    canvas.height = content.offsetHeight - 50;
};

var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var scene = new JTopo.Scene(stage); // 创建一个场景对象

//预读
$(document).ready(function () {
    //显示工程名和场景名
    $("#projectName").html($.getUrlParam("projectName"));
    $("#scenarioName").html($.getUrlParam("scenarioName"));
    //画出已有节点
    $.ajax({
        url: '/NetworkSimulation/selectNodeList',
        data: {
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (data) {
            var objs = jQuery.parseJSON(data);
            for (var i = 0; i < objs.length; i++){
                //数据库中未存节点图片，此处还应有判断来判断出节点应是哪个图片。。。气人
                createNode(objs[i].nodeName, objs[i].x, objs[i].y, "img/gaogui00.png");
            }
        },
        error: function () {

        }
    });
});
/**
 * 右上角的选中状态
 */
var selectEle = document.getElementById("selectEle");
scene.click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined) {
        selectEle.innerHTML = "none";
    }
    selectEle.innerHTML = elements[0].text;
});

/**
 * 删除选中元素
 */
$("#remove").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined){
        alert("请选中节点后在进行下一步操作");
    }else {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i] instanceof JTopo.Node) {
                alert("删除一个节点" + elements[i].text);
            }
            if (elements[i] instanceof JTopo.Link) {
                alert("删除一个链路" + elements[i].text);
            }
            scene.remove(elements[i]);
        }
    }
});

/**
 * 创建连线
 */
var beginNode = null;
var endLastNode = null;
var tempNodeA = new JTopo.Node('tempA');
tempNodeA.setSize(1, 1);

var tempNodeZ = new JTopo.Node('tempZ');
tempNodeZ.setSize(1, 1);

var link1 = new JTopo.Link(tempNodeA, tempNodeZ);

//按钮点击事件，添加
var link01 = document.getElementById("link01");
// var addlink02 = document.getElementById("addlink02");
// var link02 = document.getElementById("link02");
// var addlink03 = document.getElementById("addlink03");
// var link03 = document.getElementById("link03");
//停止添加
var link04 = document.getElementById("link04");
var flag = 0;
//1,2,3表示三种链路，4表示停止添加链路--------------------->用于添加链路类型时候的判断
$("#addlink01").click(function () {
    flag = 1;
    link01.style.color = "red";
    link02.style.color = "#333";
    link03.style.color = "#333";
    link04.style.color = "#333";
});
// addlink02.onclick = function () {
//     flag = 2;
//     link01.style.color = "#333";
//     link02.style.color = "red";
//     link03.style.color = "#333";
//     link04.style.color = "#333";
// };
// addlink03.onclick = function () {
//     flag = 3;
//     link01.style.color = "#333";
//     link02.style.color = "#333";
//     link03.style.color = "red";
//     link04.style.color = "#333";
//
// };
$("#addlink04").click(function () {
    flag = 0;
    link01.style.color = "#333";
    link02.style.color = "#333";
    link03.style.color = "#333";
    link04.style.color = "red";
});

scene.mouseup(function (e) {
    if (e.target != null && e.target instanceof JTopo.Node && flag != 0) {
        if (beginNode == null) {
            beginNode = e.target;
            scene.add(link1);
            tempNodeA.setLocation(e.x, e.y);
            tempNodeZ.setLocation(e.x, e.y);
        } else if (beginNode !== e.target) {
            endLastNode = e.target;
            //首先弹出模态框
            $("#linkModal").modal();
            //发送ajax查询from端口
            $.ajax({
                url: '/NetworkSimulation/getPortBynodeName',
                data: {
                    nodeName: beginNode.text,
                    s_id : $.getUrlParam("scenarioId")
                },
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (msg) {
                    alert(msg);
                    initFromPortList(msg);
                },
                error: function () {

                }
            });
            //发送ajax查询to端口
            $.ajax({
                url: '/NetworkSimulation/getPortBynodeName',
                data: {
                    nodeName: endLastNode.text,
                    s_id : $.getUrlParam("scenarioId")
                },
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (msg) {
                    alert(msg);
                    initToPortList(msg);
                },
                error: function () {

                }
            });
        } else {
            beginNode = null;
        }
    } else {
        scene.remove(link1);
    }
});

var fromPortList = [];
var fromPortId = [];
//初始化from端口下拉框
function initFromPortList(data) {
    fromPortList = [];
    fromPortId = [];
    var objs = jQuery.parseJSON(data);
    for (var i = 0; i < objs.length; i++){
        fromPortList[i] = objs[i].portName;
        fromPortId[i] = objs[i].pt_id;
    }
    areaCont = "";
    for (var i = 0; i < fromPortList.length; i++){
        areaCont += '<option value="' + fromPortId[i] + '">' + fromPortList[i] + '</option>';
    }
    $("#fromPort").html(areaCont);
}

var toPortList = [];
var toPortId = [];
//初始化to端口下拉框
function initToPortList(data) {
    toPortList = [];
    toPortId = [];
    var objs = jQuery.parseJSON(data);
    for (var i = 0; i < objs.length; i++){
        toPortList[i] = objs[i].portName;
        toPortId[i] = objs[i].pt_id;
    }
    areaCont = "";
    for (var i = 0; i < toPortList.length; i++){
        areaCont += '<option value="' + toPortId[i] + '">' + toPortList[i] + '</option>';
    }
    $("#toPort").html(areaCont);
}

scene.mousedown(function (e) {
    if (e.target == null || e.target === beginNode || e.target === link1) {
        scene.remove(link1);
    }
});

scene.mousemove(function (e) {
    tempNodeZ.setLocation(e.x, e.y);
});

//创建节点函数
function createNode(name, X, Y, pic) {
    var node = new JTopo.Node(name);
    node.setLocation(X, Y);
    node.fontColor = "0,0,0";
    node.setImage(pic, true);
    scene.add(node);
}

//创建连线函数
function newLink(nodeA, nodeZ, text, color) {
    var link = new JTopo.Link(nodeA, nodeZ, text);
    link.fontColor = "0,0,0";
    link.lineWidth = 3; // 线宽
    //link.dashedPattern = dashedPattern; // 虚线
    link.bundleOffset = 60; // 折线拐角处的长度
    link.bundleGap = 20; // 线条之间的间隔
    link.textOffsetY = 3; // 文本偏移量（向下3个像素）
    link.strokeColor = color;
    link.arrowsRadius = 10;
    scene.add(link);
    return link;
}

$("#weixing1").draggable({
    helper: "clone"
});
$("#weixing2").draggable({
    helper: "clone"
});
$("#weixing3").draggable({
    helper: "clone"
});
$("#weixing4").draggable({
    helper: "clone"
});

var uiOut;//全局数据-->用于传递变量-->将拖动的数据信息保存起来

$("#canvas").droppable({
    drop: function (event, ui) {
        uiOut = ui;//保存数据
        //首先弹出模态框
        $("#myModal").modal();
    }
});

var weixingName = document.getElementById("nodeName");
//创建节点模态框中的提交
$("#addNode").click(function () {
    //发送执行ajax的请求
    $.ajax({
        url: '/NetworkSimulation/addNode',
        data: {
            nodeName : $("#nodeName").val(),
            manageIp : $("#manageIP").val(),
            nodeType : $("#nodeType").val(),
            hardwareArchitecture : $("#hardwareArchitecture").val(),
            operatingSystem : $("#operatingSystem").val(),
            flavorType : $("#nodeConfig").val(),
            imageName : $("#nodeImage").val(),
            x : uiOut.offset.left - document.getElementById("slider").offsetWidth,
            y : uiOut.offset.top - 102,
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
        },
        error: function () {

        }
    });
    //在画布上绘制出节点图标
    var getId = uiOut.draggable[0].id;//jquery获取图片，竟然要加一个[0]，这是什么鬼 (⊙o⊙)
    if (getId == "weixing1") {
        createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider").offsetWidth, uiOut.offset.top - 102, "img/gaogui00.png");
    } else if (getId == "weixing2") {
        createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider").offsetWidth, uiOut.offset.top - 102, "img/zhonggui00.png");
    } else if (getId == "weixing3") {
        createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider").offsetWidth, uiOut.offset.top - 102, "img/digui00.png");
    } else if (getId == "weixing4") {
        createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider").offsetWidth, uiOut.offset.top - 102, "img/leida00.png");
    }
    //关闭模态框
    $('#myModal').modal('hide');
});

//链路模态框中的数据
var fromNodeIP = document.getElementById("fromNodeIP");
var toNodeIP = document.getElementById("toNodeIP");

//链路模态框
$("#addLink").click(function () {
    //发送执行ajax的请求
    $.ajax({
        url: '/NetworkSimulation/addLink',
        data: {
            linkName : $("#linkName").val(),
            linkType : $("#linkType").val(),
            fromNodeIP : $("#fromNodeIP").val(),
            toNodeIP : $("#toNodeIP").val(),
            txPort_id : $("#fromPort").val(),
            rxPort_id : $("#toPort").val(),
            linkNoise : $("#channelNoise").val(),
            linkInterference : $("#channelDisturbance").val(),
            channelModel : $("#channelType").val(),
            linkLength : $("#linkLenth").val(),
            scenario_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
        },
        error: function () {

        }
    });
    //在画布上绘制出链路
    if ($("#linkType").val() == 0) {//有线链路
        newLink(beginNode, endLastNode, beginNode.text + ":" + fromNodeIP.value + " -> " + endLastNode.text + ":" + toNodeIP.value, "0,0,255");
    } else if ($("#linkType").val() == 1) {//无线链路
        newLink(beginNode, endLastNode, "链路2", "0,0,0");
    }
    beginNode = null;
    scene.remove(link1);
    //关闭模态框
    $('#linkModal').modal('hide');
});

//打开内部编辑器
$("#openInnerEdit").click = function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined) {
       alert("请选中节点后在进行下一步操作");
    }else{
        window.open("innerEdit.html?nodeName="+ elements[0].text);
    }
};

//打开节点编辑器
$("#editNode").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined){
        alert("请选中节点后在进行下一步操作");
    }else {
        window.open(encodeURI("nodeEdit.html?nodeName=" + elements[0].text + "&scenarioId=" + $.getUrlParam("scenarioId")));
    }
});