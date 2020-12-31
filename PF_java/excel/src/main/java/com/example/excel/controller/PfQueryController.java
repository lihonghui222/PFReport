package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.services.PfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class PfQueryController {
    @Autowired
    private PfService pfService;

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject showUser(@RequestBody JSONObject jsonParam){
        Map map = (Map)jsonParam.get("pf");
        List list= pfService.getExcelPf(map);
        JSONObject result = new JSONObject();
        result.put("list", list);
        return result;
    }
}
