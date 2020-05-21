package com.yyf.mallcache.mapper;

import com.yyf.mallcache.bean.SecOrder;
import org.apache.ibatis.annotations.Param;

public interface SecOrderMapper {
	
Integer insert(SecOrder secOrder);
/**
 * ͨ���û�������ɱ��Ʒ�����Ҷ���
 * @return
 */

SecOrder selectByuserIdAndsecProdectId(@Param("userId") Integer userId, @Param("secproductId") Integer secproductId);
}
