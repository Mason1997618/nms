/**
 * jquery.sort.js
 * 商品发布-选择分类
 * author: 锐不可挡
 * date: 2016-07-07
**/
/*定义三级分类数据*/
//一级分类
var province = ["卫星网络项目", "自组织网络项目", "固定网络项目", "混合网络项目", "其他"];
//二级分类
var city = [
	["场景11", "场景12", "场景13"],
	["场景21", "场景22", "场景23", "场景24"],
	["场景31", "场景32", "场景33"],
	["场景41", "场景42", "场景43", "场景44"],
	["场景51", "场景52", "场景53"],
];
//三级分类
var district = [
	[
		["节点111", "链路112", "节点113"],
		["链路121", "链路122", "节点123"],
		["节点131", "链路132", "节点133", "链路134", "链路135", "节点136", "链路137", "链路138", "节点139"]
	],

	[
		["链路211", "链路212", "节点213"],
		["链路221", "链路222", "节点223", "链路224", "链路225", "节点226", "链路227", "链路228", "节点229"],
		["节点231", "链路232", "节点233"],
		["链路241", "节点242",]
	],

	[
		["链路311", "链路312", "节点313"],
		["链路321", "链路322", "节点", "链路", "链路", "节点", "链路", "链路", "节点", "节点"],
		["链路331", "链路332", "节点333", "链路334"]
	],

    [
        ["链路411", "链路412", "节点413"],
        ["链路421", "链路422", "节点423", "链路424", "链路425", "节点426", "链路427", "链路428", "节点429"],
        ["节点431", "链路432", "节点433"],
        ["链路441", "节点442",]
    ],

    [
        ["节点511", "链路512", "节点513"],
        ["链路521", "链路522", "节点523"],
        ["节点531", "链路532", "节点533", "链路534", "链路535", "节点536", "链路537", "链路538", "节点539"]
    ]

];
var expressP, expressC, expressD, expressArea, areaCont;
var arrow = " <font>&gt;</font> ";

/*初始化一级目录*/
function intProvince() {
	areaCont = "";
	for (var i=0; i<province.length; i++) {
		areaCont += '<li onClick="selectP(' + i + ');"><a href="javascript:void(0)">' + province[i] + '</a></li>';
	}
	$("#sort1").html(areaCont);
}
intProvince();

/*选择一级目录*/
function selectP(p) {
	areaCont = "";
	for (var j=0; j<city[p].length; j++) {
		areaCont += '<li onClick="selectC(' + p + ',' + j + ');"><a href="javascript:void(0)">' + city[p][j] + '</a></li>';
	}
	$("#sort2").html(areaCont).show();
	$("#sort3").hide();
	$("#sort1 li").eq(p).addClass("active").siblings("li").removeClass("active");
	expressP = province[p];
	$("#selectedSort").html(expressP);
	$("#releaseBtn").removeAttr("disabled");
	document.getElementById("releaseBtn").onclick =  function () {
        location.href="projectEdit.html";
    };
}

/*选择二级目录*/
function selectC(p,c) {
	areaCont = "";
	expressC = "";
	for (var k=0; k<district[p][c].length; k++) {
		areaCont += '<li onClick="selectD(' + p + ',' + c + ',' + k + ');"><a href="javascript:void(0)">' + district[p][c][k] + '</a></li>';
	}
	$("#sort3").html(areaCont).show();
	$("#sort2 li").eq(c).addClass("active").siblings("li").removeClass("active");
	expressC = expressP + arrow + city[p][c];
	$("#selectedSort").html(expressC);
    document.getElementById("releaseBtn").onclick =  function () {
        location.href="index3.html";
    };
}

/*选择三级目录*/
function selectD(p,c,d) {
	$("#sort3 li").eq(d).addClass("active").siblings("li").removeClass("active");
	expressD = expressC + arrow + district[p][c][d];
	$("#selectedSort").html(expressD);
    document.getElementById("releaseBtn").onclick =  function () {
        if((district[p][c][d]+"").indexOf("节点")>=0){
            location.href="nodeEdit.html";
        }
        if((district[p][c][d]+"").indexOf("链路")>=0){
            location.href="linkEdit.html";
        }
    };
}

/*点击下一步*/
$("#releaseBtn").click(function() {
	var releaseS = $(this).prop("disabled");
	if (releaseS == false) {//未被禁用
		//location.href = "商品发布-详细信息.html";//跳转到下一页
	}
});

$("#addProject").click(function () {
	//alert("111");
    $("#myModal").modal();
});