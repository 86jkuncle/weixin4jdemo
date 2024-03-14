package com.weixin4jdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/14 14:51 $
*/
@ConfigurationProperties(prefix = "youzan.config")
public class YouZanProperties {
    private String clientId;
    private String clientSecret;
    private String grantId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantId() {
        return grantId;
    }

    public void setGrantId(String grantId) {
        this.grantId = grantId;
    }
}
