//获取用户名
$(document).ready(function() {
	var url = "getUserMessage";
	var args = {
	};
	$.post(url, args, function(data) {
		if( data.userId!=null){
        $(".info-m").find("i:first").html(data.userName);
        if(data.headImage!=null)
        $(".filePic").find("img").attr("src",data.headImage.imagePath+"?t="+Math.random());
		}
	});
});