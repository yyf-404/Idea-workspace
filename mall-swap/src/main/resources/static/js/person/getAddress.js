$(document).ready(function(){
	getPayAddress();
});
//地址
function getPayAddress(){
	 var url = "getAddress";
	 var args={
	 };
	 $.post(url, args, function(data) {
		 for(var i=0;i<data.length;i++){
			 $nodeli=$("#addressul li:first").clone(true);
			 $nodeli.removeClass("defaultAddr");
			 if(i==0){
				 $nodeli.addClass("defaultAddr");
			 }
		 $nodeli.find(".province").html(data[i].addressProvince);
		 $nodeli.find(".city").html(data[i].addressCity);
		 $nodeli.find(".detail").html(data[i].addressDetail);
		 $nodeli.find(".new-txt").html(data[i].addressName);
		 $nodeli.find(".new-txt-rd2").html(data[i].addressPhone);
		 
		 $nodeli.find(":input[name='addressId']").val(data[i].addressId);
		 $("#addressul").append($nodeli);
		 $("#addressul").append("<div class='per-border'></div>");
		 }
		 $("#addressul li:first").remove();
	 });
}
function deleteAddressClick(nodea){
	var flag=confirm("你确定要删除这个地址么");
	if(flag==true){
	var addressId=$(nodea).parents("li").find(":input[name='addressId']").val();
	 var url = "deleteAddress";
	 var args={
		"addressId":addressId	 
	 };
	 $.post(url, args, function(data) { 
		 
	 });
	 window.location.reload();
	}
}