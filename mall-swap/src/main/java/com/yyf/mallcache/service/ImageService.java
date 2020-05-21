package com.yyf.mallcache.service;

import java.io.InputStream;

public interface ImageService {
void uploadHeadImage(InputStream fi, String fileName, Integer userId, String path);
    Integer  imageUpload(InputStream fi ,String imageKind,String imageName);
}
