<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色权限-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" />

</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>角色信息</dt>
                    <dd>
                        <a href="role/info/${role.id}">角色信息</a>
                        <a class="curr" href="role/power/${role.id}">角色权限</a>
                        <a href="role/list/${role.id}">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="" id="myform">
                    <input id="roleId" type="hidden" value="<#if role??>${role.id}</#if>">
                    <div class="title">
                        <h3>修改角色“客服”</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button type="reset" class="btn btn-gray">重置</button>
                            <button id="submit" type="button" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>角色权限</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    选择权限：
                                </div>
                                <div class="cnt">
                                    <label><input type="checkbox" name="" id="" class="cbx">全选</label>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    资源：
                                </div>
                                <div>
                                    <ul id="powerTree" class="ztree"></ul>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>


    <script src="js/jquery.ztree.min.js"></script>

    <script>
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes =[
            { id:1, pId:0, name:"销售额", open:true},
                { id:11, pId:1, name:"订单", open:true},
                    { id:111, pId:11, name:"批量操作", open:true},
                        { id:1111, pId:111, name:"等待", open:true},
                        { id:1112, pId:111, name:"付款通知单", open:true},
                        { id:1113, pId:111, name:"释放", open:true},
                        { id:1114, pId:111, name:"配送", open:true},
                        { id:1115, pId:111, name:"发送销售邮件", open:true},
                        { id:1116, pId:111, name:"备注", open:true},
                        { id:1117, pId:111, name:"发票", open:true},
                        { id:1118, pId:111, name:"接受", open:true},
                        { id:1119, pId:111, name:"发送订单邮件", open:true},
                        { id:11110, pId:111, name:"每页", open:true},
                        { id:11111, pId:111, name:"重新下单", open:true},
                        { id:11112, pId:111, name:"编辑", open:true},
                        { id:11113, pId:111, name:"接受或拒绝的付款方式", open:true},
                        { id:11114, pId:111, name:"取消", open:true},
                        { id:11115, pId:111, name:"创建", open:true},
                { id:12, pId:1, name:"发票", open:true},
                { id:13, pId:1, name:"货运", open:true},
                { id:14, pId:1, name:"付款通知单", open:true},
                { id:15, pId:1, name:"条款和条件", open:true},
                { id:16, pId:1, name:"处理", open:true},
                    { id:161, pId:16, name:"获取", open:true}, 
                { id:17, pId:1, name:"定期支付", open:true},  
                { id:18, pId:1, name:"账单协议", open:true},  
                    { id:181, pId:18, name:"批量操作", open:true},  
                        { id:1811, pId:181, name:"每页", open:true},
                        { id:1812, pId:181, name:"管理", open:true},
                        { id:1813, pId:181, name:"使用账单协议提交订单", open:true},
                { id:19, pId:1, name:"税", open:true},  
                    { id:191, pId:19, name:"客户税类型", open:true},  
                    { id:192, pId:19, name:"商品杂费种", open:true},  
                    { id:193, pId:19, name:"导入/导出杂费率", open:true},  
                    { id:194, pId:19, name:"管理杂费区和杂费率", open:true},  
                    { id:195, pId:19, name:"管理杂费规则", open:true},  
                { id:20, pId:1, name:"External Page Cache", open:true},  
                { id:21, pId:1, name:"控制台", open:true}, 

            { id:2, pId:0, name:"目录", open:true},  
                { id:21, pId:2, name:"评论和评分", open:true},
                    { id:211, pId:21, name:"管理评价", open:true},
                    { id:212, pId:21, name:"客户评论", open:true},
                        { id:2121, pId:212, name:"等待的评论", open:true},
                        { id:2122, pId:212, name:"所有的评论", open:true},
                { id:22, pId:2, name:"标签", open:true},
                    { id:221, pId:22, name:"待审批标签", open:true},
                    { id:222, pId:22, name:"所有的标签", open:true},
                { id:23, pId:2, name:"Google网站地图", open:true},
                { id:24, pId:2, name:"搜索关键词", open:true},
                { id:25, pId:2, name:"管理URL重写", open:true},
                { id:26, pId:2, name:"管理分类", open:true},
                { id:27, pId:2, name:"管理产品", open:true},
                { id:28, pId:2, name:"更新属性", open:true},
                { id:29, pId:2, name:"属性集", open:true},
                    { id:291, pId:29, name:"管理属性集设置", open:true},
                    { id:292, pId:29, name:"管理属性集", open:true},

            { id:3, pId:0, name:"客户", open:true},
                { id:31, pId:3, name:"管理客户", open:true},
                { id:32, pId:3, name:"客户组", open:true},
                { id:33, pId:3, name:"在线客户", open:true},

            { id:8, pId:0, name:"促销", open:true},
                { id:81, pId:8, name:"购物车价格规则", open:true},
                { id:82, pId:8, name:"目录价格规则", open:true},

            { id:4, pId:0, name:"电子杂志", open:true},
                { id:41, pId:4, name:"电子杂志模板", open:true},
                { id:42, pId:4, name:"电子杂志订阅者", open:true},
                { id:43, pId:4, name:"电子杂志列队", open:true},
                { id:44, pId:4, name:"邮件列表错误报告", open:true},

            { id:5, pId:0, name:"CMS", open:true},
                { id:51, pId:5, name:"页面", open:true},
                    { id:511, pId:51, name:"保存页面", open:true},
                    { id:512, pId:51, name:"删除页面", open:true},
                { id:52, pId:5, name:"静态块", open:true},
                { id:53, pId:5, name:"Widgets", open:true},
                { id:54, pId:5, name:"Media Gallery", open:true},
                { id:55, pId:5, name:"投票区", open:true},

            { id:6, pId:0, name:"报表",  open:true},
                { id:61, pId:6, name:"标签", open:true},
                    { id:611, pId:61, name:"商品", open:true},
                    { id:612, pId:61, name:"受欢迎的", open:true},
                    { id:613, pId:61, name:"客户", open:true},
                { id:62, pId:6, name:"搜索关键词", open:true},
                { id:63, pId:6, name:"统计", open:true},
                { id:64, pId:6, name:"评论", open:true},
                    { id:641, pId:64, name:"产品评论", open:true},
                    { id:642, pId:64, name:"客户评论", open:true},
                { id:65, pId:6, name:"客户", open:true},
                    { id:651, pId:65, name:"根据订单数排序的客户", open:true},
                    { id:652, pId:65, name:"根据订单总额排序的客户", open:true},
                    { id:653, pId:65, name:"新账户", open:true},
                { id:66, pId:6, name:"购物车", open:true},
                    { id:661, pId:66, name:"清空购物车", open:true},
                    { id:662, pId:66, name:"购物车中的商品", open:true},
                { id:67, pId:6, name:"商品", open:true},
                    { id:671, pId:67, name:"下载区", open:true},
                    { id:672, pId:67, name:"低库存", open:true},
                    { id:673, pId:67, name:"最受欢迎", open:true},
                    { id:674, pId:67, name:"订购产品", open:true},
                    { id:675, pId:67, name:"畅销商品", open:true},
                { id:68, pId:6, name:"销售额", open:true},
                    { id:681, pId:68, name:"退款总计", open:true},
                    { id:682, pId:68, name:"优惠价", open:true},
                    { id:683, pId:68, name:"发票总计", open:true},
                    { id:684, pId:68, name:"运费", open:true},
                    { id:685, pId:68, name:"销售报表", open:true},
                    { id:686, pId:68, name:"税", open:true},
                    { id:687, pId:68, name:"Paypal结算报告", open:true},
                        { id:6871, pId:687, name:"获取", open:true},
                        { id:6872, pId:687, name:"每页", open:true},

            { id:7, pId:0, name:"系统", open:true},
                { id:71, pId:7, name:"导入/导出", open:true},
                    { id:711, pId:71, name:"数据流-配置文件", open:true},
                    { id:712, pId:71, name:"数据流-高级配置文件", open:true},
                    { id:713, pId:71, name:"导入", open:true},
                    { id:714, pId:71, name:"导出", open:true},
                { id:72, pId:7, name:"索引管理", open:true},
                { id:73, pId:7, name:"权限", open:true},
                    { id:731, pId:73, name:"角色", open:true},
                    { id:732, pId:73, name:"用户", open:true},
                { id:74, pId:7, name:"缓存管理", open:true},
                { id:75, pId:7, name:"Magento Connect", open:true},
                    { id:753, pId:75, name:"Magento Connect管理器", open:true},
                    { id:754, pId:75, name:"扩展包", open:true},
                { id:75, pId:7, name:"web服务", open:true},
                    { id:753, pId:75, name:"SOAP/XML-RPC-Users", open:true},
                    { id:754, pId:75, name:"OAuth Consumers", open:true},
                        { id:7541, pId:754, name:"编辑", open:true},
                        { id:7542, pId:754, name:"删除", open:true}
        ];
        
        
        $(function(){

            var rootTree = null;

            //加载所有资源
            $.ajax({
                url: "/role/resources",
                type: "POST",
                async:false,
                success: function(result){
                    rootTree =  $.fn.zTree.init($("#powerTree"), setting, result);
                }
            });


            $("#submit").click(function(){
                var arrIds = [];
                //获取所有选中的节点
                var checkNodes = rootTree.getCheckedNodes(true);
                $.each(checkNodes,function(index){
                    arrIds.push(this.id)
                })

                if(arrIds.length==0){
                    return false;
                }

                var roleId = $("#roleId").val();

                $.ajax({
                    url: "/role/resources/save",
                    type: "POST",
                    data:{roleId:roleId,resourcesIds:arrIds},
                    success: function(result){


                    }
                });

            })


        });

    </script>
</body>
</html>