package com.weixin4jdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weixin4jdemo.entity.WxUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/12 16:23 $
*/
@Mapper
public interface WxUsersMapper extends BaseMapper<WxUsers> {

    @Select("select * from d_wxusers where openid = #{openid}")
    WxUsers selectIdByOpenid(@Param("openid") String openid);
}
