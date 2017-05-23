// JavaScript Document


function Index()
{
	this.init();	
}

Index.prototype={
	init:function()
	{
		var _this = this;
		_this.showImage();
	},
	//showImage滚动
	showImage:function()
	{
		var swiper = new Swiper('#showImage', {
			prevButton:'.swiper-button-prev',
			nextButton:'.swiper-button-next',
			spaceBetween: 10,
			centeredSlides: true,
			loop:true,
			autoplayDisableOnInteraction : false  //播放完毕后
		});
	}
};

$(function()
{
	new Index();
});




