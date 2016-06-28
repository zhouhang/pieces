/**
 * 验证类
 *
 * 1、参数是一个对象
 * 2、创建对象时必须提供id(对象源)、rules(验证规则,对象)
 * 3、创建对象时可选提供fid(反应源)、tip(输入提示信息)、success(成功提示信息)
 * 4、提供empty(非空)、maxLength(最大长度)、minLength(最小长度)、regExp(正则)、compare(比较)、ajax(同步)验证
 * 5、对象result方法返回验证结果,true通过验证,false未通过验证
 * 6、基于jQuery
 * 7、实例
 * 	  var name = new window.Validate({
 * 		  id:'name',
 *        rules:{
 *           empty:{
 *           	error:'名字不能为空'
 *           },
 *           maxLength:{
 *           	length:10,
 *           	error:'最大长度不能超过10个字符'
 *           },
 *           minLength:{
 *           	length:5,
 *           	error:'最小长度不能小于5个字符'
 *           },
 * 			 regExp:{
 *				regExp:'[a-z]+',
 *			 	attribute:'i',
 *				error:'名字中必须有数字'
 *			 },
 *			 compare:{
 *				compareTo:'name2',
 *				operator:'==',
 *				error:'名字要一致'
 *			 },
 *			 ajax:{
 *				url:'?s=test&m=ajax&o=json',
 *				param:'name',
 *				error:'用户名已经存在'
 *			 }
 *        },
 *        tip:'亲,名字不能为空',
 *		  success:'亲,名字可以使用,一旦使用不能修改'
 *    });
 *    name.result();
 *
 * @author wangjunjie <justflyhigh.com@gmail.com>
 * @version 1.2.0
 * @copyright 2013-11-22
 */
(function(win, doc){
	var Validate = win.Validate = function(config){

		//当前对象
		var obj = this;

		//验证规则
		obj.rules = (config.rules || config.rule);

		//对象源
		obj.sobj = jQuery('#' + config.id);
		//反应源
		if(config.fid == undefined){
			//对象源后边追加信息提示框
			obj.sobj.after('<span id="' + config.id + '_tip"></span>');
			obj.fobj = jQuery("#" + config.id + '_tip');
		}else{
			obj.fobj = jQuery("#" + config.fid);
		}

		/**
		 * 对象源 数据
		 *
		 * @returns string 去除左右两端空格的字符串
		 */
		obj.val = function(){
			return jQuery.trim(obj.sobj.val());
		};

		/**
		 * 非空验证
		 *
		 * @returns boolean true通过,false未通过
		 */
		obj.empty = function(){
			return obj.val() != '';
		};

		/**
		 * 邮箱验证
		 *
		 * @returns boolean true通过,false未通过
		 */
		obj.email = function(){
			var pattern = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
			return obj.regExp(pattern);
		};

		/**
		 * url验证
		 *
		 * @returns boolean true通过,false未通过
		 */
		obj.url = function(){
			var pattern = /((^http)|(^https)|(^ftp)):\/\/([A-Za-z0-9]+\.[A-Za-z0-9]+)+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
			return obj.regExp(pattern);
		};

        /**
		 * 英语验证
		 *
		 * @returns boolean true通过,false未通过
		 */
        obj.english = function(){
			var pattern = /^[a-zA-Z]*$/;
			return obj.regExp(pattern);
        };

        /**
		 * 中文验证
		 *
		 * @returns boolean true通过,false未通过
		 */
        obj.chinese = function(){
			var pattern = /^[\u4E00-\u9FA5]+$/;
			return obj.regExp(pattern);
        };
        
        /**
         * 电话验证
         * 
         * 1、验证手机
         * 2、验证固定电话
         * 
         * @returns boolean true通过,false未通过
         */
        obj.phone = function(){
        	return obj.tel() || obj.mobile();
        };
        
        /**
         * 固定电话验证
         * 
         * @returns boolean true通过,false未通过
         */
        obj.tel = function(){
        	var pattern = /(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(13\d{9}$)|(15[0135-9]\d{8}$)|(18[267]\d{8}$)/;
			return obj.regExp(pattern);
        };
        
        /**
         * 手机验证
         * 
         * @returns boolean true通过,false未通过
         */
        obj.mobile = function(){
        	var pattern = /^1[0-9][0-9]{9}$/;
        	return obj.regExp(pattern);
        };

		/**
		 * 最大长度验证
		 *
		 * @param int len 长度数值
		 * @returns boolean true通过,false未通过
		 */
		obj.maxLength = function(len){
			return obj.val().length <= len;
		};

		/**
		 * 最小长度验证
		 *
		 * @param int len 长度数值
		 * @returns boolean true通过,false未通过
		 */
		obj.minLength = function(len){
			return obj.val().length >= len;
		};

		/**
		 * 正则验证
		 *
		 * @param string pattern 正则表达式
		 * @param string attribute 可选，值为[g(全局)、i(区分大小写)、m(多行)匹配]
		 * @returns boolean true通过,false未通过
		 */
		obj.regExp = function(pattern, attribute){
            var val = obj.val();
			if(val.length == 0) return true;
			var regExp = (attribute ? new RegExp(pattern, attribute) : new RegExp(pattern));
			return regExp.test(val);
		};

		/**
		 * 比较验证
		 *
		 * @param string operator 比较操作符，例如：['=='、'>'、'<'、'>='、'<='、'!=']
		 * @param string toValue 要比较的值
		 * @returns boolean true通过,false未通过
		 */
		obj.compare = function(operator, toValue){
			return eval("'" + obj.val() + "'" + operator + "'" + toValue + "'");
		};

		/**
		 * 同步验证
		 *
		 * 1、请求方式post
		 * 2、程序通过param值来获取参数
		 * 3、程序返回json格式的数据{success:true(可使用)/false(不可使用)}
		 * 4、请求失败弹出错误状态值
		 *
		 * @param string url 请求地址
		 * @param string param 参数名
		 * @returns boolean true通过,false未通过
		 */
		obj.ajax = function(url, param){
			var result = false;
			var data = '{' + param + ':' + "'" + obj.val() + "'" + '}';
				data = eval('(' + data + ')');
			jQuery.ajax({
				url:url,
				data:data,
				async:false,
				type:'post',
				dataType:'json',
				success:function(msg){
					result = msg.success;
				},
				error:function(error){
					alert(error.status);
				}
			});
			return result;
		};

		/**
		 * 显示错误信息
		 *
		 * @param string err 信息内容
		 */
		obj.error = function(err){
			obj.tip(err, 'error_tip');
		};

		//验证正确,清除错误信息
		obj.success = function(){
			var msg = (config.success ? config.success : '');
			obj.tip(msg, 'success_tip');
		};

		/**
		 * 信息提示
		 *
		 * 1、设置消息内容和样式
		 * 2、如果样式有变动修改此方法即可
		 *
		 * @param string msg 信息内容
		 * @param string type 提示类型：error_tip，success_tip，notice_tip
		 */
		obj.tip = function(msg, type){
			obj.fobj.css({
				'font-size':'13px',
				'font-famliy':'微软雅黑,宋体',
				'margin-left':'2px',
				'padding':'2px 2px 2px 2px',
				'line-height':'18px'
			});
			switch(type){
				case 'error_tip':
					obj.fobj.css({
						'border':'1px solid #FF8080',
						'color':'#FF8080'
					});
					break;
				case 'success_tip':
					var width = (msg.length > 0 ? 1 : 0);

					obj.fobj.css({
						'border':width + 'px solid #6ce26c',
						'color':'#6ce26c'
					});
					break;
				default :
					obj.fobj.css({
						'border':'1px solid #FFD099',
						'color':'#FFD099'
					});
					break;
			}
			if(msg.length == 0) obj.fobj.css('border', '0');
			obj.fobj.text(msg);
		};

		//焦点提示信息
		obj.sobj.click(function(){
			var vr = obj.result();
			if(!vr){
				var msg = ((config.tip && (!vr)) ? config.tip : '');
				obj.tip(msg, 'notice_tip');
			}
		}).blur(function(){
			obj.result();
		});

		/**
		 * 验证结果
		 *
		 * @returns boolean true通过,false未通过
		 */
		obj.result = function(){
			var result = false;
			for(var item in obj.rules){
				//验证函数
				var func = obj[item];
				//规则对象
				var itemObj = obj.rules[item];
				//错误信息
				var error = itemObj['error'] ? itemObj['error'] : '';
				switch(item){
					case 'ajax':
						result = func(itemObj['url'], itemObj['param']);
						break;
					case 'maxLength':
					case 'minLength':
						result = func(itemObj['length']);
						break;
					case 'regExp':
						result = func(itemObj['regExp'], itemObj['attribute']);
						break;
					case 'compare':
						var toValue = jQuery.trim(jQuery("#" + itemObj['compareTo']).val());
						result = func(itemObj['operator'], toValue);
						break;
					default:
						result = func(item);
						break;
				}
				result ? this.success() : this.error(error);
				if(result == false) break;
			}
			return result;
		};
	};
})(window, document);