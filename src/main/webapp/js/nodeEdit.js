/**
 * Created by sjm on 2017/6/21.
 */
//预读
$(document).ready(function () {
    alert($.getUrlParam('nodeName'));

    $.ajax({
        url: 'node/getNode',
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

(function($){
    $.getUrlParam = function(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }
})(jQuery);