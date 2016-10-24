/**
 * Created by kevin1 on 10/20/16.
 * 常用工具代码
 */
(function($) {

    /**
     * 表单序列化为对象
     * @returns {{}}
     */
    $.fn.serializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function(){
            if (o[this.name]){
                if(!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }

    /**
     * 获取参数值
     * @param name 参数名称
     * @returns
     */
    $.fn.getParamValue=function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    /**
     * 获取参数列表
     * @returns {}
     */
    $.fn.getParams=function() {
        var url = window.location.search; //获取url中"?"符后的字串
        var params = {};
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                params[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return params;
    }

    /**
     * 把url 后面的参数赋值到对应表单
     * @returns {{}}
     */
    $.fn.initByUrlParams=function() {
        var url = window.location.search; //获取url中"?"符后的字串
        var params = {};
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                var name = strs[i].split("=")[0];
                var value = decodeURI(strs[i].split("=")[1]);
                $("[name="+name+"]").val(value);
            }
        }
        return params;
    }

}(jQuery));