package com.drayy.controller;

import com.drayy.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    @RequestMapping(value = "backUserHtml")
    public String backUserHtml(HttpServletRequest request, HttpServletResponse response, Model model){
        UserEntity userEntity = new UserEntity("lisi", "3333", "man");
        model.addAttribute("userEntity",userEntity);
        return userEntity.toString();
    }

    @RequestMapping(value = "getAll")
    public List<UserEntity> getUser() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity("zhangsan","123456","man"));
        list.add(new UserEntity("lisi","321654","woman"));
        list.add(new UserEntity("wangfer","741258","man"));
        return list;
    }

    @RequestMapping("get/{id}")
    public UserEntity getById(@PathVariable(name = "id") String id) {
        UserEntity userEntity = new UserEntity("zhangsan", "34524", "man");
        return userEntity;
    }

    @RequestMapping(value = "save")
    public String save(UserEntity userEntity) {
        return userEntity.toString()+"保存成功";
    }

    @RequestMapping(value = "saveByType/{type}")
    public String saveByType(UserEntity userEntity,@PathVariable("type")String type) {
        return "保存成功,type="+type;
    }
}
