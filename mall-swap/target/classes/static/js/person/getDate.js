$(document).ready(function(){
	var date=new Date();
	  var yearAndMonth = date.getFullYear() + "." + (date.getMonth() + 1);
		var week="";
		switch(date.getDay()){
		case 0: week="星期天";break;
		case 1: week="星期一"; break;
		case 2: week="星期二";break;
		case 3: week="星期三";break;
		case 4: week="星期四";break;
		case 5: week="星期五";break;
		case 6: week="星期六";break;
		}
		$(".day-list").find("em").html(date.getDate());
		$(".day-list").find("span:first").html(week);
		$(".day-list").find("span:last").html(yearAndMonth);
});