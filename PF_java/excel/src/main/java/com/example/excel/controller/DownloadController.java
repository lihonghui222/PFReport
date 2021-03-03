package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.model.PF;
import com.example.excel.services.PfService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/excel")
public class DownloadController {
    //private final static String excelmodel = "D:/wenjian/pf1.xls";

    @Autowired
    private PfService pfService;

    @RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public byte[] showUser(@RequestBody JSONObject jsonParam, HttpServletResponse response){
        Map map = (Map)jsonParam.get("pf");
        List list = pfService.getExcelPfAll(map);
        return excelExport(list);
    }

    /**
     * 模板文件生成数据
     * @param list
     * @return
     */
    public static byte[] excelExport(List list) {
        //String excelmodel = new ClassPathResource("model/pf.xls").getInputStream();
        byte[] b = null;
        try{
            HSSFWorkbook wb = new HSSFWorkbook(new ClassPathResource("model/pf.xls").getInputStream());
            // 根据页面index
            HSSFSheet sheet = wb.getSheet("Sheet1");
            for (int i = 0; i < list.size(); i++) {
                PF pf = (PF) list.get(i);
                // 创建HSSFRow对象
                HSSFRow row = sheet.createRow(i + 2);
                // 创建HSSFCell对象 设置单元格的值
                row.createCell(0).setCellValue(pf.getDeptCode());//部门代码
                row.createCell(1).setCellValue(pf.getDeptName());//部门名称
                row.createCell(2).setCellValue(pf.getEmployer());//用人司局
                row.createCell(3).setCellValue(pf.getOrganNature());//机构性质
                row.createCell(4).setCellValue(pf.getPositionRecruitment());//招考职位
                row.createCell(5).setCellValue(pf.getPositionAttribute());//职位属性
                row.createCell(6).setCellValue(pf.getPositionDistribution());//职位分布
                row.createCell(7).setCellValue(pf.getPositionSynopsis());//职位简介
                row.createCell(8).setCellValue(pf.getPositionCode());//职位代码
                row.createCell(9).setCellValue(pf.getOrganLevel());//机构层级
                row.createCell(10).setCellValue(pf.getExamType());//考试类别
                row.createCell(11).setCellValue(pf.getRecruitmentNumber());//招考人数
                row.createCell(12).setCellValue(pf.getMajor());//专业
                row.createCell(13).setCellValue(pf.getEducation());//学历
                row.createCell(14).setCellValue(pf.getDegree());//学位
                row.createCell(15).setCellValue(pf.getPoliticalOutlook());//政治面貌
                row.createCell(16).setCellValue(pf.getGrassrootsWorkYears());//基层工作最低年限
                row.createCell(17).setCellValue(pf.getGrassrootsWorkUndergo());//服务基层项目工作经历
                row.createCell(18).setCellValue(pf.getIfInterviewStage());//是否在面试阶段组织专业能力测试
                row.createCell(19).setCellValue(pf.getInterviewPersonnelRatio());//面试人员比例
                row.createCell(20).setCellValue(pf.getWorkplace());//工作地点
                row.createCell(21).setCellValue(pf.getLocation());//落户地点
                row.createCell(22).setCellValue(pf.getRemarks());//备注
                row.createCell(23).setCellValue(pf.getDeptWebsite());//部门网站
                row.createCell(24).setCellValue(pf.getConsultTel1());//咨询电话1
                row.createCell(25).setCellValue(pf.getConsultTel2());//咨询电话2
                row.createCell(26).setCellValue(pf.getConsultTel3());//咨询电话3
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            wb.write(out);
            wb.close();
            b = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
