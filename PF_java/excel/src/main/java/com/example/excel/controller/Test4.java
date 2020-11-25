package com.example.excel.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.dom4j.DocumentHelper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Test4 {
    public static void main(String[] args) {

        //fillTemplate();
        String fileContent = getRealTimeAdvice("D:/wenjian/50EFF5C8.txt");
        Map map1= xmlParseMap(fileContent);
        List list = new ArrayList();
        list.add(0,map1);

        String resultName = "D:/wenjian/result" + System.currentTimeMillis() + ".pdf";
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(resultName);
            byte[] bytes= createPdf("D:/wenjian/demo.pdf"  , list);
            outputStream.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("创建文件："+resultName);
    }


    public static byte[] createPdf(String demoPath, List<Map> list) {
        //读入模板pdf表单
        PdfReader reader = null;
        PdfStamper ps = null;
        //用于存储每页生成pdf流
        ByteArrayOutputStream baos[] = new ByteArrayOutputStream[list.size()];
        ByteArrayOutputStream out;
        //创建并打开一个pdf对象
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
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
                BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                //字体设置：颜色、字体、大小等
                Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
                //遍历pdf表单表格，同时给表格赋值
                Map fieldMap = s.getFields();
                Set set = fieldMap.entrySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key = (String) entry.getKey();
                    if (map.get(key) != null) {
                        List<AcroFields.FieldPosition> afList = s.getFieldPositions(key);
                        //层数
                        int page = afList.get(0).page;
                        //页面对象
                        Rectangle position = afList.get(0).position;
                        //拿到对应层对象
                        PdfContentByte pdf = ps.getOverContent(page);
                        ColumnText columnText = new ColumnText(pdf);
                        Rectangle rectangle = new Rectangle(position.getLeft(),position.getBottom(),position.getRight(),position.getTop());
                        columnText.setSimpleColumn(rectangle);
                        //获得数据
                        Chunk chunk = new Chunk(map.get(key).toString());
                        if("adviceContent".equals(key)) {
                            chunk.setCharacterSpacing(1);
                        }
                        //创建段落对象
                        Paragraph p = new Paragraph(12,chunk);
                        //设置段落行间距
                        if("adviceContent".equals(key)) {
                            p.setLeading(p.getTotalLeading() + 5);
                        }
                        columnText.addText(p);
                        p.setFont(fontChinese);
                        columnText.addElement(p);
                        columnText.go();
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
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Font getChineseFont(PdfStamper ps) {
        BaseFont bfChinese = null;
        Font fontChinese = null;
        try {
            //设置支持中文
            bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //字体设置：颜色、字体、大小等
            fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;
    }

    public static void xx(PdfStamper ps,List<AcroFields.FieldPosition> list,String content) throws IOException, DocumentException {
        //使用微软雅黑字体显示中文
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //字体设置：颜色、字体、大小等
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);;
        //List<AcroFields.FieldPosition> list = s.getFieldPositions(key);
        //层数
        int page = list.get(0).page;
        //页面对象
        Rectangle position = list.get(0).position;
        //拿到对应层对象
        PdfContentByte pdf = ps.getOverContent(page);
        ColumnText columnText = new ColumnText(pdf);
        Rectangle rectangle = new Rectangle(position.getLeft(),position.getBottom(),position.getRight(),position.getTop());
        columnText.setSimpleColumn(rectangle);
        //获得数据
        Chunk chunk = new Chunk(content);
        //创建段落对象
        Paragraph p = new Paragraph(12,chunk);
        //设置段落行间距
        p.setLeading(p.getTotalLeading()+20);
        columnText.addText(p);
        p.setFont(fontChinese);
        columnText.addElement(p);
        columnText.go();
    }


    public static void fillTemplate(){
        //模板路径
        String templatePath = "D:/wenjian/demo.pdf";
        //生成的新文件路径
        String newPDFPath = "D:/wenjian/newPdf.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);//输出流
            reader = new PdfReader(templatePath);//读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);

            BaseFont bf = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bf,12,Font.BOLD);
            AcroFields form = stamper.getAcroFields();
            List<AcroFields.FieldPosition> list = form.getFieldPositions("adviceContent");
            int page = list.get(0).page;
            Rectangle angle = list.get(0).position;
            float left = angle.getLeft();
            float right = angle.getRight();
            float top = angle.getTop();
            float bottom = angle.getBottom();
            PdfContentByte pdf = stamper.getOverContent(page);
            ColumnText text = new ColumnText(pdf);
            Rectangle r = new Rectangle(left,bottom,right,top);
            text.setSimpleColumn(r);
            Chunk chunk = new Chunk("sjdhgkfhdsgghuh谁看见的合格率会计师的更厉害沙鲁克汗两个红树林会计师的理解开好了升了好的时间里哈萨克具荷拉法开会时间看到过黄老师带回家看晃过上帝里款行管理客户是短路客户端烧开后改扩建好");
            Paragraph h = new Paragraph(12,chunk);
            h.setLeading(h.getTotalLeading()+40);
            text.addText(h);
            h.setFont(font);
            text.addElement(h);
            text.go();


            //form.setField("pageNumber", "customerName");
            stamper.setFormFlattening(true);//如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(
                    new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }
    }


    public static String getRealTimeAdvice(String fileName){
        BufferedReader br = null;
        StringBuffer ss = new StringBuffer();
        try{

            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));//构造一个BufferedReader类来读取文件
            String s = null;
            //boolean switchFlag = false;//读取开关
            while((s = br.readLine())!=null){
                ss.append(s.replaceAll("\"",""));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return ss.toString();
    }

    public static Map xmlParseMap(String xml){
        org.dom4j.Document doc = null;
        Map map = new HashMap();
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            org.dom4j.Element rootElt = doc.getRootElement(); // 获取根节点
            //01部分
            Iterator hdr = rootElt.elementIterator("Hdr");
            while (hdr.hasNext()) {
                org.dom4j.Element element1 = (org.dom4j.Element) hdr.next();
                //map.put("reqInd",element1.elementTextTrim("ReqInd"));
                map.put("requestor",element1.elementTextTrim("Requestor"));
                map.put("customerID",element1.elementTextTrim("CustomerID"));
                map.put("inChnl",element1.elementTextTrim("InChnl"));
                map.put("instrRefNo",element1.elementTextTrim("InstrRefNo"));
                map.put("reqSeqNo",element1.elementTextTrim("ReqSeqNo"));
                map.put("reqTypCod",element1.elementTextTrim("ReqTypCod"));
                map.put("reqTyp",element1.elementTextTrim("ReqTyp"));
                map.put("reqTs",element1.elementTextTrim("ReqTs"));
            }
            //02部分
            Iterator advInfo = rootElt.elementIterator("AdvInfo");
            while (advInfo.hasNext()) {
                org.dom4j.Element element2 = (org.dom4j.Element) advInfo.next();
                //map.put("reqInd",element2.elementTextTrim("ReqInd"));
                map.put("lglEnttInfoCtryCod",element2.elementTextTrim("LglEnttInfoCtryCod"));
                map.put("lglEnttInfoInstt",element2.elementTextTrim("LglEnttInfoInstt"));
                map.put("attchIndc",element2.elementTextTrim("AttchIndc"));
            }
            //TX部分
            //map.put("reqInd",rootElt.elementTextTrim("ReqInd"));
            map.put("advDate",dateTrans(rootElt.elementTextTrim("AdvDate")));
            map.put("dispatchCode",rootElt.elementTextTrim("DispatchCode"));
            map.put("branchMne",rootElt.elementTextTrim("BranchMne"));
            map.put("branchName",rootElt.elementTextTrim("BranchName"));
            map.put("custAccNumber",rootElt.elementTextTrim("CustAccNumber"));
            map.put("customerName",rootElt.elementTextTrim("CustomerName"));
            map.put("custAddr1",rootElt.elementTextTrim("CustAddr1"));
            map.put("custAddr2",rootElt.elementTextTrim("CustAddr2"));
            map.put("custAddr3",rootElt.elementTextTrim("CustAddr3"));
            map.put("custAddr4",rootElt.elementTextTrim("CustAddr4"));
            map.put("custAddr5",rootElt.elementTextTrim("CustAddr5"));
            map.put("advTitle",rootElt.elementTextTrim("AdvTitle"));
            //TC部分
            Iterator adviceContent = rootElt.elementIterator("AdviceContent");
            while (adviceContent.hasNext()) {
                org.dom4j.Element element3 = (org.dom4j.Element) adviceContent.next();
                map.put("reqInd",element3.elementTextTrim("ReqInd"));
            }
            map.put("adviceContent",rootElt.elementText("AdviceContent"));
        } catch (org.dom4j.DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String dateTrans(String oldDate){
        SimpleDateFormat oldDf = new SimpleDateFormat("dd/MM/yyyy");//原日期格式
        SimpleDateFormat nowDf = new SimpleDateFormat("yyyy-MM-dd");//转换后日期格式
        Date oldTime = null;
        try {
            oldTime = oldDf.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowDf.format(oldTime);
    }

}
