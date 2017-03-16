wx.config({
    debug: false,
    appId: weixinShare.appId,
    timestamp: weixinShare.timestamp,
    nonceStr: weixinShare.nonceStr,
    signature: weixinShare.signature,
    jsApiList: [
        'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'onMenuShareQZone'
    ]
});

wx.ready(function() {
    // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareAppMessage({
        title: weixinShare.title,
        desc: weixinShare.desc,
        link: weixinShare.link,
        imgUrl: weixinShare.imgUrl,
        trigger: function(res) {},
        success: function(res) {},
        cancel: function(res) {},
        fail: function(res) {}
    });

    // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareTimeline({        
        title: weixinShare.title,
        link: weixinShare.link,
        imgUrl: weixinShare.imgUrl,
        trigger: function(res) {},
        success: function(res) {},
        cancel: function(res) {},
        fail: function(res) {}
    });


    // 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareQQ({
        title: weixinShare.title,
        desc: weixinShare.desc,
        link: weixinShare.link,
        imgUrl: weixinShare.imgUrl,
        trigger: function(res) {},
        complete: function(res) {},
        success: function(res) {},
        cancel: function(res) {},
        fail: function(res) {}
    });

    // 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
    wx.onMenuShareWeibo({
        title: weixinShare.title,
        desc: weixinShare.desc,
        link: weixinShare.link,
        imgUrl: weixinShare.imgUrl,
        trigger: function(res) {},
        complete: function(res) {},
        success: function(res) {},
        cancel: function(res) {},
        fail: function(res) {}
    });

    // 2.5 监听“分享到QZone”按钮点击、自定义分享内容及分享接口
    wx.onMenuShareQZone({
        title: weixinShare.title,
        desc: weixinShare.desc,
        link: weixinShare.link,
        imgUrl: weixinShare.imgUrl,
        trigger: function(res) {},
        complete: function(res) {},
        success: function(res) {},
        cancel: function(res) {},
        fail: function(res) {}
    });
})