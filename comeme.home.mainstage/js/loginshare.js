// JavaScript Document


function Loginshare()
{
	this.init();	
}

Loginshare.prototype={
	init:function()
	{
		var _this = this;
		_this.setBody();
		//_this.getYzm($(".btnGetYzm"));
	},
	//setBody
	setBody:function()
	{
		$(window).on("load resize",function()
		{
			$(".wrap").css({"height":$(window).height()+"px"});
		});
	},
	//读秒获取验证码
	getYzm:function(btn)
	{
		var timer = null;
		var iStop = false;
		btn.bind("click",function()
		{
			if(iStop)
			{
				return false;
			}
			iStop=true;
			var second = 60;
			timer=setInterval(function()
			{
				btn.html("<i style='color:#ccc; font-style:normal;'>"+second + "s重新获取</i>");
				
				//重新获取
				if(second<=0)
				{
					btn.html("获取验证码");
					iStop = false;
					clearInterval(timer);
				}
				second--;
			},1000);
		});	
	}
};

$(function()
{
	new Loginshare();
});




