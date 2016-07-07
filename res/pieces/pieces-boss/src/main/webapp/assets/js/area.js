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
		var def = $wrap.val();
		console.log("def:"+def)
		$.each(citys[pid], function(i, item){
			var selected = item.id == def ? ' selected' : '';
			arr.push('<option value="', item.id, '"', selected, '>', item.areaname, '</option>');
		});
		$wrap.find('select:gt(0)').remove();
		$wrap.append(arr.join(''));
	}

	$province.on('change', function() {
		var val = $(this).val();
		getArea(val, $city);
	});

	$city.on('change', function() {
		var val = $(this).val();
		getArea(val, $area);
	});

	getArea('', $province);

})
