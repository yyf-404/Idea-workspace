$(document).ready(function() {
$(".am-btn:submit").click(function(){
	var url = "addProduct";
	var productName=$(":text[name='productName']").val();
	var productDetail=$(":text[name='productDetail']").val();
	var productStorenumber=$(":text[name='productStorenumber']").val();
	var productPrice=$(":text[name='productPrice']").val();
	var  searchImage=$(":file[name='searchImage']").get(0).files[0];// 添加文件流
	var detailImage=$(":file[name='detailImage']").get(0).files[0];
	var file=$("#file").get(0).files[0];
	var form = new FormData();
	form.append("productName", productName);
	form.append("productDetail", productDetail);
	form.append("productStorenumber", productStorenumber);
	form.append("productPrice", productPrice);
	form.append("searchImage", searchImage);
	form.append("detailImage", detailImage);
	$.ajax({
		type: 'POST',
		url: url,
		data: form,
		processData: false,//不指定编码方式
		contentType: false,//告诉jquery不需要增加请求头对于contentType的设置
	success: function (arg) {
		returnMessage(arg)
	}
});
});
});

function returnMessage(data){
	var errorMessage="";
	$errorNode=$("");
	switch(data){
		case "1": {
			errorMessage="加入成功";
			$errorNode=$("<div style='color:green'>"+errorMessage + "</div>");
		} break;
		case "2": {
			errorMessage="加入失败";
			$errorNode=$("<div style='color:red'>"+errorMessage + "</div>");
		}  break;
	}
	alert(errorMessage);
	//$errorNode .insertAfter($("h3:first"));
	//setTimeout("$errorNode.remove()",2000);
}