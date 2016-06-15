<!-- 分页 -->
<#macro pages page form="" action="" data="" type="" expression="" nullmsg="">
	<div class="page-file fr">
		<#if page.totalRecord == 0>
			<span>${nullmsg!""}</span>
		<#else>
			<a id="_page_no" href="javascript:;" data-pageno="1">首页</a>
		    <#if page.pageNo == 1>
		    	<span class="c_bbb">上一页</span>
		    <#else>
		    	<a id="_pre_page" href="javascript:;">上一页</a>
		    	<input type="hidden" value="${page.prevPage}"/>
		    </#if>
		    <#if page.pageNo == page.totalPage>
		    	<span class="c_bbb">下一页</span>
		    <#else>
		    	<a id="_next_page" href="javascript:;">下一页</a>
		    	<input type="hidden" value="${page.nextPage}"/>
		    </#if>
		    <a id="_page_no" href="javascript:;" data-pageno="${page.totalPage}">末页</a>
		    <input id="_total_page" type="hidden" value="${page.totalPage}"/>
		    <span><a id="_go" href="javascript:;">跳到</a> <input type="text" id="_go_page_${form}" value="${page.pageNo}" class="text fy" onkeyup="this.value=this.value.replace(/\D/g,'')" onpaste="return false;"/> 页</span>
		    <span>当前：<b>${page.pageNo}/${page.totalPage}</b>页</span>
		    <span>本页<b>${page.pageSize}</b>条记录</span>
		    <span>全部<b>${page.totalRecord}</b>条记录</span>
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