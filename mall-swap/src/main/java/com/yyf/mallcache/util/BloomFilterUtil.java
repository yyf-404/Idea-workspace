package com.yyf.mallcache.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.yyf.mallcache.bean.Product;
import com.yyf.mallcache.mapper.ProductMapper;
import com.yyf.mallcache.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
  public class BloomFilterUtil implements InitializingBean {
    @Autowired
    ProductMapper productMapper;

    public static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100000, 0.01);
    public BloomFilterUtil (){

    }
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
   private void init(){
        LoggerUtil.getLogger(getClass()).info("productMapper"+productMapper);
        List<Product> list= productMapper.selectPageByCondition(1,20,null,0,"");
        for(int i=0;i<list.size();i++){
            bloomFilter.put(list.get(i).getProductId());
        }
    }


}
