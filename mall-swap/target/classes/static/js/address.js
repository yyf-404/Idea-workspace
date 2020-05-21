/*! jQuery v1.7.1 jquery.com | jquery.org/license */

 $(document).ready(function(){

     $(function(){
         $(".add").click(function(){
          var t=$(this).parent().find('input[class*=text_box]');
         t.val(parseInt(t.val())+1)
        })
      $(".min").click(function(){
       var t=$(this).parent().find('input[class*=text_box]');
         t.val(parseInt(t.val())-1)
         if(parseInt(t.val())<0){
          t.val(0);
          }
       })
    }) 

	
	// 兼容IE浏览器
	    if (!document.getElementsByClassName) {
        document.getElementsByClassName = function (cls) {
            var ret = [];
            var els = document.getElementsByTagName('*');
            for (var i = 0, len = els.length; i < len; i++) {

                if (els[i].className.indexOf(cls + ' ') >=0 || els[i].className.indexOf(' ' + cls + ' ') >=0 || els[i].className.indexOf(' ' + cls) >=0) {
                    ret.push(els[i]);
                }
            }
            return ret;
        }
    }
 
 
//地址选择
				$(function() {
					$(".user-addresslist").click(function() {
						$(this).addClass("defaultAddr").siblings().removeClass("defaultAddr");
					});
					//物流方式 与 支付方式选择
					$(".logistics").each(function() {
						var i = $(this);
						var p = i.find("ul>li");
						p.click(function() {
							if (!!$(this).hasClass("selected")) {
								$(this).removeClass("selected");
							} else {
								$(this).addClass("selected").siblings("li").removeClass("selected");
							}
						})
					})
				});
 
 
 
 
})
 
// 弹出地址选择
 
			$(document).ready(function($) {
	
				var $ww = $(window).width();
	//地址
		/*		$('.theme-login').click(function() {
//					禁止遮罩层下面的内容滚动
					$(document.body).css("overflow","hidden");
				
					$(this).addClass("selected");
					$(this).parent().addClass("selected");

									
					$('.theme-popover-mask').show();
					$('.theme-popover-mask').height($(window).height());
					$(".theme-popover[name='addressMask']").slideDown(200);																		
					
				})*/
				$('.theme-login').click(function() {
					window.open("person/address");
				})
			//账户 
					$("#J_Go").click(function() {
//					禁止遮罩层下面的内容滚动
					$(document.body).css("overflow","hidden");
//					$(this).addClass("selected");
//					$(this).parent().addClass("selected");
					$('.theme-popover-mask').show();
					$('.theme-popover-mask').height($(window).height());
					$(".theme-popover[name='passwordMask']").slideDown(200);																		
					
				})
				$('.theme-poptit .close,.btn-op .close').click(function() {

					$(document.body).css("overflow","visible");
					$('.theme-login').removeClass("selected");
					$('.item-props-can').removeClass("selected");					
					$('.theme-popover-mask').hide();
					$(".theme-popover").slideUp(200);
				})

				
			}); 
 
 
 
 
 
 
 
 
 
 
 
 

