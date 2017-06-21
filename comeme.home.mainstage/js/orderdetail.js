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
		// alert($("#already_deposit").attr("class"));
		$("#em").hide();
	});

});
$("#pay").click(function() {
	/*$("#pay_choose").fadeOut();
	$("#already_deposit").attr("class", "depositSelect");*/
	//请求支付接口
	var orderId = util.getUrlParam("orderId"); 
	var url = util.baseUrl + "/order/updateOrder";
	var param ='{"orderId":'+orderId+',"payOnline":'+true+',"platformHost":'+true+',"payType":"wechat"}';
	var contentType = "application/json";
	util.requestRemoteDataJsonPosta(url,param,contentType,function(data){
		$("#pay_choose").fadeOut();
		$("#already_deposit").attr("class", "depositSelect");
	});
});