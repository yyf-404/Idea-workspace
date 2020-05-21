$(document).ready(function(){
	getPayAddress();
});
//地址
function getPayAddress(){
	 var url = "getUserProducts";
	 var args={
	 };
	 $.post(url, args, function(data) {
		 for(var i=0;i<data.length;i++){
			 $nodeli=$(".s-item-wrap:first").clone(true);
		 $nodeli.find(".s-title a").html(data[i].productName);
		 $nodeli.find(".s-value").html(data[i].productPrice);
		 $nodeli.find(".s-comment").html("库存:"+data[i].productStorenumber);
		 $nodeli.find(".s-sales").html("销量:"+data[i].productSalesamount);
			 $nodeli.find(".s-pic img").attr("src",data[i].image.imagePath);
		 $(".s-content").append($nodeli);
		 }
		 $(".s-content .s-item-wrap:first").remove();
	 });
}
