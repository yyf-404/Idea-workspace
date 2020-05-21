$(document).ready(function() {
	var url = "secProductList";

	var args = {
		
	};
	$.post(url, args, function(data) {
		getSecProductList(data);

	});
});

/* 食品列表函数 传入json数据 */
function getSecProductList(data) {
	var str = "<div class='side-title'>秒杀商品</div>";
	for (var i = 0; i < data.length; i++) {
		var image = data[i].product.image;
		if (image == null) {
			image = new Object();
			image.imagePath = "images/search/search1.jpg";
		}

		str=str+"<li> <div class='i-pic check'>"
		+"<a href='secintroduction?productId="+data[i].product.productId+"productId' target='_blank'>"
			+"<img src='"+image.imagePath+"' />"
			+"<p class='check-title'>"+data[i].product.productName+"</p>"
			+"<p class='price fl'> <b>¥</b>"
			+"	 <strong>"+data[i].secPrice+"</strong>"
			+"</p><p class='number fl'>"
			+"数量<span>"+data[i].secStorenumber+"</span>"
			+" </p></a></div></li>";
		$(".search-side").html(str);
	
	}


}
