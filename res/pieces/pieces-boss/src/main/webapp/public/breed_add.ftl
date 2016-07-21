<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增品种-boss-饮片B2B</title>
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
                        <a class="curr">基本信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/breed/save" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>新增品种</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:window.location.href='/breed/list'">返回</button>
                            <button type="submit" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>基本信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>所属分类：
                                </div>
                                <div class="cnt">
                                    <select name="classifyId" id="classifyId">
                                        <option value="">--请选择--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>原药品种：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="" autocomplete="off" name="name" id="name" placeholder="">                            
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>品种别名：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="aliases" class="ipt ipt-mul"></textarea>
                                    <span class="tips">多个别名请用逗号隔开</span>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>常见切制规格：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.specelist as specifications>
                                           <label><input type="checkbox" name="spece"  class="cbx" value="${specifications.id }">${specifications.name }</label>
                                    </#list>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>常见原药产地：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.originlist as origin>
                                           <label><input type="checkbox" name="origins"  class="cbx" value="${origin.id }">${origin.name }</label>
                                    </#list>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>等级：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.levellist as level>
                                           <label><input type="checkbox" name="levels"  class="cbx" value="${level.id }">${level.name }</label>
                                    </#list>
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
    <script src="/js/common.js"></script>
    <script>
        var roleAddPage = {
            v: {
            	
            },
            fn: {
                init: function() {
                	this.getCategory($("#classifyId"));
                    this.formValidate();
                },
                formValidate: function() {
                    $("#myform").validator({
                        fields: {
                        	classifyId: "required",
                        	name: "required",
                        	aliases: "required",
                        	specifications: "required",
                        	place: "required",
                        	level:"required"
                        },
                        valid: function(form) {
            		    	if ( $(form).isValid() ) {
            		    		$.ajax({
            			            url: "/breed/save",
            			            data: $(form).formSerialize(),
            			            type: "POST",
            			            success: function(data){
            			            	$('.user-info :input').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
            			            	$.notify({
            	                            type: 'success', 
            	                            title: '新增品种成功。',
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
            				$.each(data,function(i, item){
                    			arr.push('<option value="', item.id, '"', '>', item.name, '</option>');
                    		});
                    		$wrap.append(arr.join(''));
            			}
            		});
            	}
            }
        }
        $(function() {
            roleAddPage.fn.init();
        })
    </script>
</body>
</html>