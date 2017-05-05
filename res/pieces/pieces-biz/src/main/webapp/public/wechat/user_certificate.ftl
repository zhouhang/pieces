<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>资质认证-上工好药</title>
</head>
  <body class="bg-gray">

  <section class="ui-content">
      <div class="order">
          <div class="item">
              <input type="hidden" name="customerID" id="customerID" value="">
              <span class="error"></span>
              <strong class="t1">请选择企业类型</strong>
              <a href="/h5c/user/certificateSelect?type=" class="arrow"></a>
          </div>
      </div>
      <div class="floors">
          <div class="hd">医疗机构许可证</div>
          <div class="ui-upload thumb">
              <em class="ui-file required" data-max="4" data-num="0"></em>
              <span>请上传医疗机构许可证图片</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">营业执照副本</div>
          <div class="ui-upload thumb">
              <em class="ui-file" data-max="4" data-num="0"></em>
              <span>请上传企业营业执照副本图片，如果是老版的营业执照，还需要上传组织机构代码证以及税务登记证图片</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">GSP证书</div>
          <div class="ui-upload thumb">
              <em class="ui-file" data-max="4" data-num="0"></em>
              <span>请上传GSP证书图片</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">GMP证书</div>
          <div class="ui-upload thumb">
              <em class="ui-file required" data-max="4" data-num="0"></em>
              <span>请上传GMP证书图片</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">药品经营许可证</div>
          <div class="ui-upload thumb">
              <em class="ui-file" data-max="4" data-num="0"></em>
              <span>请上传药品经营许可证图片，如果做过资质变更，还需要上传资质变更记录表图片，变更记录表最多能传3张</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">药品生产许可证</div>
          <div class="ui-upload thumb">
              <em class="ui-file required" data-max="4" data-num="0"></em>
              <span>请上传药品生产许可证图片，如果做过资质变更，还需要上传资质变更记录表图片，变更记录表最多能传3张</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">法人授权委托书<a href="#none" class="mid eg-img">示例图片</a></div>
          <div class="ui-upload thumb">
              <em class="ui-file required" data-max="4" data-num="0"></em>
              <span>请上传采购人员的法人授权委托书图片，如果采购人员与收货人员不是同一个人，还需要上传收货人员授权委托书图片</span>
          </div>
      </div>
      <div class="floors">
          <div class="hd">印鉴章备案<a href="#none" class="mid eg-img">示例图片</a></div>
          <div class="ui-upload thumb">
              <em class="ui-file required" data-max="4" data-num="0"></em>
              <span>请上传贵单位的印鉴章备案图片</span>
          </div>
      </div>

      <div class="ui-button">
          <button type="button" class="ubtn ubtn-red" id="submit">提交</button>
      </div>

  </section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script>

      var _global = {
          fn: {
              init: function() {
                  this.bindEvent();
                  this.chooseType();
                  this.formValidate();
              },
              bindEvent: function() {
                  var that = this,
                          idx = 0;

                  var showPic = function(localIds, $el, hide) {
                      var model = [];
                      $.each(localIds, function(i, url) {
                          model.push('<em class="ui-file">');
                          model.push('<img src="' , url , '" />');
                          model.push('<i class="del"></i>');
                          model.push('</em>');
                      })

                      $el.before(model.join(''));
                      hide && $el.empty('').hide();
                  }

                  // 图片预览
                  $('.thumb').on('click', 'img', function() {
                      var pic = [];
                      $(this).closest('.thumb').find('img').each(function() {
                          pic.push(this.src);
                      })
                      wx.previewImage({
                          current: this.src,
                          urls: pic
                      });
                  })

                  // 上传图片
                  $('.ui-file').on('click', function() {
                      var maxSize = $(this).data('max'),
                              number = $(this).data('num'),
                              $el = $(this);

                      $(this).parent().find('.error').remove();

                      wx.chooseImage({
                          count: (maxSize - number), // 最多4张
                          // sizeType: 'compressed', // 可以指定是原图还是压缩图，默认二者都有
                          sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                          success: function (res) {
                              var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                              showPic(localIds, $el, maxSize - number === 0);
                              wx.uploadImage({
                                  localId: localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
                                  isShowProgressTips: 1, // 默认为1，显示进度提示
                                  success: function () {
                                      // showPic(localIds);
                                  },
                                  fail: function (error) {
                                      picPath = '';
                                      localIds = '';
                                      alert(Json.stringify(error));
                                  }
                              });
                          }
                      });
                  })

                  // 删除图片
                  $('.ui-upload').on('click', '.del', function() {
                      $(this).parent().remove();
                      return false;
                  })

                  // 示例图片
                  $('.eg-img').on('click', function() {
                      layer.open({
                          className: 'layer-help',
                          content: '<div class="hd">示例图片</div><div class="bd">您可以上传类似这样的法人授权委托书图片</div><div class="pic"><img style="width:250;height:382px;" src="assets/images/certify.jpg" /></div>'
                      });
                  })
              },
              // 选择企业类型
              chooseType: function() {
                  var $floor = $('.floors'),
                          params = _YYY.getParams();

                  // 0.医疗机构许可证
                  // 1.营业执照副本
                  // 2.GSP证书
                  // 3.GMP证书
                  // 4.药品经营许可证
                  // 5.药品生产许可证
                  // 6.法人授权委托书
                  // 7.印鉴章备案

                  $floor.hide();
                  if (!params.type) {
                      return this;
                  } else if (params.type == 1) {
                      // 药店
                      $floor.eq(1).show();
                      $floor.eq(6).show();
                  } else if (params.type == 2) {
                      // 医疗机构
                      $floor.eq(0).show();
                      $floor.eq(1).show().find('.hd').append('<em>（盈利性医疗机构必填）</em>');
                      $floor.eq(2).show().find('.hd').append('<em>（盈利性医疗机构必填）</em>');
                      $floor.eq(4).show().find('.hd').append('<em>（盈利性医疗机构必填）</em>');
                      $floor.eq(6).show();
                      $floor.eq(7).show();

                  } else if (params.type == 3) {
                      // 制药企业
                      $floor.eq(1).show();
                      $floor.eq(3).show();
                      $floor.eq(5).show();
                      $floor.eq(6).show();
                      $floor.eq(7).show();

                  } else {
                      // 医药公司
                      $floor.eq(1).show();
                      $floor.eq(2).show();
                      $floor.eq(4).show();
                      $floor.eq(6).show();
                      $floor.eq(7).show();
                  }
              },
              // 提交表单
              formValidate: function() {
                  var that = this;
                  $('#submit').on('click', function() {
                      var pass = that.checkImg();
                      if (pass) {
                          //如果检验通过
                          var certify={};
                          certify.type=${type!};
                          var uqs=[];
                          var boxs = $('.ui-content').find('.floor:visible');
                          boxs.each(function() {
                              if($(this).attr("style")=="display: none;"){
                                  return;
                              }
                              var uq={};
                              var pics=[];
                              uq.type=$(this).attr('type');
                              $(this).find(".up-img").each(function (index) {
                                  var url=$(this).find("input[type='hidden']").val();
                                  var pic={};
                                  if(url!=undefined){
                                      pic.pictureUrl=url;
                                      pic.indexNum=index;
                                      pics.push(pic);
                                  }

                              });
                              if(pics.length!=0){
                                  uq.pictures=pics;
                                  uqs.push(uq);
                              }
                          });
                          var wxUqs = [];// 微信图片上传后对象
                          function upload(uqs){
                              var up = uqs.pop();
                              wx.uploadImage({
                                  localId: up.pictureUrl, // 需要上传的图片的本地ID，由chooseImage接口获得
                                  isShowProgressTips: 1, // 默认为1，显示进度提示
                                  success: function (res) {
                                      up.pictureUrl = res.serverId;
                                      wxUqs.push(up);
                                      if(uqs.length > 0){
                                          upload(uqs);
                                      } else {
                                          submit(wxUqs);
                                      }
                                  },
                                  fail: function (error) {
                                      picPath = '';
                                      localIds = '';
                                      alert(Json.stringify(error));
                                  }
                              });
                          };

                          function submit(wxUqs) {
                              certify.userQualificationVos=wxUqs;
                              $.ajax({
                                  url: "/h5c/user/certificate",
                                  data: JSON.stringify(certify),
                                  type: "POST",
                                  contentType: "application/json; charset=utf-8",
                                  success: function (data) {
                                      var status = data.status;
                                      var info = data.info;
                                      if (status == 'y') {
                                          window.location.href = '/h5c/user/certificateSuccess';
                                          return;
                                      }
                                  },
                                  complete: function() {

                                  }
                              });
                          }
                          upload(uqs);

                      }
                  })
              },
              checkImg: function() {
                  var pass = true;
                  $('.floors').each(function() {
                      if ($(this).css('display') != 'none') {
                          if ($(this).find('.required').data('num') == 0) {
                              pass = false;
                              $(this).find('.ui-upload').append('<span class="error">请上传证件照片</span>');
                          }
                      }
                  })
                  return pass;
              }
          }
      }

      $(function(){
          wx.config({
              debug: false,
              appId: '${signature.appid!}',
              timestamp: ${signature.timestamp!},
              nonceStr: '${signature.noncestr!}',
              signature: '${signature.signature!}',
              jsApiList: [
                  'chooseImage',
                  'previewImage',
                  'uploadImage'
              ]
          });
          _global.fn.init();
      });

  </script>
</body>
</html>