<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>管理员列表-boss-上工好药</title>
</head>

<body class="wrapper">
    <#include "./common/header.ftl">
    <#include "./common/aside.ftl"/>
    <!-- fa-floor start -->
    <div class="content">
        <div class="breadcrumb">
            <ul>
                <li>账号权限</li>
                <li>管理员列表</li>
            </ul>
        </div>

        <div class="box">
            <div class="tools">
                <div class="filter">
                    <form id="searchForm" action="">
                        <label>姓名：</label>
                        <input type="text" name="name" class="ipt" placeholder="请输入">
                        <label>角色：</label>
                        <select name="roleId" id="roleId" class="slt">
                            <option value="">全部</option>
                            <#list roleList as role>
                                <option value="${role.id}">${role.name}</option>
                            </#list>
                        </select>
                        <button id="search" class="ubtn ubtn-blue">搜索</button>
                    </form>
                </div>
                <div class="action-add pb">
                    <button class="ubtn ubtn-blue" id="jaddNewAdmin">新建管理员</button>
                </div>
            </div>

            <div class="table">
                <table>
                    <thead>
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>姓名</th>
                        <th>用户名</th>
                        <th>电话</th>
                        <th>角色</th>
                        <th width="150">创建时间</th>
                        <th width="230" class="tc">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list memberPage.list as member>
                        <tr>
                            <td><input type="checkbox" value="${member.id}"></td>
                            <td>${member.name}</td>
                            <td>${member.username}</td>
                            <td>${member.mobile!""}</td>
                            <td>${member.roleName!""}</td>
                            <td>${member.createDate?datetime}</td>
                            <td class="tc">
                                    <a class="ubtn ubtn-blue jedit" data-id="${member.id}">编辑</a>
                                    <a href="javascript:;" class="ubtn ubtn-gray jdel" data-id="${member.id}">删除</a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#import "./module/pager.ftl" as pager />
        <@pager.pager info=memberPage url="member/index" params=memberParams />
    </div>
</div>
    <!-- 管理员弹出框表单 -->
    <form id="myform" class="hide">
        <div class="fa-form fa-form-layer">
            <input type="hidden" name="id" class="ipt">
            <div class="item">
                <div class="txt"><i>*</i>用户名：</div>
                <div class="cnt">
                    <input type="text" name="username" class="ipt" placeholder="用户名" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>角色：</div>
                <div class="cnt">
                    <select name="roleId" id="roleId" class="slt">
                        <#list roleList as role>
                            <option value="${role.id}">${role.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="item">
                <div class="txt">密码：</div>
                <div class="cnt">
                    <input type="text" name="password" class="ipt" placeholder="密码(修改密码时为空即不修改)" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>姓名：</div>
                <div class="cnt">
                    <input type="text" name="name" class="ipt" placeholder="姓名" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>电话：</div>
                <div class="cnt">
                    <input type="text" name="mobile" class="ipt" placeholder="电话" autocomplete="off">
                </div>
            </div>
            <div class="item">
                <div class="txt"><i>*</i>邮箱：</div>
                <div class="cnt">
                    <input type="text" name="email" class="ipt" placeholder="邮箱" autocomplete="off">
                </div>
            </div>

            <div class="button">
                <button type="submit" class="ubtn ubtn-blue">保存</button>
                <button type="button" class="ubtn ubtn-gray">取消</button>
            </div>
        </div>
    </form>

    <#include "./common/footer.ftl"/>
    <script src="assets/plugins/validator/jquery.validator.min.js"></script>
    <script src="assets/js/jquery.form.js"></script>

    <script>
    //定义根变量
        var _global = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${memberPage.pageNum},
                pageSize:${memberPage.pageSize},
                deleteUrl: '',
                flag: false
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    this.addNewAdmin();
                    this.bindEvent();
                },
                // 管理员新建 or 编辑
                addNewAdmin: function() {
                    var $adminForm = $('#myform'),
                            self = this;

                    $adminForm.validator({
                        fields: {
                            username: '用户名: required',
                            name: '姓名: required',
                            mobile: '电话: required; mobile',
                            email: '邮箱: required; email'
                        },
                        valid: function (form) {
                            if ( $adminForm.isValid() ) {
                                $.ajax({
                                    url: "/member/save",
                                    data: $adminForm.formSerialize(),
                                    type: "POST",
                                    success: function(result){
                                        layer.closeAll();
                                        if (result.status == "200") {
                                            $.notify({
                                                type: 'success',
                                                title: '保存成功',
                                                text: result.msg,
                                                delay: 3e3,
                                                call: function() {
                                                    setTimeout(function() {
                                                        location.href = "/member/index";
                                                    }, 3e3);
                                                }
                                            });
                                            return false;
                                        }
                                    }
                                });
                            }
                        }
                    });

                    // 关闭弹层
                    $adminForm.on('click', '.ubtn-gray', function () {
                        layer.closeAll();
                    })

                    // 新建
                    $('#jaddNewAdmin').on('click', function() {
                        $adminForm[0].reset();
                        layer.open({
                            area: ['600px'],
                            type: 1,
                            moveType: 1,
                            content: $adminForm,
                            title: '新建管理员'
                        });
                    })

                    // 查询
                    $('#search').on('click', function() {
                        $.ajax({
                            url: "/member/index",
                            data: $('#searchForm').formSerialize(),
                            type: "POST",
                            success: function(data){
                                location.href = "/member/index";
                            }
                        });
                    })

                    // 编辑
                    $('.table').on('click', '.jedit', function() {
                        if (_global.v.flag) {
                            return false;
                        }
                        _global.v.flag = true;
                        $adminForm[0].reset();
                        self.showinfo($(this).data('id'));
                        return false; // 阻止链接跳转
                    })
                },
                showinfo: function(id) {
                    var $adminForm = $('#myform');

                    var showBox = function(data) {
                        $adminForm.find('.ipt[name="id"]').val(data.member.id);
                        $adminForm.find('.ipt[name="username"]').val(data.member.username);
                        $adminForm.find('.slt[name="roleId"]').val(data.member.roleId);
                        //$adminForm.find('.ipt[name="password"]').val(data.member.password);
                        $adminForm.find('.ipt[name="name"]').val(data.member.name);
                        $adminForm.find('.ipt[name="mobile"]').val(data.member.mobile);
                        $adminForm.find('.ipt[name="email"]').val(data.member.email);
                        layer.closeAll();
                        layer.open({
                            area: ['600px'],
                            type: 1,
                            moveType: 1,
                            content: $adminForm,
                            title: '编辑管理员'
                        });
                    }

                    // 加载数据
                    var k = $.ajax({
                        url: '/member/edit/'+id,
                        success: function(data) {
                            showBox(data);
                        },
                        complete: function() {
                            _global.v.flag = false;
                        }
                    })

                    // loading
                    layer.open({
                        area: ['200px'],
                        type: 1,
                        moveType: 1,
                        content: '<div class="layer-loading">加载中...</div>',
                        title: '编辑管理员',
                        cancel: function() {
                            k.abort();
                        }
                    });
                },
                bindEvent: function() {
                    var $table = $('.table'),
                            $cbx = $table.find('td input:checkbox'),
                            $checkAll = $table.find('th input:checkbox'),
                            count = $cbx.length;
                    // 删除
                    $table.on('click', '.jdel', function() {
                        var id = $(this).data('id');
                        layer.confirm('确认删除此账户？', {icon: 3, title: '提示'}, function (index) {
                            $.get('/member/delete/'+id, function (data) {
                                if (data.status == "200") {
                                    layer.close(index);
                                    window.location.reload();
                                }
                            }, "json");
                        });
                        return false; // 阻止链接跳转
                    })

                    // 全选
                    $checkAll.on('click', function() {
                        var isChecked = this.checked;
                        $cbx.each(function() {
                            this.checked = isChecked;
                        })
                    })
                    // 单选
                    $cbx.on('click', function() {
                        var _count = 0;
                        $cbx.each(function() {
                            _count += this.checked ? 1 : 0;
                        })
                        $checkAll.prop('checked', _count === count);
                    })
                }
            }
        }
        //加载页面js
        $(function() {
            _global.fn.init();
        });


    </script>
</body>
</html>