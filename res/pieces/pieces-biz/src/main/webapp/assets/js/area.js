$(function() {
	var $province = $('#province'),
		$city = $('#city'),
		$area = $('#area'),
		def1 = $province.data('value');

	var citys = {};

	function getArea(pid, $wrap) {
		if (citys[pid]) {
			toHtml(pid, $wrap);
			return false;
		}
		$.ajax({
			url: '/gen/area',
			data: {'parentId': pid},
			// dataType: 'jsonp',
			success: function(data) {
				citys[pid] = data;
				toHtml(pid, $wrap);
			}
		});
	}

	function toHtml(pid, $wrap) {
		var arr = [];
		var def = $wrap.data('value');
		$.each(citys[pid], function(i, item){
			var selected = item.id == def ? ' selected' : '';
			arr.push('<option value="', item.id, '"', selected, '>', item.areaname, '</option>');
		});
		$wrap.find('option:gt(0)').remove();
		$wrap.append(arr.join(''));
	}

	$province.on('change', function() {
		var val = $(this).val();
		getArea(val, $city);
		getArea('9999', $area);
	});

	$city.on('change', function() {
		var val = $(this).val();
		getArea(val, $area);
	});

	getArea('', $province);

})
