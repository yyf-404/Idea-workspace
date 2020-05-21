package com.yyf.mallcache.redis;

public abstract class BasePrefix implements KeyPrefix {
  private String prefix;
  private int expireSeconds=0;//0 ��ʾ��������
  
	@Override
	public int getExpireSeconds() {
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	public BasePrefix(String prefix, int expireSeconds) {
		super();
		this.prefix = prefix;
		this.expireSeconds = expireSeconds;
	}

}
