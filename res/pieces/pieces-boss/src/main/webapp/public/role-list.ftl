<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色用户-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
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
                        <a href="role/power/${role.id}">角色权限</a>
                        <a class="curr" href="role/list/${role.id}">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title title-btm">
                    <h3><i class="fa fa-people"></i>修改角色 “超级管理员”</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">保存</button>
                        <button type="submit" class="btn btn-red">保存并继续</button>
                    </div>
                </div>

                <div class="pagin">
                    <div class="extra">
                        <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                        <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                    </div>
                    <div class="skip">
                        <span>第</span>
                        <a class="fa fa-chevron-left btn btn-gray"></a><input type="text" class="ipt" value="1"><a class="fa fa-chevron-right btn btn-gray"></a>
                        <span>页，共</span><em>6</em><span>页</span>
                        <i>|</i>
                        <span>每页</span>
                        <select name="" id="">
                            <option value="">10</option>
                            <option value="">20</option>
                            <option value="">30</option>
                            <option value="">40</option>
                        </select>
                        <span>个记录，共有 2 个记录</span>
                    </div>
                </div>

                <div class="chart">
                    <table class="tc">
                        <thead>
                            <tr>
                                <th width="100"><label><input type="checkbox" class="cbx" /></label></th>
                                <th width="100">编号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>邮箱</th>
                                <th width="100">状态</th>
                            </tr>
                            <tr>
                                <td>
                                    <select name="" >
                                        <option value="">是</option>
                                    </select>
                                </td>
                                <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                                <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                                <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                                <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                                <td>
                                    <select name="" >
                                        <option value="">激活</option>
                                    </select>
                                </td>
                            </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                            <tr>
                                <td><label><input type="checkbox" class="cbx" /></label></td>
                                <td>10</td>
                                <td>administrator</td>
                                <td>超级管理员</td>
                                <td>super.yaicai.pro</td>
                                <td>激活</td>
                            </tr>
                            <tr>
                                <td><label><input type="checkbox" class="cbx" /></label></td>
                                <td>10</td>
                                <td>administrator</td>
                                <td>超级管理员</td>
                                <td>super.yaicai.pro</td>
                                <td>激活</td>
                            </tr>
                            <tr>
                                <td><label><input type="checkbox" class="cbx" /></label></td>
                                <td>10</td>
                                <td>administrator</td>
                                <td>超级管理员</td>
                                <td>super.yaicai.pro</td>
                                <td>激活</td>
                            </tr>
                            <tr>
                                <td><label><input type="checkbox" class="cbx" /></label></td>
                                <td>10</td>
                                <td>administrator</td>
                                <td>超级管理员</td>
                                <td>super.yaicai.pro</td>
                                <td>激活</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>


</body>
</html>