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
	},
	//setBody
	setBody:function()
	{
		$(window).on("load resize",function()
		{
			$(".wrap").css({"height":$(window).height()+"px"});
		});
	}
};

$(function()
{
	new Loginshare();
});




