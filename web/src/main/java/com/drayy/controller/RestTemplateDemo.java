package com.drayy.controller;

import com.drayy.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RestTemplateDemo {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "getbackhtml")
    public UserEntity getBackHtml(){
        String url = "http://localhost:8080/backUserHtml";
        ResponseEntity<UserEntity> responseEntity = restTemplate.getForEntity(url, UserEntity.class );
        UserEntity userEntity = responseEntity.getBody();
        return userEntity;
    }

    //无参数的getForEntity
    @RequestMapping("getForEntity")
    public List<UserEntity> getAll2() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/getAll", List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();
        List<UserEntity> list = responseEntity.getBody();
        System.out.println(list.toString());
        return list;

    }

    //有参数的 getForEntity 请求,参数列表 如下才是参数的正确使用方式
    @RequestMapping("getForEntity/{id}")
    public UserEntity getById2(@PathVariable(name = "id") String id) {
        String url = "http://localhost:8080/get/{id}?username={username}&password={password}&sex={sex}";
        Map<String,String> map = new HashMap<>();
        map.put("id","11");
        map.put("username","lisi");
        map.put("password","12345");
        map.put("sex","man");
        ResponseEntity<UserEntity> responseEntity = restTemplate.getForEntity(url, UserEntity.class, map );
        UserEntity userEntity = responseEntity.getBody();
        return userEntity;
    }

    //但是,通常情况下我们并不想要Http请求的全部信息,只需要相应体即可.对于这种情况,RestTemplate提供了 getForObject() 方法用来只获取 响应体信息.
    //getForObject 和 getForEntity 用法几乎相同,指示返回值返回的是 响应体,省去了我们 再去 getBody() .


    //无参数的 getForObject 请求
    @RequestMapping("getAll2")
    public List<UserEntity> getAll() {
        List<UserEntity> list = restTemplate.getForObject("http://localhost:8080/getAll", List.class);
        System.out.println(list.toString());
        return list;
    }

    //有参数的 get 请求,使用map封装请求参数
    @RequestMapping("get3/{id}")
    public UserEntity getById3(@PathVariable(name = "id") String id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        UserEntity userEntity = restTemplate.getForObject("http://localhost:8080/get/{id}", UserEntity.class, map);
        return userEntity;
    }

    //post 请求,提交 UserEntity 对像
    @RequestMapping("saveUser")
    public String save(UserEntity userEntity) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/save", userEntity, String.class);
        String body = responseEntity.getBody();
        return body;
    }

    // 有参数的 postForEntity 请求,使用map封装
    @RequestMapping("saveUserByType2/{type}")
    public String save3(UserEntity userEntity,@PathVariable("type")String type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/saveByType/{type}", userEntity, String.class,map);
        String body = responseEntity.getBody();
        return body;
    }
}
