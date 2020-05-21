package com.yyf.mallcache.mapper;


import java.util.List;

import com.yyf.mallcache.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
  
    int deleteByPrimaryKey(Integer userId);

  
    int insert(User record);

 
    User selectByPrimaryKey(Integer userId);


    List<User> selectAll();


    int updateByPrimaryKey(User record);
    
    User selectByLoginId(String loginId);
    /**
               *  �жϵ�½Id�Ƿ�ʹ��
     * @param loginId
     * @return
     */
    Integer confirmLoginId(String loginId);
    
    Integer updateUserImage(@Param("userId") Integer userId, @Param("imageId") Integer imageId);
    /**
     * �޸�����
     * @param userId
     * @param loginPassword
     * @return
     */
    Integer updateLoginPassword(@Param("userId") Integer userId, @Param("loginPassword") String loginPassword);
    Integer updateUserName(@Param("userId") Integer userId, @Param("userName") String userName);

}