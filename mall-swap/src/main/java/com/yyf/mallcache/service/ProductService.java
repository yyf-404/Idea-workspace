package com.yyf.mallcache.service;

import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.vo.ProductPO;
import com.yyf.mallcache.vo.ProductPageVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;




public interface ProductService {
 public ProductPageVO getProductPageVO(ProductPO productPO);

 public ProductInfo getProductInfo(Integer id);
 
 public SecProductInfo getSecProductInfo(Integer id) ;

 public List<SecProduct> getSecProductList();
 public Product updateProduct(Product product, ShoppingItem item);
 /*
  添加商品
  */
 public Boolean addProduct(Integer userId,String productName, String productDetail, BigDecimal productPrice,
                           Integer productStorenumber, InputStream fi1, InputStream fi2);
 public List<Product> getUserProducts(Integer userId);
 public List<Comment> getComments(Integer productId);
 public Integer submitComments(Integer productId,Integer userId,String words);
}
