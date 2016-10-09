!(function($) {
    var cacheData = {}; // 缓存城市数据

	$.fn.citys = function(parameter) {
		var defaults = {
            dataUrl       : '/gen/area',                //数据库地址
            dataType      : 'json',                     //数据库类型:'json'或'jsonp'
            provinceField : 'provinceCode',             //省份字段名
            cityField     : 'cityCode',                 //城市字段名
            areaField     : 'areaId',               	//地区字段名
            province      : 0,                          //省份,可以为地区编码或者名称
            city          : 0,                          //城市,可以为地区编码或者名称
            area          : 0,                          //地区,可以为地区编码或者名称
            code          : 0,                          //地区编码,设置后省份、城市、地区编码会被覆盖
            defText       : ['-省-', '-市-', '-区/县-'], // 默认文本
        };
        var options = $.extend({}, defaults, parameter);

        return this.each(function() {
        	var 
        		$this     = $(this),
        		$province = $this.find('select[name="'+options.provinceField+'"]'),
        		$city     = $this.find('select[name="'+options.cityField+'"]'),
        		$area     = $this.find('select[name="'+options.areaField+'"]');

            $province.empty().html('<option value="">' + options.defText[0] + '</option>');
            $city.empty().html('<option value="">' + options.defText[1] + '</option>');
            $area.empty().html('<option value="">' + options.defText[2] + '</option>');
            options.province = $province.data('value');
            options.city     = $city.data('value');
            options.area     = $area.data('value');
            //如果设置地区编码，则忽略单独设置的信息
            if(options.code){   
				options.province = options.code - options.code%1e4;
				options.city     = options.code%1e4 ? options.code - options.code%1e2 : 0;
				options.area     = options.code%1e2 ? options.code : 0;
            }

            var format = {
            	init: function() {
            		this.bindEvent();
                    this.getCityById('', $province, options.province);
                    options.province && this.getCityById(options.province, $city, options.city);
                    options.city && this.getCityById(options.city, $area, options.area);
            	},
            	bindEvent: function() {
                    var self = this;
            		//事件绑定
                    $province.off('change.pickArea').on('change.pickArea',function(){
                        options.province = this.value;
                        $city.empty().html('<option value="">' + options.defText[1] + '</option>');
                        $area.empty().html('<option value="">' + options.defText[2] + '</option>');
                        options.province && self.getCityById(options.province, $city, 0);
                    });
                    $city.off('change.pickArea').on('change.pickArea',function(){
                        options.city = this.value;
                        $area.empty().html('<option value="">' + options.defText[2] + '</option>');
                        options.city && self.getCityById(options.city, $area, 0);
                    });
            	},
                getCityById: function(pid, $select, def) {
                    var self = this;
                    if (cacheData[pid]) {
                        self.initSelect(cacheData[pid], $select, def);
                        return;
                    }
                    $.ajax({
                        url: options.dataUrl,
                        type: 'get',
                        dataType: options.dataType,
                        data: {'parentId': pid},
                        success: function(data) {
                            self.initSelect(data, $select, def);
                            cacheData[pid] = data;
                        }
                    })
                },
                initSelect: function(data, $select, def) {
                    var html = [];
                    $.each(data, function(i, item) {
                        html.push('<option value="', item.id, '">', item.areaname, '</option>');
                    })
                    $select.append(html.join(''));
                    def && $select.val(def)
                }
            }

            format.init();
        })
	}
})(jQuery);


$(function() {
    $('#pickArea').citys({
        provinceField: 'province',
        cityField: 'city',
        areaField: 'areaId'
    });
})