// var json1 = '[{"fromBigNodeName":"Maui_HI_AMOS","innerNodeName":["Maui_HI_AMOS"],"toBigNodeNames":["LEO"]},{"fromBigNodeName":"LEO","innerNodeName":["LEO16","LEO15","LEO26","LEO36","LEO25","LEO14","LEO46","LEO35","LEO24","LEO13","LEO45","LEO34","LEO12","LEO23","LEO44","LEO33","LEO11","LEO22","LEO43","LEO32","LEO21","LEO42","LEO31","LEO41"],"toBigNodeNames":["GEO","LEO"]},{"fromBigNodeName":"GEO","innerNodeName":["GEO2","GEO3"],"toBigNodeNames":["GEO","Maui_HI_AMOS","LEO"]},{"fromBigNodeName":"Berkeley","innerNodeName":["Berkeley"],"toBigNodeNames":["LEO"]}]';
// var json2 = '[{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"GEO3"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO42","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO43","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO44","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO45","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO46","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO45","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO44","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO43","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO42","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO41","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"Maui_HI_AMOS"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"Maui_HI_AMOS","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO36","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO45"},{"fromNodeName":"LEO35","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO44"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO46"},{"fromNodeName":"LEO34","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO43"},{"fromNodeName":"LEO33","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO42"},{"fromNodeName":"LEO32","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"LEO31","linkStatus":0,"toNodeName":"LEO41"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO21","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO22","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO23","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO24","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO25","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO26","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO35"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO36"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO33"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO34"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO31"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO32"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"GEO1"},{"fromNodeName":"GEO2","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"GEO3","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO16"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO13"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO14"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO11"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO12"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO16","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO25"},{"fromNodeName":"LEO15","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO24"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO26"},{"fromNodeName":"LEO14","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO23"},{"fromNodeName":"LEO13","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO22"},{"fromNodeName":"LEO12","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"LEO11","linkStatus":0,"toNodeName":"LEO21"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO15"},{"fromNodeName":"Berkeley","linkStatus":0,"toNodeName":"LEO16"}]';

//??????????????????????????????
var silentByChild = true;
var objs;
var links;
// var objs = jQuery.parseJSON(json1);
// var links = jQuery.parseJSON(json2);

/**
 * ??????url
 */
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var url = decodeURI(window.location.search);
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);

/**
 * ???????????????json
 */
$.ajax({
    url: '/NetworkSimulation/getStkNode',
    data: {
        s_id : $.getUrlParam("scenarioId")
    },
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (msg) {
        //?????????????????????
        objs = jQuery.parseJSON(msg);
        console.log("???????????????????????????");
    },
    error: function () {

    }
});

/**
 * ???????????????json??????
 */
$.ajax({
    url: '/NetworkSimulation/getStkLink',
    data: {
        s_id : $.getUrlParam("scenarioId")
    },
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (msg) {
        //?????????????????????
        links = jQuery.parseJSON(msg);
        console.log("?????????link????????????");
    },
    error: function () {

    }
});

/**
 * ?????????????????????????????????
 * @returns {Array} ???????????????????????????
 */
function getLeftTree() {
    var leftTree = [];
    for (var i = 0; i < objs.length; i++) {
        //?????????????????????????????????
        var bigNode = {};
        bigNode["text"] = objs[i].fromBigNodeName;
        //??????????????????????????????
        bigNode["state"] = {};
        bigNode["state"]["expanded"] = false;
        //????????????????????????????????????
        bigNode["selectable"] = false;
        //?????????????????????????????????
        bigNode["nodes"] = [];
        for (var j = 0; j < objs[i].innerNodeName.length; j++) {
            var smallNode = {};
            smallNode["text"] = objs[i].innerNodeName[j];
            //???????????????????????????
            bigNode["nodes"].push(smallNode);
        }
        //?????????????????????
        leftTree.push(bigNode);
    }
    return leftTree;
}

/**
 * ??????????????????
 */
$("#tree").treeview({
    data: getLeftTree(),
    multiSelect : false
});

/**
 * ????????????????????????????????????
 * @param node ??????????????????????????????
 * @returns {Array} ???????????????????????????
 */
function getRightTree(node) {
    var checkedNodeNames = [];
    for (var i = 0; i < links.length; i++) {
        if (node.text == links[i].fromNodeName && links[i].linkStatus == 0) {
            //???????????????????????????????????????????????????
            checkedNodeNames.push(links[i].toNodeName);
        }
    }
    console.log("?????????????????????????????????" + checkedNodeNames);
    var rightTree = [];
    for (var i = 0; i < objs.length; i++) {
        //?????????????????????????????????
        var bigNode = {};
        bigNode["text"] = objs[i].fromBigNodeName;
        //??????????????????????????????
        bigNode["state"] = {};
        // bigNode["state"]["expanded"] = false;
        //?????????????????????????????????
        bigNode["nodes"] = [];
        //???????????????????????????????????????????????????????????????????????????
        var checkedCount = 0;
        for (var j = 0; j < objs[i].innerNodeName.length; j++) {
            var smallNode = {};
            //?????????????????????????????????
            for (var k = 0; k < checkedNodeNames.length; k++) {
                if (objs[i].innerNodeName[j] == checkedNodeNames[k]) {
                    //???????????????????????????????????????????????????
                    smallNode["text"] = objs[i].innerNodeName[j];
                    smallNode["state"] = {};
                    smallNode["state"]["checked"] = true;
                    //???????????????????????????
                    bigNode["nodes"].push(smallNode);
                    checkedCount++;
                }
            }
            // if (!smallNode["state"]["checked"]) {
            //     //????????????????????????????????????????????????????????????????????????????????????????????????????????????
            //     smallNode["state"]["disabled"] = true;
            // }
        }
        if (bigNode["nodes"].length == checkedCount && bigNode["nodes"].length != 0) {
            //??????????????????????????????????????????
            bigNode["state"]["checked"] = true;
            bigNode["state"]["selected"] = true;
        }
        // if (checkedCount > 0 && checkedCount < bigNode["nodes"].length) {
        //     //?????????????????????
        //     bigNode["state"]["selected"] = true;
        // }
        if (checkedCount == 0) {
            //?????????????????????
            bigNode["state"]["disabled"] = true;
        }
        //?????????????????????
        rightTree.push(bigNode);
    }
    return rightTree;
}

/**
 * ?????????????????????????????????
 */
$('#tree').on('nodeSelected', function(event, node) {
    console.log("?????????????????????????????????" + node.text);
    // var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
    // if (parentNode.nodeId == undefined) {
    //     //??????????????????????????????
    //
    // } else {
    //     //????????????????????????
    //
    // }
     var selectNodeNames = node.text;
    //??????????????????
    $("#tree_1").treeview({
        data: getRightTree(node),
        showCheckbox: true,
        highlightSelected: false,
        multiSelect : false,
        onNodeChecked : function (event, node) {
            if (node.nodes != null) {
                //?????????????????????????????????????????????????????????
                // $("#alertInfo").removeAttr("hidden");
                $.each(node.nodes, function(index, value) {
                    $("#tree_1").treeview('checkNode', value.nodeId, {
                        silent : true
                    });
                });
            } else {
                //???????????????????????????
                for (var i = 0; i < links.length; i++) {
                    if (selectNodeNames == links[i].fromNodeName && node.text == links[i].toNodeName) {
                        links[i].linkStatus = 0;
                        console.log(links[i].fromNodeName + "???" + links[i].toNodeName + "???????????????????????????");
                    }
                }
                var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
                if (parentNode.nodeId == undefined) {
                    //????????????????????????????????????getParent?????????bug???????????????undefined?????????????????????
                    return;
                } else {
                    var isAllchecked = true; // ??????????????????
                    //????????????????????????????????????
                    var siblings = $("#tree_1").treeview('getSiblings', node.nodeId);
                    for ( var i in siblings) {
                        // ????????????????????????????????????
                        if (!siblings[i].state.checked) {
                            isAllchecked = false;
                            break;
                        }
                    }
                    // ??????????????????
                    if (isAllchecked) {
                        $("#tree_1").treeview('checkNode', parentNode.nodeId, {
                            silent : true
                        });
                    } else {
                        // ???????????????????????????
                        $("#tree_1").treeview('selectNode', parentNode.nodeId, {
                            silent : true
                        });
                    }
                }
            }
        },
        onNodeUnchecked : function (event, node) {
            // $("#alertInfo").attr("hidden", "hidden");
            // ?????????????????????
            if (node.nodes != null) {
                // ???????????????????????????????????????????????????????????????????????????????????????
                // ?????????????????????????????????????????????
                // ????????????????????????????????????????????????
                if (silentByChild) {
                    $.each(node.nodes, function(index, value) {
                        $("#tree_1").treeview('uncheckNode', value.nodeId, {
                            silent : true
                        });
                    });
                }
            } else {
                // ???????????????????????????
                for (var i = 0; i < links.length; i++) {
                    if (selectNodeNames == links[i].fromNodeName && node.text == links[i].toNodeName) {
                        links[i].linkStatus = 1;
                        console.log(links[i].fromNodeName + "???" + links[i].toNodeName + "???????????????????????????");
                    }
                }
                var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
                if (parentNode.nodeId == undefined) {
                    //????????????????????????????????????getParent?????????bug???????????????undefined?????????????????????
                    return;
                } else {
                    var isAllUnchecked = true; // ????????????????????????
                    // ???????????????????????????????????????????????????????????????
                    var siblings = $("#tree_1").treeview('getSiblings', node.nodeId);
                    for (var i in siblings) {
                        if (siblings[i].state.checked) {
                            isAllUnchecked = false;
                            break;
                        }
                    }
                    // ????????????????????????????????????????????????????????????
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

/**
 * ??????????????????????????????????????????
 * @returns {Array}
 */
function getSecondTree() {
    var leftTree = [];
    for (var i = 0; i < objs.length; i++) {
        //?????????????????????????????????
        var bigNode = {};
        bigNode["text"] = objs[i].fromBigNodeName;
        //?????????????????????
        leftTree.push(bigNode);
    }
    return leftTree;
}

/**
 * ??????????????????????????????
 */
$("#tree_2").treeview({
    data: getSecondTree(),
    multiSelect : false
});

/**
 * ?????????????????????????????????????????????
 * @param node ???????????????????????????
 * @returns {Array}
 */
function getThirdTree(node) {
    var checkedNames;
    for (var i = 0; i < objs.length; i++) {
        if (node.text == objs[i].fromBigNodeName) {
            //???????????????????????????????????????
            checkedNames = objs[i].toBigNodeNames;
        }
    }
    console.log("???????????????????????????" + checkedNames);
    var rightTree = [];
    for (var i = 0; i < checkedNames.length; i++) {
        var bigNode = {};
        bigNode["text"] = checkedNames[i];
        rightTree.push(bigNode);
    }
    return rightTree;
}

/**
 * ??????????????????????????????????????????
 * @type {Array}
 */
var rules = new Array();
/**
 * ?????????????????????????????????????????????????????????????????????????????????
 */
$('#tree_2').on('nodeSelected', function(event, node) {
    console.log("?????????????????????????????????" + node.text);
    //??????????????????????????????
    var rule = new Object();
    var fromName = node.text;
    rule.fromBigNodeName = fromName;
    rule.options = [];
    //????????????????????????
    $("#tree_3").treeview({
        data: getThirdTree(node),
        multiSelect : false,
        onNodeSelected : function (event, node) {
            console.log("?????????????????????????????????" + node.text);
            var toName = node.text;
            rule.toBigNodeName = toName;
            if (fromName == toName) { // ??????????????????????????????????????????????????????????????????????????????
                $("#selectInfo").removeAttr("hidden");
            }
            rule.option = $("#rule").val();
            $("#rule").val(rule.option); // ??????????????????
            $("#rule").change(function () {
                rule.option = $("#rule").val();
            });
            // ??????????????????rules??????????????????
            for (var i = 0; i < rules.length; i++) {
                if (rule.fromBigNodeName == rules[i].fromBigNodeName && rule.toBigNodeName == rules[i].toBigNodeName) {
                    rules[i].option = $("#rule").val(); // ?????????rules???????????????????????????????????????????????????option
                } else { // ???????????????????????????????????????
                    rules.push(rule);
                }
            }
            // //????????????????????????????????????
            // $('input[name = "checkbox"]').each(function(){
            //     $(this).removeAttr("checked");
            // });
            // //???????????????????????????
            // for (var i = 0; i < rules.length; i++) {
            //     if (fromName == rules[i].fromBigNodeName && toName == rules[i].toBigNodeName) {
            //         //??????????????????????????????rules[i]
            //         for (var j = 0; j < rules[i].options.length; j++) {
            //             $('[id = "' + rules[i].options[j] + '"]').attr("checked", "checked");
            //         }
            //     }
            // }
            // //????????????????????????????????????????????????
            // $('input[name = "checkbox"]').change(function () {
            //     var id_array = [];
            //     $('input[name = "checkbox"]:checked').each(function(){
            //         id_array.push($(this).val());
            //     });
            //     console.log("???????????????????????????" + id_array);
            //     //????????????????????????????????????
            //     for (var i = 0; i < id_array.length; i++) {
            //         rule.options[i] = id_array[i];
            //     }
            //     //??????????????????rules??????????????????
            //     for (var i = 0; i < rules.length; i++) {
            //         if (rule.fromBigNodeName == rules[i].fromBigNodeName && rule.toBigNodeName == rules[i].toBigNodeName) {
            //             rules[i].options = []; // ?????????????????????
            //             //?????????rules???????????????????????????????????????????????????option
            //             for (var j = 0; j < id_array.length; j++) {
            //                 rules[i].options[j] = id_array[j];
            //             }
            //         } else {
            //             //???????????????????????????????????????
            //             rules.push(rule);
            //         }
            //     }
            // });
        }
    });
});

/**
 * ????????????????????????????????????????????????????????????
 * @type {Array}
 */
var bigNodeConfigArray = new Array();
/**
 * ??????????????????????????????????????????????????????????????????????????????????????????
 * @param data
 */
function initMyModal(data) {
    // data = 'a,b,c';
    var bigNodeNameArray = data.split(',');
    var html = '';
    for (var i = 0; i < bigNodeNameArray.length; i++) {
        html += '<option value="' + bigNodeNameArray[i] + '">' + bigNodeNameArray[i] + '</option>';
        //???????????????????????????????????????
        var bigNodeConfig = new Object();
        bigNodeConfig.nodeName = bigNodeNameArray[i];
        bigNodeConfig.nodeType = null;
        bigNodeConfig.hardwareArchitecture = null;
        bigNodeConfig.operatingSystem = null;
        bigNodeConfig.flavorType = null;
        bigNodeConfig.imageName = null;
        bigNodeConfig.iconUrl = null;
        // bigNodeConfig.innerRules = [];
        bigNodeConfigArray.push(bigNodeConfig);
    }
    // console.log(bigNodeConfigArray);
    $("#bigNodeName").html(html);
    $("#bigNodeName").val(''); // ?????????????????????????????????
    $("#myModal").modal();
}

/**
 * ????????????????????????????????????
 */
$("#bigNodeName").click(function () {
    //?????????????????????????????????
    for (var i = 0; i < bigNodeConfigArray.length; i++) {
        if (bigNodeConfigArray[i].nodeName == $("#bigNodeName").val()) {
            console.log("?????????????????????????????????" + $("#bigNodeName").val());
            //???????????????????????????????????????
            $("#nodeType").val(bigNodeConfigArray[i].nodeType);
            $("#hardwareArchitecture").val(bigNodeConfigArray[i].hardwareArchitecture);
            $("#operatingSystem").val(bigNodeConfigArray[i].operatingSystem);
            $("#nodeConfig").val(bigNodeConfigArray[i].flavorType);
            $("#nodeImage").val(bigNodeConfigArray[i].imageName);
            if ($("#bigNodeName").val().indexOf('GEO') >= 0) {
                bigNodeConfigArray[i].iconUrl = "img/gaogui01.png";
            }
            if ($("#bigNodeName").val().indexOf('LEO') >= 0) {
                bigNodeConfigArray[i].iconUrl = "img/zhonggui01.png";
            }
            if ($("#bigNodeName").val().indexOf('Facility') >= 0) {
                bigNodeConfigArray[i].iconUrl = "img/leida01.png";
            }
            if ($("#bigNodeName").val().indexOf('GroundVehicle') >= 0) {
                bigNodeConfigArray[i].iconUrl = "img/junjian01.png";
            }
            // // ????????????????????????????????????
            // $('input[name = "inlineCheckbox"]').each(function(){
            //     $(this).removeAttr("checked");
            // });
            // //???????????????????????????
            // for (var j = 0; j < bigNodeConfigArray[i].innerRules.length; j++) {
            //     $('[id = "' + bigNodeConfigArray[i].innerRules[j] + '"]').attr("checked", 'checked');
            // }
        }
    }
});

/**
 * ??????????????????????????????????????????????????????????????????
 */
$("#bigNodeConfigOperation").change(function () {
    // console.log("???????????????????????????" + $("#bigNodeName").val());
    for (var i = 0; i < bigNodeConfigArray.length; i++) {
        if ($("#bigNodeName").val() == bigNodeConfigArray[i].nodeName) {
            //???????????????????????????????????????????????????????????????????????????
            bigNodeConfigArray[i].nodeType = $("#nodeType").val();
            bigNodeConfigArray[i].hardwareArchitecture = $("#hardwareArchitecture").val();
            bigNodeConfigArray[i].operatingSystem = $("#operatingSystem").val();
            bigNodeConfigArray[i].flavorType = $("#nodeConfig").val();
            bigNodeConfigArray[i].imageName = $("#nodeImage").val();
            // var id_array = [];
            // $('input[name = "inlineCheckbox"]:checked').each(function(){
            //     id_array.push($(this).val());
            // });
            // // console.log("???????????????????????????" + id_array);
            // for (var j = 0; j < id_array.length; j++) {
            //     bigNodeConfigArray[i].innerRules[j] = id_array[j];
            // }
            // bigNodeConfigArray[i].iconUrl = "img/gaogui01.png"; // ?????????????????????
            console.log("????????????????????????" + bigNodeConfigArray[i]);
        }
    }
});

/**
 * ??????????????????
 */
$("#submit").click(function () {
    // initMyModal();
    //??????????????????????????????
    $.ajax({
        url: '/NetworkSimulation/submitRegulation',
        data: {
            // regulationOption : $("input[name='optionsRadiosinline']:checked").val(),//??????option1,option2,option3?????????
            linkJson : JSON.stringify(links),//???????????????status???json
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {//??????'"A","B","C"'
            console.log("???????????????????????????" + msg);
            initMyModal(msg);
        },
        error: function () {

        }
    });
    //???????????????????????????
    $.ajax({
        url: '/NetworkSimulation/submitBigRules',
        data: {
            rulesJson : JSON.stringify(rules),//????????????????????????
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            console.log(msg);
        },
        error: function () {

        }
    });
});

/**
 * ?????????????????????
 */
$("#addNode").click(function () {
    console.log(JSON.stringify(bigNodeConfigArray));
    $.ajax({
        url: '/NetworkSimulation/setBigNodeAttr',
        data: {
            bigNodeConfigArray : JSON.stringify(bigNodeConfigArray),
            s_id : $.getUrlParam("scenarioId")
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            $.alert(msg);
            $("#myModal").modal('hide');
            // ?????????100s???????????????
            $(function() {
                var progressbar = $( "#progressbar" ),
                    progressLabel = $( ".progress-label" );
                progressbar.progressbar({
                    value: false,
                    change: function() {
                        progressLabel.text( progressbar.progressbar( "value" ) + "%" );
                    },
                    complete: function() {
                        window.parent.location.reload();
                        window.close();
                        progressLabel.text( "?????????" );
                    }
                });
                function progress() {
                    var val = progressbar.progressbar( "value" ) || 0;
                    progressbar.progressbar( "value", val + 1 );
                    if ( val < 99 ) {
                        setTimeout( progress, 1000 );
                    }
                }
                setTimeout( progress, 3000 );
            });
        },
        error: function () {

        }
    });
});