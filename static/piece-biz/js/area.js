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
			url: '../json/',
			data: {'parentid': pid},
			url: 'json/' + pid + '.json',
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
		$.each(citys[pid], function(i, item){
			var selected = item.i == def ? ' selected' : '';
			arr.push('<option value="', item.i, '"', selected, '>', item.n, '</option>');
		});
		$wrap.find('option:gt(0)').remove();
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

	getArea('index', $province); 

})
