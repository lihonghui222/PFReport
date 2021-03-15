package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.model.PF;
import com.example.excel.services.PfService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/excel")
public class DownloadController {
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
        byte[] b = null;
        try{
            HSSFWorkbook wb = new HSSFWorkbook(new ClassPathResource("model/pf.xls").getInputStream());
            // 根据页面index
            HSSFSheet sheet = wb.getSheet("Sheet1");
            //设置单元格样式1
            HSSFCellStyle style1 = wb.createCellStyle();
            style1.setWrapText(true);//设置自动换行;
            style1.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
            //设置单元格样式2
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());//设置背景颜色;
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);//solid 填充  foreground  前景色
            style2.setWrapText(true);//设置自动换行;
            style2.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
            for (int i = 0; i < list.size(); i++) {
                PF pf = (PF) list.get(i);
                // 创建HSSFRow对象
                HSSFRow row = sheet.createRow(i + 2);
                row.setHeight((short)(13.8*2*20));//设置行高为两个普通行高

                Map<Integer,HSSFCell> map = new HashMap<Integer,HSSFCell>();
                for (int j=0;j<=26;j++){
                    HSSFCell cell =row.createCell(j);
                    map.put(j,cell);
                    if(i%2 == 1){
                        cell.setCellStyle(style2);
                    }else{
                        cell.setCellStyle(style1);
                    }
                }
                // 创建HSSFCell对象 设置单元格的值
                map.get(0).setCellValue(pf.getDeptCode());//部门代码
                map.get(1).setCellValue(pf.getDeptName());//部门名称
                map.get(2).setCellValue(pf.getEmployer());//用人司局
                map.get(3).setCellValue(pf.getOrganNature());//机构性质
                map.get(4).setCellValue(pf.getPositionRecruitment());//招考职位
                map.get(5).setCellValue(pf.getPositionAttribute());//职位属性
                map.get(6).setCellValue(pf.getPositionDistribution());//职位分布
                map.get(7).setCellValue(pf.getPositionSynopsis());//职位简介
                map.get(8).setCellValue(pf.getPositionCode());//职位代码
                map.get(9).setCellValue(pf.getOrganLevel());//机构层级
                map.get(10).setCellValue(pf.getExamType());//考试类别
                map.get(11).setCellValue(pf.getRecruitmentNumber());//招考人数
                map.get(12).setCellValue(pf.getMajor());//专业
                map.get(13).setCellValue(pf.getEducation());//学历
                map.get(14).setCellValue(pf.getDegree());//学位
                map.get(15).setCellValue(pf.getPoliticalOutlook());//政治面貌
                map.get(16).setCellValue(pf.getGrassrootsWorkYears());//基层工作最低年限
                map.get(17).setCellValue(pf.getGrassrootsWorkUndergo());//服务基层项目工作经历
                map.get(18).setCellValue(pf.getIfInterviewStage());//是否在面试阶段组织专业能力测试
                map.get(19).setCellValue(pf.getInterviewPersonnelRatio());//面试人员比例
                map.get(20).setCellValue(pf.getWorkplace());//工作地点
                map.get(21).setCellValue(pf.getLocation());//落户地点
                map.get(22).setCellValue(pf.getRemarks());//备注
                map.get(23).setCellValue(pf.getDeptWebsite());//部门网站
                map.get(24).setCellValue(pf.getConsultTel1());//咨询电话1
                map.get(25).setCellValue(pf.getConsultTel2());//咨询电话2
                map.get(26).setCellValue(pf.getConsultTel3());//咨询电话3
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
