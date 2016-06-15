/**
 * Created by zhouli on 2014/12/3.
 */
function Alert(obj){
    var str;
	var buttons;
	str = obj.str;
    if(!obj.buttons){
        obj.buttons=[{
            name:'确定',
            id:'Abtn',
            classname:'btn-style',
            ev:{click:function(data){
                disappear();
                $(".bghui").remove();
            }
            }
        }];

    }
    buttons = obj.buttons;
    this.disappear=function(){
        $("#bgdiv").remove();
        $("#msgdiv").remove();
        $("#msgtitle").remove();
        msgobj=null;
        msgdiv=null;
    };
    disappear();
    this.m_text=str;
    this.m_buttons=buttons;
    this.m_width=200;
    //this.m_height=100;
    this.m_wait=1000;
    this.m_fade=500;
    this.m_bordercolor="#0188cc";
    this.m_titlecolor="#99ccff";
    var msgw,msgh,bordercolor;
    msgw=m_width;//提示窗口的宽度
    //msgh=m_height;//提示窗口的高度
    titleheight=25 //提示窗口标题高度
    bordercolor=m_bordercolor;//提示窗口的边框颜色
    titlecolor=m_titlecolor;//提示窗口的标题颜色

    var swidth,sheight;
    swidth=document.body.offsetWidth;
    sheight=document.body.offsetHeight;
    if (sheight<screen.height)
    {
        sheight=screen.height;
    }
    //防止多次点击保存按钮
     /*if(bgobj==undefined){
        var bgobj=document.createElement("div");
     }
     bgobj.setAttribute('id','bgdiv');
     bgobj.style.position="absolute";
     bgobj.style.top="0";
     bgobj.style.background="#777";
     bgobj.style.filter="progid:dximagetransform.microsoft.alpha(style=3,opacity=25,finishopacity=75";
     bgobj.style.opacity="0.6";
     bgobj.style.left="0";
     bgobj.style.width=swidth + "px";
     bgobj.style.height=sheight + "px";
     bgobj.style.zindex = "10000";*/

	 
    if(msgobj==null){
        var msgobj=document.createElement("div");
    }
    msgobj.setAttribute("id","msgdiv");
    msgobj.style.position = "absolute";
    msgobj.style.left = "50%";
    msgobj.style.top = "50%";
    if(title==null){
        var title=document.createElement("h4");
    }
    title.setAttribute("id","msgtitle");
    title.setAttribute("align","left");
    title.innerHTML="消息提示";
    var close=document.createElement('div');
    close.setAttribute("id","aClose");
    close.onclick=function(){
        disappear();
        $(".bghui").remove();
    };
    var btn_cont = document.createElement('div');
    btn_cont.setAttribute("align","center");
    btn_cont.setAttribute("id","btnCont");

    $('body').append(msgobj);
    $("#msgdiv").append(title);
    $("#msgdiv").append(close);

    var txt=document.createElement("p");
    txt.setAttribute("id","msgtxt");
    txt.innerHTML=str;
    $("#msgdiv").append(txt);
    $("#msgdiv").append(btn_cont);



    msgobj = $("#btnCont");

    for(var i in this.m_buttons){
        msgobj.append("<input type='button' class='"+this.m_buttons[i].classname+"' id='"+this.m_buttons[i].id+"' value='"+this.m_buttons[i].name+"'/>");
        var $btn = $('#'+this.m_buttons[i].id);
        for(var k in this.m_buttons[i].ev){
            $btn.on(k+'',this.m_buttons[i].ev[k]);
			
        }
    }

    /*this.fadeout=function(){
        $("#bgdiv").fadeOut(1000);
        $("#msgdiv").fadeOut(1000);
        $("#msgtitle").fadeOut(1000,function(){disappear()});
    }
    setTimeout("fadeout()",2000);*/
}
//背景蒙层
function bghui(){
    var html = '<div class="bghui"></div>';
    var height = $(document).height();
    $('body').append(html);
    $('.bghui').css('height',height);
}