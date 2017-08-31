// var json1 = '[{"fromBigNodeName":"Maui_HI_AMOS","innerNodeName":["Maui_HI_AMOS"],"toBigNodeNames":["LEO"]},{"fromBigNodeName":"LEO","innerNodeName":["LEO16","LEO15","LEO26","LEO36","LEO25","LEO14","LEO46","LEO35","LEO24","LEO13","LEO45","LEO34","LEO12","LEO23","LEO44","LEO33","LEO11","LEO22","LEO43","LEO32","LEO21","LEO42","LEO31","LEO41"],"toBigNodeNames":["GEO","LEO"]},{"fromBigNodeName":"GEO","innerNodeName":["GEO2","GEO3"],"toBigNodeNames":["GEO","Maui_HI_AMOS","LEO"]},{"fromBigNodeName":"Berkeley","innerNodeName":["Berkeley"],"toBigNodeNames":["LEO"]}]';
// var json2 = '[{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"GEO3"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO42","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO43","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO44","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO45","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO46","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO45","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO44","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO43","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO42","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"Maui_HI_AMOS"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO16"}]';

//用于右边树的勾选控制
var silentByChild = true;
var objs;
var links;

//解析url参数的函数，包括解码
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var url = decodeURI(window.location.search);
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

//请求大类的json文件
$.ajax({
    url: '/NetworkSimulation/getStkNode',
    data: {
        s_id : $.getUrlParam("scenarioId")
    },
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (msg) {
        console.log(msg);
        //初始化大类对象
        objs = jQuery.parseJSON(msg);
        console.log("初始化大类对象成功");
    },
    error: function () {

    }
});

//请求链路的json文件
$.ajax({
    url: '/NetworkSimulation/getStkLink',
    data: {
        s_id : $.getUrlParam("scenarioId")
    },
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (msg) {
        console.log(msg);
        //初始化链路对象
        links = jQuery.parseJSON(msg);
        console.log("初始化link对象成功");
    },
    error: function () {

    }
});

//定义左边的树
$('#tree').treeview({
    data: getLeftTree(),
    multiSelect : false
});

//初始化左边树的对象
function getLeftTree() {
    var leftTree = [];
    for (var i = 0; i < objs.length; i++) {
        //创建大类节点进行初始化
        var bigNode = {};
        bigNode["text"] = objs[i].fromBigNodeName;
        //初始化为未展开的状态
        bigNode["state"] = {};
        bigNode["state"]["expanded"] = false;
        //设置该节点是否可以被选择
        bigNode["selectable"] = false;
        //初始化大类下面的子节点
        bigNode["nodes"] = [];
        for (var j = 0; j < objs[i].innerNodeName.length; j++) {
            var smallNode = {};
            smallNode["text"] = objs[i].innerNodeName[j];
            //把子节点放入大类中
            bigNode["nodes"].push(smallNode);
        }
        //把大类放入树中
        leftTree.push(bigNode);
    }
    return leftTree;
}

//得到右边树的对象
function getRightTree(checkedNodeNames) {
    console.log("需要勾选的右边节点是：" + checkedNodeNames);
    var rightTree = [];
    for (var i = 0; i < objs.length; i++) {
        //创建大类节点进行初始化
        var bigNode = {};
        bigNode["text"] = objs[i].fromBigNodeName;
        //初始化为未展开的状态
        bigNode["state"] = {};
        // bigNode["state"]["expanded"] = false;
        //初始化大类下面的子节点
        bigNode["nodes"] = [];
        //用于记录子节点勾选个数，进而对父节点状态进行初始化
        var checkedCount = 0;
        for (var j = 0; j < objs[i].innerNodeName.length; j++) {
            var smallNode = {};
            smallNode["text"] = objs[i].innerNodeName[j];
            smallNode["state"] = {};
            //要找到那些节点需要勾选
            for (var k = 0; k < checkedNodeNames.length; k++) {
                if (smallNode["text"] == checkedNodeNames[k]) {
                    //此时该子节点是被选中的
                    smallNode["state"]["checked"] = true;
                    checkedCount++;
                }
            }
            if (!smallNode["state"]["checked"]) {
                //没有被勾选的节点也要禁止其进行勾选，只能对已勾选的节点进行取消勾选的操作
                smallNode["state"]["disabled"] = true;
            }
            //把子节点放入大类中
            bigNode["nodes"].push(smallNode);
        }
        if (bigNode["nodes"].length == checkedCount) {
            //此时一个父节点的子节点全被勾选了
            bigNode["state"]["checked"] = true;
            bigNode["state"]["selected"] = true;
        }
        if (checkedCount > 0 && checkedCount < bigNode["nodes"].length) {
            //子节点部分勾选
            bigNode["state"]["selected"] = true;
        }
        if (checkedCount == 0) {
            //无子节点被勾选
            bigNode["state"]["disabled"] = true;
        }
        console.log("大类对象：" + bigNode);
        //把大类放入树中
        rightTree.push(bigNode);
    }
    console.log(rightTree);
    return rightTree;
}

//选择左边的节点触发事件
$('#tree').on('nodeSelected', function(event, node) {
    console.log("已经点击了左边的节点：");
    console.log(node.text);
    var checkedNodeNames = [];
    for (var i = 0; i < links.length; i++) {
        if (node.text == links[i].fromNodeName) {
            //记录下与左边大类关联的右边大类名字
            checkedNodeNames.push(links[i].toNodeName);
        }
    }
     var selectNodeNames = node.text;
    //定义右边的树
    $("#tree_1").treeview({
        data: getRightTree(checkedNodeNames),
        showCheckbox: true,
        highlightSelected: false,
        multiSelect : false,
        onNodeChecked : function (event, node) {
            if (node.nodes != null) {
                //如果勾选的是父节点，将其子节点全部勾选
                $.each(node.nodes, function(index, value) {
                    $("#tree_1").treeview('checkNode', value.nodeId, {
                        silent : true
                    });
                });
            } else {
                //如果勾选的是子节点
                for (var i = 0; i < links.length; i++) {
                    if (selectNodeNames == links[i].fromNodeName && node.text == links[i].toNodeName) {
                        links[i].linkStatus = 0;
                        console.log(links[i].fromNodeName + "和" + links[i].toNodeName + "之间的链路已经连接");
                    }
                }
                var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
                if (parentNode.nodeId == undefined) {
                    //如果是无子节点的父节点，getParent函数有bug，无法返回undefined，需要自己判断
                    return;
                } else {
                    var isAllchecked = true; // 是否全部选中
                    //获取兄弟节点查看勾选状态
                    var siblings = $("#tree_1").treeview('getSiblings', node.nodeId);
                    for ( var i in siblings) {
                        // 有一个没选中，则不是全选
                        if (!siblings[i].state.checked) {
                            isAllchecked = false;
                            break;
                        }
                    }
                    // 全选，则打钩
                    if (isAllchecked) {
                        $("#tree_1").treeview('checkNode', parentNode.nodeId, {
                            silent : true
                        });
                    } else {
                        // 非全选，设置为红色
                        $("#tree_1").treeview('selectNode', parentNode.nodeId, {
                            silent : true
                        });
                    }
                }
            }
        },
        onNodeUnchecked : function (event, node) {
            // 选中的是父节点
            if (node.nodes != null) {
                // 这里需要控制，判断是否是因为市级节点引起的父节点被取消选中
                // 如果是，则只管取消父节点就行了
                // 如果不是，则子节点需要被取消选中
                if (silentByChild) {
                    $.each(node.nodes, function(index, value) {
                        $("#tree_1").treeview('uncheckNode', value.nodeId, {
                            silent : true
                        });
                    });
                }
            } else {
                // 子级节点被取消选中
                for (var i = 0; i < links.length; i++) {
                    if (selectNodeNames == links[i].fromNodeName && node.text == links[i].toNodeName) {
                        links[i].linkStatus = 1;
                        console.log(links[i].fromNodeName + "和" + links[i].toNodeName + "之间的链路已经断开");
                    }
                }
                var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
                if (parentNode.nodeId == undefined) {
                    //如果是无子节点的父节点，getParent函数有bug，无法返回undefined，需要自己判断
                    return;
                } else {
                    var isAllUnchecked = true; // 是否全部取消选中
                    // 市级节点有一个选中，那么就不是全部取消选中
                    var siblings = $("#tree_1").treeview('getSiblings', node.nodeId);
                    for (var i in siblings) {
                        if (siblings[i].state.checked) {
                            isAllUnchecked = false;
                            break;
                        }
                    }
                    // 全部取消选中，那么省级节点恢复到默认状态
                    if (isAllUnchecked) {
                        silentByChild = true;
                        $("#tree_1").treeview('unselectNode', parentNode.nodeId, {
                            silent : true
                        });
                        $("#tree_1").treeview('uncheckNode', parentNode.nodeId, {
                            silent : true
                        });
                    } else {
                        silentByChild = false;
                        $("#tree_1").treeview('selectNode', parentNode.nodeId, {
                            silent : true
                        });
                        $("#tree_1").treeview('uncheckNode', parentNode.nodeId, {
                            silent : true
                        });
                    }
                }
            }
        }
    });
});

//点击提交
$("#submit").click(function () {
    $.ajax({
        url: '/NetworkSimulation/submitRegulation',
        data: {
            regulationOption : $("input[name='optionsRadiosinline']:checked").val(),
            linkJson : JSON.stringify(links),
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {

        },
        error: function () {

        }
    });
});