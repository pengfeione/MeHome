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
		//_this.houseMask();
		_this.setBody();
		//_this.hosueList();
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
	houseMask:function()
	{
		$(".houseList ul li").each(function()
		{
			var self = $(this);
			var houseMask = self.find(".houseMask");
			var houseImg = self.find("img");
			houseMask.css({"width":($(window).width()-20)+"px","height":houseImg.get(0).offsetHeight+"px"});
		});
	},
	setBody:function()
	{
		$(window).bind("load resize",function()
		{
			var h=$(window).height();
			$("#houseList").css({"height" : (h-44)+"px"});
		});
	},
	hosueList:function()
	{
		var _this = this;
		var html  = "";
		var html
		_this.bindUpDownScroll("houseList",$("#pullDown"),$("#pullUp"),
		function(scroller){
			html = '<li><a href="#"><img src="static/img/test/test1.jpg" /><i class="houseMask"></i><h3>Ascott商务服务中心</h3><em></em><span>老牌英伦风格，顶级配置</span></a></li>';
			$(".houseList ul").prepend(html);
			_this.houseMask();
			scroller.refresh();
		},
		function(scroller)
		{
			html = '<li><a href="#"><img src="static/img/test/test1.jpg" /><i class="houseMask"></i><h3>Ascott商务服务中心</h3><em></em><span>老牌英伦风格，顶级配置</span></a></li>';
			$(".houseList ul").append(html);
			_this.houseMask();
			scroller.refresh();
		});
	},
	//上拉下拉加载数据
	bindUpDownScroll: function(el,pullDownEl,pullUpEl,callDownFun,callUpFun)
	{
		//var pullUpEl       = $('#pullUp');
		//var pullDownEl     = $('#pullDown');
		var pullDownOffset = pullDownEl.offset().top; //下拉
		var pullUpOffset   = pullUpEl.offset().top;   //上拉

		var indexScroll = new iScroll(el,{
			vScrollbar    : false,
			useTransition : true,
			checkDOMChanges:true,
			onBeforeScrollStart: function(e)
			{
				var target = e.target;
				e.preventDefault();

				//去除特殊控件的阻止默认事件
				while (target.nodeType != 1)
				{
					target = target.parentNode;
					if(target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
					{
						e.preventDefault();
					}
				}
			},
			onRefresh : function()
			{
				//加载完毕有刷新加载
				if(pullDownEl && pullDownEl.hasClass('loading'))
				{
					pullDownEl.removeClass('loading');
					pullDownEl.hide();
				}
				if(pullUpEl && pullUpEl.hasClass('loading'))
				{
					pullUpEl.removeClass('loading');
					pullUpEl.hide();
				}
			},
			onScrollMove : function()
			{
				if (this.y > 5 && !pullDownEl.hasClass('flip'))
				{
					pullDownEl.addClass('flip');
					pullDownEl.show();
					this.minScrollY = 0;
				}
				else if(this.y < 5 && pullDownEl.hasClass('flip'))
				{
					pullDownEl.removeClass('flip');
					this.minScrollY = -pullDownOffset;
				}
				else if (this.y < (this.maxScrollY - 5) && !pullUpEl.hasClass('flip'))
				{
					pullUpEl.addClass('flip');
					this.maxScrollY = this.maxScrollY;
					pullUpEl.show();
				}
				else if(this.y > (this.maxScrollY + 5) && pullUpEl.hasClass('flip'))
				{
					pullUpEl.removeClass('flip');
					this.maxScrollY = pullUpOffset;
				}
			},
			onScrollEnd : function()
			{
				//下拉滚动结束后去回调数据
				if (pullDownEl.hasClass('flip'))
				{
					pullDownEl.removeClass('flip');
					pullDownEl.addClass('loading');
					setTimeout(function()
					{
						callDownFun(indexScroll);
					},1000);
				}
				else if(pullUpEl.hasClass('flip'))
				{
					pullUpEl.removeClass('flip');
					pullUpEl.addClass('loading');
					setTimeout(function()
					{
						callUpFun(indexScroll);
					},1000);
				}
			}
		});
	},
	//下拉加载数据
	bindDownScroll: function(el,pullDownEl,callDownFun)
	{
		var pullDownOffset = pullDownEl.offset().top; //下拉

		var indexScroll = new iScroll(el,{
			vScrollbar    : false,
			useTransition : true,
			checkDOMChanges:true,
			onBeforeScrollStart: function(e)
			{
				var target = e.target;
				e.preventDefault();

				//去除特殊控件的阻止默认事件
				while (target.nodeType != 1)
				{
					target = target.parentNode;
					if(target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
					{
						e.preventDefault();
					}
				}
			},
			onRefresh : function()
			{
				//加载完毕有刷新加载
				if(pullDownEl && pullDownEl.hasClass('loading'))
				{
					pullDownEl.removeClass('loading');
					pullDownEl.hide();
				}
			},
			onScrollMove : function()
			{
				if (this.y > 5 && !pullDownEl.hasClass('flip'))
				{
					pullDownEl.addClass('flip');
					pullDownEl.show();
					this.minScrollY = 0;
				}
				else if(this.y < 5 && pullDownEl.hasClass('flip'))
				{
					pullDownEl.removeClass('flip');
					this.minScrollY = -pullDownOffset;

				}
			},
			onScrollEnd : function()
			{
				//下拉滚动结束后去回调数据
				if (pullDownEl.hasClass('flip'))
				{
					pullDownEl.removeClass('flip');
					pullDownEl.addClass('loading');
					setTimeout(function()
					{
						callDownFun(indexScroll);
					},1000);
				}
			}
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
