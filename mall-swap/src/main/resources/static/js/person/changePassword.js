$(document).ready(function(){
	$(".am-btn.am-btn-danger").click(function(){
		var url = "changeLoginPassword";
		var oldPassword= $(":input[name='oldPassword']").val();
		var newLogiPassword= $(":input[name='newLogiPassword']").val();
		var confirmPassword=$(":input[name='confirmPassword']").val();
		var args={
				"oldPassword":oldPassword,
				"newLogiPassword":newLogiPassword,
				"confirmPassword":confirmPassword
		};
		$.post(url,args,function(data){
			errorMessage(data);
		});

	});
});

function errorMessage(data){
	 var errorMessage="";
	 switch(data){
	 case "0": {
		 errorMessage="修改成功";
	 } break;
	 case "1": {
		 errorMessage="原密码错误";
	 } break;
	 case "2": {
		 errorMessage="两次密码不一致";
	 } break;
	 default: {
		 //改为跳转错误页面
		 errorMessage="出错了"; 
	 } break;
	 }	
	 $errorNode=$("<div style='color:red'>"+errorMessage + "</div>");
	 $errorNode .insertAfter($(".m-progress"));
	 setTimeout("$errorNode.remove()",2000);
}