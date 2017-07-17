$("#next").click(function() {
	$("#hold_choose").fadeOut();
	$("#pay_choose").fadeIn();
});
$("#pay_close").click(function() {
	$("#pay_choose").fadeOut();
});
$("#next_close").click(function() {
	$("#hold_choose").fadeOut();
});
$("#confirm").click(function() {
	$("#hold_choose").fadeIn();
});
$("#already_deposit").click(function() {
	if ($("#already_deposit").attr("class") == "depositInit") {
		$("#hold_choose").fadeIn();
	} else {
		$("#order_offline").fadeIn();
	}
});
$("#offline_cancel").click(function() {
	$("#order_offline").fadeOut();
});
$("#offline_sure").click(function() {
	var orderId = util.getUrlParam("orderId");
	var url = util.baseUrl + "/order/updateOrder";
	var param ='{"orderId":'+orderId+',"platformHost":'+false+'}';
	var contentType = "application/json";
	util.requestRemoteDataJsonPosta(url,param,contentType,function(data){
		$("#order_offline").fadeOut();
		$("#already_deposit").attr("class", "depositInit");
		// alert($("#already_deposit").attr("class"));1
		$("#em").hide();
	});

});
$("#pay").click(function() {
	/*$("#pay_choose").fadeOut();
	$("#already_deposit").attr("class", "depositSelect");*/
	
	//请求支付接口
	var orderId = util.getUrlParam("orderId"); 
	var userInfo = util.getCookie("userInfo");
	var userInfoJson = eval('(' + userInfo + ')');
	var uid = userInfoJson.userId;
	var url = util.baseUrl + "/order/payment_create_order";
	var param ='{"orderId":'+orderId+',"tradeType":"JSAPI","payer":'+uid+',"payType":"wechat"}';
	var contentType = "application/json";
	util.requestRemoteDataJsonPosta(url,param,contentType,function(data){
		$("#pay_choose").fadeOut();
		$("#already_deposit").attr("class", "depositSelect");
		window.location.href =data.result.mwebUrl;
//		alert("appid:"+data.result.appId);
//		alert("timeStamp:"+data.result.seconds);
//		alert("nonceStr:"+data.result.nonceStr);
//		alert("package:"+data.result.packageStr);
//		alert("paySign:"+data.result.paySign);
		if (typeof WeixinJSBridge == "undefined"){
	    	   if( document.addEventListener ){
	    	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	    	   }else if (document.attachEvent){
	    	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	    	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	    	   }
	    }else{
	    	   onBridgeReady(data);
	    }
	});
});

function onBridgeReady(data){
	//由后台接口获取
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId":data.result.appId,     //公众号名称，由商户传入     "wx966efd886c5be652"
	           "timeStamp":data.result.seconds+"",         //时间戳，自1970年以来的秒数     
	           "nonceStr":data.result.nonceStr, //随机串     "e61463f8efa94090b1f366cccfbbb444"
	           "package":data.result.packageStr, //prepay_id=u802345jgfjsdfgsdg888    
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":data.result.paySign //微信签名 "70EA570631E4BB79628FBCA90534C63FF7FADD89"
	       },
	       function(res){     
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {alert("支付通信成功");}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
	           if(res.err_msg == "get_brand_wcpay_request:cancel" ) {alert("支付通信取消");}
	           if(res.err_msg == "get_brand_wcpay_request:fail" ) {alert(JSON.stringify(res));alert("支付通信失败");}
	       }
	   ); 
	}