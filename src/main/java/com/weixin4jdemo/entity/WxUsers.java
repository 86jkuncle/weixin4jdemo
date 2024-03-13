package com.weixin4jdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/12 16:20 $
*/
@TableName("d_wxusers")
public class WxUsers {
    @TableId(type = IdType.AUTO)
    private Integer wxUsersId;
    private String nickName;
    private Integer sex;
    private String img;
    private String openid;


    public Integer getWxUsersId() {
        return wxUsersId;
    }

    public void setWxUsersId(Integer wxUsersId) {
        this.wxUsersId = wxUsersId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
