function getTree() {
    // Some logic to retrieve, or generate tree structure
    var tree = [
        {
            text: "Parent 1",
            state: {
                // checked: true,
                expanded: true
                // selected: true
            },
            nodes: [
                {
                    text: "Child 1"
                },
                {
                    text: "Child 2"
                }
            ]
        },
        {
            text: "Parent 2"
        },
        {
            text: "Parent 3"
        },
        {
            text: "Parent 4"
        },
        {
            text: "Parent 5"
        }
    ];
    return tree;
}

//定义左边的树
$('#tree').treeview({
    data: getTree(),
    // showCheckbox: true,
    multiSelect : false// 不允许多选，因为我们要通过check框来控制
});

//定义右边的树
$("#tree_1").treeview({
    data: getTree(),
    showCheckbox: true,
    highlightSelected: false,
    multiSelect : false// 不允许多选，因为我们要通过check框来控制
});

//选择左边的节点触发事件
$('#tree').on('nodeSelected', function(event, node) {
    // var selectNode = $('#tree').treeview('getSelected', node.nodeId);
    console.log(node);
});

//右边的树有节点被勾选时
$('#tree_1').on('nodeChecked ', function(event, node) {
    if (node.nodes != null) {
        //如果勾选的是父节点，将其子节点全部勾选
        $.each(node.nodes, function(index, value) {
            $("#tree_1").treeview('checkNode', value.nodeId, {
                silent : true
            });
        });
    } else {
        //如果勾选的是子节点
        var parentNode = $("#tree_1").treeview('getParent', node.nodeId);

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
            // 非全选
        }
    }
});

//右边的节点被取消勾选时
$("#tree_1").on('nodeUnchecked ', function (event, node) {
    // 选中的是父节点
    if (node.nodes != null) {
        // 这里需要控制，判断是否是因为市级节点引起的父节点被取消选中
        // 如果是，则只管取消父节点就行了
        // 如果不是，则子节点需要被取消选中
        if (silentByChild) {
            //取消勾选父节点
        } else {
            //取消勾选父节点
            //取消勾选所有子节点
            $.each(node.nodes, function (index, value) {
                $("#tree_1").treeview('uncheckNode', value.nodeId, {
                    silent: true
                });
            });
        }
    } else {
        // 子节点被取消选中
        var parentNode = $("#tree_1").treeview('getParent', node.nodeId);
        //取消勾选父节点
        silentByChild = true;
        $("#tree_1").treeview('uncheckNode', parentNode.nodeId, {
            silent : true
        })
    }
    silentByChild = false;
});