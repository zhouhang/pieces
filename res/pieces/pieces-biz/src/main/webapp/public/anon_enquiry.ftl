<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl"/>
    <div class="main-body">

        <div class="wrap">
            <div class="enquiry" id="app">
                <div class="hd">
                    <h2>询价单</h2>
                </div>
                <div class="title">
                    <p><strong>询价商品</strong></p>
                    <p>填写需要询价的商品名称、规格等级和数量。一次询价多个商品可点击“添加商品”或“批量询价”。</p>
                </div>

                <form action="" id="myform">
                    <div class="list">
                        <table>
                            <thead>
                            <tr>
                                <th><i>*</i>品名</th>
                                <th>片型</th>
                                <th>数量（公斤）</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="item in items">
                                <td width="330">
                                    <div class="ipt-wrap">
                                        <input type="text" class="ipt" v-bind:name="'name' + item.idx" v-model="item.val1" v-on:blur="hideMsg(item)" autocomplete="off" placeholder="">
                                        <span class="error" v-show="item.e1">此处不可空白</span>
                                    </div>
                                </td>
                                <td width="330">
                                    <div class="ipt-wrap">
                                        <input type="text" class="ipt" v-bind:name="'type' + item.idx" v-model="item.val2" autocomplete="off" placeholder="">
                                    </div>
                                </td>
                                <td width="330">
                                    <div class="ipt-wrap">
                                        <input type="text" class="ipt" v-bind:name="'quantity' + item.idx" v-model="item.val3" v-on:blur="toInt(item)" autocomplete="off" placeholder="">
                                    </div>
                                </td>
                                <td><button type="button" class="add" v-on:click="addRow"><i class="fa fa-plus"></i>增加一行</button></td>
                                <td><button type="button" class="del fa fa-times-circle-o" v-on:click="delRow(item.idx)" title="删除" v-if="item.idx!==1"></button></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="batch">
                        <span class="btn" v-on:click="batch">&gt;&gt;批量询价</span>
                        <ul class="files">
                            <li v-for="file in files"><i class="fa fa-attach"></i><span>{{file.content}}</span><a href="javascript:;" v-on:click="delAttach(file.content)">删除</a></li>
                        </ul>
                    </div>
                    <div class="title">
                        <p><strong>联系方式</strong></p>
                        <p>填写您的联系方式，方便报价人员与您联系。</p>
                    </div>
                    <div class="fa-form contact">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>联系人：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" v-model="contact.name" v-on:blur="checkName" name="userName" autocomplete="off" placeholder="">
                                <span class="error" v-show="check.name"><i class="fa fa-prompt"></i>请填写联系人</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>电&#12288;话：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" v-model="contact.mobile" v-on:blur="checkMobile" name="mobile" autocomplete="off" placeholder="">
                                <span class="error" v-show="check.mobile"><i class="fa fa-prompt"></i>{{check.mobileText}}</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">
                                <i>*</i>验证码：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt code" v-model="contact.verify" v-on:blur="checkVerify" name="verify" autocomplete="off" placeholder=""><img width="98" height="40" v-bind:src="verification" alt="验证码" title="换一换" v-on:click="changeVcode">
                                <span class="error" v-show="check.verify"><i class="fa fa-prompt"></i>请填写验证码</span>
                            </div>
                        </div>
                        <div class="group">
                            <div class="cnt">
                                <button type="button" class="btn btn-red" v-on:click="submitForm">提交</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script src="/js/vue.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="/js/enquiry.js"></script>
    <script src="/js/layer/layer.js"></script>
</body>
</html>