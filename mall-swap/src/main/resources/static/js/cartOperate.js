
function subAmountClick(node){
	var productAmount=$(node).next().val()-1;
	var userId=$("#hiddenVal>:input[name='userId']").val();
	var productId=$(node).parents("ul").children("li[class='td td-item']").find(":input").val();
	var url = "updateShoppingItem";
	var args = {"productId":productId,
		"productAmount":productAmount,
		"userId":userId
	};
	
	$.post(url, args, function(data) {
		if(data>0){
			//数量改变
			$(node).parents("ul").children("li[class='td td-amount']").find(":text").
			val(productAmount);
			//items价格改变 
			$(node).parents("ul").children("li[class='td td-sum']").find("em[class='J_ItemSum number']").
			html(data);
			//重新计算价格
			checkClick();
			
		}
	});	
	
}
function addAmountClick(node){
	var productAmount=parseInt($(node).prev().val())+1;
	var userId=$("#hiddenVal>:input[name='userId']").val();
	var productId=$(node).parents("ul").children("li[class='td td-item']").find(":input").val();
	var url = "updateShoppingItem";
	var args = {"productId":productId,
		"productAmount":productAmount,
		"userId":userId
	};
	
	$.post(url, args, function(data) {
		if(data>0){
			//数量改变
			$(node).parents("ul").children("li[class='td td-amount']").find(":text").
			val(productAmount);
			//items价格改变 
			$(node).parents("ul").children("li[class='td td-sum']").find("em[class='J_ItemSum number']").
			html(data);
			//重新计算价格
			checkClick();
			
		}
	});	
	
}
//删除某个购物项
function deleteItemClick(node){
	var flag=confirm("你确定要删除这件商品么");
	if(flag==false) return false;
	var userId=$("#hiddenVal>:input[name='userId']").val();
	var productId=$(node).parents("ul").children("li[class='td td-item']").find(":input").val();
	var url = "deleteShoppingItem";
	var args = {"productId":productId,
		"userId":userId  };	
	$.post(url, args, function(data) {
		if(data>0){
			$(node).parents("ul").remove();
			$(node).parents("ul").html("");
			//重新计算价格
			checkClick();
			
		}
	});	
}
	//删除全部购物项
	function deleteAllClick(){
		var flag=confirm("你确定要删除所有商品么");
		if(flag==false) return false;
		var userId=$("#hiddenVal>:input[name='userId']").val();
		var url = "deleteAllItem";
		var args = {
			"userId":userId 
			};	
		$.post(url, args, function(data) {
			if(data>0){
				//刷新页面
				location.reload(); 
				
			}
		});	
	
}
	//结算点击函数
	function payClick(aNode){
		//给href加上参数 传过去选中的product productId=1& productId=2
		var product=$(".bundle-main .cart-checkbox>:checked[name='items[]']").parents("ul")
		.find(":input[name='productId']");
		if(product.length<=0) return false;
		var str="?";
		for(var i=0;i<product.length;i++){
			if(i!=0) str=str+"&";
			str=str+"productId="+$(product[i]).val();
		}
		var href=aNode.href
		$(aNode).attr("href",href+str);
		
	}