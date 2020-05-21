package com.yyf.mallcache.service;

public interface SecService {
String toSec(Integer userId, Integer secproductId);
String validSecBySql(Integer userId, Integer secproductId);
 String submitSec(Integer userId, Integer secproductId);
 String getSecResult(Integer userId, Integer secproductId);
 String createSecPath(Integer userId, Integer secproductId);
 boolean checkPath(Integer userId, Integer secproductId, String path);
}
