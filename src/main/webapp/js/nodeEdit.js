/**
 * Created by sjm on 2017/6/21.
 */
/**
 * 解析url参数的函数，包括解码
 */
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var url = decodeURI(window.location.search);
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

var portObjs; // 存储端口对象
var L2LinkObjs; // 二层链路对象

$(document).ready(function () {
    $.ajax({ // 获得节点的属性，显出节点属性
        url: '/NetworkSimulation/getNodeBynodeName',
        data: {
            nodeName : $.getUrlParam("nodeName"),
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            initNodeAttr(msg);
        },
        error: function () {

        }
    });
    if ($.getUrlParam("isL2node") == 1) { // 编辑的是二层节点
        $.ajax({ // 返回二层链路的对象列表json
            url: '/NetworkSimulation/getL2LinkListByNodeName',
            data: {
                nodeName : $.getUrlParam("nodeName"),
                s_id : $.getUrlParam("scenarioId")
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                console.log(msg);
                initEthList(msg);
            },
            error: function () {

            }
        });
        $("#addPort").attr('disabled', 'disabled'); // 二层节点不能在这里新建端口
    } else { // 编辑的是三层节点
        $.ajax({ // 获得网口列表，显示网口
            url: '/NetworkSimulation/getPortList',
            data: {
                n_id : $("#nodeId").val()
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                initPortList(msg);
            },
            error: function () {

            }
        });
    }
});

/**
 * 显示节点属性
 * @param data 节点对象的json
 */
function initNodeAttr(data) {
    var objs = jQuery.parseJSON(data);
    $("#nodeName").val(objs.nodeName);
    $("#nodeId").val(objs.n_id);
    $("#manageIP").val(objs.manageIp);
    $("#nodeType").val(objs.nodeType);
    $("#hardwareArchitecture").val(objs.hardwareArchitecture);
    $("#operatingSystem").val(objs.operatingSystem);
    $("#nodeConfig").val(objs.flavorType);
    $("#nodeImage").val(objs.imageName);
    $("#portCount").val(objs.length);
}

/**
 * 显示三层节点的网口列表
 */
function initPortList(data) {
    portObjs = jQuery.parseJSON(data);
    var areaCont = "";
    for (var i = 0; i < portObjs.length; i++){
        if (portObjs[i].isTemplate == 0) { // 不是模板的端口，是模板的不显示出来
            areaCont += '<option onClick="selectP(' + portObjs[i].pt_id + ');">' + portObjs[i].portName + '</option>';
        }
    }
    $("#selectPort").html(areaCont);
}

/**
 * 显示二层节点的网卡列表
 * @param data 二层链路对象列表
 */
function initEthList(data) {
    L2LinkObjs = jQuery.parseJSON(data);
    var html = '';
    for (var i = 0; i < L2LinkObjs.length; i++) {
        if ($.getUrlParam("nodeName") == L2LinkObjs[i].fromNodeName) {
            html += '<option value="' + L2LinkObjs[i].fromEth + '" onclick="selectE(1);">' + L2LinkObjs[i].fromEth + '(' + L2LinkObjs[i].linkName + ')' + '</option>';
        }
        if ($.getUrlParam("nodeName") == L2LinkObjs[i].toNodeName) {
            html += '<option value="' + L2LinkObjs[i].toEth + '" onclick="selectE(2);">' + L2LinkObjs[i].toEth + '(' + L2LinkObjs[i].linkName + ')' + '</option>';
        }
    }
    $("#selectPort").html(html);
}

/**
 * 选中列表中网口
 * @param i 选中的网口的id
 */
function selectP(i) {
    $("#editPort").removeAttr("disabled");
    $("#delPort").removeAttr("disabled");
    document.getElementById("editPort").onclick = function () { // 打开网口编辑器
        window.open(encodeURI("portEdit.html?portId=" + i));
    };
    document.getElementById("delPort").onclick = function () { // 删除网口
        $.ajax({
            url: '/NetworkSimulation/deletePort',
            data: {
                pt_id : i
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                alert(msg);
                window.location.reload();
            },
            error: function () {

            }
        });
    };
}

/**
 * 选中二层节点的网卡
 */
function selectE(k) {
    console.log("已选择网卡：" + $("#selectPort").val());
    $("#editPort").removeAttr("disabled");
    var toNodeName, toEth;
    if (k == 1) { // 是from端的网卡
        for (var i = 0; i < L2LinkObjs.length; i++) {
            if ($("#selectPort").val() == L2LinkObjs[i].fromEth) {
                toNodeName = L2LinkObjs[i].toNodeName;
                toEth = L2LinkObjs[i].toEth;
            }
        }
    } else { // 是to端的网卡
        for (var i = 0; i < L2LinkObjs.length; i++) {
            if ($("#selectPort").val() == L2LinkObjs[i].toEth) {
                toNodeName = L2LinkObjs[i].fromNodeName;
                toEth = L2LinkObjs[i].fromEth;
            }
        }
    }
    document.getElementById("editPort").onclick = function () { // 打开网口编辑器
        window.open(encodeURI("portEdit.html?nodeName=" + $.getUrlParam("nodeName") + "&ethName=" + $("#selectPort").val() + "&toNodeName=" + toNodeName + "&toEth=" + toEth));
    };
}

/**
 * 选择网口类型需要禁用掉一些属性
 */
$("#portType").change(function () {
    if ($("#portType").val() == 1) { // 类型1
        $("#transmitterPowerDiv").removeAttr("hidden", "hidden");
        $("#transmitterFrequencyDiv").removeAttr("hidden", "hidden");
        $("#transmitterBandwidthDiv").removeAttr("hidden", "hidden");
        $("#transmitterGainDiv").removeAttr("hidden", "hidden");
        $("#receiverFrequencyDiv").removeAttr("hidden", "hidden");
        $("#receiverBandwidthDiv").removeAttr("hidden", "hidden");
        $("#receiverGainDiv").removeAttr("hidden", "hidden");
        $("#modemDiv").removeAttr("hidden", "hidden");
        $("#maximumRateDiv").attr("hidden", "hidden");
        $("#packetLossDiv").attr("hidden", "hidden");
    }
    if ($("#portType").val() == 2) { // 类型2
        $("#transmitterPowerDiv").attr("hidden", "hidden");
        $("#transmitterFrequencyDiv").attr("hidden", "hidden");
        $("#transmitterBandwidthDiv").attr("hidden", "hidden");
        $("#transmitterGainDiv").attr("hidden", "hidden");
        $("#receiverFrequencyDiv").attr("hidden", "hidden");
        $("#receiverBandwidthDiv").attr("hidden", "hidden");
        $("#receiverGainDiv").attr("hidden", "hidden");
        $("#modemDiv").attr("hidden", "hidden");
        $("#maximumRateDiv").removeAttr("hidden", "hidden");
        $("#packetLossDiv").removeAttr("hidden", "hidden");
    }
});

/**
 * 点击新建网口，要初始化可选模板参数
 */
$("#addPort").click(function () {
    $("#myModal").modal(); // 弹出模态框
    initPortTemplate();
});

/**
 * 初始化新建端口里面的下拉模板
 */
function initPortTemplate() {
    var html = '';
    for (var i = 0; i < portObjs.length; i++) {
        if (portObjs[i].isTemplate == 1) { // 是模板的端口
            html += '<option value="' + portObjs[i].portName + '">' + '（模板）' + portObjs[i].portName + '</option>';
        }
    }
    $("#portTemplate").html(html);
    $("#portTemplate").val(''); // 初始化让它谁也不选
}

/**
 * 选中某一个模板之后自动填入部分参数
 */
$("#portTemplate").change(function () {
    var portTemplateName = $("#portTemplate").val();
    for (var i = 0; i < portObjs.length; i++) {
        if (portTemplateName == portObjs[i].portName) { // 找到选中模板对应的对象
            $("#portType").val(portObjs[i].portType);
            $("#transmitterPower").val(portObjs[i].transmitterPower);
            $("#transmitterFrequency").val(portObjs[i].transmitterFrequency);
            $("#transmitterBandwidth").val(portObjs[i].transmitterBandwidth);
            $("#transmitterGain").val(portObjs[i].transmitterGain);
            $("#receiverFrequency").val(portObjs[i].receiverFrequency);
            $("#receiverBandwidth").val(portObjs[i].receiverBandwidth);
            $("#receiverGain").val(portObjs[i].receiverGain);
            $("#modem").val(portObjs[i].modem);
            $("#maximumRate").val(portObjs[i].maximumRate);
            $("#packetLoss").val(portObjs[i].packetLoss);
        }
    }
});

/**
 * 判断ip是否合法的正则
 */
function isValidIP(ip) {
    var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    return reg.test(ip);
}

/**
 * 判断输入的ip地址是否合法
 */
$("#portIp").blur(function () {
    var ip = $("#portIp").val();
    if (isValidIP(ip)) {
        $("#portIpErrorInfo").attr("hidden", "hidden");
    } else {
        $("#portIpErrorInfo").removeAttr("hidden");
    }
});

/**
 * 新建网口提交
 */
$("#submitPort").click(function () {
    if ($("#portName").val() == "") { // 端口名称输入为空时
        $.alert("端口名称不能为空！");
        return;
    }
    if ($("input[name='saveAsTemplateCheckbox']:checked").val() == 1) { // 选中了保存为模板
        $.ajax({
            url: '/NetworkSimulation/addPort',
            data: {
                n_id : $("#nodeId").val(),
                portName : $("#portName").val(),
                isMultiplexing : $("#isMultiplexing").val(),
                portType : $("#portType").val(),
                portIp : $("#portIp").val(),
                transmitterPower : $("#transmitterPower").val(),
                transmitterFrequency : $("#transmitterFrequency").val(),
                transmitterBandwidth : $("#transmitterBandwidth").val(),
                transmitterGain : $("#transmitterGain").val(),
                receiverFrequency : $("#receiverFrequency").val(),
                receiverBandwidth : $("#receiverBandwidth").val(),
                receiverGain : $("#receiverGain").val(),
                modem : $("#modem").val(),
                maximumRate : $("#maximumRate").val(),
                packetLoss : $("#packetLoss").val(),
                isTemplate : 1
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                $.alert(msg);
                $("#myModal").modal('hide');
                window.location.reload();
            },
            error: function () {

            }
        });
    } else { // 未选中保存为模板
        $.ajax({
            url: '/NetworkSimulation/addPort',
            data: {
                n_id : $("#nodeId").val(),
                portName : $("#portName").val(),
                isMultiplexing : $("#isMultiplexing").val(),
                portType : $("#portType").val(),
                portIp : $("#portIp").val(),
                transmitterPower : $("#transmitterPower").val(),
                transmitterFrequency : $("#transmitterFrequency").val(),
                transmitterBandwidth : $("#transmitterBandwidth").val(),
                transmitterGain : $("#transmitterGain").val(),
                receiverFrequency : $("#receiverFrequency").val(),
                receiverBandwidth : $("#receiverBandwidth").val(),
                receiverGain : $("#receiverGain").val(),
                modem : $("#modem").val(),
                maximumRate : $("#maximumRate").val(),
                packetLoss : $("#packetLoss").val(),
                isTemplate : 0
            },
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (msg) {
                $.alert(msg);
                $("#myModal").modal('hide');
                window.location.reload();
            },
            error: function () {

            }
        });
    }
});

/**
 * 编辑节点属性提交
 */
$("#editNode").click(function () {
    $.ajax({
        url: '/NetworkSimulation/editNode',
        data: {
            nodeName : $("#nodeName").val(),
            n_id : $("#nodeId").val(),
            manageIp : $("#manageIP").val(),
            nodeType : $("#nodeType").val(),
            hardwareArchitecture : $("#hardwareArchitecture").val(),
            operatingSystem : $("#operatingSystem").val(),
            nodeConfig : $("#nodeConfig").val(),
            nodeImage : $("#nodeImage").val(),
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
        	
            $.alert(msg);
            //parent.location.reload();
            location.href = encodeURI("index3.html?nodeName=" + $("#nodeName").val() + "&scenarioId=" + $.getUrlParam("scenarioId")); // tiaohuichangjing页面_tjq
        },
        error: function () {

        }
    });
});