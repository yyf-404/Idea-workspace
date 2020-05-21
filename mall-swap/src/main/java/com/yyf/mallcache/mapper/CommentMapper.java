package com.yyf.mallcache.mapper;

import com.yyf.mallcache.bean.Comment;


import java.util.List;

public interface CommentMapper {
    List<Comment> selectByProductId(Integer productId);
    Integer insert(Comment comment);
}
