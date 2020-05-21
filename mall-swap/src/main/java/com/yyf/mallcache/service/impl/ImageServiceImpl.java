package com.yyf.mallcache.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.yyf.mallcache.bean.Image;
import com.yyf.mallcache.mapper.ImageMapper;
import com.yyf.mallcache.mapper.UserMapper;
import com.yyf.mallcache.service.ImageService;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageMapper imageMapper;
	@Autowired
	UserMapper userMapper;
	@Override
	public void uploadHeadImage(InputStream fi,String fileName,Integer userId,String path) {
       //����ͼƬ
		byte[] b=new byte[1024];
		int len=0;
		FileOutputStream fo = null;
		int random=(int) (Math.random()*10000);
		try {
			fo=new FileOutputStream(path+ ConfigUtil.HEADIMAGEPATH+random+fileName);
			while((len=fi.read(b))!=-1) {
				fo.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fo.close();
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		}
		Image image=new Image();
       image.setImageName(fileName);
       image.setImagePath(ConfigUtil.HEADIMAGEPATH+random+fileName);
       imageMapper.insert(image);
       userMapper.updateUserImage(userId, image.getImageId());
	}

	@Override
	public Integer imageUpload(InputStream fi, String imageKind,String imageName) {
		//����ͼƬ
		byte[] b = new byte[1024];
		int len = 0;
		FileOutputStream fo = null;
		//long time = new Date().getTime(); 为了项目演示不使用时间轴生成图片
		String fileStr = ConfigUtil.FILEUPLOAD + imageKind +
				"/" + imageName + ".jpg";
		LoggerUtil.getLogger(this.getClass()).info(fileStr);
		try {
			fo = new FileOutputStream(fileStr);
			while ((len = fi.read(b)) != -1) {
				fo.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fo.close();
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		Image image = new Image();
		image.setImageName("" + imageName);
		image.setImagePath("images/" + imageKind +
				"/" + imageName + ".jpg");
		imageMapper.insert(image);
		return image.getImageId();

	}

}
