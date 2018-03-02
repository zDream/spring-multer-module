package com.drayy.controller;

import com.drayy.demo.Cold;
import com.drayy.demo.CompactDisc;
import com.drayy.demo.Creamy;
import com.drayy.util.SpittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
//        String url="https://graph.qq.com/oauth2.0/token";
//        HttpHeaders headers = new HttpHeaders();
//        LinkedMultiValueMap body=new LinkedMultiValueMap();
//        body.add("grant_type","authorization_code");
//        body.add("client_id","101459253");
//        body.add("client_secret","eb59f792f7c33a3fa4366cdbd3fb8ceb");
//        body.add("code",code);
//        body.add("redirect_uri","http://passport.seei.tv:8080/auth/qq/callback");
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity entity = new HttpEntity(body, headers);
//        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        String body1 = exchange.getBody();

        Map<String, String> map = new LinkedHashMap<>();
        map.put("grant_type","authorization_code");
        map.put("client_id","101459253");
        map.put("client_secret","eb59f792f7c33a3fa4366cdbd3fb8ceb");
        map.put("code",code);
        map.put("redirect_uri","http://passport.seei.tv:8080/auth/qq/callback");

        String forObject = restTemplate.getForObject("https://graph.qq.com/oauth2.0/token", String.class, map);

//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://graph.qq.com/oauth2.0/token", String.class, map);
//        String userEntity = responseEntity.getBody();
        System.out.println("");
        return "back";
    }

}
