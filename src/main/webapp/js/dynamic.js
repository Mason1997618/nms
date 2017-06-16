/**
 * Created by 包子 on 2016/8/19.
 */
//设置canvas画布大小
var canvas = document.getElementById('canvas');
var content = document.getElementById('content');
window.onload = window.onresize = function () {
    canvas.width = content.offsetWidth;
    canvas.height = content.offsetHeight - 50;
};

var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
var scene = new JTopo.Scene(stage); // 创建一个场景对象

/**
 * 右上角的选中状态
 */
var selectEle = document.getElementById("selectEle");
scene.click(function () {
    var elements = scene.selectedElements;
    if (elements[0] == undefined) {
        selectEle.innerHTML = "none";
    } else {
        selectEle.innerHTML = elements[0].text;
    }
});

/**
 * 添加连线
 */
var addLink = document.getElementById("addLink");
addLink.onclick = function () {
    var fromNodeIP = document.getElementById("fromNodeIP");
    var toNodeIP = document.getElementById("toNodeIP");
    //找到链路的两个节点元素
    var fromNode = document.getElementById("fromNode");
    var fromNode1 = scene.findElements(function (e) {
        return e.text == fromNode.value;
    });
    var toNode = document.getElementById("toNode");
    var toNode1 = scene.findElements(function (e) {
        return e.text == toNode.value;
    });
    //拿到链路的与两个节点通信的ip字段
//    var linkNodeIP = document.getElementById("linkNodeIP");//与节点通信的ip
//    var linkIP = document.getElementById("linkIP");//外网192.168.2
    //newLink(fromNode1[0], toNode1[0], fromNode.value + ":" + fromNodeIP.value + "->" + linkNodeIP.value + "->" + toNode.value + ":" + toNodeIP.value, "0,200,255");
     $.ajax({
         url: 'link/add',// 跳转到 action
         data: {
             fromNode: fromNode.value,
             fromNodeIP: fromNodeIP.value,
             toNode:toNode.value,
             toNodeIP:toNodeIP.value,
         },
         type: 'post',
         dataType: 'json',
         async: false,
         success: function (msg) {
             alert("添加链路成功！");
             newLink(fromNode1[0], toNode1[0], fromNode.value + ":" + fromNodeIP.value + "->" + toNode.value + ":" + toNodeIP.value, "0,200,255");
         },
         error: function () {
             alert("添加链路失败，请检查IP地址是否重复！");
         }
     });


};

/**
 * 删除选中元素
 */
var remove = document.getElementById("remove");
remove.onclick = function () {
    var elements = scene.selectedElements;
    for (var i = 0; i < elements.length; i++) {
        if (elements[i] instanceof JTopo.Node) {
            alert("删除一个节点" + elements[i].text);
        }
        if (elements[i] instanceof JTopo.Link) {
            alert("删除一个链路" + elements[i].text);
            alert(elements[i].nodeA.text);
            alert(elements[i].nodeZ.text);
        }
        scene.remove(elements[i]);
    }
};

/**
 * 创建连线
 */
    //连线
var beginNode = null;
var tempNodeA = new JTopo.Node('tempA');
tempNodeA.setSize(1, 1);

var tempNodeZ = new JTopo.Node('tempZ');
tempNodeZ.setSize(1, 1);

var link1 = new JTopo.Link(tempNodeA, tempNodeZ);

var flag = 0;//1,2,3表示三种链路，4表示停止添加链路--------------------->用于添加链路类型时候的判断
scene.mouseup(function (e) {

    if (e.target != null && e.target instanceof JTopo.Node && flag != 0) {
        if (beginNode == null) {
            beginNode = e.target;
            scene.add(link1);
            tempNodeA.setLocation(e.x, e.y);
            tempNodeZ.setLocation(e.x, e.y);
        } else if (beginNode !== e.target) {
            var endNode = e.target;
            alert("添加一条链路，这里应该蹦出一个div表示链路");
            if (flag == 1) {
                newLink(beginNode, endNode, "链路1", "0,200,255");
            } else if (flag == 2) {
                newLink(beginNode, endNode, "链路2", "0,0,0");
            } else if (flag == 3) {
                newLink(beginNode, endNode, "链路3", "178,34,34");
            }
            beginNode = null;
            scene.remove(link1);
        } else {
            beginNode = null;
        }
    } else {
        scene.remove(link1);
    }
});

scene.mousedown(function (e) {
    if (e.target == null || e.target === beginNode || e.target === link1) {
        scene.remove(link1);
    }
});
scene.mousemove(function (e) {
    tempNodeZ.setLocation(e.x, e.y);
});


//创建连线函数
function newLink(nodeA, nodeZ, text, color) {
    var link = new JTopo.Link(nodeA, nodeZ, text);
    link.fontColor = "0,0,0";
    link.lineWidth = 3; // 线宽
    //link.dashedPattern = dashedPattern; // 虚线
    link.bundleOffset = 60; // 折线拐角处的长度
    link.bundleGap = 20; // 线条之间的间隔
    link.textOffsetY = -3; // 文本偏移量（向下3个像素）
    link.strokeColor = color;
    link.arrowsRadius = 10;//必须添加箭头，表示流向
    scene.add(link);
    return link;
}
