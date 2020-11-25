package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.services.PfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/excel")
public class DownloadController {

    @Autowired
    private PfService pfService;

    @RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject showUser(@RequestBody JSONObject jsonParam){
        Map map = (Map)jsonParam.get("pf");
        List list= pfService.getExcelPf(map);
        JSONObject result = new JSONObject();
        result.put("list", list);
        return result;
    }
}
