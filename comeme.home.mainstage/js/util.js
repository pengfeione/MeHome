
function Util(){
	this.baseUrl = "http://api.mjiahome.com/api";//api.mjiahome.com
	this.reportUrl = "http://event.m.shihou.tv/shihou_report";
	this.webUrl = "http://m.shihoutv.com/lion_module_share_temp/share?room_id=";
	this.init();
}

Util.prototype = {
	init:function(){
		var _this = this;
	},
	parseResult:function(){
		var size = arguments.length;
		if(size == 0){
			return '';
		}
		var result = arguments[0];
		for(var i = 1; i < size; i++){
			if(result == undefined){
				return '';
			}
			var argu = arguments[i];
			if(i == size){
				return argu;
			}
			result = result[argu];
		}
		return result;
	},
	//获取url中的参数
    getUrlParam: function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    },
	requestRemoteData: function(url, data, callback, async){
		this.logURL(url);
		this.logParameter(data);
		var begin = (new Date()).getTime();
		$.ajax({
	        url: url,
	        type: 'GET',
	        async: async || false,
	        dataType: "html",
	        data: data,
	        success: callback,
	        error: function (data) {
	        	var status = data.status;
	        	console.log(url + "获取数据失败!");
	        }
	    });
	},
	sendRequest: function(url, data, type, async, successCallBack, errorCallBack){
		this.logURL(url);
		this.logParameter(data);
		$.ajax({
			url: url,
			data: data,
			type: type,
			async: async,
			dataType: "json",
			contentType:"application/json;charset=UTF-8",
			success: successCallBack,
			error: errorCallBack
		});
	},
	requestRemoteDataJson: function(url, data, callback, async){
		this.logURL(url);
		this.logParameter(data);
		var begin = (new Date()).getTime();
		$.ajax({
	        url: url,
	        type: 'GET',
	        async: async || true,
	        cache: false, 
	        dataType: "json",
	        data: data,
	        success: callback,
	        error: function (data) {
	        	var status = data.status;
	        	console.log(url + "获取数据失败!");
	        }
	    });
	},
	
	requestRemoteDataJsonPost: function(url, data,contentType, callback,async){
		this.logURL(url);
		this.logParameter(data);
		var begin = (new Date()).getTime();
		$.ajax({
	        url: url,
	        type: 'POST',
	        async: async || true,
	        cache: false, 
	        dataType: "json",
	        data: data,
	        contentType:contentType,
	        success: callback,
	        error: function (data) {
	        	var status = data.status;
	        	console.log(url + "获取数据失败!");
	        }
	    });
	},
	requestRemoteDataJsonPosta: function(url, data,contentType, callback,async){
		this.logURL(url);
		this.logParameter(data);
		var begin = (new Date()).getTime();
		$.ajax({
	        url: url,
	        type: 'POST',
	        async: async || false,
	        cache: false, 
	        dataType: "json",
	        data: data,
	        contentType:contentType,
	        success: callback,
	        error: function (data) {
	        	var status = data.status;
	        	console.log(url + "获取数据失败!");
	        }
	    });
	},
	requestRemoteDataJsonHeaders: function(url, data, callback, async){
		var tockenIframe = $('#tocken_iframe').html();
		console.log(tockenIframe);
		this.logURL(url);
		this.logParameter(data);
		var begin = (new Date()).getTime();
		$.ajax({
	        url: url,
	        type: 'GET',
	        async: async || false,
	        cache: false, 
	        dataType: "json",
	        data: data,
	        success: callback,
	        error: function (data) {
	        	var status = data.status;
	        	console.log(url + "获取数据失败!");
	        }
	    });
	},
	preDate:function(){
		return this.GetDateStr(-1);
	},
	sleep:function(n) {//单位毫秒
    	var start = new Date().getTime();
    	while(true)  if(new Date().getTime()-start > n) break;
   },
	GetDateStr:function(AddDayCount) { 
		var dd = new Date(); 
		dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
		var y = dd.getFullYear(); 
		var m = dd.getMonth()+1;//获取当前月份的日期 
		var d = dd.getDate(); 
		if(m < 10)
			m = "0"+m;
		if(d < 10)
			d = "0"+d;
		return y+""+m+""+d; 
	},
	log:function(data){
		console.log(data);
	},
	logURL:function(url){
		console.log("url --> "+url);
	},
	logParameter:function(data){
		console.log("parameter --> "+JSON.stringify(data));
	},
	logResult:function(result){
		console.log("result --> "+JSON.stringify(result));
	},
	toDate: function(date) {
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
			
	},
	getUserId: function(callback){
		this.setCookie("dev", "");
		 var token = this.getCookie('token');
		 this.setCookie("token", token);
		  if(token == ''){
		 	return null;
		 }
		 var url =  this.baseUrl + '/guess/getUidByToken?xLionToken='+token;
		 this.requestRemoteDataJson(url,null,callback);
	},
	getCookie:function(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	},
	setCookie:function(name,value){
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days*24*60*60*1000);
//		exp.setTime(exp.getTime() + 60*1000);
		//测试 TODO http://me.trancyboy.com/api
     	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/; domain=.m.mjiahome.com";
	},
	isLogin:function(){
		var url = this.baseUrl + '/guess/isLogin';
		this.requestRemoteDataJsonHeaders(url,null,function(data){
			console.log("result --> "+JSON.stringify(data));
			$('#isLogin').val(data.result);
			$('#lab_user').html(data.result);
		});
	},
	//分享弹窗
	sharePage:function(from_user_id,share_url,share_icon,share_title,share_desc){
		console.log('from_user_id:'+from_user_id);
		console.log('share_url:'+share_url);
		console.log('share_icon:'+share_icon);
		console.log('share_title:'+share_title);
		console.log('share_desc:'+share_desc);
	    var web_share = {
	        "qq": {
	            "url": share_url,
	            "img": share_icon,
	            "title": share_title,
	            "subtitle": share_desc
	        },
	        "wx_person": {
	            "url": share_url,
	            "img": share_icon,
	            "title": share_title,
	            "subtitle": share_desc
	        },
	        "wx_group": {
	            "url": share_url,
	            "img": share_icon,
	            "title": share_title,
	            "subtitle": share_desc
	        },
	        "wb": {
	            "url": share_url,
	            "img": share_icon,
	            "title": share_title,
	            "subtitle": share_desc
	        }
	    };
	
	    if (typeof(window.lion) != "undefined" && typeof(window.lion.web_share) == 'function')
	    {
	        window.lion.web_share(JSON.stringify(web_share));
	    } 
	},
	wechatShare:function(imgUrl, title, desc, link){
		 var wxShareConfig = {
                imgUrl: imgUrl,
                title: title,
                desc: desc,
            	link: link
            }
            $.ajax({
            	
                url: 'http://event.m.shihou.tv/shihou_kpl_guess/share_api_sign',
                type: 'GET',
                async: false,
                dataType: "json",
                data: {'url': encodeURI(encodeURI(location.href.split('#')[0]))},
                success: function (data) {
                    console.log(data.result);
                    wx.config({
                        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                        appId: data.result.appid, // 必填，公众号的唯一标识
                        timestamp: data.result.timestamp, // 必填，生成签名的时间戳
                        nonceStr: data.result.noncestr, // 必填，生成签名的随机串
                        signature: data.result.signature,// 必填，签名，见附录1
                        jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline', 'onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    });
                    wx.ready(function () {
                        wx.onMenuShareTimeline(wxShareConfig);
                        wx.onMenuShareAppMessage(wxShareConfig);
                        wx.onMenuShareQQ(wxShareConfig);
                    });
                },
                faile: function (data) {
                    alert(JSON.stringify(data));
                }
            });
	},
	//登录弹窗
	popupLogin:function(obj){
		var html = '<div class="jctc longin" style="display:none;">'
				  +'	<h2>您还未登录！</h2>'
				  +'	<center class="jctc2P" style="text-align:center;">您需要登录后才可进行后续操作。</center>'
				  +'	<div class="jctc3Btn"><a class="enter login_quit">确定</a></div>'
				  +'</div>';
		obj.append(html);
		$(".login_quit").on(this.clickTouch(),function()
		{
			$(".jctc,.vmask,.login").fadeOut();			
		})
	},
	//下载弹窗
	popupDownload:function(obj){
		var html = '<div class="jctc download" id="commonDownload" style="display:block;">'+
					    '<h2>立即下载</h2>'+
					    '<p class="jctc2P" style="text-align:center;">请先下载App，登录后进行后续操作。</p>'+
					    '<div class="jctc3Btn">'+
					     	'<a href="http://www.shihou.tv/app" class="enter btnDownload">下载</a>'+
					     	'<a class="enter btnComCancel">取消</a>'+
					    '</div>'+
					'</div>';
		obj.append(html);		
		
		$(".btnComCancel").on("click",function()
		{
			$(".download").hide();	
			$(".vmask").hide();
		})
	},
	clickTouch:function ()
	{
		var clickTouch = "touchend";
		var browser = {
			versions: function()
			{
				var u = navigator.userAgent, app = navigator.appVersion;
				return {//移动终端浏览器版本信息
					trident: u.indexOf('Trident') > -1, //IE内核
					presto: u.indexOf('Presto') > -1, //opera内核
					webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
					gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
					mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
					iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
					iPad: u.indexOf('iPad') > -1, //是否iPad
					webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
				};
			}(),
			language: (navigator.browserLanguage || navigator.language).toLowerCase()
		}
		if (browser.versions.mobile)
		{
			clickTouch = "touchend";
		}
		else
		{
			clickTouch = "click";
		}
		return clickTouch;
	},
	 identityCodeValid:function(code) { 
 		var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
		var tip = "";
  		var pass= true;
 		if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
  			tip = "身份证号格式错误";
			pass = false;
  		}else if(!city[code.substr(0,2)]){
  			tip = "地址编码错误";
			pass = false;
  		}else{
	  		//18位身份证需要验证最后一位校验位
			if(code.length == 18){
	  			code = code.split('');
	  			//∑(ai×Wi)(mod 11)
	  			//加权因子
				var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
	  			//校验位
				var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
	  			var sum = 0;
	  			var ai = 0;
	  			var wi = 0;
	  			for (var i = 0; i < 17; i++)
	 			 {
	  				ai = code[i];
	  				wi = factor[i];
	  				sum += ai * wi;
	  			}
	  			var last = parity[sum % 11];
	  			if(parity[sum % 11] != code[17]){
	  				tip = "校验位错误";
					pass =false;
	  			}
	  		}
  		}
  		if(!pass) alert(tip);
  		return pass;
  	}

};

