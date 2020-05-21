function addToCart(){

	var userId= $(":input[name='userId']").attr("value");
	//验证用户id是否存在
	 if(userId>0){
	var  productAmount=$("#text_box").val();
	var productId=$("#productIdVal").val();
	var url = "addToCart";
	var args = {
		"productId" : productId,
		"productAmount" : productAmount,
		"userId" : userId
	};
	$.post(url, args, function(data) {
	});
	//商品购买时 购物车显示会增加（有待修改）
     var num=$(".cart_num").html();
     if(num==0){
    	   num=num-0+1;
    	     $(".cart_num").html(num);
     }
     $("#payMessage").show();
     setTimeout("$('#payMessage').hide()",1000);
     return false; 
	 }
}
//立刻购买
function payinitClick(aNode){
	var productId=$("#productIdVal").val();
	str="?productId="+productId;
	var href=aNode.href
	$(aNode).attr("href",href+str);
	
}