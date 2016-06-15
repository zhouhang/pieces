<!-- 前台分页 -->
<#macro pages page form="" action="" data="" type="" expression="" nullmsg="">
	<div class="page-file pageup">
		<#if page.totalRecord == 0>
			<span>${nullmsg!""}</span>
		<#else>
			<#assign num_display_entries=5>
			<#assign num_edge_entries=2>
			<#assign ne_half=((num_display_entries/2)?ceiling)>
			<#assign upper_limit=(page.totalPage-num_display_entries)>
			<#assign start=0>
			<#if page.pageNo gt ne_half>
				<#if upper_limit gt (page.pageNo-ne_half)>
					<#if (page.pageNo-ne_half) gt 0>
						<#assign start=page.pageNo-ne_half>
					<#else>
						<#assign start=0>
					</#if>
				<#else>
					<#if upper_limit gt 0>
						<#assign start=upper_limit>
					<#else>
						<#assign start=0>
					</#if>
				</#if>
			</#if>
			
			<#assign end=0>
			<#if page.pageNo gt ne_half>
				<#if page.totalPage gt (page.pageNo+ne_half)>
					<#assign end=page.pageNo+ne_half>
				<#else>
					<#assign end=page.totalPage>
				</#if>
			<#else>
				<#if page.totalPage gt num_display_entries>
					<#assign end=num_display_entries>
				<#else>
					<#assign end=page.totalPage>
				</#if>
			</#if>
			<#-- 上一页 -->
			<#if page.pageNo gt 1>
				<a id="_pre_page" href="#" class="prev">上一页</a>
            	<input type="hidden" value="${page.prevPage}" />
            <#else>
            	<a class="prev disab" href="javascript:void(0);" title="已是首页">上一页</a>
			</#if>
			
			<#-- starting points -->
			<#if (start gt 0) && (num_edge_entries gt 0)>
				<#assign startPoint=0>
				<#if num_edge_entries gt start>
					<#assign startPoint=start>
				<#else>
					<#assign startPoint=num_edge_entries>
				</#if>
				<#list 0..startPoint as point>
					<#if point gte startPoint>
						<#break>
					</#if>
					<a id="_page_no" href="#" <#if (point + 1) = page.pageNo>class="show"</#if>>${point + 1}</a>
				</#list>
				<#if num_edge_entries lt start>
					<span>...</span>
				</#if>
			</#if>
			
			<#list start..end as num>
				<#if num gte end>
					<#break>
				</#if>
				<a id="_page_no" href="#" <#if (num + 1) = page.pageNo>class="show"</#if>>${num + 1}</a>
			</#list>
			
			<#-- ending points -->
			<#if (end lt page.totalPage) && (num_edge_entries gt 0)>
				<#if (page.totalPage - num_edge_entries) gt end>
					<span>...</span>
				</#if>
				<#assign endPoint=0>
				<#if (page.totalPage - num_edge_entries) gt end>
					<#assign endPoint=page.totalPage - num_edge_entries>
				<#else>
					<#assign endPoint=end>
				</#if>
				<#list endPoint..page.totalPage as point>
					<#if point gte page.totalPage>
						<#break>
					</#if>
					<a id="_page_no" href="#" <#if (point + 1) = page.pageNo>class="show"</#if>>${point + 1}</a>
				</#list>
			</#if>
			
			<#-- 下一页 -->
			<#if page.pageNo lt page.totalPage>
				<a id="_next_page" href="#" class="next">下一页</a>
		    	<input type="hidden" value="${page.nextPage}"/>
            <#else>
            	<a class="next disab" href="javascript:void(0);" title="已是尾页">下一页</a>
			</#if>
			<span class="c_bbb">共${page.totalPage}页</span><input id="_total_page" type="hidden" value="${page.totalPage}"/>
			<span>跳到&nbsp;&nbsp;<input type="text" class="text fy" id="_go_page" value="${page.pageNo}" onkeyup="this.value=this.value.replace(/\D/g,'')" onpaste="return false;"/>  页</span>
            <span><a id="_go" class="btn-gray">确定</a></span>
		    <script>
		       _data = jQuery.trim("${data!''}");
		       _form = jQuery.trim("${form!''}");
		       _type = jQuery.trim("${type!''}");
		       _action = jQuery.trim("${action!''}");
		       _expression = jQuery.trim("${expression!''}");
		       
		        /**
				 * 分页相关的jQuery脚本。
				 */
				function pager(form, data, action, expression, orderStr, pageSize) {
					this._form = form;
					this._data = data;
					this._action = action;
					this._expression = expression;
					this._orderStr = orderStr;
					this._pageSize = pageSize;
					var _this = this;
					
					this.paginate = function(p) {
						jQuery.ajaxSetup({
									contentType : "application/x-www-form-urlencoded; charset=utf-8"
								})
						if (_this._form == "" && _this._action == "" && _this._expression == "") {
							var url = window.location.href;
							if (url.indexOf("?") != -1) {
								if (url.charAt(url.length - 1) != "&") {
									var goUrl = url + "&pageNo=" + p;
									if (_this._orderStr && _this._orderStr.length > 0) {
										goUrl += "&orderStr="
												+ encodeURIComponent(_this._orderStr);
									}
									if (_this._pageSize && _this._pageSize.length > 0) {
										goUrl += "&pageSize="
												+ encodeURIComponent(_this._pageSize);
									}
									window.location.href = goUrl;
								} else {
									var goUrl = url + "pageNo=" + p;
									if (_this._orderStr && _this._orderStr.length > 0) {
										goUrl += "&orderStr="
												+ encodeURIComponent(_this._orderStr);
									}
									if (_this._pageSize && _this._pageSize.length > 0) {
										goUrl += "&pageSize="
												+ encodeURIComponent(_this._pageSize);
									}
									window.location.href = goUrl;
								}
							} else {
								var goUrl = url + "?pageNo=" + p;
								if (_this._orderStr && _this._orderStr.length > 0) {
									goUrl += "&orderStr=" + encodeURIComponent(_this._orderStr);
								}
								if (_this._pageSize && _this._pageSize.length > 0) {
									goUrl += "&pageSize="
											+ encodeURIComponent(_this._pageSize);
								}
								window.location.href = goUrl;
							}
						} else if (_this._form != "") {
							if (_this._action != "") {
								// TODO;
							}
							
							if (_this._expression != "") {
								$("#" + _this._form).append("<input type='hidden' name='"
										+ _this._expression + "' value='" + p + "' />");
							} else {
								$("#" + _this._form + " input[type=hidden][name=pageNo]")
										.remove();
								$("#" + _this._form)
										.append("<input type='hidden' name='pageNo' value='"
												+ p + "' />");
							}
							if (_this._orderStr && _this._orderStr.length > 0) {
								$("#" + _this._form)
										.append("<input type='hidden' name='orderStr' value='"
												+ _this._orderStr + "' />");
							}
							if (_this._pageSize && _this._pageSize.length > 0) {
								$("#" + _this._form + " input[type=hidden][name=pageSize]").remove();
								$("#" + _this._form)
										.append("<input type='hidden' name='pageSize' value='"
												+ _this._pageSize + "' />");
							}
							
							if (_this._data != "") {
								$("#" + _this._data).load(_this._action,
										$("#" + _this._form).serializeArray());
							} else {
								$("#" + _this._form).submit();
							}
						}
					}
				
					
					$("a[id=_pre_page],a[id=_next_page]").bind("click", function() {
						
								_this.paginate($.trim($(this).next().val()));
							});
					$("a[id=_none_pre_page],a[id=_none_next_page]").bind("click", function() {
								return false;
							});
					$("a[id=_page_no]").bind("click", function() {
								if($(this).data('pageno')){
									_this.paginate($.trim($(this).data('pageno')));
								} else {
									_this.paginate($.trim($(this).text()));
								}
							});
					$("a[id=_go]").bind("click", function() {
						
						var p = $.trim($("input[id=_go_page_" + _this._form + "]").val());
						var pTotal = $.trim($("input[id=_total_page_" + _this._form + "]")
								.val());
						if (p=='') {
							alert("\u8bf7\u8f93\u5165\u9875\u6570");			
							return;
						}
						if (!/^[0-9]*[1-9][0-9]*$/.test(p)) {
							alert("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u9875\u6570");			
							return;
						}
						if (parseInt(p) <= 0) {
							p = "1";
						} else if (parseInt(p) >= parseInt(pTotal)) {
							p = pTotal;
						}
						_this.paginate(p);
						return false;
					});
					
					$("input[id=_go_page_" + _this._form + "]").off("keydown");
					
					$("input[id=_go_page_" + _this._form + "]").on("keydown",
							function(event) {
								if (event.keyCode == 13) {
									if ($("input[id=_go_page_" + _this._form + "]").prev("a")
											.size() > 0) {
										$($("input[id=_go_page_" + _this._form + "]").prev("a")[0])
												.click();
									}
									return false;
								}
							});
					
					// //排序处理
					$(".list-order-field").bind("click", function() {
								var orderField = $(this).prop("id");
								var order = "asc"
								if (orderField && orderField.length > 0) {
									if (_this._orderStr && _this._orderStr.length > 0) {
										var parts = _this._orderStr.split(" ");
										if (orderField == parts[0]) {
											if (parts[parts.length - 1] == "asc") {
												order = "desc";
											} else {
												order = "asc";
											}
										} else {
											order = "asc";
										}
									} else {
										order = "asc";
									}
									_this._orderStr = orderField + " " + order;
									_this.paginate("1"); // 排序返回到第一页
								}
							});
				    $("#_page_size").bind("change",function(){
				    	_this._pageSize = $(this).val();
				    	_this.paginate("1"); // 排序返回到第一页
				    });
					
					if (_this._orderStr && _this._orderStr.length > 0) { // 处理样式
						var parts = _this._orderStr.split(" ");
						if (parts.length > 1) {
							$(".list-order-field[id=" + parts[0] + "]")
									.addClass("list-order-field-on-" + parts[1]);
						}
					}
					
				
					$("a[id=_pre_page]").prop("id", "_pre_page_already");
					$("a[id=_next_page]").prop("id", "_next_page_already");
					$("a[id=_none_pre_page]").prop("id", "_none_pre_page_already");
					$("a[id=_none_next_page]").prop("id", "_none_next_page_already");
					$("a[id=_page_no]").prop("id", "_page_no_already");
					$("a[id=_go]").prop("id", "_go_already");
					$("input[id=_go_page]").prop("id", "_go_page_" + _this._form);
					$("input[id=_total_page]").prop("id", "_total_page_" + _this._form);
					$("select[id=_page_size]").prop("id", "_page_size_" + _form);
				}
				
				$(function() {
					try {
				
						if ($("#_page_orderStr_" + _form).size() > 0) {
							_orderStr = $("#_page_orderStr_" + _form).val();
						} else {
							_orderStr = $("#_page_orderStr").val();
							$("#_page_orderStr").prop("id", "_page_orderStr_" + _form);
						}
						
						if ($("#_page_size_" + _form).size() > 0) {
							_pageSize = $("#_page_size_" + _form).val();
						} else {
							_pageSize = $("#_page_size").val();
							
						}
						
						new pager(_form, _data, _action, _expression, _orderStr, _pageSize);
					} catch (e) {
					}
				});
			</script>
		</#if>
	</div>
</#macro>