package com.yyf.mallcache.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.mapper.*;
import com.yyf.mallcache.redis.ProductKey;
import com.yyf.mallcache.service.ImageService;
import com.yyf.mallcache.service.ProductService;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.util.BloomFilterUtil;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.yyf.mallcache.vo.ProductPO;
import com.yyf.mallcache.vo.ProductPageVO;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductInfoMapper productInfoMapper;
	@Autowired
	ImageMapper imageMapper;
	@Autowired
	SecProductMapper secProductMapper;
	@Autowired
	RedisService redisService;
	@Autowired
    ImageService imageService;
	@Autowired
	ProductImageMapper productImageMapper;
	@Autowired
	UserProductMapper userProductMapper;
	@Autowired
	CommentMapper commentMapper;
	@Override
	public ProductPageVO getProductPageVO(ProductPO productPO) {
		if(productPO.getProductName()==null||productPO.getProductName().equals("null"))
		{
			productPO.setProductName("");
		}
		if(productPO.getProductKind()==null||productPO.getProductKind().equals("null"))
		{
			productPO.setProductKind("");
		}
		List<Product> list = productMapper.selectPageByCondition(productPO.getPageNo(), ConfigUtil.PAGE_SIZE,
				productPO.getProductKind(), productPO.getOrderWay(), productPO.getProductName());
		ProductPageVO pagevo = new ProductPageVO();
		pagevo.setProductList(list);
		pagevo.setPageNo(productPO.getPageNo());
		LoggerUtil.getLogger(this.getClass()).info(Arrays.asList(list).toString());
		pagevo.setProductTotalCount(productMapper.getTotalcountByCondition(
				productPO.getProductKind(),  productPO.getProductName() ));
		return pagevo;
	}

	@Override
	public ProductInfo getProductInfo(Integer id) {
		if(id<=0 || !BloomFilterUtil.bloomFilter.mightContain(id)) return null;
		LoggerUtil.getLogger(getClass()).info("getProductInfo(id="+id+")");
		ProductInfo productInfo = productInfoMapper.getProductInfoById(id);
		Product product =productMapper.selectByPrimaryKey(id);
		if(product!=null) {
			productInfo.setProduct(product);
			productInfo.setDetailImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.Detail));
			productInfo.setLoupeBigImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPEBIG));
			productInfo.setLoupeSmallImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPESMALL));
			productInfo.setLoupeMidImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPEMid));
			String productName=productInfo.getProduct().getProductName();
			productInfo.setProductTasteList(productMapper.selectTasteByProductName(productName));
			LoggerUtil.getLogger(getClass()).info(productInfo.toString());
		}else {

		}
		return productInfo;
	}
	public List<SecProduct> getSecProductList(){

		return secProductMapper.selectAll();
	}
	private SecProduct getSecProductFromCache (Integer id){
		SecProduct product =redisService.get(ProductKey.getSecProductKey(),""+id,SecProduct.class);
		LoggerUtil.getLogger(getClass()).info("getSecProductFromCache( prduct="+product+" id="+id+")");
		if(product==null ){
			//进行加分布式锁设置
			//获取倒锁 ProductKey.getSecMutexKey()+id 为key  new Integer(1) 为value
			// ConfigUtil.TimeOut 超时时间  防止单个线程一直持有
            if(redisService.sent(ProductKey.getSecMutexKey(),""+id,new Integer(1),ConfigUtil.TimeOut)){
				LoggerUtil.getLogger(getClass()).info("获取到互斥锁");
				product =secProductMapper.selectByProductId(id);
				redisService.set(ProductKey.getSecProductKey(), ""+id, product);
				redisService.del(ProductKey.getSecMutexKey(),""+id);//释放锁
			}

		}
		return product;
	}
	public SecProductInfo getSecProductInfo(Integer id) {

		SecProductInfo productInfo = productInfoMapper.getSecProductInfoById(id);
		SecProduct product=getSecProductFromCache(id);
		if(productInfo!=null) {
			productInfo.setProduct(product);
			productInfo.setDetailImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.Detail));
			productInfo.setLoupeBigImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPEBIG));
			productInfo.setLoupeSmallImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPESMALL));
			productInfo.setLoupeMidImage(imageMapper.selectByProductIdGetKindImage(id, ConfigUtil.LOUPEMid));
			String productName=productInfo.getProduct().getProductName();
			productInfo.setProductTasteList(productMapper.selectTasteByProductName(productName));
			Date now=new Date();
			if(productInfo.getProduct().getStartDate().compareTo(now)>0) {
				productInfo.setSecWay(ConfigUtil.SEC_WAYBEFORM);
				productInfo.setRemailSeconds((productInfo.getProduct().getStartDate().getTime()-now.getTime())/1000);
			}else if (productInfo.getProduct().getEndDate().compareTo(now)<0){
				productInfo.setSecWay(ConfigUtil.SEC_WAYEND);
				productInfo.setRemailSeconds(new Long(0));
			}else {
				productInfo.setSecWay(ConfigUtil.SEC_WAYSTART);
				productInfo.setRemailSeconds(new Long(0));
			}
		}
		return productInfo;
	}
	// 更新库存 销量
	@CachePut(cacheNames ={"productId"},key ="#product.productId" )
	public Product updateProduct(Product product, ShoppingItem item) {
		if (item!=null){
			product.setProductStorenumber(product.getProductStorenumber() - item.getProductAmount());
			product.setProductSalesamount(product.getProductSalesamount() + item.getProductAmount());
		}

		productMapper.updateByPrimaryKey(product);
		return product;
	}
	/*

	 */
	@Override
	public Boolean addProduct(Integer userId,String productName, String productDetail, BigDecimal productPrice, Integer productStorenumber, InputStream fi1, InputStream fi2) {
		Integer searchImageId=imageService.imageUpload(fi1 , ConfigUtil.SEARCHIMAGE,productName);
		Integer detailImageId=imageService.imageUpload(fi2 , ConfigUtil.Detail,productName);
		Product product=new Product(null,productName,productDetail,productPrice,productStorenumber,0);
		productMapper.insert(product);
		Integer productId=product.getProductId();
		if(productId>0){
			ProductImage productImage=new ProductImage(productId,searchImageId,ConfigUtil.SEARCHIMAGE);
			ProductImage productImage2=new ProductImage(productId,detailImageId,ConfigUtil.Detail);
			LoggerUtil.getLogger(this.getClass()).info("productId="+productId+" searchImageId="+searchImageId);
			productImageMapper.insert(productImage);
			productImageMapper.insert(productImage2);
			userProductMapper.insert(new UserProduct(userId,productId));
			return true;
		}
		return false;
	}

	@Override
	public List<Product> getUserProducts(Integer userId) {
		List<Product> list=productMapper.selectProductByUserId(userId);
		LoggerUtil.getLogger(this.getClass()).info("userId="+userId+" "+Arrays.asList(list).toString());
		return list;
	}

	@Override
	public List<Comment> getComments(Integer productId) {
		return  commentMapper.selectByProductId(productId);
	}

	@Override
	public Integer submitComments(Integer productId, Integer userId, String words) {
		User user=new User();
		user.setUserId(userId);
		Comment comment=new Comment(user,productId,words,new Date());
		return commentMapper.insert(comment);
	}
}
