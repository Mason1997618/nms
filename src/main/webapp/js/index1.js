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
    }
    selectEle.innerHTML = elements[0].text;
});

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
        }
        scene.remove(elements[i]);
    }
};

/**
 * 创建连线
 */
//连线
var beginNode = null;
var endLastNode = null;
var tempNodeA = new JTopo.Node('tempA');
tempNodeA.setSize(1, 1);

var tempNodeZ = new JTopo.Node('tempZ');
tempNodeZ.setSize(1, 1);

var link1 = new JTopo.Link(tempNodeA, tempNodeZ);

//按钮点击事件
var addlink01 = document.getElementById("addlink01");
var link01 = document.getElementById("link01");
var addlink02 = document.getElementById("addlink02");
var link02 = document.getElementById("link02");
var addlink03 = document.getElementById("addlink03");
var link03 = document.getElementById("link03");
var addlink04 = document.getElementById("addlink04");
var link04 = document.getElementById("link04");
var flag = 0;//1,2,3表示三种链路，4表示停止添加链路--------------------->用于添加链路类型时候的判断
addlink01.onclick = function () {
    flag = 1;
    link01.style.color = "red";
    link02.style.color = "#333";
    link03.style.color = "#333";
    link04.style.color = "#333";
};
addlink02.onclick = function () {
    flag = 2;
    link01.style.color = "#333";
    link02.style.color = "red";
    link03.style.color = "#333";
    link04.style.color = "#333";
};
addlink03.onclick = function () {
    flag = 3;
    link01.style.color = "#333";
    link02.style.color = "#333";
    link03.style.color = "red";
    link04.style.color = "#333";

};
addlink04.onclick = function () {
    flag = 0;
    link01.style.color = "#333";
    link02.style.color = "#333";
    link03.style.color = "#333";
    link04.style.color = "red";

};

scene.mouseup(function (e) {

    if (e.target != null && e.target instanceof JTopo.Node && flag != 0) {
        if (beginNode == null) {
            beginNode = e.target;
            scene.add(link1);
            tempNodeA.setLocation(e.x, e.y);
            tempNodeZ.setLocation(e.x, e.y);
        } else if (beginNode !== e.target) {
            endLastNode = e.target;
            // var endNode = e.target;
            //首先弹出模态框
            $("#linkModal").modal();
            // if (flag == 1) {
            //     newLink(beginNode, endNode, "链路1", "0,200,255");
            // } else if (flag == 2) {
            //     newLink(beginNode, endNode, "链路2", "0,0,0");
            // } else if (flag == 3) {
            //     newLink(beginNode, endNode, "链路3", "178,34,34");
            // }
            // beginNode = null;
            // scene.remove(link1);
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

//节点模态框中的数据
var weixingName = document.getElementById("weixingName");
var outIP = document.getElementById("outIP");
var zone = document.getElementById("zone");

var addNode = document.getElementById("addNode");
//节点模态框中的提交
addNode.onclick = function () {
    //发送执行ajax的请求
	$.ajax({
		url: 'node/add',// 跳转到 action
			data: {
			name: weixingName.value,
			ip: outIP.value,
			zone: zone.options[zone.selectedIndex].value
		},
		type: 'post',
		dataType: 'json',
		async: false,
		success: function (msg) {
			alert("添加卫星成功！");
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
		    //清空模态框内的表格内容
		    weixingName.value = "";
		    outIP.value = "";
		},
		error: function () {
			alert("添加卫星失败，请检查IP地址是否重复！");
		}
	});
	

};

//节点模态框中的数据
var fromNodeIP = document.getElementById("fromNodeIP");
var toNodeIP = document.getElementById("toNodeIP");

//链路模态框
var addLink = document.getElementById("addLink");
addLink.onclick = function () {
    //发送执行ajax的请求
    $.ajax({
        url: 'link/addMTM',// 跳转到 action
        data: {
            fromNode: beginNode.text,
            fromNodeIP: fromNodeIP.value,
            toNode:endLastNode.text,
            toNodeIP:toNodeIP.value,
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert("添加链路成功！");
            if (flag == 1) {
                newLink(beginNode, endLastNode, beginNode.text + ":" + fromNodeIP.value + " -> " + endLastNode.text + ":" + toNodeIP.value, "0,200,255");
            } else if (flag == 2) {
                newLink(beginNode, endLastNode, beginNode.text + ":" + fromNodeIP.value + " -> " + endLastNode.text + ":" + toNodeIP.value, "0,0,0");
            } else if (flag == 3) {
                newLink(beginNode, endLastNode, beginNode.text + ":" + fromNodeIP.value + " -> " + endLastNode.text + ":" + toNodeIP.value, "178,34,34");
            }
            beginNode = null;
            scene.remove(link1);
            //关闭模态框
            $('#linkModal').modal('hide');
            fromNodeIP.value = "";
            toNodeIP.value = "";
        },
        error: function () {
            alert("添加链路失败，请检查IP地址是否重复！");
        }
    });
};

//===================动态变化=============================
function link(fromNode,endNode,status) { //定义一个链路类，表示链路的通断状态
    this.fromNode = fromNode;
    this.endNode = endNode;
    this.status = status;
};
function timeTable(startTime,linkArray) { //定义一个时间段类，表示该时间段内的链路状态
    this.startTime = startTime;
    this.linkArray = linkArray;
};

var startDynamic = document.getElementById("startDynamic");
startDynamic.onclick = function () {
    window.open ('startDynamic.html', '动态参数设置', 'height=500,width=1500,top=10,left=10,toolbar=yes,menubar=yes,scrollbars=yes,resizable=yes,location=yes,status=no');
};
var startChangeDynamic = document.getElementById("startChangeDynamic");

var delData = new Array(); //存放要删除的链路，当再一次添加链路的时候，能够顺利读取ip地址段

function showalert(mess) { //对应链路删除添加函数,mess为一个timeTable
	for(var linkle=0; linkle < mess.linkArray.length; linkle++){
		mess.linkArray[linkle];//这是对应的一条链路
		//如果链路为删除，status为0，要将删除的链路ip地址保存下来，添加的时候要用
		if(mess.linkArray[linkle].status == 0){
			delData.push(getLinkByName(mess.linkArray[linkle].fromNode,mess.linkArray[linkle].endNode));
			var ipaddress = getIPAdrByName(mess.linkArray[linkle].fromNode,mess.linkArray[linkle].endNode)+"";
			$.ajax({
		        url: 'link/delMTM',// 跳转到 action
		        data: {
		            fromNode: mess.linkArray[linkle].fromNode,
		            fromNodeIP: ipaddress.substring(ipaddress.indexOf(":")+1,ipaddress.indexOf(" ->")),
		            toNode: mess.linkArray[linkle].endNode,
		            toNodeIP: ipaddress.substring(ipaddress.lastIndexOf(":")+1,ipaddress.length)
		        },
		        type: 'post',
		        dataType: 'json',
		        async: false,
		        success: function (msg) {	
		        	scene.remove(getLinkByName(msg.fromNode,msg.endNode));
		        },
		        error: function () {
		            alert("删除链路失败");
		        }
		    });
		}
		//如果链路为添加，status为1
		if(mess.linkArray[linkle].status == 1){
			var ipaddress = getIPAdrByName(mess.linkArray[linkle].fromNode,mess.linkArray[linkle].endNode)+"";
		    $.ajax({
		        url: 'link/addMTM',// 跳转到 action
		        data: {
		            fromNode: mess.linkArray[linkle].fromNode,
		            fromNodeIP: ipaddress.substring(ipaddress.indexOf(":")+1,ipaddress.indexOf(" ->")),
		            toNode: mess.linkArray[linkle].endNode,
		            toNodeIP:ipaddress.substring(ipaddress.lastIndexOf(":")+1,ipaddress.length)
		        },
		        type: 'post',
		        dataType: 'json',
		        async: false,
		        success: function (msg) {		  
		            newLink(getNodeByName(msg.fromNode), getNodeByName(msg.endNode), getIPAdrByName(msg.fromNode,msg.endNode), "0,200,255");
		        },
		        error: function () {
		            alert("添加链路失败");
		        }
		    });
		}
	}
} 

function getIPAdrByName(fromNode,endNode){ //通过节点名称拿到ip地址
	for(var delIndex = 0;delIndex < delData.length; delIndex++){
		if(delData[delIndex].nodeA.text == fromNode && delData[delIndex].nodeZ.text == endNode){
            return delData[delIndex].text;
        }
	}
}

function getNodeByName(name) {//通过名称拿到节点
    var nodeElements = scene.getDisplayedNodes();
    for(var nodeIndex = 0;nodeIndex < nodeElements.length; nodeIndex++){
        if(nodeElements[nodeIndex].text == name){
            return nodeElements[nodeIndex];
        }
    }
}

function getLinkByName(fromNode,endNode) {//通过名称拿到链路
    var linkElements = scene.getDisplayedElements();
    for(var linkIndex = 0;linkIndex < linkElements.length; linkIndex++){
        if (linkElements[linkIndex] instanceof JTopo.Link) {
        	if(linkElements[linkIndex].nodeA.text == fromNode && linkElements[linkIndex].nodeZ.text == endNode){
                return linkElements[linkIndex];
            }
        }
    }
}

startChangeDynamic.onclick = function () {
    //从后台拿到所有的数据
	//发送执行ajax的请求
    $.ajax({
        url: 'dyna/get',// 跳转到 action
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (msg) {
        	alert("读取数据成功，开始动态变化 ");//拿到了所有需要变化的数据,界面上开始动态变化
        	//1.设置动态变化时间点
        	for(var dyna = 0;dyna < msg.length;dyna++){
        		window.setTimeout(function (a) {
        	        return function(){
        	        	showalert(a); 
        	        };
        	    }(msg[dyna]),msg[dyna].startTime*60*1000); 
        	}
        },
        error: function () {
            alert("读取数据失败");
        }
    });
};