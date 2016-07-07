$(function() {
	var $province = $('#province'),
		$city = $('#city'),
		$area = $('#area'),
		province = $province.data('value') || 0, //省份地区编码
		city = $city.data('value') || 0, //城市地区编码
		area = $area.data('value') || 0; //地区地区编码

	var citys = {};

	function getArea(pid, $wrap) {
		if (citys[pid]) {
			toHtml(pid, $wrap);
			return false;
		}
		$.ajax({
			url: 'gen/area',
			data: {'parentId': pid},
			// dataType: 'jsonp',
			success: function(data) {
				citys[pid] = data;
				toHtml(pid, $wrap);
			},
			error: function() {
				setTimeout(function() {
					getArea(pid);
				}, 1e3);
			}
		});
	}

	function toHtml(pid, $wrap) {
		var arr = [];
		var def = $wrap.data('value');
		$wrap.data('value', '');
		$.each(citys[pid], function(i, item){
			var selected = item.id == def ? ' selected' : '';
			arr.push('<option value="', item.id, '"', selected, '>', item.areaname, '</option>');
		});
		$wrap.find('option:gt(0)').remove();
		$wrap.append(arr.join(''));
	}

	$province.on('change', function() {
		var val = $(this).val();
		$city.find('option:gt(0)').remove();
		$area.find('option:gt(0)').remove();
		val &&　getArea(val, $city);
	});

	$city.on('change', function() {
		var val = $(this).val();
		$area.find('option:gt(0)').remove();
		val && getArea(val, $area);
	});

	getArea('', $province);
	city && getArea(province, $city);
	area && getArea(city, $area);

})
