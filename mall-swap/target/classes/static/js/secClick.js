$(document).ready(function() {
	$("#secButton").click(function() {
      if($("#secButton").attr("title")=="秒杀正在进行"){
    	  getSecPath();
      }
	});

});
function getSecPath(){
	  var url="getSecPath";
	  var secproductId= $("#productIdVal").val();
	  var args={
		"secproductId":secproductId	  
	  };
	$.post(url,args,function(data){
			toSecById(data);
	});	
}
//跳转秒杀页面
function toSecById(path){
	  var secproductId= $("#productIdVal").val();
	  var url=path+"/toSec";
	  var args={
		"secproductId":secproductId	  
	  };
	$.post(url,args,function(data){
		doSecByData(data);
		
	});	
}
//轮询秒杀结果
function getSecResult(){
	  var url="getSecResult";
	  var secproductId= $("#productIdVal").val();
	  var args={
		"secproductId":secproductId	  
	  };
	$.post(url,args,function(data){
		doSecByData(data);
	});
	
}
function doSecByData(data){

	switch(data){
	case "500":{
		window.location.href="paySuccess";
	};break;
	case "501":{
		window.location.href="login";
	};break;
	case "502":{
		 layer.msg("不能重复下单");
	};break;
	case "503":{
		 layer.msg("商品已经售空");
	};break;
	case "504":{
		 layer.msg("请在个人信息中添加地址");
	};break;
	case "505":{
		//50 毫秒后轮询
		setTimeout("getSecResult()",50);
	};break;
	case "506":{
		layer.msg("非法请求");
	};break;
	default:{
		 layer.msg("出错了");
	};break;
	}
}