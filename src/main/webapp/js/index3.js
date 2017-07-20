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

// 定义获取和更新时间的函数
function showTime() {
    var curTime = new Date();
    $("#systemTime").html(curTime.toLocaleString());
    setTimeout("showTime()", 1000);
}

//预读
$(document).ready(function () {
    showTime();
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
                //数据库中未存节点图片，此处还应有判断来判断出节点应是哪个图片。。。
                createNode(objs[i].nodeName, objs[i].x, objs[i].y, "img/gaogui00.png");
            }
        },
        error: function () {

        }
    });
    //获取所有链路并画出
    $.ajax({
        url: '/NetworkSimulation/getLinkList',
        data: {
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (data) {
        	setTimeout(function () {
        		var fromNode = undefined;
                var toNode = undefined;
                //解析出来link对象
                var objs = jQuery.parseJSON(data);
                //获取画布上所有node对象
                var elements = scene.getDisplayedNodes();
                for (var i = 0; i < objs.length; i++) {
                    //对每个link对象找到fromNode和toNode
                    for (var j = 0; j < elements.length; j++) {
                        if (objs[i].fromNodeName == elements[j].text) {
                            fromNode = elements[j];
                        }
                        if (objs[i].toNodeName == elements[j].text) {
                            toNode = elements[j];
                        }
                    }              
                    //画出链路
                    if (objs[i].linkStatus == 1 ) {
                        //断开的链路，红色
                        newLink(fromNode, toNode, objs[i].linkName, "255,0,0");
                    }
                    if (objs[i].linkStatus == 0) {
                        //接通的链路，蓝色
                        newLink(fromNode, toNode, objs[i].linkName, "0,0,255");
                    }
                }
        	}, 1000);
        },
        error: function () {

        }
    });
});

/**
 * 右上角的选中状态
 */
scene.click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined) {
        $("#selectEle").html("none");
    }
    $("#selectEle").html(elements[0].text);
});

/**
 * 删除选中元素
 */
$("#remove").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined){
        $.alert("请选中节点后在进行下一步操作");
    }else {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i] instanceof JTopo.Node) {
                $.alert("删除一个节点" + elements[i].text);
                //从画布删除节点
                $.ajax({
                    url: '/NetworkSimulation/deleteNode',
                    data: {
                        nodeName: elements[i].text,
                        s_id : $.getUrlParam("scenarioId")
                    },
                    type: 'post',
                    dataType: 'json',
                    async: false,
                    success: function (msg) {
                        if (msg == "删除成功") {
                            scene.remove(elements[i]);
                        }
                    },
                    error: function () {

                    }
                });
            }
            if (elements[i] instanceof JTopo.Link) {
            	console.log(elements[i].strokeColor);
                if (elements[i].strokeColor == "255,0,0") {
                    //如果是断开的链路
                    $.alert("无法删除断开的链路！");
                } else {
                    $.alert("删除一个链路" + elements[i].text);
                    //从画布删除链路
                    $.ajax({
                        url: '/NetworkSimulation/deleteLink',
                        data: {
                            linkName: elements[i].text,
                            s_id : $.getUrlParam("scenarioId")
                        },
                        type: 'post',
                        dataType: 'json',
                        async: false,
                        success: function (msg) {
                            if (msg == "删除成功") {
                                scene.remove(elements[i]);
                            }
                        },
                        error: function () {

                        }
                    });
                }
            }
        }
    }
    //10秒后刷新链路
    setTimeout(function () {
        window.location.reload();
    }, 10 * 1000);
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

//判断ip是否合法的正则
function isValidIP(ip)
{
    var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    return reg.test(ip);
}

//新建链路——模态框
var link_ips = [];
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
                    initToPortList(msg);
                },
                error: function () {

                }
            });
            //查询已有链路的地址
            $.ajax({
                url: '/NetworkSimulation/getLinkList',
                data: {
                    s_id : $.getUrlParam("scenarioId")
                },
                type: 'post',
                dataType: 'json',
                async: false,
                success: function (data) {
                    var objs = jQuery.parseJSON(data);
                    for (var i = 0; i < objs.length; i++){
                        //存已有ip地址的前三位
                        link_ips[i] = objs[i].fromNodeIP.substring(0,objs[i].fromNodeIP.lastIndexOf("."));
                    }
                    console.log("已读取存在的网段");
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

//判断输入的ip与已存在的ip不属于同网段
$("#fromNodeIP").blur(function () {
    var ip = $("#fromNodeIP").val();
    if (isValidIP(ip)) {
        //再判断是否重复
        for (var i = 0; i < link_ips.length; i++){
            if (ip.match(link_ips[i]) != null){
                //匹配到不空，说明有重复的网段，显示出错误信息
                $("#fromNodeIpErrorInfo").removeAttr("hidden");
                $("#fromNodeIpErrorInfo").html("输入的地址网段与已有的重复，请重新输入！");
                $("#fromNodeIP").val("");
                return false;
            }
        }
        //匹配到都是null，说明没有重复的网段
        $("#fromNodeIpErrorInfo").attr("hidden", "hidden");
        return true;
    } else {
        $("#fromNodeIpErrorInfo").removeAttr("hidden");
        $("#fromNodeIpErrorInfo").html("输入的ip地址不合法，请重新输入！");
        $("#fromNodeIP").val("");
        return false;
    }
});

$("#toNodeIP").blur(function () {
    var ip = $("#toNodeIP").val();
    if (isValidIP(ip)) {
        //再判断是否重复
        for (var i = 0; i < link_ips.length; i++){
            if (ip.match(link_ips[i]) != null){
                $("#toNodeIpErrorInfo").removeAttr("hidden");
                $("#toNodeIpErrorInfo").html("输入的地址网段与已有的重复，请重新输入！");
                $("#toNodeIP").val("");
                return false;
            }
        }
        $("#toNodeIpErrorInfo").attr("hidden", "hidden");
        return true;
    } else {
        $("#toNodeIpErrorInfo").removeAttr("hidden");
        $("#toNodeIpErrorInfo").html("输入的ip地址不合法，请重新输入！");
        $("#toNodeIP").val("");
        return false;
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
    for (var i = 0; i < objs.length; i++){
        if (objs[i].portStatus == 0){
            areaCont += '<option value="' + fromPortId[i] + '">' + fromPortList[i] + '</option>';
        } else {
            areaCont += '<option value="' + fromPortId[i] + '" disabled="disabled">' + fromPortList[i] + '(已占用)' + '</option>';
        }
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
    for (var i = 0; i < objs.length; i++){
        if (objs[i].portStatus == 0){
            areaCont += '<option value="' + toPortId[i] + '">' + toPortList[i] + '</option>';
        } else {
            areaCont += '<option value="' + toPortId[i] + '" disabled="disabled">' + toPortList[i] + '(已占用)' + '</option>';
        }
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
//新建节点
$("#canvas").droppable({
    drop: function (event, ui) {
        uiOut = ui;//保存数据
        //保存已有ip
        var node_ips = [];
        //首先弹出模态框
        $("#myModal").modal();
        //查询已有节点的信息，确保输入的管理ip不会重复
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
                    node_ips[i] = objs[i].manageIp;
                }
            },
            error: function () {

            }
        });
    }
});

//判断输入管理ip的合法性
$("#manageIP").blur(function () {
    var ip = $("#manageIP").val();
    //先检查合法性
    var reSpaceCheck = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
    if (reSpaceCheck.test(ip)) {
        ip.match(reSpaceCheck);
        if (RegExp.$1 == 192 && RegExp.$2 == 168 && RegExp.$3 == 10
            && RegExp.$4<=255 && RegExp.$4>=0) {
            //再检查是否有重复
            for (var i = 0; i < node_ips.length; i++){
                if (ip == node_ips[i]){
                    $("#nodeIpErrorInfo").removeAttr("hidden");
                    $("#nodeIpErrorInfo").html("输入的ip又已有节点的管理ip重复，请重新输入！");
                    //清空输入框的值
                    $("#manageIP").val("");
                    return false;
                }
            }
            $("#nodeIpErrorInfo").attr("hidden", "hidden");
            return true;
        }else {
            $("#nodeIpErrorInfo").removeAttr("hidden");
            $("#nodeIpErrorInfo").html("输入的网段不合法，请重新输入！");
            //清空输入框的值
            $("#manageIP").val("");
            return false;
        }
    } else {
        $("#nodeIpErrorInfo").removeAttr("hidden");
        $("#nodeIpErrorInfo").html("输入的ip地址不合法，请重新输入！");
        //清空输入框的值
        $("#manageIP").val("");
        return false;
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
            x : uiOut.offset.left - document.getElementById("slider_1").offsetWidth,
            y : uiOut.offset.top - 102,
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            if (msg == "创建成功") {
                $.alert(msg);
                //在画布上绘制出节点图标
                var getId = uiOut.draggable[0].id;//jquery获取图片，竟然要加一个[0]，这是什么鬼 (⊙o⊙)
                if (getId == "weixing1") {
                    createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider_1").offsetWidth, uiOut.offset.top - 102, "img/gaogui00.png");
                } else if (getId == "weixing2") {
                    createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider_1").offsetWidth, uiOut.offset.top - 102, "img/zhonggui00.png");
                } else if (getId == "weixing3") {
                    createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider_1").offsetWidth, uiOut.offset.top - 102, "img/digui00.png");
                } else if (getId == "weixing4") {
                    createNode(weixingName.value, uiOut.offset.left - document.getElementById("slider_1").offsetWidth, uiOut.offset.top - 102, "img/leida00.png");
                }
                //关闭模态框
                $('#myModal').modal('hide');
            } else {
                $.alert(msg);
            }
        },
        error: function () {

        }
    });
});

//链路模态框中请求的发送
$("#addLink").click(function () {
    //发送执行ajax的请求
    $.ajax({
        url: '/NetworkSimulation/addLink',
        data: {
            linkName : $("#linkName").val(),
            linkType : $("#linkType").val(),
            fromNodeIP : $("#fromNodeIP").val(),
            toNodeIP : $("#toNodeIP").val(),
            fromNodeName : beginNode.text,
            toNodeName : endLastNode.text,
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
            if (msg == "创建成功") {
                $.alert(msg);
                //在画布上绘制出链路
                if ($("#linkType").val() == 0) {//有线链路
                    // newLink(beginNode, endLastNode, beginNode.text + ":" + fromNodeIP.value + " -> " + endLastNode.text + ":" + toNodeIP.value, "0,0,255");
                    newLink(beginNode, endLastNode, $("#linkName").val(), "0,0,255");
                } else if ($("#linkType").val() == 1) {//无线链路
                    newLink(beginNode, endLastNode, "链路2", "0,0,0");
                }
                beginNode = null;
                scene.remove(link1);
                //关闭模态框
                $('#linkModal').modal('hide');
            } else {
                $.alert(msg);
            }
        },
        error: function () {

        }
    });
});

//打开内部编辑器
$("#openInnerEdit").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined || elements[0] instanceof JTopo.Link){
        $.alert("请选中节点后在进行下一步操作");
    } else {
        window.open(encodeURI("innerEdit.html?nodeName="+ elements[0].text + "&scenarioId=" + $.getUrlParam("scenarioId")));
    }
});

//打开节点编辑器
$("#editNode").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined || elements[0] instanceof JTopo.Link){
        $.alert("请选中节点后在进行下一步操作");
    } else {
        window.open(encodeURI("nodeEdit.html?nodeName=" + elements[0].text + "&scenarioId=" + $.getUrlParam("scenarioId")));
    }
});

//设置链路定时断开
$("#cutLink").click(function () {
   var elements = scene.selectedElements;
   if (elements[0] == undefined || elements[0] instanceof JTopo.Node){
       $.alert("请选中链路后在进行下一步操作");
   } else {
       //弹出模态框
       $("#cutLinkModal").modal();
   }
});

//设置链路定时断开提交
$("#cutLinkSubmit").click(function () {
    setTimeout(function () {
        $.ajax({
            url: '/NetworkSimulation/cutLink',
            data: {
                linkName : elements[0].text,
                scenario_id : $.getUrlParam("scenarioId")
            },
            type: 'post',
            dataType: 'json',
            async: true,
            success: function (msg) {
                $.alert(msg);
                if (msg == "断开成功") {
                    //变化链路为红色虚线
                    changeLinkToRed(elements[0]);
                }
            },
            error: function () {

            }
        });
    }, $("#cutLinkTime").val() * 1000);
    //关闭模态框
    $("#cutLinkModal").modal('hide');
});

//接通链路
$("#connectLink").click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined || elements[0] instanceof JTopo.Node){
        $.alert("请选中链路后在进行下一步操作");
    } else {
        $.ajax({
            url: '/NetworkSimulation/connectLink',
            data: {
                linkName : elements[0].text,
                scenario_id : $.getUrlParam("scenarioId")
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                $.alert(msg);
                if (msg == "恢复成功") {
                    //变化链路为蓝色
                    changeLinkToBlue(elements[0]);
                }
            },
            error: function () {

            }
        });
    }
});

//options
var linkListHtml ='';
//status
var statusMap = [];
//设置链路通段时间
$("#setLinkTime").click(function () {
    //弹出模态框
    $("#setLinkTimeModal").modal();
    $.ajax({
        url: '/NetworkSimulation/getLinkList',
        data: {
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (data) {
            initSelectLink(data);
        },
        error: function () {

        }
    });
    // var testJson = '[{"channelModel":0,"fromNodeName":"55","l_id":44,"linkInterference":234,"linkLength":34,"linkName":"link321","linkNoise":132,"linkStatus":0,"linkType":0,"rxPort_id":95,"scenario_id":25,"toNodeName":"66","txPort_id":94},{"channelModel":0,"fromNodeName":"6677","l_id":45,"linkInterference":234,"linkLength":34,"linkName":"link321123","linkNoise":132,"linkStatus":0,"linkType":0,"rxPort_id":97,"scenario_id":25,"toNodeName":"55","txPort_id":96}]';
    // initSelectLink(testJson);
});

//初始化可选链路列表
function initSelectLink(data) {
    linkListHtml = '';
    var objs = jQuery.parseJSON(data);
    for (var i = 0; i < objs.length; i++) {
        linkListHtml += '<option value="' + objs[i].linkName + '" >' + objs[i].linkName + '</option>';
        statusMap[objs[i].linkName] = objs[i].linkStatus;
    }
    // console.log(linkListHtml);
    $("#selectLink_0").html(linkListHtml);
}

//判断链路状态来决定是否能选择连通或断开
function selectConnction(i) {
    //如果是断开的那一行
    // console.log($('[id = "selectLink_' + i + '"]').val());
    if (statusMap[$('[id = "selectLink_' + i + '"]').val()] == 1) {
        $('[id = "switch_' + i + '"]').children().last().attr("disabled", "disabled");
        $('[id = "switch_' + i + '"]').children().first().removeAttr("disabled", "disabled");
    }
    //如果是连通的一行
    if (statusMap[$('[id = "selectLink_' + i + '"]').val()] == 0) {
        $('[id = "switch_' + i + '"]').children().first().attr("disabled", "disabled");
        $('[id = "switch_' + i + '"]').children().last().removeAttr("disabled", "disabled");
    }
}

//用于记录第几行的数据的标记,默认已经有第0行
var flag_1 = 0;
//点击增加一行
$("#addOneControl").click(function () {
    ++flag_1;
    var htmlString1 = '<div class="form-group">\
            <label class="col-sm-2 control-label">时刻</label>\
            <div class="col-sm-2">\
            <input type="text" class="form-control" placeholder="X秒" id="moment_' + flag_1 + '">\
            </div>\
            <div class="col-sm-4">\
            <select class="form-control" id="selectLink_' + flag_1 + '" onchange="selectConnction(' + flag_1 + ');">';
    var htmlString2 = '</select>\
            </div>\
            <div class="col-sm-4">\
            <select class="form-control" id="switch_' + flag_1 + '">\
            <option value="0" >连通</option>\
            <option value="1" >断开</option>\
            </select>\
            </div>\
            </div>';
    var htmlString = $("#setLinkTimeForm").html() + htmlString1 + linkListHtml + htmlString2;
    $("#setLinkTimeForm").html(htmlString);
});

//点击删除一行
$("#minusOneControl").click(function () {
    if (flag_1 > 0) {
        //flag最小回归为0
        --flag_1;
    }
    $("#setLinkTimeForm").children().last().remove();
});

//设置链路通断时间点击提交
$("#setLinkTimeSubmit").click(function () {
    //i为行数
    // console.log("flag_1:" + flag_1);
    console.log("链路名：" + $('[id = "selectLink_' + 0 + '"]').val());
    if ($("#totalTime").val() == '') {
        $.alert("请输入总仿真时间！");
        return false;
    }
    var elements = scene.getDisplayedElements();
    //遍历每行定时任务
    for (var i = 0; i <= flag_1; i++) {
        if ($('[id = "moment_' + i + '"]').val() > $("#totalTime").val()) {
            $.alert("第" + i + "行的时刻输入大于总仿真时间，请重新输入！");
            $('[id = "moment_' + i + '"]').val('');
            return false;
        }
        if ($('[id = "switch_' + i + '"]').val() == 0) {
            console.log("发送第" + i + "行连通请求");
            console.log("链路名：" + $('[id = "selectLink_' + i + '"]').val());
            $.ajax({
                url: '/NetworkSimulation/connectLink',
                data: {
                    linkName : $('[id = "selectLink_' + i + '"]').val(),
                    scenario_id : $.getUrlParam("scenarioId"),
                    delayTime : $('[id = "moment_' + i + '"]').val()
                },
                type: 'post',
                dataType: 'json',
                async: true,
                success: function (msg) {
                    if (msg == "恢复成功") {
                        //变化链路为蓝色
                        for (var i = 0; i < elements.length; i++) {
                            //在画布上找到该链路对象
                            if (elements[i] instanceof JTopo.Link && elements[i].text == $('[id = "selectLink_' + i + '"]').val()) {
                                changeLinkToBlue(elements[i]);
                            }
                        }
                    } else {
                        $.alert("第" + i + "条指令执行失败：" + msg);
                    }
                },
                error: function (msg) {

                }
            });
        }
        if ($('[id = "switch_' + i + '"]').val() == 1) {
            console.log("发送第" + i + "行断开请求");
            console.log("链路名：" + $('[id = "selectLink_' + i + '"]').val());
            $.ajax({
                url: '/NetworkSimulation/cutLink',
                data: {
                    linkName : $('[id = "selectLink_' + i + '"]').val(),
                    scenario_id : $.getUrlParam("scenarioId"),
                    delayTime : $('[id = "moment_' + i + '"]').val()
                },
                type: 'post',
                dataType: 'json',
                async: true,
                success: function (msg) {
                    if (msg == "断开成功") {
                        //变化链路为红色虚线
                        for (var i = 0; i < elements.length; i++) {
                            //在画布上找到该链路对象
                            if (elements[i] instanceof JTopo.Link && elements[i].text == $('[id = "selectLink_' + i + '"]').val()) {
                                changeLinkToRed(elements[i]);
                            }
                        }
                    } else {
                        $.alert("第" + i + "条指令执行失败：" + msg);
                    }
                },
                error: function (msg) {

                }
            });
        }
    }
    $.alert("画布将在" + $("#totalTime").val() + "s后刷新！请稍等");
    setTimeout(function () {
        window.location.reload();
    }, $("#totalTime").val() * 1000 + 10 * 1000);
    //关闭模态框
    $("#setLinkTimeModal").modal('hide');
});