//  搜索点击函数
//function searchClick() {
//	var productName = $("#searchInput").val();
//	var url = "search";
//    window.location.href=url+"?productName="+productName;
//}
$(document).ready(function() {
$("#ai-topsearch").click(function () {
	var productName = $("#searchInput").val();
	var url = "search";
    window.location.href=url+"?productName="+productName;
});
});