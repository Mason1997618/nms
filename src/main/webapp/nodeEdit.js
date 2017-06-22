/**
 * Created by sjm on 2017/6/21.
 */
//预读
$(document).ready(function () {
    //测试解析url
    //alert($.getUrlParam('nodeName'));
    initNodeAttr();

    $.ajax({
        url: '/NetworkSimulation/getNode',
        data: {
            nodeName : $.getUrlParam('nodeName')
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
});

//显示节点属性
function initNodeAttr(data) {
    var objs = jQuery.parseJSON(data);
    $("#nodeName").val(objs[0].nodeName);
    $("#nodeId").val(objs[0].n_id);
    $("#manageIP").val(objs[0].manageIP);
    $("#nodeType").val(objs[0].nodeType);
    $("#hardwareArchitecture").val(objs[0].hardwareArchitecture);
    $("#operatingSystem").val(objs[0].operatingSystem);
    $("#nodeConfig").val(objs[0].nodeConfig);
    $("#nodeImage").val(objs[0].nodeImage);
}

//解析url参数的函数
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);