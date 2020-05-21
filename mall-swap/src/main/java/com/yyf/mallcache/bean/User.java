package com.yyf.mallcache.bean;

import java.io.Serializable;

public class User implements Serializable {

	private Integer userId;

	private String loginId;

	private String loginPassword;

	private String userName;

	private String userPhone;

	private String userEmail;

	private Image headImage;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId == null ? null : loginId.trim();
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword == null ? null : loginPassword.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail == null ? null : userEmail.trim();
	}

	public Image getHeadImage() {
		return headImage;
	}

	public void setHeadImage(Image headImage) {
		this.headImage = headImage;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, String loginId, String loginPassword, String userName, String userPhone,
			String userEmail, Image headImage) {
		super();
		this.userId = userId;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.headImage = headImage;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginId=" + loginId + ", loginPassword=" + loginPassword + ", userName="
				+ userName + ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", headImage=" + headImage + "]";
	}
	
}