$(document).ready(function() {
	var url = "product";
	var locationStr=window.location.href;
	var productId=locationStr.split("productId")[1].split("=")[1];
	//保存商品Id
	 $("#productIdVal").val(productId);
	var args = {
		"productId" : productId
	};
	$.post(url, args, function(data) {
		//填充属性
		$("#productNameDetaildiv>h1").html(data.product.productName);
		$("#productPriceDetailb").html(data.product.productPrice);
		$("b.sys_item_price").html(data.product.productPrice);
		$("#productsalesDetailspan").html(data.product.productSalesamount);
		$("#productstoreDetailspan").html(data.product.productStorenumber);
		getdetaillist(data);
		getLoupe(data);
		getproductDetail(data);
		getTasteul(data);
		
	});
});
function getLoupe(data){
  if(data.loupeBigImage[0]==null) return false;
	//放大镜图片
    $("#loupeDefault>a").attr("href",data.loupeBigImage[0].imagePath);
    $("#loupeDefault>a>img").attr("src",data.loupeMidImage[0].imagePath);
    $("#loupeDefault>a>img").attr("rel",data.loupeBigImage[0].imagePath);
   //手机显示图片
    $(".slides").append("<li><img src='"+data.loupeMidImage[0].imagePath+"' title='pic' /></li>");
    var str="<li class='tb-selected' > "+
    "<div class='tb-pic tb-s40'> <a href='#'  onclick='return thumblistclick(this)'> "+
  " <img src='"+data.loupeSmallImage[0].imagePath+"' mid='" +
  data.loupeMidImage[0].imagePath+"' big='"+data.loupeBigImage[0].imagePath+"' >" +
  		"</a>"+"</div>"+"</li>";
    for(var i=1;i<data.loupeBigImage.length;i++){
    str=str+"<li><div class='tb-pic tb-s40'> <a href='#' onclick='return thumblistclick(this)'> "+
    " <img src='"+data.loupeSmallImage[i].imagePath+"' mid='" +
    data.loupeMidImage[i].imagePath+"' big='"+data.loupeBigImage[i].imagePath+"' >" +
    		"</a>"+"</div>"+"</li>";
    //手机显示图片
    $(".slides").append("<li><img src='"+data.loupeMidImage[i].imagePath+"' title='pic' /></li>");
    }
    $("#thumblist").html(str);
}
function getTasteul(data){
	if(data.productTasteList==null) return false;
	var str="<li class='sku-line selected'>"+data.productTasteList[0] +"<i></i></li>";
	for(var i=1;i<data.productTasteList.length;i++){
		str=str+"<li class='sku-line selected'>"+data.productTasteList[i] +"<i></i></li>";
	}
	$("#tasteul").html(str);
}
//细节图片
function getdetaillist(data){
	if(data.detailImage==null) return false;
    var str="";
    for(var i=0;i<data.detailImage.length;i++){
    	str=str+"<img src='"+data.detailImage[i].imagePath+"' />";
    }
	$(".twlistNews").html(str);
}
function getproductDetail(data){
	if(data.product.productDetail==null) return false;
	var str=data.product.productDetail.split("&");
	var s=""
	for(var i=0;i<str.length;i=i+2){
		s=s+"<li title=''>"+str[i]+":&nbsp;"+str[i+1]+"</li>";
	}
	$("#J_AttrUL").html(s);
}