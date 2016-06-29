/**
 * Created by liupiao on 2015/2/10.
 */
 
 /**
 * author:��Ư
 * description:�������
 * 
 */
(function($, window, document){ 
	$.fn.searcher = function(options){ 
		var settings;
		//default settings:
		var defaults = {
			init: true,
			onSearch: '',	//��������ʱ����
			onHot: '',
			onBreedLink: '',
			onCategoryLink: '',
			suggestAjaxParams:	'',	//suggestʱ���ajax����
		};
		
		//����settings
		function configSettings(){
			//�̳��ⲿ���ò���
			var settings = $.extend( {}, defaults, options );		
			//��ʼ��
			if(settings.init){
				//////////////////////////////////����suggest//////////////////////////////////
				//������hover�¼�
				$('.search .text-bg').hover(
					function(){
						$('.match-bg h5').length>0?$('.match-bg').show():$('.match-bg').hide();
					},
					function(){
						$('.match-bg').hide();
					}
				); 
				//�����򰴼��¼�
				$('input[type="text"].search-text').keyup(function(){
					if($(this).val()){
						//��������suggest
						toSuggest();
					}else{
						//�����������
						placeSugVal([]);
					}
				});
				//suggest����¼�
				$('.match-bg').delegate('h5','click',function(){
					var _val = $(this).text();
					$('input[type="text"].search-text').val(_val);
					//������������
					$('input[type="button"].search-btn').click();
					$('.match-bg').hide();
				});
				//////////////////////////////////����������ת//////////////////////////////////
				//���������ؼ����б����¼�
				$('a.breedHost').click(function() {
					if(!settings.onHot){
						settings.onHot = function(){
							window.location.href='/search?keyWords='+$(this).html()
						};
					}
					settings.onHot.call(this);
				});
				//ҩ��Ʒ�ֵ���¼�
				$('a.breedLink').click(function() {
					if(!settings.onBreedLink){
						settings.onBreedLink = function(){window.location.href='/search/breed/'+$(this).attr('id')+'?value='+$(this).attr('value')};
					}
					settings.onBreedLink.call(this);
				});
				//ҩ����Ŀ����¼�
				$('a.categorysLink').click(function() {
					if(!settings.onCategoryLink){
						settings.onCategoryLink = function(){window.location.href='/search/category/'+$(this).attr('id')+'?value='+$(this).attr('value');};
					}
					settings.onCategoryLink.call(this);
				});
				//ҩ����Ŀ����¼�
				$('span.categorysLink').click(function() {
					if(!settings.onCategoryLink){
						settings.onCategoryLink = function(){window.location.href='/search/category/'+$(this).attr('id')+'?value='+$(this).attr('value');};
					}
					settings.onCategoryLink.call(this);
				});
				//////////////////////////////////������ť�¼�//////////////////////////////////
				//������ť����¼�
				/*$('input[type="button"].search-btn').click(function() {
					if(!settings.onSearch){
						settings.onSearch = function(){window.location.href='/search?keyWords='+$('input[type="text"].search-text').val()};
					}
					settings.onSearch.call(this);
				});*/

                $('input[type="button"].search-btn').on('click',function() {
                    if(!settings.onSearch){
                        settings.onSearch = function(){window.location.href='/search?keyWords='+$('input[type="text"].search-text').val()};
                    }
                    settings.onSearch.call(this);
                });
                $(document).keydown(function(e) {
                    // 回车键事件
                    if(e.which == 13) {
                        $('input[type="button"].search-btn').click();
                    }
                });

            }
			return settings;
		}
		
		function toSuggest(){
			//��������suggest
			if(!settings.suggestAjaxParams){
				settings.suggestAjaxParams = function(){
					return {url:'/search/suggest/keyWords',type:'POST',data:{keyWords:$('input[type="text"].search-text').val()}};
				};
			}
			var ap = settings.suggestAjaxParams();
			if(ap && ap.url){
				$.ajax({
					 url: ap.url,
					 type: ap.type||'GET', 
					 data: ap.data||{keyWords:$('input[type="text"].search-text').val()},
					 success:function(sdata){
						if(sdata && sdata.page && sdata.page.results){
							placeSugVal(sdata.page.results);
						}else{
							placeSugVal([]);
						}
					 }
				});
			}
		}
		
		//����suggest�������
		function placeSugVal(values){
			var ht = '';
			if(values && values.length>0){
				for(var i=0;i<values.length;i++){
					ht += '<h5>'+values[i].suggestedValue+'</h5>';
				}
			}
			$('.match-bg').html(ht);
			ht===''?$('.match-bg').hide():$('.match-bg').show();
		}
		
		// ���������
		this.each(function() {
			// setp1. ����settings
			settings = configSettings();
		});
		
		// step3. ����
		return settings;
	}; 
})(jQuery,window,document); 		
