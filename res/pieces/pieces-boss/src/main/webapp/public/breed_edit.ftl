<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改品种-boss-饮片B2B</title>
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
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/breed/save" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>修改品种 “${breed.name }”</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:window.location.href='/breed/list'">返回</button>
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
                                    <select name="classifyId" id="classifyId" data-value="${breed.classifyId}">
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
                                    <i>*</i>常见切制规格：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.specelist as specifications>
                                           <label><input type="checkbox" name="specs"  class="cbx" value="${specifications.id }" <#if specifications.checked>checked="checked"</#if>>${specifications.name }</label>
                                    </#list>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>常见原药产地：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.originlist as origin>
                                           <label><input type="checkbox" name="origins"  class="cbx" value="${origin.id }" <#if origin.checked>checked="checked"</#if>>${origin.name }</label>
                                    </#list>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>等级：
                                </div>
                                <div class="cnt cnt-mul">
                                    <#list breed.levellist as level>
                                           <label><input type="checkbox" name="levels"  class="cbx" value="${level.id }" <#if level.checked>checked="checked"</#if>>${level.name }</label>
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
    <script src="/js/layer/layer.js"></script>
    <script>
        var roleAddPage = {
            v: {},
            fn: {
                init: function() {
                	this.getCategory($("#classifyId"));
                    this.formValidate();
                    
                    $('#delete').on('click', function() {
                        var $self = $(this);
                        layer.confirm('确认删除该品种？', {
                            btn: ['确认','取消'] //按钮
                        }, function(index){
                        	layer.close(index);
                        	$.ajax({
        			            url: "/breed/delete/" + $("#id").val(),
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
                        return false;
                    });
                },
                formValidate: function() {
                	$("#myform").validator({
                		rules: {
                			aliases: [/^[,，\u4E00-\u9FA5]+$/, "请填写中文和大小写逗号"]
                	    },
                        fields: {
                        	classifyId: "required",
                        	name: "required",
                        	aliases: "required,aliases",
                        	spece: "checked",
                        	origins: "checked",
                        	levels: "checked"
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
            	}
            }
        }
        $(function() {
            roleAddPage.fn.init();
        })
    </script>
</body>
</html>