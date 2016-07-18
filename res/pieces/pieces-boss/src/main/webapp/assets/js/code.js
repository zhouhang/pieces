/**
 * Created by kevin1 on 7/18/16.
 */

(function($) {
    $.fn.code = function (options) {
        var url = $.fn.code.settings.url;
        var $this = $(this);
        $.getJSON(url,{beedId:options.beedId, typeId:options.typeId}, function (data){
            if (data.status === "y") {
                var html = "";
                $.each(data.data, function(k, v){
                   html += "<option value='"+v.id+"'>"+ v.name+"</option>";
                });
                $this.html(html);
            }
        })
    }
    $.fn.code.type = {
        COMMODITY_SPECIFICATIONS: 10000,//"切制规格"
        COMMODITY_PLACE: 10001,//"原药产地"
        COMMODITY_LEVEL: 10002,//"等级"
        COMMODITY_executiveStandard:10003 //执行标准
    }

    $.fn.code.settings = {
        url:"/code/query"
    }
})(jQuery);
