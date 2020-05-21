$(document).ready(function() {
	var url = "getUserMessage";
	var args = {
	};
	$.post(url, args, function(data) {
		if( data.userId!=null){
        $(".user_info>li:first").html("用户名："+data.userName);
        if(data.headImage!=null)
        $(".avatar_imgbox>img").attr("src",data.headImage.imagePath);
        $(":input[name='userId']").attr("value",data.userId);
        $("#menu-hdWord").html("你好,"+data.userName);
        $("#menu-hdWord").next().attr("href","userLogout");
        $("#menu-hdWord").next().html("注销");
	}
	});
});