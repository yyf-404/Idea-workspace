package com.yyf.mallcache.mapper;


import com.yyf.mallcache.bean.Order;

import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(Integer orderId);


    int insert(Order record);


    Order selectByPrimaryKey(Integer orderId);


    List<Order> selectAll();


    int updateByPrimaryKey(Order record);
    //ͨ���û�id��ѯ ����
    List<Order> selectByUserId(Integer userId);
}