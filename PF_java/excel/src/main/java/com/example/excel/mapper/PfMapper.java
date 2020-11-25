package com.example.excel.mapper;

import com.example.excel.model.PF;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper    //必须要声明的，不然系统不知道这个类是作为底层交互的
public interface PfMapper {
    //@Select("insert into `position`(deptCode,deptName) values (#{deptCode},#{deptName})")
    int insertPf(String deptCode, String deptName);

    int insertExcelPf(PF pf);

    List getExcelPf(Map map);

    int deleteAllExcelPf();
}

