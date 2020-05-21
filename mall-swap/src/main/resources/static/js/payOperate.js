//支付操作的js代码
 function submitOrderClick(nodea){

	var url = "submitOrder";
	//把选中购物项传给后端
	var len=$(".bundle-main>ul").length;
	var productIds=[];
	//选中地址
	//userId
	var addressId=$(".user-addresslist.defaultAddr").find(":input[name='addressId']").val();
	if(addressId>0==false) {
		errorAlert('9');
		return;
	}
	for(var i=0;i<len;i++){
		var temp=$($(".bundle-main>ul")[i]).children("li[class='td td-item']")
				.find(":input[name='productId']").val();
		productIds[i]=temp;
	}
	var accountNumber=$(":input[name='accountNumber']").val();
	if(accountNumber>0==false){
		errorAlert('8');
		return;
	}
	var loginId=$(":input[name='loginId']").val();

	var args={
			"addressId":addressId,
			"productIds[]":productIds,
			"loginId":loginId,
			"accountNumber":accountNumber
	};
	$.post(url,args,function(data){
		errorAlert(data);
	});
 }
 //错误提醒
 function errorAlert(data){
	 var errorMessage="";
	 switch(data){
	 case "1": {
		 window.location.href="paySuccess"
	 } break;
	 case "2": {
		 errorMessage="交易失败";
	 } break;
	 case "3": {
		 errorMessage="登陆账号错误";
	 } break;
	 case "4": {
		 errorMessage="支付密码错误";
	 } break;
	 case "5": {
		 errorMessage="余额不足";
	 } break;
	 case "6": {
		 errorMessage="库存不足";
	 } break;
	 case "7": {
		 errorMessage="请重新登陆";
	 } break;
	 case "8": {
		 errorMessage="支付密码必须为数字";
	 } break;
	 case "9": {
		 errorMessage="请选择一个地址";
	 } break;
	 default: {
		 //改为跳转错误页面
		 errorMessage="出错了"; 
	 } break;
	 }
	 $errorNode=$("<div style='color:red'>"+errorMessage + "</div>");
	 $errorNode .insertBefore($(".theme-popover[name='passwordMask']")
	 .find(".am-form-group:first"));
	 removeErrorMessage($errorNode);
 }
  //时间函数 移除错误信息
 
 function removeErrorMessage($errorNode){
	 setTimeout("$errorNode.remove()",2000);
 }
