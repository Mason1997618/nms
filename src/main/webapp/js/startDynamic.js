/**
 * Created by User on 2017/4/6.
 */
//定义一个链路类，表示链路的通断状态
function link(fromNode,endNode,status) {
    this.fromNode = fromNode;
    this.endNode = endNode;
    this.status = status;
//    this.toString = function(){
//        return this.fromNode+"-"+this.endNode+"-"+this.status;
//    };
};

//定义一个时间段类，表示该时间段内的链路状态
function timeTable(startTime,linkArray) {
    this.startTime = startTime;
//    this.endTime = endTime;
    this.linkArray = linkArray;
//    this.toString = function(){
//        return this.startTime+" "+this.endTime+" "+this.linkArray;
//    };
};

var addTime = document.getElementById("addTime");
addTime.onclick = function () {
    $("#form").append("<div class='row'> <div class='col-md-3' style='box-shadow:inset 1px -1px 1px #444, inset -1px 1px 1px #444;'>起始时间(min)<input type='text'/> <button type='button' class='btn btn-default add'>加</button><button type='button' class='btn btn-default del'>减</button> </div> <div class='col-md-8' style='box-shadow:inset 1px -1px 1px #444, inset -1px 1px 1px #444;'> <div class='row'> </div> </div> </div>");

    var addNodes = document.getElementsByClassName("add");
    for(var i=0;i<addNodes.length;i++){
        addNodes[i].onclick = (function (i) {
            return function () {
                var parent = addNodes[i].parentNode;
                var brotherNode;
                if(parent.nextSibling.nodeType==3) {
                    brotherNode=parent.nextSibling.nextSibling; //如果浏览器是FireFox
                } else {
                    brotherNode=parent.nextSibling; //如果浏览器是IE
                }
                $(brotherNode.children[0]).append("<div class='col-md-8' style='box-shadow: inset 1px -1px 1px #444, inset -1px 1px 1px #444;'>箭头起始节点：<input type='text'/> 箭头结束节点：<input type='text'/> <select> <option value='1'>连接</option> <option value='0'>断开</option> </select> </div>");
            }
        })(i);
    }

    var delNodes = document.getElementsByClassName("del");
    for(var j=0;j<delNodes.length;j++){
        delNodes[j].onclick = (function (j) {
            return function () {
                var parent = addNodes[j].parentNode;
                var brotherNode;
                if(parent.nextSibling.nodeType==3) {
                    brotherNode=parent.nextSibling.nextSibling; //如果浏览器是FireFox
                } else {
                    brotherNode=parent.nextSibling; //如果浏览器是IE
                }
                $(brotherNode.children[0].lastElementChild).remove();
            }
        })(j);
    }
};

var delTime = document.getElementById("delTime");
delTime.onclick = function () {
    $(document.getElementById("form").lastElementChild).remove();
};

var lastData = new Array();

var submitData = document.getElementById("submitData");
submitData.onclick = function () {
    for( var m=0;m<$(".col-md-4").length;m++){
        // alert($(".col-md-4")[m].children[0].value);//第一个时间 start
        // alert($(".col-md-4")[m].children[1].value);//第一个时间 end
        var time = new timeTable($(".col-md-4")[m].children[0].value,new Array());
        for(var n=0;n<$(".col-md-8")[m].children[0].children.length;n++){
            // alert($(".col-md-8")[m].children[0].children[n].children[0].value);//startNode
            // alert($(".col-md-8")[m].children[0].children[n].children[1].value);//endNode
            // alert($(".col-md-8")[m].children[0].children[n].children[2].value);//status
            var temp = $(".col-md-8")[m].children[0].children[n];
            var links = new link(temp.children[0].value,temp.children[1].value,temp.children[2].value);
            time.linkArray.push(links);
        }
        // alert(time.startTime+" "+time.endTime+" "+time.linkArray.toString());
        lastData.push(time);
    }

    $.ajax({
        url: 'dyna/change',// 跳转到 action
        data: {
            data: JSON.stringify(lastData)
        },
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert("存储数据成功");
            window.close(); //关闭当前页面
        },
        error: function () {
            alert("存储数据失败");
            window.close(); //关闭当前页面
        }
    });
};