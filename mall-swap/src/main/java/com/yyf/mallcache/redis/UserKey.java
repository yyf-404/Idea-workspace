package com.yyf.mallcache.redis;

public class UserKey extends BasePrefix {
	private final static  String USER="userKey:";
   private final static  String ID="id";
   private final static  String NAME="name";
   private final static  String TOKEN="token";
   private final static  String ID_PRODUCTID="id-productId";
   private final static  int TOKEN_EXPIRE=3600;
	public UserKey(String prefix, int expireSeconds) {
		super(USER+prefix, expireSeconds);
		
	}
	public UserKey(String prefix) {
		super(USER+prefix, 0);
		
	}
    public static KeyPrefix  getUserIdKey() {//userKey:id
    	return new UserKey(ID);
    }
    public static KeyPrefix  getUserNameKey() {
    	return new UserKey(NAME);
    }
    public static KeyPrefix  getUserTokenKey() {
    	return new UserKey(TOKEN,TOKEN_EXPIRE);
    }
    public static KeyPrefix  getUserSecKey() {
    	return new UserKey(ID_PRODUCTID,0);//Ҫע�����  
    }
}
