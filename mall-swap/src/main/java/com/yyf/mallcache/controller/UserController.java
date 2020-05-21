package com.yyf.mallcache.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyf.mallcache.bean.Address;
import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.redis.UserKey;
import com.yyf.mallcache.service.RedisService;
import com.yyf.mallcache.service.UserService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import com.yyf.mallcache.util.UUIDUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	//验证码存入到session中的名称
	public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;


	//使用springsecurity后 被拦截器拦截 无法到达当前方法
	//	@ResponseBody
//	@RequestMapping("/userLogin")
//	public String userLogin(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword,
//							@Param("validCode") String validCode,HttpServletRequest request,HttpServletResponse response) {
//		LoggerUtil.getLogger(this.getClass()).info("loginId="+loginId+" loginPassword="+loginPassword);
//		String returnFlag = ConfigUtil.LOGIN_FAIL;
//		HttpSession session = request.getSession();
//		String checkCode =(String) session.getAttribute(CHECK_CODE_KEY);
//		if(checkCode==null||!checkCode.equalsIgnoreCase(validCode)) {
//			return ConfigUtil.LOGIN_IMAGEFAIL;
//		}
//		User user = userService.userLogin(loginId, loginPassword);
//		LoggerUtil.getLogger(this.getClass()).info("loginId="+loginId+" loginPassword="+loginPassword+"user"+user);
//		if (user != null && user.getUserId() > 0) {
//			returnFlag = ConfigUtil.LOGIN_SUCCESS;
//			//用户登陆后随机（uuid）生成一个tokenId作为键，用户对象作为value，存入redis。
//			String tokenId= UUIDUtil.uuid();
//			redisService.set(UserKey.getUserTokenKey(), tokenId, user);
//			//同时在cookie中以一个固定的键（比如toke字符串），value为tokenID
//			Cookie cookie=new Cookie(ConfigUtil.COOKIE_NAME_TOKEN,tokenId);
//			//有效时间
//			cookie.setMaxAge(UserKey.getUserTokenKey().getExpireSeconds());
//			//作用路径
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			session.setAttribute(ConfigUtil.USERSTR, user);
//		}
//
//		return returnFlag;
//	}
	@ResponseBody
	@RequestMapping("/userRegister")
	public String userRegister(@Param("loginId") String loginId, @Param("loginPassword") String loginPassword,
							   @Param("passwordRepeat") String passwordRepeat, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//验证输入数据是否合法
		String returnFlag = userService.confirmRegisterInput(loginId, loginPassword, passwordRepeat);
		if(!returnFlag.equals(ConfigUtil.REGISTER_SUCCESS))
			return returnFlag;
		User user = userService.userRegister(loginId, loginPassword);
		if (user != null && user.getUserId() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute(ConfigUtil.USERSTR, user);
			return ConfigUtil.REGISTER_SUCCESS;
		}
		return  ConfigUtil.REGISTER_ERROR;
	}

//	/** 使用springsecurity后 被拦截器拦截 无法到达当前方法
//	 * 注销用户
//	 *
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/userLogout")
//	public String userLogout(HttpServletRequest request,
//							 @CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) {
//		// 待实现 可以获得访问的地址再返回
//		System.out.println(cookieToken+"cookieToken");
//		HttpSession session = request.getSession();
//		session.invalidate();
//		boolean res=redisService.del(UserKey.getUserTokenKey(),cookieToken);
//		System.out.println(res+"res");
//		return ConfigUtil.HOME;
//	}

	@ResponseBody
	@RequestMapping("/updateUserName")
	public String updateUserName(HttpServletRequest request,@RequestParam("userName")String userName) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		System.out.println("updateUser");
		if(user!=null&&user.getLoginId()!=null) {
			return userService.updateUserName(user.getUserId(), userName);
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/getUserMessage")
	public User getUserMessage(HttpServletRequest request,
							   @CookieValue(value=ConfigUtil.COOKIE_NAME_TOKEN,required=false)String cookieToken) {
		User user = null;
		HttpSession session = request.getSession();
		//user = (User) session.getAttribute(ConfigUtil.USERSTR);
		user=redisService.get(UserKey.getUserTokenKey(), cookieToken, User.class);
		if(user!=null&&user.getLoginId()!=null) {
			user=userService.getUser(user.getLoginId());
			return user;
		}
		return null;
	}
	//改变账号密码
	@ResponseBody
	@RequestMapping("/changeLoginPassword")
	public String changeLoginPassword(@RequestParam("oldPassword")String oldPassword, @RequestParam("newLogiPassword")
			String newLogiPassword	, @RequestParam("confirmPassword")
											  String confirmPassword,HttpServletRequest request) {
		if(!confirmPassword.equals(newLogiPassword)) return ConfigUtil.CHANGE_CONFIRMFAIL;
		User user = null;
		HttpSession session = request.getSession();
		user = (User) session.getAttribute(ConfigUtil.USERSTR);
		if(user==null || user.getUserId()<0) {
			return ConfigUtil.CHANGE_ERROR;
		}

		return userService.changeLoginPassword(oldPassword, newLogiPassword, user.getUserId());
	}
	@ResponseBody
	@RequestMapping("/getAddress")
	public List<Address> getAddress(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		if (user != null)
			return userService.getAddress(user.getUserId());
		return null;
	}

	@ResponseBody
	@RequestMapping("/getAccountBalance")
	public BigDecimal getAccountBalance(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		if (user != null)
			return userService.getBalance(user.getUserId());
		return null;
	}

	@RequestMapping("/savaAddress")
	public String savaAddress(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		Integer userId = -1;
		if (user != null)
			userId = user.getUserId();
		if (userId < 0)
			return ConfigUtil.LOGIN;
		String addressName = request.getParameter("addressName");
		String addressPhone = request.getParameter("addressPhone");
		String addressProvince = request.getParameter("addressProvince");
		String addressCity = request.getParameter("addressCity");
		String addressDetail = request.getParameter("addressDetail");
		userService.savaAddress(userId, addressProvince, addressCity, addressDetail, addressName, addressPhone);

		return ConfigUtil.ADDRESS;
	}

	@RequestMapping("/deleteAddress")
	public void deleteAddress(HttpServletRequest request, @RequestParam("addressId") Integer addressId) {
		userService.deleteAddress(addressId);
	}
}
