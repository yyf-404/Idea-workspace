package com.yyf.mallcache.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yyf.mallcache.bean.User;
import com.yyf.mallcache.service.ImageService;
import com.yyf.mallcache.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ImageController {
	public static final String IMAGEUPLOAD="person/imageUpload";
	private static final long serialVersionUID = 1L;
	// 验证码存入到session中的名称
	public static final String CHECK_CODE_KEY = "CHECK_CODE_KEY";
	// 验证码字体的高度
	private int fontSize = 10;

	// 验证码中的字符串的y坐标. 即：验证码中的字符串于验证码图形左上角的 codeY 位置处
	private int codeY = 0;
	// 设置验证图片的宽度, 高度, 验证码的个数
	private int width = 80;
	private int height = 40;
	private int codeCount = 4;
	// 验证码由哪些字符组成
	char[] codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray();
	@Autowired
	ImageService imageService;
	//	@RequestMapping("/getUserHead")
//	public void getUserHead( HttpServletRequest request,HttpServletResponse response)
//			throws IOException {
//
//	}
	@RequestMapping("/userHeadUpload")
	public String userHeadUpload(@RequestParam("file") MultipartFile headImage, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ConfigUtil.USERSTR);
		if (user != null)
			imageService.uploadHeadImage(headImage.getInputStream(), headImage.getOriginalFilename()
					, user.getUserId(),request.getRealPath("/"));
		return ConfigUtil.PERSON_INDEX;
	}



	@RequestMapping("/validInput")
	public void validInput(HttpServletRequest request, HttpServletResponse response) throws IOException {
		fontSize = height - 10;
		codeY = height - 5;
		BufferedImage buffImg = getImage(request.getSession());
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 通知浏览器以图片的方式打开
		response.setHeader("Content-type", "image/jpeg");
		// 将图像输出到输出流中
		ServletOutputStream sos = null;
		sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}

	private BufferedImage getImage(HttpSession session) {
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 在 buffImg 中创建一个 Graphics2D 图像
		Graphics2D graphics = null;
		graphics = buffImg.createGraphics();
		// 设置一个颜色, 使 Graphics2D 对象的后续图形使用这个颜色
		graphics.setColor(Color.WHITE);

		// 填充一个指定的矩形: x - 要填充矩形的 x 坐标; y - 要填充矩形的 y 坐标; width - 要填充矩形的宽度; height -
		// 要填充矩形的高度
		graphics.fillRect(0, 0, width, height);

		// 创建一个 Font 对象: name - 字体名称; style - Font 的样式常量; size - Font 的点大小
		Font font = null;
		font = new Font("", Font.BOLD, fontSize);
		// 使 Graphics2D 对象的后续图形使用此字体
		graphics.setFont(font);

		graphics.setColor(Color.BLACK);

		// 绘制指定矩形的边框, 绘制出的矩形将比构件宽一个也高一个像素
		graphics.drawRect(0, 0, width - 1, height - 1);
		StringBuffer randomCode = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字
			String strRand = null;
			strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length - 1)]);

			// 把正在产生的随机字符放入到 StringBuffer 中
			randomCode.append(strRand);
			// 用随机产生的颜色将验证码绘制到图像中
			graphics.setColor(Color.BLUE);

		}
		graphics.drawString(randomCode.toString(), 0, fontSize);
		// 可以对randomCode.toString()进行加密 防止内容被识别
		session.setAttribute(CHECK_CODE_KEY, randomCode.toString());
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(20);
			int y1 = random.nextInt(20);
			graphics.drawLine(x, y, x + x1, y + y1);
		}

		return buffImg;
	}

}
