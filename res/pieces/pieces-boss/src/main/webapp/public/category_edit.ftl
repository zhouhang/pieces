<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改分类-boss-上工好药</title>
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
                        <a class="curr" href="/category/list">基本信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/category/save" id="myform">
                    <div class="title">
                        <h3><i class="fa fa-chevron-right"></i>修改分类 “${category.name }”</h3>
                        <div class="extra">
                            <a class="btn btn-gray" href="/category/list">返回</a>
                            <@shiro.hasPermission name="category:delete">
                            <button type="button" id="delete" class="btn btn-gray">删除</button>
                            </@shiro.hasPermission>
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
                            <div class="group">
                                <div class="txt">
                                    关键字：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="keyWord" id="keyWord" class="ipt ipt-mul">${category.keyWord!}</textarea>
                                    <span class="tips">关键字个数不超过5个，每个关键字不超过8个汉字</span>
                                </div>
                            </div>
                            <div class="group">
                                <div class="txt">
                                    描述：
                                </div>
                                <div class="cnt cnt-mul">
                                    <textarea name="intro" id="intro" class="ipt ipt-mul">${category.intro!}</textarea>
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
                    this.formValidate();
                    this.delete();                    
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
            	                            title: data.info,
            	                            delay: 3e3
            	                        });
            			            }
            			        });
            		    	} 
            			}
                    });
                },
                delete: function() {
                    var iid = $("#id").val();
                    $('#delete').on('click', function() {
                        layer.confirm('确认删除该类别？', {icon: 3, title:'提示'}, function(index){
                            layer.close(index);
                            $.ajax({
                                url: "/category/delete/" + iid,
                                type: "POST",
                                success: function(data){
                                    if(data.status == "y"){
                                        $.notify({
                                            type: 'success', 
                                            title: data.info,
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
                    })
                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>