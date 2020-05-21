//获得用户界面的订单项
$(document).ready(function() {
	var url = "getUserOrder";
	var args = {
		
	};
	$.post(url, args, function(data) {
		getOrderItems(data);
		/* getOrderAddress(data.userId); */
	});
});
// 生成订单
function getOrderItems(data){
	if(data==null|| data==""){
		
	}else{
		getAllOrder(data);
		getDeliverOrder(data);
		getWaitTakeOrder(data);
		 getFinishOrder(data);
	}
}
function getAllOrder(data){
	for(var i=0;i<data.length;i++){
		// 克隆节点
		$divNode=$("#orderDemodiv").clone();
         //去除重复id
		$divNode.attr("id","");
		// 保存订单id
		$divNode.find(".order-title").find(".dd-num>a").
        html(data[i].orderId);
		var date =new Date(data[i].orderTime);
	   var str = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " 
	  + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		$divNode.find(".order-title").find("span").html("成交时间："+str);
		//计算总金额
		var totalPrice=0;
		for(var j=0;j<data[i].items.length;j++){//td td-item
			$ulNode=$("#orderItemDemo").clone();
			$ulNode.attr("id","");
        if(data[i].items[j].product.image!=null){
        $ulNode.find(".td.td-item").find(".item-pic>a>img").
        attr("src",data[i].items[j].product.image.imagePath);
        }
        //商品名
        $ulNode.find(".td.td-item").find(".item-info p:first").
        html(data[i].items[j].product.productName);
        // 价格
        $ulNode.find(".td.td-price").find(".item-price").
        html(data[i].items[j].itemPrice);
        totalPrice=totalPrice+parseFloat(data[i].items[j].itemPrice);
        //数量 number
        $ulNode.find(".td.td-number").find(".item-number").
        html("×"+data[i].items[j].productAmount);
        //加入当前生成的订单ul
        $divNode.find(".order-left").append($ulNode);
		}
		//总价格
		$divNode.find(".order-right").find(".td.td-amount")
		.find(".item-amount").html("合计:"+totalPrice);
		//订单状态 td td-status
		$divNode.find(".order-right").find(".td.td-status")
		.find(".item-status>p:first").html(data[i].orderWay);
		//删除重复的模板元素
		 $divNode.find(".order-left>ul:first").remove();
		$("#tab1").find(".order-list").append($divNode);
		}
}
//代发货的订单
function getDeliverOrder(data){
	setOrderItem(data,"#tab2","待发货");	
}
//代收货的订单
function getWaitTakeOrder(data){
	setOrderItem(data,"#tab3","待收货");
}
//已完成订单
function getFinishOrder(data){
	setOrderItem(data,"#tab4","已完成");	
}

function setOrderItem(data,nodeStr,conditionStr){
	for(var i=0;i<data.length;i++){
		//判断是否为待发货订单
		if(data[i].orderWay!=conditionStr) continue;
		// 克隆节点
		$divNode=$("#orderDemodiv").clone();
         //去除重复id
		$divNode.attr("id","");
		// 保存订单id
		$divNode.find(".order-title").find(".dd-num>a").
        html(data[i].orderId);
		var date =new Date(data[i].orderTime);
	   var str = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " 
	  + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		$divNode.find(".order-title").find("span").html("成交时间："+str);
		//计算总金额
		var totalPrice=0;
		for(var j=0;j<data[i].items.length;j++){//td td-item
			$ulNode=$("#orderItemDemo").clone();
			$ulNode.attr("id","");
        if(data[i].items[j].product.image!=null){
        $ulNode.find(".td.td-item").find(".item-pic>a>img").
        attr("src",data[i].items[j].product.image.imagePath);
        }
        //商品名
        $ulNode.find(".td.td-item").find(".item-info p:first").
        html(data[i].items[j].product.productName);
        // 价格
        $ulNode.find(".td.td-price").find(".item-price").
        html(data[i].items[j].itemPrice);
        totalPrice=totalPrice+parseFloat(data[i].items[j].itemPrice);
        //数量 number
        $ulNode.find(".td.td-number").find(".item-number").
        html("×"+data[i].items[j].productAmount);
        //加入当前生成的订单ul
        $divNode.find(".order-left").append($ulNode);
		}
		//总价格
		$divNode.find(".order-right").find(".td.td-amount")
		.find(".item-amount").html("合计:"+totalPrice);
		//订单状态 td td-status
		$divNode.find(".order-right").find(".td.td-status")
		.find(".item-status>p:first").html(data[i].orderWay);
		//删除重复的模板元素
		 $divNode.find(".order-left>ul:first").remove();
		$(nodeStr).find(".order-list").append($divNode);
		}	
}