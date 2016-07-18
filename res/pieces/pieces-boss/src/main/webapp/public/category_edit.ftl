<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改分类-boss-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/css/style.css" />
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
                        <h3><i class="fa fa-chevron-right"></i>修改分类 “${category.name }”</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:window.location.href='/category/list'">返回</button>
                            <button type="button" id="delete" class="btn btn-gray">删除</button>
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
                                <input type="hidden" value="${category.id }" name="id" id="id" >
                                    <input type="text" class="ipt" value="${category.name }" name="name" id="name">                            
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="/js/common.js"></script>
    <script src="/js/layer/layer.js"></script>
    <script>
        var roleAddPage = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                    
                    $('#delete').on('click', function() {
                        var $self = $(this);
                        layer.confirm('确认删除该类别？', {
                            btn: ['确认','取消'] //按钮
                        }, function(index){
                        	layer.close(index);
                        	$.ajax({
        			            url: "/category/delete/" + $("#id").val(),
        			            type: "POST",
        			            success: function(data){
        			            	if(data.status == "y"){
        			            		$.notify({
            	                            type: 'success', 
            	                            title: '删除分类成功。',
            	                            text: '3秒后自动跳转到分类列表页', 
            	                            delay: 3e3, 
            	                            call: function() {
            	                                setTimeout(function() {
            	                                    location.href = '/category/list';
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
                    })
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
            			            	$.notify({
            	                            type: 'success', 
            	                            title: '修改分类成功。',
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
            roleAddPage.fn.init();
        })
    </script>
</body>
</html>