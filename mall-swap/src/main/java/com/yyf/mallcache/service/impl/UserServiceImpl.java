package com.yyf.mallcache.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.yyf.mallcache.bean.Account;
import com.yyf.mallcache.bean.Address;
import com.yyf.mallcache.bean.Image;
import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.mapper.AccountMapper;
import com.yyf.mallcache.mapper.AddressMapper;
import com.yyf.mallcache.mapper.ImageMapper;
import com.yyf.mallcache.mapper.UserMapper;
import com.yyf.mallcache.service.UserService;
import com.yyf.mallcache.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	AddressMapper addressMapper;
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	ImageMapper imageMapper;
	@Override
	public User userLogin(String loginId, String loginPassword) {
		//1.��֤������ȷ��
		if(loginId==null||loginPassword==null) return null;
		if(loginId.trim().equals("")||loginPassword.trim().equals("")) return null;
		User user=userMapper.selectByLoginId(loginId);
		//2.��֤����
		if(user!=null&&loginPassword.equals(user.getLoginPassword())) {
			//3.�������
			user.setLoginPassword(null);
			return user;
		}
		return null;
	}

	@Override
	public User userRegister(String loginId, String loginPassword) {
		User user=new User();
		user.setLoginId(loginId);
		user.setLoginPassword(loginPassword);
		//Ĭ���û���Ϊ �û��ӵ�½�˺�
		user.setUserName("用户"+loginId);
		Image image=new Image();
		image.setImageId(ConfigUtil.DEFAULT_HEADIMAGE);
		user.setHeadImage(image);
		userMapper.insert(user);
		 Account account =new Account();
		 account.setAccountBalance(new BigDecimal(ConfigUtil.initAccountBalance));
		 account.setUserId(user.getUserId());
		 account.setAccountNumber(ConfigUtil.initAccountNumber);
		 accountMapper.insert(account);
		return user;
	}
/**
 * ȷ���û�ע�����ݵ���ȷ��
 */
@Override
public String confirmRegisterInput(String loginId, String loginPassword, String passwordRepeat) {
	          //1.��֤������ȷ��
			if(loginId==null||loginPassword==null) return ConfigUtil.REGISTER_LENGTHFAIL;
			if(loginId.trim().equals("")||loginPassword.trim().equals("")) 
				return ConfigUtil.REGISTER_LENGTHFAIL;
			if(loginId.length()<6||loginPassword.length()<6) 
				return ConfigUtil.REGISTER_LENGTHFAIL;
			if(userMapper.confirmLoginId(loginId)>0) return ConfigUtil.REGISTER_IDFALI;
	// �ж�loginPassword�Ƿ����passwordRepeat
	if ( passwordRepeat == null || !loginPassword.equals(passwordRepeat)) {
		return ConfigUtil.REGISTER_REPEATEDFAIL;
	}
	return ConfigUtil.REGISTER_SUCCESS;
}
/**
 * ͨ���û�id ��õ�ַ����
 */
	@Override
	public List<Address> getAddress(Integer userId) {
		return 	addressMapper.selectAByUserID(userId);
	}

@Override
public BigDecimal getBalance(Integer userId) {
	return accountMapper.selectAccountBalanceByUserId(userId);
}

@Override
public void savaAddress(Integer userId, String addressProvince, String addressCity, String addressDetail,
		String addressName, String addressPhone) {
	Address address=new Address(null, userId, addressProvince,  addressCity, addressDetail,
			 addressName,  addressPhone);
	addressMapper.insert(address);
}

@Override
public void deleteAddress(Integer addressId) {
	addressMapper.deleteByPrimaryKey(addressId);
}

@Override
public String changeLoginPassword(String oldPassword, String newLogiPassword, Integer userId) {
	User user =userMapper.selectByPrimaryKey(userId);
	//2.��֤����
			if(user==null||!oldPassword.equals(user.getLoginPassword())) {
			return ConfigUtil.CHANGE_OLDFILE;
			}
			userMapper.updateLoginPassword(userId, newLogiPassword);
	            return ConfigUtil.CHANGE_SUCCESS;
}
@Transactional
@Override
public User getUser(String loginId) {
	User user=userMapper.selectByLoginId(loginId);
	//2.��֤����
	if(user!=null) {
		//3.�������
		user.setLoginPassword(null);
		return user;
	}
	return null;
}

@Override
public String updateUserName(Integer userId,String userName) {
	if(userMapper.updateUserName(userId, userName)>0) {
		return ConfigUtil.UPDATE_SUCCESS;
	}
	return null;
}


}
