$(document).ready(function(){
	$("#dyMobileButton>img").click(function(){
		var url = "ValidInputServlet";
		//必须每次改变src 图片才会刷新 
		$("#dyMobileButton>img").attr("src","validInput?flag="+Math.random());
	});
});
function loginClick(){
	var url = "userLogin";
	var loginId= $(":input[name='loginId']").val();//loginPassword
	var loginPassword= $(":input[name='loginPassword']").val();
	var validCode=$(":input[name='validCode']").val();
	var args={
			"loginId":loginId,
			"loginPassword":loginPassword,
			"validCode":validCode
	};
	$.post(url,args,function(data){
		errorUser(data);
	});

}
function errorUser(data){
	 var errorMessage="";
	 switch(data){
	 case "1": {
		 window.location.href="loginSuccess"
	 } break;
	 case "3": {
		 errorMessage="验证码错误";
	 } break;
	 default: {
		 //改为跳转错误页面
		 errorMessage="用户名或密码错误";
	 } break;
	 }	
	 $errorNode=$("<div style='color:red'>"+errorMessage + "</div>");
	 $errorNode .insertAfter($("h3:first"));
	 $("#dyMobileButton>img").attr("src","validInput?flag="+Math.random());
	 setTimeout("$errorNode.remove()",2000);
}