/**
 * Created by zph on 2016/10/9.
 */
/**
 * 点击按钮添加卫星
 */
var addGeoNode = document.getElementById("addGeoNode");
var addLeoNode = document.getElementById("addLeoNode");
var addEarthNode = document.getElementById("addEarthNode");
addGeoNode.onclick = function () {
    addNode("geoNode", "geoIP", "img/gaogui00.png", 20);
};
addLeoNode.onclick = function () {
    addNode("leoNode", "leoIP", "img/zhonggui00.png", 200);
};
addEarthNode.onclick = function () {
    addNode("earthNode", "earthIP", "img/leida00.png", 400);
};

/**
 * 复用函数，向后端发送请求创建虚拟机，如果请求成功，则在界面上创建一个卫星节点图标
 */
function addNode(node, ip, pic, height) {
    var weixing = document.getElementById(node);
    var weixingIP = document.getElementById(ip);
    if (weixingIP.value == ""||weixing.value == "") {
        alert("请填写地址");
    }else{
    	$.ajax({
    		url: 'node/add',// 跳转到 action
    			data: {
    			name: weixing.value,
    			ip: weixingIP.value
    		},
    		type: 'post',
    		dataType: 'json',
    		async: false,
    		success: function (msg) {
    			alert("添加卫星成功！");
    			createNode(weixing.value, parseInt(Math.random() * 700 + 110, 10), height, pic);
    		},
    		error: function () {
    			alert("添加卫星失败，请检查IP地址是否重复！");
    		}
    	});
    }
}

//创建节点函数
function createNode(name, X, Y, pic) {
    var node = new JTopo.Node(name);
    node.setLocation(X, Y);
    node.fontColor = "0,0,0";
    node.setImage(pic, true);
    node.shadow = "false";
    node.alpha = 1;
    scene.add(node);
}