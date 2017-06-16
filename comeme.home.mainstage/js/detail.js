// JavaScript Document


function Detail()
{
	this.init();	
}

Detail.prototype={
	init:function()
	{
		var _this = this;
		_this.houseDetailScrollImg();
		_this.houseStyle();
		_this.showHideFl();
		_this.houseRule();
		_this.showImageSamll();
	},
	houseDetailScrollImg:function()
	{
		var swiper = new Swiper('#bannerDetail', {
			pagination : '.swiper-pagination',
			paginationClickable: true, //此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
			spaceBetween: 10,
			centeredSlides: true,
			autoplay: 5000,
			loop:true,
			autoplayDisableOnInteraction : false  //播放完毕后
		});
	},
	//showImage滚动
	showImage:function()
	{
		var swiper = new Swiper('.swiper-container', {
			prevButton:'.swiper-button-prev',
			nextButton:'.swiper-button-next',
			centeredSlides: true,
			loop:true,
			autoplayDisableOnInteraction : false  //播放完毕后
		});	
	},
	houseStyle:function()
	{
		$(".houseTag a").on("click",function()
		{
			$(this).addClass("curr").siblings("a").removeClass("curr");	
			$(".houseSection").eq($(this).index()).show().siblings().hide();
		});
	},
	//个人福利
	showHideFl:function()
	{
		$(".btnShow").on("click",function()
		{
			if(!$(this).hasClass("btnHide"))
			{
				$(".apartFlcont p").css('height','auto');
				$(this).find("span").html("收起");
				$(this).addClass("btnHide");
			}
			else
			{
				$(".apartFlcont p").css('height','60px');
				$(this).find("span").html("展开");
				$(this).removeClass("btnHide");
			}
			
		});
	},
	//房屋规则
	houseRule:function()
	{
		$(".btnHouseRule").on("click",function()
		{
			$(".houseRule,.mask").fadeIn();	
		});
		
		$(".houseRule .showClose").on("click",function()
		{
			$(".houseRule,.mask").fadeOut();	
		});
	},
	//显示图片
	showImageSamll:function()
	{
		var _this = this;
		$(".apartImg").on("click",function()
		{
			$(".showLargeImg,.mask").fadeIn();
			_this.showImage();
		});
		
		$(".showLargeImg .showClose").on("click",function()
		{
			$(".showLargeImg,.mask").fadeOut();	
		});
	}
};

$(function()
{
	new Detail();
});




