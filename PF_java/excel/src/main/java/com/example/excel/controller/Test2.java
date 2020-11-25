package com.example.excel.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test2 {
    public static void main(String[] args) throws IOException, DocumentException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("执行开始时间："+df.format(new Date()));
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
        map.put("address1", "address1");
        map.put("address2", "address2");
        map.put("address3", "address3");
        map.put("address4", "address4");
        map.put("address5", "address5");
        map.put("branch", "XXX");
        map.put("branchName", "宁波分行");
        map.put("date", "2020/7/27");
        map.put("customerNumber", "客户号");
        map.put("accountNumber", "交易账号");
        //map.put("pageNumber", "1 of 2");
        map.put("dispatch", "XXX");
        map.put("customerName", "客户名称");
        map.put("adivceType", "汇出汇款通知书");
        map.put("tradeNumber", "200727YLP10027432");
        map.put("payerAccountNumber", "601-006877-020");
        map.put("bankName", "收款行名称");
        map.put("payeeAccountNumber", "787090100100010475");
        map.put("payeeName", "收款人");
        map.put("moneyNumber", "CNY 5,000,000.00");
        map.put("payerName", "付款人");
        map.put("remarks", "往来款");
        map.put("commission", "CNY 50.00");
        list.add(0,map);
        list.add(1,map);
        /*for(int i=0;i<100;i++){
            list.add(map);
        }*/

        String resultName = "D:/wenjian/result" + System.currentTimeMillis() + ".pdf";
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(resultName);
            byte[] bytes = createPdf("D:/wenjian/demo.pdf" , list);
            outputStream.write(bytes);
            System.out.println("执行结束时间："+df.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] createPdf(String demoPath, List<Map> list) {
        //读入模板pdf表单
        PdfReader reader = null;
        PdfStamper ps = null;
        //用于存储每页生成pdf流
        ByteArrayOutputStream baos[] = new ByteArrayOutputStream[list.size()];
        ByteArrayOutputStream out;
        //创建并打开一个pdf对象
        Document doc = new Document();
        try {
            out = new ByteArrayOutputStream();
            PdfCopy pdfCopy = new PdfCopy(doc,out);
            doc.open();

            //循环填充表单
            for (int i=0;i<list.size();i++) {
                Map map = list.get(i);
                map.put("pageNumber", (i+1)+" of "+list.size());
                reader = new PdfReader(demoPath);
                baos[i] = new ByteArrayOutputStream();
                //根据模板生成新的pdf
                ps = new PdfStamper(reader, baos[i]);
                //获取新的pdf表单
                AcroFields s = ps.getAcroFields();
                //使用微软雅黑字体显示中文
                BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                s.addSubstitutionFont(bf);
                //遍历pdf表单表格，同时给表格赋值
                Map fieldMap = s.getFields();
                Set set = fieldMap.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = (String) entry.getKey();
                    if (map.get(key) != null) {
                        s.setField(key, "" + map.get(key));
                    }
                }
                ps.setFormFlattening(true);
                ps.close();
                reader.close();
                //文档拼接
                PdfImportedPage impage = pdfCopy.getImportedPage(new PdfReader(baos[i].toByteArray()), 1);
                pdfCopy.addPage(impage);
            }
            //关闭
            doc.close();
            return out.toByteArray() != null ? out.toByteArray() : null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }




    /*public static void main(String[] args) {
        //加载Excel文档
        Workbook wb = new Workbook();
        wb.loadFromFile("D:/wenjian/a.xlsx");

        //调用方法保存为PDF格式
        wb.saveToFile("D:/wenjian/a.pdf", FileFormat.PDF);
    }*/
}
