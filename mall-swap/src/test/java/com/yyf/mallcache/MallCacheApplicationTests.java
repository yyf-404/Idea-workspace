package com.yyf.mallcache;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.yyf.mallcache.bean.*;
import com.yyf.mallcache.config.CacheConfig;
import com.yyf.mallcache.mapper.*;
import com.yyf.mallcache.service.ProductService;
import com.yyf.mallcache.service.impl.ProductServiceImpl;
import com.yyf.mallcache.util.BloomFilterUtil;
import com.yyf.mallcache.util.ConfigUtil;
import com.yyf.mallcache.util.FastJsonUtil;
import com.yyf.mallcache.util.LoggerUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@EnableCaching
@MapperScan(value ="com.yyf.mallcache.mapper")
@SpringBootTest
class MallCacheApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ImageMapper imageMapper;

     @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SecProductMapper secProductMapper;
    @Autowired
    AmqpTemplate amqpTemplate;
    @Autowired
    ProductMapper productMapper;

    @Test
    void mysql() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    void mapper() throws SQLException {
        // List list=userMapper.selectAll();
        //System.out.println(Arrays.asList(list));

        // List<Image> list= imageMapper.selectAll();
        //  System.out.println(Arrays.asList(list));
        List list = secProductMapper.selectAll();
        System.out.println(list);
    }

    @Test
    void redis() throws SQLException {
        //System.out.println(stringRedisTemplate);
       // System.out.println(redisTemplate.getDefaultSerializer());
        //redisTemplate.opsForValue().set("image11", imageMapper.selectByPrimaryKey(1));
      //  redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        Product product=productMapper.selectByPrimaryKey(7);
        redisTemplate.opsForValue().set("product7",product);
        System.out.println(redisTemplate.opsForValue().get("product7"));
        //stringRedisTemplate.opsForValue().set("key","123456");
        //System.out.println(stringRedisTemplate.opsForValue().get("key"));
    }
    @Test
    void redis2() throws SQLException {
         // redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        User user=userMapper.selectByPrimaryKey(1);
        redisTemplate.opsForValue().set("user123",user);
        System.out.println(redisTemplate.opsForValue().get("user123"));

    }
    @Test
    void mq() throws SQLException {
        System.out.println(amqpTemplate);
        ArrayList list = new ArrayList();

    }

    @Autowired
    ProductService productService;

    @Test
    void cache() throws SQLException, UnsupportedEncodingException, InterruptedException {

        Product product = productMapper.selectByPrimaryKey(17);
        System.out.println(product);
        product.setProductPrice(new BigDecimal(7));
        productService.updateProduct(product, null);
        product = productMapper.selectByPrimaryKey(17);
        productMapper.selectPageByCondition(1, 8, null, 0, "");
        // System.out.println(product);

        Thread.sleep(10000);

    }

    @Autowired
    ProductInfoMapper productInfoMapper;

    //研究association的查询是否会走缓存
    //<association property="product" select="com.yyf.mallcache.mapper.ProductMapper.selectByPrimaryKey"
    @Test
    void cache2() throws SQLException, UnsupportedEncodingException, InterruptedException {

        ProductInfo productInfo = productInfoMapper.getProductInfoById(1);

        // System.out.println(product);

        Thread.sleep(10000);

    }

    @Test
    void log() throws SQLException {
        LoggerUtil.getLogger(getClass()).info("yyf");

    }

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100000, 0.01);

    @Test
    void boom() throws SQLException {
        for (int i = 0; i < 10; i += 2)
            bloomFilter.put(i);
        for (int i = 0; i < 10; i++) {
            if (bloomFilter.mightContain(i)) {
                System.out.println(i);
            }
        }
    }

    @Test
    void boom2() throws SQLException {
        for (int i = 0; i < 10; i++) {
            if (BloomFilterUtil.bloomFilter.mightContain(i)) {
                System.out.println(i);
            }
        }
    }

    @Test
    void cache3() throws SQLException {
        // productService.getSecProductInfo(6);
        int id = 6;
        SecProductInfo productInfo = productInfoMapper.getSecProductInfoById(id);
        SecProduct product = secProductMapper.selectByProductId(id);
        String str= FastJsonUtil.beanToString(product);
        System.out.println(str);
        SecProduct secProduct=FastJsonUtil.stringToBean(str,SecProduct.class);
        System.out.println();
    }
    @Test
    void file() throws SQLException {
        File file=new File("./");
        System.out.println(file.getAbsolutePath());
        File file2=new File("src");
        System.out.println(file2.getAbsolutePath());
        String path = System.getProperty("user.dir");
        System.out.println(path);
    }
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    UserProductMapper userProductMapper;
    @Test
    void productImage() throws SQLException {
       // ProductImage productImage=new ProductImage(18,110,ConfigUtil.SEARCHIMAGE);
       // productImageMapper.insert(productImage);
        //Product product=new Product(null,"衣服","jac",new BigDecimal(2),2,0);
       // Integer id=productMapper.insert(product);
       // System.out.println(product.getProductId());
    }
    @Test
    void userProduct() throws SQLException {

      //  UserProduct userProduct=new UserProduct(1,1);
       // userProductMapper.insert(userProduct);
        List<Product> list=productMapper.selectProductByUserId(1019);
        System.out.println(Arrays.asList(list));
    }

    @Test
    void userOperation() throws SQLException {
        User record = userMapper.selectByPrimaryKey(2);
        System.out.println(record);
        LoggerUtil.getLogger(this.getClass()).info(record.toString());
        userMapper.updateUserName(record.getUserId(),"李五");
        record = userMapper.selectByPrimaryKey(2);
        LoggerUtil.getLogger(this.getClass()).info(record.toString());
    }
    @Autowired
    CommentMapper commentMapper;
    @Test
    void comment() throws SQLException {
        User user=new User();
        user.setUserId(2);
        Comment comment=new Comment(user,1,"语言",new Date());
        commentMapper.insert(comment);
     List<Comment> list=commentMapper.selectByProductId(1);
        System.out.println(Arrays.asList(list));
    }
    @Test
    void time() throws SQLException {
        System.out.println(new Date());
    }
}