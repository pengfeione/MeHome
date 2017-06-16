// JavaScript Document


function Index()
{
	this.init();	
}

Index.prototype={
	init:function()
	{
		var _this = this;
		//_this.bannerScroll();
	},
	//banner滚动
	bannerScroll:function()
	{
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			paginationClickable: true, //此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
			spaceBetween: 10,
			centeredSlides: true,
			autoplay: 5000,
			loop:true,
			autoplayDisableOnInteraction : false  //播放完毕后
		});
	},
	showPop:function(iTck,iMark)
	{
		var sTop=$(window).scrollTop(),
		cWidth=$(window).width(),
		cHeight=$(window).height(),
		popWidth=iTck.width();
		popHeight=iTck.height();
	
		iMark.show();
		iTck.show();
		iMark.height($(document).height());
		iTck.css({"left":(cWidth-popWidth)/2,"top":(cHeight-popHeight)/2+sTop});
	},
	hidePop:function(iTck,iMark)
	{
		iTck.hide();
		iMark.hide();
	}
};

$(function()
{
	new Index();
});




