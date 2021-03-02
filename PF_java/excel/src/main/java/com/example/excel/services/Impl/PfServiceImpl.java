package com.example.excel.services.Impl;

import com.example.excel.mapper.PfMapper;
import com.example.excel.model.PF;
import com.example.excel.services.PfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service    //必须声明的，不然系统不知道此为逻辑层
public class PfServiceImpl implements PfService {
    @Autowired(required = false)
    private PfMapper pfMapper;

    public int insertPf(PF pf) {
        return pfMapper.insertPf(pf.getDeptCode(), pf.getDeptName());
    }

    @Override
    public int insertExcelPf(PF pf) {
        return pfMapper.insertExcelPf(pf);
    }

    @Override
    public List getExcelPf(Map map) {
        return pfMapper.getExcelPf(map);
    }

    @Override
    public int deleteAllExcelPf() {
        return pfMapper.deleteAllExcelPf();
    }

    @Override
    public int getExcelPfNumber(Map map) {
        return pfMapper.getExcelPfNumber(map);
    }
}
