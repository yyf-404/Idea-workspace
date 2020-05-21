//bundle-main
$(document).ready(function() {
	
	var url = "toShoppingCart";
	var args = {
		
	};
	
	$.post(url, args, function(data) {
		getItems(data);
		
	});
});
//得到购物项的信息
function getItems(data){
	if(data==null|| data==""){
		
	}else{
		//保存用户名
		$("#hiddenVal>:input[name='userId']").val(data.userId);
		//购物车图片
		for(var i=0;i<data.shoppingItems.length;i++){
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
		//价格
		$newNode.children("li[class='td td-price']").find("em[class='price-original']").
		html(data.shoppingItems[i].product.productPrice);
		//折扣后价格
		$newNode.children("li[class='td td-price']").find("em[class='J_Price price-now']").
		html(data.shoppingItems[i].product.productPrice);
		//数量
		$newNode.children("li[class='td td-amount']").find(":text").
		val(data.shoppingItems[i].productAmount);
		//金额
		$newNode.children("li[class='td td-sum']").find("em[class='J_ItemSum number']").
		html(data.shoppingItems[i].itemPrice);
		if(data.shoppingItems[i].product.image!=null&&data.shoppingItems[i].product.image!="")
			$newNode.children("li[class='td td-item']").find("img").
			attr("src",data.shoppingItems[i].product.image.imagePath);
		
		$(".bundle-main").append($newNode);
		}
	}
}
function checkClick(){
	var count=0;
	var len=$(".bundle-main .cart-checkbox>:checked").length;
	//获得选中元素价格
	for(var i=0;i<len;i++){
		var temp=$($(".bundle-main .cart-checkbox>:checked")[i]).parents("ul")
				.children("li[class='td td-sum']").find("em[class='J_ItemSum number']").html();
		count=count+parseFloat(temp);
	}
	$("#J_SelectedItemsCount").html(len);
	$("#J_Total").html(count.toFixed(2));
}
function totalCheckClick(checkNode){
	var len=$(".bundle-main .cart-checkbox>:checkbox[name='items[]']").length;
	var len2=$(".bundle-main .cart-checkbox>:checked[name='items[]']").length;
     if($(checkNode).attr("checked")!="checked"){
    	 //如果没选中了  就全不选
    	 $(".bundle-main .cart-checkbox>:checkbox[name='items[]']").removeAttr("checked");
    	 $("#J_SelectedItemsCount").html(0);
    		$("#J_Total").html("0.00");
     }else{
    	 var count=0;
    	 //如果选中了 就全选
    	 $(".bundle-main .cart-checkbox>:checkbox[name='items[]']").attr("checked","true");
    		for(var i=0;i<len;i++){
    			var temp=$($(".bundle-main .cart-checkbox>:checkbox[name='items[]']")[i]).parents("ul")
    					.children("li[class='td td-sum']").find("em[class='J_ItemSum number']").html();
    			count=count+parseFloat(temp);
    		}
    		 $("#J_SelectedItemsCount").html(len);
     		$("#J_Total").html(count.toFixed(2));
     }
}