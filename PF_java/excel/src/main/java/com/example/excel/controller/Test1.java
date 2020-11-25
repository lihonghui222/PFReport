package com.example.excel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {
    public static void main(String[] args) {
        List<Map> list1 = new ArrayList();
        Map map1 = new HashMap();
        map1.put("customerNo","CNHASE502000755");
        map1.put("cifName","XI'AN COMPANY");
        map1.put("userName","CELIA CAI");
        map1.put("idType","I");
        map1.put("idNo","3453536356456");
        map1.put("homePhone","15129258909");
        map1.put("businessPhone","608675117409");
        map1.put("status","C");
        list1.add(0,map1);
        Map map2 = new HashMap();
        map2.put("customerNo","1");
        map2.put("cifName","2");
        map2.put("userName","sysadmin1");
        map2.put("idType","2");
        map2.put("idNo","1");
        map2.put("homePhone","2");
        map2.put("businessPhone","2");
        map2.put("status","1");
        list1.add(1,map2);

        List<Map> list2 = new ArrayList();
        Map map4 = new HashMap();
        map4.put("customerNo","1");
        map4.put("cifName","2");
        map4.put("userName","sysadmin1");
        map4.put("idType","2");
        map4.put("idNo","1");
        map4.put("phone","2");
        map4.put("status","1");
        //list2.add(map4);

        //存在list1但不存在list2的
        List<Map> xxx = list1.stream().filter(map -> find(map,1, list2)==1).collect(Collectors.toList());
        System.out.println(xxx);
        //存在list2但不存在list1的
        List<Map> xxxxxx= list2.stream().filter(map -> find(map,2, list1)==1).collect(Collectors.toList());
        System.out.println(xxxxxx);

    }


    public static int find(Map map,int type,List<Map> list) {
        int res = -1;
        boolean b = false;
        String status = null;
        Object phone = null;
        if(list.size() == 0){
            res = 1;
            return res;
        }
        for (int i = 0; i < list.size(); i++) {
            if(type == 1){//取银企通数据与Hub数据对比
                status = "hub有，银企通无";
                if(map.get("businessPhone") != null && map.get("businessPhone") != ""){//Hub数据中办公电话是否存在
                    //Hub数据中有办公电话，取办公电话对比
                    b = (list.get(i).get("phone") == map.get("businessPhone"));
                    phone = map.get("businessPhone");
                }else{
                    //Hub数据中无办公电话，取家庭电话对比
                    b = (list.get(i).get("phone") == map.get("homePhone"));
                    phone = map.get("businessPhone");
                }
            }else{//取Hub数据与银企通数据对比
                status = "hub无，银企通有";
                if(list.get(i).get("businessPhone") != null && list.get(i).get("businessPhone") != ""){
                    //Hub数据中有办公电话，取办公电话对比
                    b = (list.get(i).get("businessPhone") == map.get("phone"));
                }else{
                    //Hub数据中无办公电话，取家庭电话对比
                    b = (list.get(i).get("homePhone") == map.get("phone"));
                }
            }
            //对比需要数据，不存在的数据返回1，继续遍历。存在的数据返回-1，跳出此次循环。
            if (list.get(i).get("customerNo") == map.get("customerNo") && list.get(i).get("cifName") == map.get("cifName")
                    && list.get(i).get("userName") == map.get("userName") && list.get(i).get("idType") == map.get("idType") &&
                    list.get(i).get("idNo") == map.get("idNo") && b) {
                res = -1;
                break;
            }else{
                map.put("status", status);
                if(type == 1){
                    map.put("phone", phone);
                }
                res = 1;
            }
        }
        return res;
    }
}


