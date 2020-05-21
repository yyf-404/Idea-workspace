package com.yyf.mallcache.bean;

public class SecOrder {
private Integer secorderId;

private Integer userId;

private Integer secproductId;

public Integer getSecorderId() {
	return secorderId;
}

public void setSecorderId(Integer secorderId) {
	this.secorderId = secorderId;
}

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}

public Integer getSecproductId() {
	return secproductId;
}

public void setSecproductId(Integer secproductId) {
	this.secproductId = secproductId;
}

@Override
public String toString() {
	return "SecOrder [secorderId=" + secorderId + ", userId=" + userId + ", secproductId=" + secproductId + "]";
}


}
