package com.yyf.mallcache.redis;

public class ProductKey extends BasePrefix {
	private final static  String PRODUCT="productKey:";
	private final static  String SECPRODUCT="secProduct";
	private final static  String MUTEX="mutex";
   private final static  String STOCK="stock";
   private final static  String PATH="path";
    private final static Integer EXPIRE=60;
	public ProductKey(String prefix, int expireSeconds) {
		super(PRODUCT+prefix, expireSeconds);
		
	}
	public ProductKey(String prefix) {
		super(PRODUCT+prefix, 0);
		
	}
	//存储数量
    public static KeyPrefix  getProductStockKey() {//userKey:id
    	return new ProductKey(STOCK,EXPIRE);
    }
    //存储秒杀商品对象
	public static KeyPrefix  getSecProductKey() {//userKey:id
		return new ProductKey(SECPRODUCT,EXPIRE);
	}
    // 秒杀路径
    public static KeyPrefix  getSecPathKey() {//userKey:id
    	return new ProductKey(PATH,EXPIRE);
    }
	public static KeyPrefix  getSecMutexKey() {//userKey:id
		return new ProductKey(MUTEX,EXPIRE);
	}
}
