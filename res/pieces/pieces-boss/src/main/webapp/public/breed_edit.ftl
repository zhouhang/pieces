<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改品种-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>品种信息</dt>
                    <dd>
                        <a class="curr" href="/breed/list">基本信息</a>
                        <a href="/breed/commodity?breeId=${breed.id }">商品排序</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/breed/save" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>修改品种 “${breed.name }”</h3>
                        <div class="extra">
                            <a  class="btn btn-gray" href="/breed/list">返回</a>
                            <@shiro.hasPermission name="breed:delete">
                            <button id="delete" type="button" class="btn btn-gray">删除</button>
                            </@shiro.hasPermission>
                            <button type="submit" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>基本信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <input type="hidden" value="${breed.id }"  name="id" id="id">
                                <div class="txt">
                                    <i>*</i>所属分类：
                                </div>
                                <div class="cnt">
                                    <select name="classifyId" id="classifyId" data-value="${breed.classifyId}" class="wide">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>原药品种：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${breed.name }" autocomplete="off" name="name" id="name" placeholder="">                            
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>品种别名：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="aliases" class="ipt ipt-mul">${breed.aliases }</textarea>
                                    <span class="tips">多个别名请用逗号隔开</span>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    关键字：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="keyWord" id="keyWord" class="ipt ipt-mul">${breed.keyWord!}</textarea>
                                    <span class="tips">关键字个数不超过5个，每个关键字不超过8个汉字</span>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    描述：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="intro" id="intro" class="ipt ipt-mul">${breed.intro!}</textarea>
                                    <span class="tips">描述控制在80个汉字之内，160个字符之间</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                	this.getCategory($("#classifyId"));
                    this.formValidate();
                    this.delete();
                },
                formValidate: function() {
                	$("#myform").validator({
                		rules: {
                			aliases: [/^[,，\u4E00-\u9FA5]+$/, "请填写中文和大小写逗号"]
                	    },
                        fields: {
                        	classifyId: "required",
                        	name: "required",
                        	aliases: "required,aliases"
                        },
                        valid: function(form) {
            		    	if ( $(form).isValid() ) {
            		    		$.ajax({
            			            url: "/breed/save",
            			            data: $(form).formSerialize(),
            			            type: "POST",
            			            success: function(data){
            			            	$.notify({
            	                            type: 'success', 
            	                            title: data.info,
            	                            delay: 3e3
            	                        });
            			            }
            			        });
            		    	} 
            			}
                    });
                },
                getCategory : function($wrap) {
            		$.ajax({
            			type: 'POST',
            			url: '/category/list',
            			success: function(data) {
            				var arr = [];
            				var def = $wrap.data('value');
            				$.each(data,function(i, item){
            					var selected = item.id == def ? ' selected' : '';
                    			arr.push('<option value="', item.id, '"',selected, '>', item.name, '</option>');
                    		});
                    		$wrap.append(arr.join(''));
            			}
            		});
            	},
                delete: function() {
                    var iid = $("#id").val();
                    $('#delete').on('click', function() {
                        layer.confirm('确认删除该品种？', {icon: 3, title:'提示'}, function(index){
                            layer.close(index);
                            $.ajax({
                                url: "/breed/delete/" + iid,
                                type: "POST",
                                success: function(data){
                                    if(data.status == "y"){
                                        $.notify({
                                            type: 'success', 
                                            title: data.info,
                                            text: '3秒后自动跳转到品种列表页', 
                                            delay: 3e3, 
                                            call: function() {
                                                setTimeout(function() {
                                                    location.href = '/breed/list';
                                                }, 3e3);
                                            }
                                        });
                                    }else{
                                        $.notify({
                                            type: 'error', 
                                            title: data.info, 
                                            delay: 3e3
                                        });
                                    }
                                }
                            });
                        });
                    });
                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>