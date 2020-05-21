$(document).ready(function() {
	var url = "toShoppingCart";
	var args = {
		
	};
	$.post(url, args, function(data) {
		getItems(data);
		getPayAddress(data.userId);
	});
});
//得到购物项的信息
function getItems(data){
	if(data==null|| data==""){
		
	}else{
		//得到被选中的商品
		var locationStr=window.location.href;
		var productStr=locationStr.split("?")[1].split("&");
		var len=0;
		var id=[];
		for(var i=0;i<productStr.length;i++){
			var reg=/^productId=[0-9]*$/;
			if(reg.test(productStr[i])){
				id[len]=productStr[i].split("=")[1];
				len++;
			}
		}
		
		//保存用户名
		$("#hiddenVal>:input[name='userId']").val(data.userId);
		//购物车图片
		for(var i=0;i<data.shoppingItems.length;i++){
			for(var j=0;j<len;j++){
				//不是选中的节点 不加入文档中
				if(data.shoppingItems[i].productId!=id[j]) continue;
			//克隆节点
		$newNode=$("#firstDemoul").clone();
		//去除重复id
		$newNode.attr("id","");
		//保存商品id
		$newNode.children("li[class='td td-item']").find(":input").
		val(data.shoppingItems[i].productId);
		//商品名
		$newNode.children("li[class='td td-item']").find("a").
		html(data.shoppingItems[i].product.productName);
		//商品种类
		$newNode.children("li[class='td td-info']").find("span").
		html("口味："+data.shoppingItems[i].product.productTaste);
		//折扣后价格
		$newNode.children("li[class='td td-price']").find("em[class='J_Price price-now']").
		html(data.shoppingItems[i].product.productPrice);
		//数量
		$newNode.children("li[class='td td-amount']").find("span").
		html(data.shoppingItems[i].productAmount);
		//金额
		$newNode.children("li[class='td td-sum']").find("em[class='J_ItemSum number']").
		html(data.shoppingItems[i].itemPrice);
		if(data.shoppingItems[i].product.image!=null&&data.shoppingItems[i].product.image!="")
			$newNode.children("li[class='td td-item']").find("img").
			attr("src",data.shoppingItems[i].product.image.imagePath);
		$(".bundle-main").append($newNode);
			}
			}
		getPayAmount();
	}
}
/**
 * 获得支付金额
 * @returns
 */
function getPayAmount(data){
	//实际价格
	var len=$(".bundle-main>ul").length;
	var count=0;
	for(var i=0;i<len;i++){
		count=count+parseFloat($($(".bundle-main>ul")[i]).children("li[class='td td-sum']").find("em[class='J_ItemSum number']").
		html());
	}
	$("#J_ActualFee").html(count.toFixed(2));
	$(".buy-point-discharge").find("em[class='pay-sum']").html(count.toFixed(2));
}
//地址
function getPayAddress(){
	 var url = "getAddress";
	 var args={
	 };
	 $.post(url, args, function(data) {
//		 $("#addressul .province").html(data[0].addressProvince);
//		 $("#addressul .city").html(data[0].addressCity);
//		 $("#addressul .detail").html(data[0].addressDetail);
//		 $("#addressul .buy-user").html(data[0].addressName);
//		 $("#addressul .buy-phone").html(data[0].addressPhone);
		 for(var i=0;i<data.length;i++){
			 $nodeli=$("#addressul li:first").clone(true);
			 if(i==0){
				 $nodeli.addClass("defaultAddr");
			 }
		 $nodeli.find(".province").html(data[i].addressProvince);
		 $nodeli.find(".city").html(data[i].addressCity);
		 $nodeli.find(".detail").html(data[i].addressDetail);
		 $nodeli.find(".buy-user").html(data[i].addressName);
		 $nodeli.find(".buy-phone").html(data[i].addressPhone);
		 //保存地址id
		 $nodeli.find(":input[name='addressId']").val(data[i].addressId);
		 $("#addressul").append($nodeli);
		 $("#addressul").append("<div class='per-border'></div>");
		 }
		});
}