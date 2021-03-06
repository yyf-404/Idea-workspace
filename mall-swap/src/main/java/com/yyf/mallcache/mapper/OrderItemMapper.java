package com.yyf.mallcache.mapper;


import java.util.List;
import com.yyf.mallcache.bean.OrderItem;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table order_item
	 *
	 * @mbggenerated Thu Aug 01 11:20:46 CST 2019
	 */
	int deleteByPrimaryKey(Integer itemId);

	int insert(OrderItem record);

	/**
	 * ��������
	 * 
	 * @param items
	 * @return
	 */
	int batchinsert(@Param("items") List<OrderItem> items);

	
	OrderItem selectByPrimaryKey(Integer itemId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table order_item
	 *
	 * @mbggenerated Thu Aug 01 11:20:46 CST 2019
	 */
	List<OrderItem> selectAll();


	int updateByPrimaryKey(OrderItem record);
	
	List<OrderItem> selectByOrderId(Integer orderId);
}