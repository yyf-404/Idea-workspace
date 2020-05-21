//获取用户名
$(document).ready(function() {
	var url = "getUserOrder";
	var args = {};
	$.post(url, args, function(data) {
		getOrderItems(data);
	});
});
// 生成订单
function getOrderItems(data) {
	if (data == null || data == "") {
		//移除本来存在的订单项
		$(".m-logistics").remove();
	} else {
		getOrderAmount(data);
	}
}
function getOrderAmount(data) {
	var sendCount = 0;
	var takeCount = 0;
	var finishCount = 0;
	for (var i = 0; i < data.length; i++) {
		// 待发货
		if (data[i].orderWay == "待发货")
			sendCount++;
		// 待发货
		if (data[i].orderWay == "待收货")
			takeCount++;
		// 已完成
		if (data[i].orderWay == "已完成")
			finishCount++;

	}
	// m-order
	$(".m-order").find("li[name='send']").find("em").html(sendCount);
	$(".m-order").find("li[name='take']").find("em").html(takeCount);
	$(".m-order").find("li[name='finish']").find("em").html(finishCount);
	getOrderItem(data);
}
function getOrderItem(data) {
	var len = data.length;
	// 只显示最近的一条
	$(".m-logistics").find("p[name='productName']").html(
			data[0].items[0].product.productName);
	//图片
	if(data[0].items[0].product.image!=null)//productImage
		{
		$(".m-logistics").find("img[name='productImage']").attr(
			"src",data[0].items[0].product.image.imagePath);
		}
	var date = new Date(data[0].orderTime);
	var str = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
			+ date.getDate() + " " + date.getHours() + ":"
			+ date.getMinutes() + ":" + date.getSeconds();
	$(".m-logistics").find("time").html(str);

}