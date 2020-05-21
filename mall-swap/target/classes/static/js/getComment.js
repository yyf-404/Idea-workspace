$(document).ready(function () {
	getComments();
	$("#CommentsSubmit").click(function () {
		var url = "submitComments";
		var productId = $("#productIdVal").val();
		var userId=$("#userId").val();
		var words=$("textarea").val();
		var args = {
			"productId": productId,
			"userId":userId,
			"words":words
		};
		$.post(url, args, function (data) {
			$(".am-comments-list li:not(:first)").remove();
			getComments();
		});

	});
});


function getComments() {
	var url = "getComments";
	var productId = $("#productIdVal").val();
	var args = {
		"productId": productId
	};
	$.post(url, args, function (data) {
		for (var i = 0; i < data.length; i++) {

			$nodeli = $(".am-comments-list li:first").clone(true);
			$nodeli.find(".am-comment-avatar").attr("src",data[i].user.headImage.imagePath);
			$nodeli.find(".am-comment-author").html(data[i].user.userName);
			$nodeli.find(".J_TbcRate_ReviewContent").html(data[i].words);
			$nodeli.find("time").html(data[i].time);

			$(".am-comments-list").append($nodeli);
		}
		$(".am-comments-list li:first").remove();
	});
}
