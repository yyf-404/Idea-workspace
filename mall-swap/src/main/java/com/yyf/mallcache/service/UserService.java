package com.yyf.mallcache.service;

import com.yyf.mallcache.bean.Address;
import com.yyf.mallcache.bean.User;

import java.math.BigDecimal;
import java.util.List;



public interface UserService {
User userLogin(String loginId, String loginPassword);

User userRegister(String loginId, String loginPassword);

User getUser(String loginId);
String confirmRegisterInput(String loginId, String loginPassword, String passwordRepeat);
List<Address> getAddress(Integer userId);

BigDecimal getBalance(Integer userId);

void savaAddress(Integer userId, String addressProvince, String addressCity, String addressDetail,
                 String addressName, String addressPhone);

void deleteAddress(Integer addressId);

String changeLoginPassword(String oldPassword, String newLogiPassword, Integer userId);

String updateUserName(Integer userId, String userName);
}
