package com.weixin4jdemo.controller;

import com.weixin4jdemo.entity.WxUsers;
import com.weixin4jdemo.mapper.WxUsersMapper;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.model.sns.SnsUser;
import org.weixin4j.spring.WeixinTemplate;
import org.weixin4j.util.SignUtil;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/12 16:15 $
*/
@Controller
public class WxController {
    private final WeixinTemplate weixinTemplate;
    private final WxUsersMapper wxUsersMapper;
    @Autowired
    public WxController(WeixinTemplate weixinTemplate,WxUsersMapper wxUsersMapper){
        this.weixinTemplate = weixinTemplate;
        this.wxUsersMapper = wxUsersMapper;
    }

    @GetMapping("/index1")
    public void index(Model model, HttpServletResponse response) throws IOException {
        System.out.println(weixinTemplate.getAppId());
        model.addAttribute("appId", weixinTemplate.getAppId());
        //snsapi_base和snsapi_userinfo
        String url =
            "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4762ede9bcb822a4&redirect_uri=https://d405-110-53-241-253.ngrok-free.app/auth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }


    @GetMapping("/auth")
    @ResponseBody
    public Map<String, Object> login(@RequestParam("code") String code) throws WeixinException {
        System.out.println(code);
        SnsUser snsUser = weixinTemplate.sns().getSnsUserByCode(code); //通过code获取access_token信息
        String subscribe = weixinTemplate.user().info(snsUser.getOpenid()).getSubscribe();
        Map<String, Object> map = new HashMap<>(); //返回值map
        String randomStr = UUID.randomUUID().toString().substring(0, 18);
        map.put("appId", weixinTemplate.getAppId()); //appid,前端若不需要可忽略
        Date date = new Date();
        //String sign = SignUtil.getSignature(weixinTemplate.getJsApiTicket().getTicket(), randomStr, new Long(date.getTime() / 1000).toString(), (String) param.get("url"));
        //-----以下代码根据具体业务处理-----
        map.put("subscribe",Integer.parseInt(subscribe)); //用户是否关注公众号 0-否，1-是
        //拼装前端需要的返回结果
        Map<String, Object> signature_dict = new HashMap<>();
        signature_dict.put("nonceStr", randomStr);
        //signature_dict.put("signature", sign);
        signature_dict.put("timestamp", new Long(date.getTime() / 1000));
        map.put("signature_dict", signature_dict);
        //根据openid查询用户id,以此决定更新还是新增用户信息
        WxUsers wxUsers = wxUsersMapper.selectIdByOpenid(snsUser.getOpenid());
        if(wxUsers == null){
            wxUsers = new WxUsers();
        }
        wxUsers.setNickName(snsUser.getNickname());
        wxUsers.setSex(snsUser.getSex());
        wxUsers.setImg(snsUser.getHeadimgurl());
        wxUsers.setOpenid(snsUser.getOpenid());
        map.put("nickName",wxUsers.getNickName());
        map.put("img",wxUsers.getImg());
        if (wxUsers.getWxUsersId() == null) {
            wxUsersMapper.insert(wxUsers);
            map.put("wxUsersId",wxUsers.getWxUsersId());
        } else {
            map.put("wxUsersId",wxUsers.getWxUsersId());
            wxUsersMapper.updateById(wxUsers);
        }
        return map;
    }
}
