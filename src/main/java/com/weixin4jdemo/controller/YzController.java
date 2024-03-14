package com.weixin4jdemo.controller;

import com.weixin4jdemo.config.YouZanProperties;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/13 16:49 $
*/
@RestController
public class YzController {

    private final RedisTemplate redisTemplate;
    private final YouZanClient youZanClient;
    private final YouZanProperties youZanProperties;

    private static final String YZTOKEN = "YZTOKEN";

    @Autowired
    public YzController(RedisTemplate redisTemplate,YouZanClient youZanClient,YouZanProperties youZanProperties){
        this.redisTemplate = redisTemplate;
        this.youZanClient = youZanClient;
        this.youZanProperties = youZanProperties;
    }

    @GetMapping("/yz")
    @ResponseBody
    public YouZanProperties index() throws SDKException {
        Object yzToken = redisTemplate.opsForValue().get(YZTOKEN);
        if(yzToken == null){
            DefaultYZClient yzClient = new DefaultYZClient();
            TokenParameter tokenParameter = TokenParameter.self()
                .clientId(youZanProperties.getClientId())
                .clientSecret(youZanProperties.getClientSecret())
                .grantId(youZanProperties.getGrantId())
                .refresh(false)
                .build();
            OAuthToken oAuthToken = yzClient.getOAuthToken(tokenParameter);
            yzToken = oAuthToken.getAccessToken();
            redisTemplate.opsForValue().set(YZTOKEN, yzToken);

        }
        //return youZanClient.getVersion();
//        return yzToken.toString();
        return youZanProperties;

    }
}
