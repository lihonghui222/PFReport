package com.example.excel.services;

import com.example.excel.model.PF;

import java.util.List;
import java.util.Map;

public interface PfService {
    int insertPf(PF pf);

    int insertExcelPf(PF pf);

    List getExcelPf(Map map);

    int deleteAllExcelPf();

    int getExcelPfNumber(Map map);
}
