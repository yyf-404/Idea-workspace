$(document).ready(function() {
$(".info-btn>:button").click(function(){
	var url = "updateUserName";
	var userName=$(":text[name='userName']").val();
	var args = {
			"userName":userName
	};
	$.post(url, args, function(data) {
		if( data>0){
        location.reload();
	}
	});
	
});
});