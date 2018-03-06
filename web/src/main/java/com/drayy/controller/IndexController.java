package com.drayy.controller;

import com.alibaba.fastjson.JSONObject;
import com.drayy.demo.Cold;
import com.drayy.demo.CompactDisc;
import com.drayy.demo.Creamy;
import com.drayy.util.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    @Creamy
    @Cold
    public CompactDisc compactDisc;

    @RequestMapping("/index")
    public String index(){
        System.out.println("登录 ");
        compactDisc.play();

        return "index";
    }

    @RequestMapping("/auth/qq/callback")
    public String qqCallback(HttpServletRequest request, HttpServletResponse response,Model model) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        request.setAttribute("code",code);
        request.setAttribute("state",state);
        System.out.println("回调成功");
        System.out.println(code);
        System.out.println(state);

        RestTemplate restTemplate = new RestTemplate();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();

        List messageConverters=new ArrayList();
        messageConverters.add(new SourceHttpMessageConverter());
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        Map<String, String> map = new HashMap<>();
        map.put("grant_type","authorization_code");
        map.put("client_id","101459253");
        map.put("client_secret","eb59f792f7c33a3fa4366cdbd3fb8ceb");
        map.put("code",code);
        map.put("redirect_uri","http://passport.seei.tv:8080/auth/qq/callback");

//        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://graph.qq.com/oauth2.0/token?grant_type={grant_type}&" +
//                "client_id={client_id}&client_secret={client_secret}&code={code}&redirect_uri={redirect_uri}", String.class, map);
//        //forObject = access_token=ED414A77C09F03EF82CED4AB70A91A83&expires_in=7776000&refresh_token=BDBB2042F46BA3862C159AFAFF4C2B63

        String forObject = restTemplate.getForObject("https://graph.qq.com/oauth2.0/token?grant_type={grant_type}&" +
                "client_id={client_id}&client_secret={client_secret}&code={code}&redirect_uri={redirect_uri}", String.class, map);


        System.out.println();

        /**
        String forObject = restTemplate.getForObject("https://graph.qq.com/oauth2.0/token?grant_type={grant_type}&" +
                "client_id={client_id}&client_secret={client_secret}&code={code}&redirect_uri={redirect_uri}", String.class, map);
        //forObject = access_token=ED414A77C09F03EF82CED4AB70A91A83&expires_in=7776000&refresh_token=BDBB2042F46BA3862C159AFAFF4C2B63

        Map<String,String> token = new HashMap();
        String[] split = forObject.split("&");
        for(String s : split){
            String[] split1 = s.split("=");
            token.put(split1[0],split1[1]);
        }
        System.out.println("");

        //获取openid
        String open_id = restTemplate.getForObject("https://graph.qq.com/oauth2.0/me?access_token={access_token}", String.class, token.get("access_token"));
        System.out.println("openid = "+open_id+" token = "+token.get("access_token"));

        int start = open_id.indexOf("{");
        int end = open_id.lastIndexOf("}");
        System.out.println(start);
        System.out.println(end);
        System.out.println(open_id.substring(start,end+1));

        JSONObject jsonObject1 = JSONObject.parseObject(open_id.substring(start,end+1));
        String openid = (String) jsonObject1.get("openid");

        //获取用户信息
        map.put("access_token",token.get("access_token"));
        map.put("openid",openid);
        String info = restTemplate.getForObject("https://graph.qq.com/user/get_user_info?access_token={access_token}&oauth_consumer_key={client_id}&openid={openid}", String.class, map);
        System.out.println();
         */
        return "back";
    }

}
