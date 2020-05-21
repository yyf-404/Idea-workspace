$(document).ready(function() {
	var url = "productList";
	var href=window.location.href.split("?");
	var productNames="";
	if(href[1]!=undefined)
	productNames=href[1].split("&");
	var productName;
	for(var i=0;i<=productNames.length-1;i++){
		var reg=/^productName=[\d\D]*$/;
		if(reg.test(productNames[i])){
			productName=decodeURI(productNames[i].split("=")[1]);
		}
	}
	if(productName== undefined) productName="";
	//保存查询的productName
	$("#hideVal>[name='productName']").val(productName);
	$(".font-pale").html("搜索关键字:"+productName);
	var args = {
		"productKind" : null,
		"orderWay" : 0,
		"pageNo" : 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
		setProductAmount(data);
	});
});
//页面显示查询结果总数
function setProductAmount(data){
	
	$("strong[class='num']").html(data.productTotalCount);
}
/* 食品列表函数 传入json数据 */
function getProductList(data) {
	var str = "";
	for (var i = 0; i < data.productList.length; i++) {
		var image = data.productList[i].image;
		if (image == null) {
			image = new Object();
			image.imagePath = "images/search/search1.jpg";
		}
		str = str + "<li> <a href='introduction?productId="+data.productList[i].productId+"productId' target='_blank'><div class='i-pic limit'>"
				+ "<img src='" + image.imagePath
				+ " ' />" + "<p class='title fl'>"
				+ data.productList[i].productName + "</p>"
				+ "<p class='price fl'>" + "<b>¥</b> <strong>"
				+ data.productList[i].productPrice + "</strong>" + "</p>"
				+ "<p class='number fl'>" + "销量<span>"
				+ data.productList[i].productSalesamount + "</span>" + "</p>"
				+ "</div></a></li>";
	}
	$("#product_list").html(str);
	// 当前第几页
	$("#hideVal>[name='pageNo']").val(data.pageNo);

}
/* 得到分页栏 传入json数据 */
function getPageUl(data) {
	//取整数  js除法 会带小数
	var pageCount = parseInt((data.productTotalCount - 1) / data.pageSize + 1);
	var str = "";
	str = "<li  id='pageliPre' onclick='return pagePreClick()'><a href='#'>&laquo;</a></li>";
	for (var i = 1; i <= pageCount; i++) {
		str = str + "<li id='pageli" + i + "' value='" + i
				+ "' onclick='return pageClick(this)'><a href='#'  >" + i
				+ "</a></li>";
	}
	str = str
			+ "<li  id='pageliNext' onclick='return pageNextClick()'><a  href='#'>&raquo;</a></li>";
	$("#pageUl").html(str);
	if (data.pageNo == 1) {
		$("#pageliPre").addClass("am-disabled");
		$("#pageliPre").attr("onclick", null);
	}
	if (data.pageNo == pageCount) {
		$("#pageliNext").addClass("am-disabled");
		$("#pageliNext").attr("onclick", null);
	}
	$("#pageli" + data.pageNo).addClass("am-active");
	$("#pageli" + data.pageNo).attr("onclick", null);
	$("#pageli" + data.pageNo + ">a").attr("href", "javascript:void(0)");
}
// 分页栏 1 2 3..点击函数
function pageClick(pageul) {
	// 从隐藏表单域获取值
	var productName = $("#hideVal>[name='productName']").val();
	var orderWay=$("#hideVal>[name='orderWay']").val();
	var url = "productList";
	var productKind=$("#hideVal>[name='productKind']").val();
	var pageNo = pageul.value;
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : pageNo,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
	return false;
}
// 分页栏 《《点击函数
function pagePreClick() {
	var productName = $("#hideVal>[name='productName']").val();
	var orderWay=$("#hideVal>[name='orderWay']").val();
	var url = "productList";
	var pageNo = $("#hideVal>[name='pageNo']").val();
	var productKind=$("#hideVal>[name='productKind']").val();
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : pageNo - 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
	return false;
}
// 分页栏 》》点击函数
function pageNextClick() {
	var productName = $("#hideVal>[name='productName']").val();
	var orderWay=$("#hideVal>[name='orderWay']").val();
	var url = "productList";
	var pageNo = $("#hideVal>[name='pageNo']").val();
	var productKind=$("#hideVal>[name='productKind']").val();
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : pageNo - 0 + 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
	return false;
}

//综合排序
function commonliClick() {
	// 排序条件
	//保存排序条件
	$("#hideVal>[name='orderWay']").val(0);
	//被选中的排序 会改变样式
	$(".sort>[class='first']").removeClass();
	$("#commonOrderli").addClass("first");
	$(".sortIcon").html("");
	var productName = $("#hideVal>[name='productName']").val();
	var productKind=$("#hideVal>[name='productKind']").val();
	var url = "productList";
	var args = {
		"productKind" : productKind,
		"orderWay" : 0,
		"pageNo" : 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
	$("#commonOrderli>a").attr("href", "javascript:void(0)");
	return false;
}
// 价格排序
function priceliClick() {
	// 排序条件
	var orderWay = $("#priceOrderli").val();
	$(".sort>[class='first']").removeClass();
	$("#priceOrderli").addClass("first");
	
	//保存排序条件
	$("#hideVal>[name='orderWay']").val(orderWay);
	var productName = $("#hideVal>[name='productName']").val();
	var productKind=$("#hideVal>[name='productKind']").val();
	var url = "productList";
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
if ($("#priceOrderli").val() == 1) {
	$(".sortIcon").html("");
	$("#priceOrderli").find(".sortIcon").html("↑");
	$("#priceOrderli").val(2);
	}else{
		$(".sortIcon").html("");
		$("#priceOrderli").find(".sortIcon").html("↓");
		$("#priceOrderli").val(1);
	}
	return false;
}
//销量排序
function salesliClick() {
	// 排序条件
	var orderWay = $("#salesCountOrderli").val();
	$(".sort>[class='first']").removeClass();
	$("#salesCountOrderli").addClass("first");
	var productKind=$("#hideVal>[name='productKind']").val();
	//保存排序条件
	$("#hideVal>[name='orderWay']").val(orderWay);
	var productName = $("#hideVal>[name='productName']").val();
	var url = "productList";
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
	});
if ($("#salesCountOrderli").val() == 3) {
	$(".sortIcon").html("");
	$("#salesCountOrderli").find(".sortIcon").html("↓");
	$("#salesCountOrderli").val(4);
	}else{
		$(".sortIcon").html("");
		$("#salesCountOrderli").find(".sortIcon").html("↑");
		$("#salesCountOrderli").val(3);
	}
	return false;
}
//种类选择
function kindSelectClick(selectdd) {
	// 排序条件
	var orderWay = $("#hideVal>[name='orderWay']").val();
	//保存排序条件
	var productKind=$(selectdd).attr("value");
	$("#hideVal>[name='productKind']").val(productKind);
	var productName = $("#hideVal>[name='productName']").val();
	var url = "productList";
	var args = {
		"productKind" : productKind,
		"orderWay" : orderWay,
		"pageNo" : 1,
		"productName" : productName
	};
	$.post(url, args, function(data) {
		getProductList(data);
		getPageUl(data);
		setProductAmount(data);
	});
	return false;
}