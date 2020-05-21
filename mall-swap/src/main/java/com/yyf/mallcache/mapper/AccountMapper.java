package com.yyf.mallcache.mapper;



import java.math.BigDecimal;
import java.util.List;

import com.yyf.mallcache.bean.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);
    
    Integer selectAccountNumberByUserId(Integer userId);
    /**
     * ��ѯ�˻����
     * @param userId
     * @return
     */
    BigDecimal selectAccountBalanceByUserId(Integer userId);
    /**
     * ��ѯ�˻� ������ѯ֧������
     * @param userId
     * @return
     */
     Integer updateAccountBalance(@Param("accountBalance") BigDecimal accountBalance
             , @Param("userId") Integer userId);
     
    Account  selectByUserId(Integer userId);
    

    int updateByPrimaryKey(Account record);
    
    Integer updateAccountNumber(@Param("userId") Integer userId, @Param("accountNumber") Integer accountNumber);
}