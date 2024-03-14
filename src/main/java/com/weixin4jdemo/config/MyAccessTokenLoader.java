package com.weixin4jdemo.config;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.WeixinException;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;
import org.weixin4j.spring.WeixinTemplate;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/14 15:09 $
*/
@Component
public class MyAccessTokenLoader implements ITokenLoader {

    private final RedisTemplate redisTemplate;


    private static final String WX_GLOBAL_ACCESS_TOKEN = "WX_GLOBAL_ACCESS_TOKEN";

    @Autowired
    public MyAccessTokenLoader(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Token get() {
        return (Token) redisTemplate.opsForValue().get(WX_GLOBAL_ACCESS_TOKEN);

    }

    @Override
    public void refresh(Token accessToken) {
        redisTemplate.opsForValue().set(WX_GLOBAL_ACCESS_TOKEN, accessToken, accessToken.getExpires_in()-600L,TimeUnit.SECONDS);
    }
}
