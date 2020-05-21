$(document).ready(function(){
	url="getAccountBalance";
	args={};
	$.post(url,args,function(data){
		if(data>0)
		$(".num.ng-binding:first").html(data);
	});
});