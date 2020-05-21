//获取用户名
$(document).ready(function() {
	var url = "getUserMessage";
	var args = {
	};
	$.post(url, args, function(data) {
		if( data.userId!=null){
        $(".m-userinfo").find("em[class='s-name']").html("("+data.userName+")");
        if(data.headImage!=null)
        $(".m-userinfo").find("img").attr("src",data.headImage.imagePath);

	}
	});
});