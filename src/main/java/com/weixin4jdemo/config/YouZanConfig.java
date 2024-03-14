package com.weixin4jdemo.config;

import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/14 14:40 $
*/
@Configuration
public class YouZanConfig {

    @Bean
    @Scope("singleton")
    public YouZanClient youZanClient(){
        return new DefaultYZClient();
    }
}
