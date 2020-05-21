
function loginClick(){
	var url = "userRegister";
	var loginId= $(":input[name='loginId']").val();//loginPassword
	var loginPassword= $(":input[name='loginPassword']").val();
	var passwordRepeat=$(":input[name='passwordRepeat']").val();
	var args={
			"loginId":loginId,
			"loginPassword":loginPassword,
			"passwordRepeat":passwordRepeat
	};
	$.post(url,args,function(data){
		errorStr(data);
	});
}
function errorStr(data){
	 var errorMessage="";
	 switch(data){
	 case "0": {
		 window.location.href="registerSuccess";
	 } break;
	 case "1": {
		 errorMessage="登陆ID已被使用";
	 } break;
	 case "2": {
		 errorMessage="两次密码不一致";
	 } break;
	 case "3": {
		 errorMessage="登陆ID和登陆密码必须大于于6位";
	 } break;
	 default: {
		 //改为跳转错误页面
		 errorMessage="出错了"; 
	 } break;
	 }	
	 $errorNode=$("<div style='color:red'>"+errorMessage + "</div>");
	 $errorNode.insertAfter($("ul:first"));
	 setTimeout("$errorNode.remove()",2000);
}
