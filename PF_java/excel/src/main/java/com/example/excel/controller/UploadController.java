package com.example.excel.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.excel.model.PF;
import com.example.excel.services.PfService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/excel")
public class UploadController {
    private PF pf;
    private int insertNum = 0;//插入数据条数
    @Autowired
    private PfService pfService;

    //@CrossOrigin(origins = "*",maxAge = 3600)//本行代码解决跨域问题
    @PostMapping("/upload")
    public JSONObject fileUploads(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        //插入数据前先删除数据
        int deleteNum = pfService.deleteAllExcelPf();
        //文件处理存储入库
        readExcel(file.getInputStream());
        //String fileName = file.getOriginalFilename();// 获取上传的文件名称

        JSONObject result = new JSONObject();
        result.put("returnMessage", "上传成功！");
        return result;
    }

    /**
     * 读取.xlsx格式excel文件内容
     * @param inputs
     */
    public void readExcel(InputStream inputs){
        try {
            // 1. 打开文件（流读取excel文件）
            //FileInputStream inputs = new FileInputStream(file_path);
            // 2. 创建excel对象workbook（工作薄）  多态Workbook(Excel对象)，即可接收2003版本，也可2007版本
            Workbook wb = WorkbookFactory.create(inputs);
            // 3. 获取sheet（表单）
            int sheetNumber = wb.getNumberOfSheets();//页签数量
            for (int i=0 ; i<sheetNumber ; i++){
                Sheet sh = wb.getSheetAt(i); // 通过索引获取表单
                // 4. 获取最大行号: 索引(0开始)
                int lastRowNum = sh.getLastRowNum();
                //System.out.println("最大行数："+lastRowNum);
                // 5. 循环所有的行（除去前两行标题）
                for(int j=2;j<=lastRowNum;j++) {
                    // 5.1 获取当前行
                    Row row = sh.getRow(j);
                    // 5.2  获取当前行最大列号: 长度
                    int lastCellNum = row.getLastCellNum();
                    //System.out.println("最大列号："+lastCellNum);
                    // 6. 遍历每行所有的单元格
                    for(int z=0;z<lastCellNum;z++) {
                        // 6.1 获取cell(单元格)
                        Cell cell = row.getCell(z, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // 6.2 设置单元格值类型
                        cell.setCellType(CellType.STRING);
                        // 6.3 获取单元格的内容
                        String cellValue = cell.getStringCellValue();
                        //System.out.println("第"+(i+1)+"页"+"第"+(j+1)+"行"+"第"+(z+1)+"列的单元格内容是："+cellValue);
                        insertExcelPf(z,cellValue.replaceAll(" ",""),(i==sheetNumber-1 && j==lastRowNum && z==lastCellNum-1));
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("成功插入数据条数为："+insertNum);
                // 7. 关闭文件流
                inputs.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取数据插入数据库
     * @param cellNum 所属列
     * @param cellValue 单元格参数
     * @param lastData 是否已执行到最后
     * @return
     */
    public void insertExcelPf(int cellNum,String cellValue,boolean lastData){
        switch (cellNum) {
            case 0 ://部门代码
                if(!StringUtils.isEmpty(pf)){
                    //此处执行插入sql
                    int a = pfService.insertExcelPf(pf);
                    insertNum = insertNum + a;
                }
                pf = new PF();
                pf.setDeptCode(cellValue);
                break;
            case 1 ://部门名称
                pf.setDeptName(cellValue);
                break;
            case 2 ://用人司局
                pf.setEmployer(cellValue);
                break;
            case 3 ://机构性质
                pf.setOrganNature(cellValue);
                break;
            case 4 ://招考职位
                pf.setPositionRecruitment(cellValue);
                break;
            case 5 ://职位属性
                pf.setPositionAttribute(cellValue);
                break;
            case 6 ://职位分布
                pf.setPositionDistribution(cellValue);
                break;
            case 7 ://职位简介
                pf.setPositionSynopsis(cellValue);
                break;
            case 8 ://职位代码
                pf.setPositionCode(cellValue);
                break;
            case 9 ://机构层级
                pf.setOrganLevel(cellValue);
                break;
            case 10 ://考试类别
                pf.setExamType(cellValue);
                break;
            case 11 ://招考人数
                pf.setRecruitmentNumber(cellValue);
                break;
            case 12 ://专业
                pf.setMajor(cellValue);
                break;
            case 13 ://学历
                pf.setEducation(cellValue);
                break;
            case 14 ://学位
                pf.setDegree(cellValue);
                break;
            case 15 ://政治面貌
                pf.setPoliticalOutlook(cellValue);
                break;
            case 16 ://基层工作最低年限
                pf.setGrassrootsWorkYears(cellValue);
                break;
            case 17 ://服务基层项目工作经历
                pf.setGrassrootsWorkUndergo(cellValue);
                break;
            case 18 ://是否在面试阶段组织专业能力测试
                pf.setIfInterviewStage(cellValue);
                break;
            case 19 ://面试人员比例
                pf.setInterviewPersonnelRatio(cellValue);
                break;
            case 20 ://工作地点
                pf.setWorkplace(cellValue);
                break;
            case 21 ://落户地点
                pf.setLocation(cellValue);
                break;
            case 22 ://备注
                pf.setRemarks(cellValue);
                break;
            case 23 ://部门网站
                pf.setDeptWebsite(cellValue);
                break;
            case 24 ://咨询电话1
                pf.setConsultTel1(cellValue);
                break;
            case 25 ://咨询电话2
                pf.setConsultTel2(cellValue);
                break;
            case 26 ://咨询电话3
                pf.setConsultTel3(cellValue);
                break;
        }
        if(lastData){//最后一次执行sql
            int a = pfService.insertExcelPf(pf);
            insertNum = insertNum + a;
            pf = new PF();
        }
    }

}
