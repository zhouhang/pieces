<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的珍药材</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />    
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/store_foreground.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<style>
		tr.over td {  
	    	background: #dedede; /*这个将是鼠标高亮行的背景色*/  
	    	cursor:pointer;
	    } 
	</style>
	<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
</head>
<body>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
  	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
  	<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
  	
<#include "common/header.ftl">
<div class="area-1200 clearfix">
    <!-- 我的仓单左侧 -->
    <#include "common/left.ftl">
            <div class="hy-right fr">
               <div class="need-to-know" id="info_1">
           			<h1>
            			<a id="info_link_1" href="#"><img src="${RESOURCE_JS}/images/jzt-user-center/down.png"></a>
            			仓单挂牌流程与须知
            		</h1>
                	<div class="wire_content">
	                	<ul>
	                       <li><span>卖家挂牌</span></li>
	                       <li class="wire"></li> 
	                       <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                       <li class="intro">1. 填写挂牌信息<br/>
	                            2. 提交后，等待买家摘牌<br/>
	                            3. 买家摘牌后，生成订单，需支付保证金，才可锁定药材数量，如买方违约，将扣除保证金作为违约金
				           </li>
				       </ul>
	                   <ul>
	                    	<li><span>买家摘牌</span></li>
	                        <li class="wire"></li>
	                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 买家摘牌后需缴纳保证金<br/>
	                            2. 买家支付保证金后，即锁定相应数量药材，如买方违约，将扣除保证金作为违约金
				            </li>
	                   </ul>
	                   <ul>
	                    	<li><span>仓库备货</span></li>
	                        <li class="wire"></li>
	                        <li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 仓库根据买家下单数量，按实际情况进行备货</li>
	                    </ul>
					   <ul>
	                    	<li><span>完成交易</span></li>
	                        <li class="wire"></li><li><img src="${RESOURCE_JS}/images/jzt-user-center/Listing.png"></li>
	                        <li class="intro">1. 买家支付保证金后10个工作日内必须支付剩余货款，否则视为违约，将扣除保证金支付给卖家<br/>
	                            2. 买家支付货款后，药材将生成新的仓单，交易完成</li>
	                    </ul>
                	</div>
                </div>
                <div class="explain"><h1>挂牌说明</h1>
	               <p>1. 挂牌交易需填写：标题、挂牌数量、挂牌价格、最低起订、挂牌期限、是否提供发票及提供发票价格、商品详细描述。</p>
	               <p>2. 挂牌信息提交后，会有相关工作人员进行审核，审核通过后，挂牌信息显示在商品列表中。</p>
	               <p>3. 挂牌交易基于仓单，请先选择要挂牌的仓单。
				</div>
				<!-- 选择仓单 弹层(开始)  -->
				
				<div class="explain">
					<h1>选择仓单</h1>
				</div>
			    <div class="box">
			            <form id="conditionForm">
			                <ul class="stage-search" style="background-color:#FFF;">
			                    <li>
			                      <span>所在仓库：</span>
				                    <select class="text-store text-select" id="selectWareHouse"></select>
				                    <span>入库时间：</span>
				                    <input type="text" class="text-store text-6 mr10 Wdate" id="datetimepicker1" value=""  />至<input type="text" class="text-store text-6 ml10 Wdate" id="datetimepicker2"/>
									<span style="margin-left:42px;">品种：</span><input class="text-store text-5" type="text" id="breedName"/>
									<span><a class="col_999" href="javascript:void(0);" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');">清空</a></span><input type="button" class="btn-red ml10" id="btn_queryWhlist" value="查询" />
			                    </li>
			         
			                </ul>
			            </form>
			            <div id="wlList">
			               <table class="table-store" id="table_whlist" width="100%" cellpadding="1" cellspacing="1">
			                    <tr row-head-title='wlRowHeadTitle' id="tr_id">
			                        <th width="100">品名</th>
			                        <th width="150">仓单号</th>
			                        <th width="100">总重</th>
			                        <th width="100">所在仓库</th>
			                        <th width="100">入库日期</th>
			                    </tr>
			                </table>
			            </div>
			        </div>    
				<!--选择仓单(结束)  -->   
       </div>
   </div>
</div>
<#include "common/footer.ftl">

<script type="text/javascript">

   $(function(){
	    //查询 	
	   $('#btn_queryWhlist').click(function(){
	   	var data = {
	   		selectWareHouse:$('#selectWareHouse').val(),
	   		datetimepicker1:$('#datetimepicker1').val(),
	   		datetimepicker2:$('#datetimepicker2').val(),
	   		breedName:$('#breedName').val()
	   	};
	   	var firstTable = $('#wlList').pagination('wlList');
	   	firstTable.refresh('/listing/queryWList',data,'post');
	   
	   //add by fanyuna 为了解决 仓库名称改变，页面不刷新，直接点查询时，查询条件 所在仓库 显示的名称也跟着变化	
	   	ajaxWareHouse($('#selectWareHouse').val());
	   });

	    $("#add_detailst").dialog({
	        autoOpen: false,
	        width: 800
	    });
	    
	  //日期控件
		$('#datetimepicker1').click(function(){
			WdatePicker({
				dateFmt:'yyyy/MM/dd',
				maxDate:'#F{$dp.$D(\'datetimepicker2\',{d:-1});}',
				readOnly:true
			});
		});
		$('#datetimepicker2').click(function(){
			WdatePicker({
				dateFmt:'yyyy/MM/dd',
				minDate:'#F{$dp.$D(\'datetimepicker1\',{d:1});}',
				readOnly:true
			});
		});
		
		
	    $('#info_link_1').on('click',function(){
	        $(this).parents('#info_1').toggleClass('toogle');
	        if($(this).parents('#info_1').hasClass('toogle')){
	            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/up.png');
	        }else{
	            $(this).children('img').attr('src','${RESOURCE_JS}/images/jzt-user-center/down.png');
	        }
	    }) 

		//初始化数据
		ajaxWareHouse('');
	    ajaxPage();
	   
   });
   
   //update by fanyuna 此参数增中selected选中仓库  参数
   function ajaxWareHouse(selected){
  		//遍历仓库
       $('#selectWareHouse').empty();
       $.ajax({
       	 url: "/listing/queryWl",
			 type: 'post', 
			 dataType:"json",
			 success:function(data){
			 	var listBusiWareHouseVo= data.listBusiWareHouseVo;
			 	$('#selectWareHouse').append($("<option value=''>").text('全部仓库'));
			 	for(var i=0;i<listBusiWareHouseVo.length;i++){
			 		var busiWareHouseVo=listBusiWareHouseVo[i];
			 		var wareHouseId=busiWareHouseVo.wareHouseId;
			 		var wareHouseName=busiWareHouseVo.wareHouseName;
			 		var option = $("<option value='"+wareHouseId+"'>").text(wareHouseName);
			 		//add by fanyuna 为了解决 仓库名称改变，页面不刷新，直接点查询时，查询条件 所在仓库 显示的名称也跟着变化	
			 		if(selected==wareHouseId){
			 			option = $("<option value='"+wareHouseId+"' selected='selected' >").text(wareHouseName);
			 		}
		     		$('#selectWareHouse').append(option);
			 	 }
			  }
       });	
  	};
  	
    
	function display_table(link_id,id){ 
		var link_id = document.getElementById(link_id); 
		var id = document.getElementById(id); 
		if(id.style.display=='none'){ 
		id.style.display =""; 
		link_id.innerHTML = "隐藏信息"; 
		}else{ 
		id.style.display ="none"; 
		link_id.innerHTML = "显示信息"; 
		} 
	}
	
	
	function ajaxPage(){
    	//调用分页方法，
		var firstTable = $('#wlList').pagination({
			targetId: 'wlList',
			type: 'post',
			url: '/listing/queryWList',
			init:  true,
			rowHtml: function(rowData) {
				var wlid = rowData.wlid;
				var html = '<tr>';
				var time=new Date(rowData.wlrkdate).format("yyyy-MM-dd");
				html+="<td>"+rowData.breedname+"</td>";
				html+="<td>"+wlid+"</td>";
				html+="<td>"+rowData.wltotal+rowData.dictvalue+"</td>";
				html+="<td>"+rowData.warehousename+"</td>";
				html+="<td>"+time+"</td></tr>";
				return html;
			},
			afterRefresh: function(rowData){
				$("#table_whlist tr").mouseover(function(){$(this).addClass("over")}),      
				$("#table_whlist tr").mouseout(function(){$(this).removeClass("over")}); 
				$("#table_whlist tr").each(
						function(){
							$(this).click(function(){
								clickTab($(this).find('td:eq(1)').text());
							});	
							
						}	
				);
			},
			emptyRow : function(page){
				var html = '<tr><td colspan="5" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td></tr>';
				$("#tr_id").after(html);
			}
		})	
  	}
    
    //点击每一行，将数据查询出来返回到用户的页面
    function  clickTab(id){
    	window.location.replace("/listing/add?wlid="+id);
    }
	//日期转换	    
	Date.prototype.format = function(format){
		var o = {
			"M+" : this.getMonth()+1, //month
			"d+" : this.getDate(), //day
			"h+" : this.getHours(), //hour
			"m+" : this.getMinutes(), //minute
			"s+" : this.getSeconds(), //second
			"q+" : Math.floor((this.getMonth()+3)/3), //quarter
			"S" : this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		}
		for(var k in o) {
			if(new RegExp("("+ k +")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
			}
		}
		return format;
    } 
	
	
   
</script> 

</body>
</html>