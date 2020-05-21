package com.yyf.mallcache.mapper;


import com.yyf.mallcache.bean.Address;

import java.util.List;

public interface AddressMapper {

    int deleteByPrimaryKey(Integer addressId);


    int insert(Address record);


    Address selectByPrimaryKey(Integer addressId);



    int updateByPrimaryKey(Address record);
    
    List<Address> selectAByUserID(Integer userId);
}