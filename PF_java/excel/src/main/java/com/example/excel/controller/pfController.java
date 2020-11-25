package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.model.PF;
import com.example.excel.services.PfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pf")
public class pfController {
    @Autowired
    private PfService pfService;

    @RequestMapping("/insertPf")
    //@RequestMapping(value = "/insertPf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject showUser(){
    //public JSONObject showUser(@RequestBody JSONObject jsonParam){
        List list = new ArrayList();
        PF pf=new PF();
        pf.setDeptCode("123");
        pf.setDeptName("hhh");
        //int a = pfService.insertPf(pf);
        list.add("成功！");

        JSONObject result = new JSONObject();
        //result.put("改变数据条数：", a);
        result.put("集合数据样例：", list);
        return result;
    }
}

