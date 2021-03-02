package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.services.PfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected static final Logger logger = LoggerFactory.getLogger(PfQueryController.class);
    @Autowired
    private PfService pfService;

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject showUser(@RequestBody JSONObject jsonParam){
        Map map = (Map)jsonParam.get("pf");
        //分页数据
        List list= pfService.getExcelPf(map);
        //总数据条数
        int listNumber = pfService.getExcelPfNumber(map);
        logger.info("查询出数据条数："+listNumber);
        JSONObject result = new JSONObject();
        result.put("list", list);
        result.put("listNumber", listNumber);
        return result;
    }
}
