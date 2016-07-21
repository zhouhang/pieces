<!DOCTYPE html>
<html lang="en">
<head>
    <title>新增分类-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>分类信息</dt>
                    <dd>
                        <a class="curr">基本信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/category/save" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>新增分类</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:window.location.href='/category/list'">返回</button>
                            <button type="submit" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>基本信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>分类名称：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="" autocomplete="off" name="name" id="name" placeholder="">                            
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
        var categoryAddPage = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                },
                formValidate: function() {
                    $("#myform").validator({
                        fields: {
                            name: "required"
                        },
                        valid: function(form) {
            		    	if ( $(form).isValid() ) {
            		    		$.ajax({
            			            url: "/category/save",
            			            data: $(form).formSerialize(),
            			            type: "POST",
            			            success: function(data){
            			            	$('#name').val('');
            			            	$.notify({
            	                            type: 'success', 
            	                            title: '新增分类成功。',
            	                            delay: 3e3
            	                        });
            			            }
            			        });
            		    	} 
            			}
                    });
                }
            }
        }
        $(function() {
        	categoryAddPage.fn.init();
        })
    </script>
</body>
</html>