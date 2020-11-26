package com.example.excel.test;

import com.itextpdf.text.pdf.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Test3 {
    public static int index = 2;
    private static List<String> contentList = new ArrayList<String>();
    private static StringBuffer sb = new StringBuffer();
    private static int size = 83;//每行最大字节数
    private static int maxRows = 90;//每页最大行数

    public static void main(String[] args) throws Exception {
        /*List<Map> list = getBatchAdviceData("D:/wenjian/3A944DA4.txt");*/

        String fileContent = getRealTimeAdvice("D:/wenjian/50EFF5C9.txt");
        List<Map> list= xmlParseMap(fileContent);
        /*List<Map> list = new ArrayList<Map>();
        list.add(0,map2);*/

        String resultName = "D:/wenjian/result" + System.currentTimeMillis() + ".pdf";
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(resultName);
            byte[] bytes= createPdf("D:/wenjian/demo.pdf","D:/wenjian/demo1.pdf"  , list);
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

    public static List<Map> getBatchAdviceData(String fileName){
        BufferedReader br = null;
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
        Map receiveMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        try{
            // 有[BOM]处理
            br = new BufferedReader(new InputStreamReader(skipUTF8BOM(new FileInputStream(new File(fileName)), "utf-8"), "utf-8"));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){
                if (s.trim().length() < 2){
                    continue;
                }
                if("01".equals(s.substring(0,2))){
                    index = 2;
                    map.put("System Code",s.substring(index,indexChange(3)));
                    map.put("Country Code",s.substring(index,indexChange(2)));
                    map.put("Group Member",s.substring(index,indexChange(4)));
                    map.put("customerID",s.substring(index,indexChange(12)));
                    map.put("inChnl",s.substring(index,indexChange(1)));
                    map.put("instrRefNo",s.substring(index,indexChange(16)));
                    map.put("reqSeqNo",s.substring(index,indexChange(7)));
                    map.put("Filler",s.substring(index,indexChange(3)));
                    map.put("reqTyp",s.substring(index,indexChange(2)));
                    map.put("reqTypCod",s.substring(index,indexChange(50)));
                    map.put("reqTs",s.substring(index,indexChange(26)));
                }else if("02".equals(s.substring(0,2))){
                    index = 2;
                    map.put("lglEnttInfoCtryCod",s.substring(index,indexChange(2)));
                    map.put("lglEnttInfoInstt",s.substring(index,indexChange(4)));
                    map.put("Customer Delivery Priority",s.substring(index,indexChange(1)));
                    map.put("Delivery Priority",s.substring(index,indexChange(1)));
                    map.put("attchIndc",s.substring(index,indexChange(1)));
                    map.put("Confidentiality Indicator",s.substring(index,indexChange(1)));
                }else if("03".equals(s.substring(0,2))){
                    index = 2;
                    map.put("Delivery Channel",s.substring(index,indexChange(1)));
                    map.put("Destination Country Code",s.substring(index,indexChange(2)));
                    map.put("Destination Delivery Address",s.substring(index,indexChange(70)));
                    map.put("Attachment Output Format",s.substring(index,indexChange(1)));
                    map.put("Filler",s.substring(index,indexChange(41)));
                    map.put("Recipient Security Option",s.substring(index,indexChange(1)));
                    map.put("Language Code",s.substring(index,indexChange(5)));
                }else if("HE".equals(s.substring(0,2))){
                    index = 2;
                    map.put("Email Subject Date",s.substring(index,indexChange(8)));
                }else if("T".equals(s.substring(0,1)) && isNumeric(s.substring(1,2))){
                    //字段长度不足自动填充
                    s = fillString(s,528);
                    index = 2;
                    map.put("advDate",s.substring(index,indexChange(10)));
                    map.put("dispatchCode",s.substring(index,indexChange(52)));
                    map.put("branchMne",s.substring(index,indexChange(3)));
                    map.put("branchName",s.substring(index,indexChange(30)));
                    map.put("custAccNumber",s.substring(index,indexChange(26)));
                    map.put("customerName",s.substring(index,indexChange(55)));
                    map.put("custAddr1",s.substring(index,indexChange(55)).toString().trim());
                    map.put("custAddr2",s.substring(index,indexChange(55)).toString().trim());
                    map.put("custAddr3",s.substring(index,indexChange(55)).toString().trim());
                    map.put("custAddr4",s.substring(index,indexChange(55)).toString().trim());
                    map.put("custAddr5",s.substring(index,indexChange(55)).toString().trim());
                    map.put("advTitle",s.substring(index,indexChange(75)));
                }else if("TC".equals(s.substring(0,2))){
                    index = 2;
                    if(!"END".equals(s.substring(3,6))) {
                        sb.append(s.substring(index,indexChange(130))+"\r\n");
                    }else{
                        map.put("adviceContent",sb);
                        sb = new StringBuffer();
                        receiveMap.put("branchName",map.get("branchName"));
                        receiveMap.put("advDate",map.get("advDate"));
                        receiveMap.put("custAccNumber",map.get("custAccNumber"));
                        receiveMap.put("accountNumber",map.get("accountNumber"));
                        receiveMap.put("pageNumber",map.get("pageNumber"));
                        receiveMap.put("dispatchCode",map.get("dispatchCode"));
                        receiveMap.put("custAddr1",map.get("custAddr1"));
                        receiveMap.put("custAddr2",map.get("custAddr2"));
                        receiveMap.put("custAddr3",map.get("custAddr3"));
                        receiveMap.put("custAddr4",map.get("custAddr4"));
                        receiveMap.put("custAddr5",map.get("custAddr5"));
                        receiveMap.put("branchMne",map.get("branchMne"));
                        receiveMap.put("customerName",map.get("customerName"));
                        receiveMap.put("advTitle",map.get("advTitle"));
                        receiveMap.put("customerID",map.get("customerID"));
                        receiveMap.put("instrRefNo",map.get("instrRefNo"));
                        //receiveMap.put("adviceContent",map.get("adviceContent"));
                        analysisString(map.get("adviceContent").toString(),size,maxRows);
                        for (String str : contentList){
                            receiveMap.put("adviceContent",str);
                            list.add(receiveMap);
                            receiveMap = new HashMap();
                            receiveMap.put("modelChange","2");
                        }
                        contentList = new ArrayList<String>();
                        //list.add(receiveMap);
                        receiveMap = new HashMap();
                    }
                }
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
        return list;
    }

    public static String fillString(String str, int length) {
        if(str.length()<length){
            StringBuffer sb = new StringBuffer(str);
            for (int i = sb.length(); i <= length; i++){
                sb.append(" ");
            }
            str = sb.toString();
        }
        return str;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static int indexChange(int num){
        index = index + num;
        return index;
    }

    /** 跳过[BOM]位 */
    public static InputStream skipUTF8BOM(InputStream is, String charSet) throws Exception {
        if (!charSet.toUpperCase().equals("UTF-8")) return is;
        if (!is.markSupported()) {
            //   如果输入流不支持mark功能时，用BufferedInputStream替换InputStream
            is = new BufferedInputStream(is);
        }
        is.mark(3); // 标记先头三位
        if (is.available() >= 3) {
            byte b[] = {0, 0, 0};
            is.read(b, 0, 3);
            if (b[0] != (byte) 0xEF ||
                    b[1] != (byte) 0xBB ||
                    b[2] != (byte) 0xBF) {
                is.reset();// 如果文件不含有[BOM]位时，将文件指针复位
            }
        }
        return is;
    }







    /**
     *  读取txt文件并打包成List集合
     * @param fileName  文件位置
     * @return  文件内部取值对应集合
     */
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



    public static List<Map> xmlParseMap(String xml){
        Document doc = null;
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            //01部分
            Iterator hdr = rootElt.elementIterator("Hdr");
            while (hdr.hasNext()) {
                Element element1 = (Element) hdr.next();
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
                Element element2 = (Element) advInfo.next();
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
                Element element3 = (Element) adviceContent.next();
                map.put("reqInd",element3.elementTextTrim("ReqInd"));
            }
            analysisString(rootElt.elementText("AdviceContent"),size,maxRows);
            for (String s : contentList){
                map.put("adviceContent",s);
                list.add(map);
                map = new HashMap();
                map.put("modelChange","2");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    /**
     *
     * @param demoPath  模板路径
     * @param list   存pdf数据集合<可多页>
     * @param demoPath2 模板路径2
     * @return byte[] 返回pdf字节流
     */
    public static byte[] createPdf(String demoPath, String demoPath2, List<Map> list) {
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
                if("2".equals(map.get("modelChange"))){
                    reader = new PdfReader(demoPath2);
                }else{
                    reader = new PdfReader(demoPath);
                }
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
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  解析字符串，最后获得每页需显示数据list
     * @param str       需解析的字符串
     * @param size      每行最大字节数
     * @param maxRows   每页显示行数
     */
    public static void analysisString(String str,int size,int maxRows){
        int rows = 0;//当前行数
        int num = 0;//遍历次数
        try {
            //对数组进行按行分组
            String[] arrayString= str.split("\n");
            for (String s : arrayString){
                num++;
                //去除末尾空格
                byte[] bytes = s.replaceAll("\\s+$","").getBytes("gbk");
                //获得当前行及每页显示数据
                rows = getRows(bytes,size,rows,maxRows,(arrayString.length == num));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     *  根据传输的字节，获得当前字节数为多少行，且在每个maxRows行数时，记录之前行数数据入list
     * @param bytes     需处理数据
     * @param size      每行最大字节数
     * @param rows      当前行数
     * @param maxRows   每页显示行数
     * @param ifLast    是否为最后一条数据
     * @return
     * @throws UnsupportedEncodingException
     */
    public static int getRows(byte[] bytes,int size,int rows,int maxRows,boolean ifLast) throws UnsupportedEncodingException {
        boolean flag = false;
        //每次进入方法，行数+1
        rows++;
        if(rows%maxRows == 0){//当前行数到达每页能显示的最大行数时[可能不止一页]
            flag = true;
        }
        //字节负数统计并进行求模​
        int num = 0;
        if(size >= bytes.length){//当前字节不满一行时
            sb.append(new String(bytes,"gbk")+"\n");//字符串拼接
            if(flag || ifLast){//当达到最大行数时，对集合存入数据
                contentList.add(sb.toString());
                sb = new StringBuffer();
            }
            return rows;
        }else{
            //循环到需要分割的长度，后面的增加1
            for(int i = 0 ; i < size; i++ ){
                if(bytes[i]<0){
                    num++;
                    num = num % 2;
                }
            }
        }
        //当num为1时，代表截取字节最后一位为中文的一半；num为0时，正常取值
        if(size + num >= bytes.length){
            sb.append(new String(bytes,"gbk")+"\n");//字符串拼接
            if(flag || ifLast){//当达到最大行数时，对集合存入数据
                contentList.add(sb.toString());
                sb = new StringBuffer();
            }
            return rows;
        }else{
            byte[] byte3 = new byte[size + num];
            System.arraycopy(bytes, 0, byte3, 0,size + num);
            sb.append(new String(byte3,"gbk")+"\n");
            if(flag){//当达到最大行数时，对集合存入数据
                contentList.add(sb.toString());
                sb = new StringBuffer();
            }
            byte[] byte2 = new byte[bytes.length-(size + num)];
            System.arraycopy(bytes, size + num, byte2, 0,bytes.length-(size + num));
            rows = getRows(byte2,size,rows,maxRows,ifLast);//字节数不止一行时，递归
            if(ifLast && sb.toString().length()!=0) {//最后一行时，对集合存入数据
                contentList.add(sb.toString());
                sb = new StringBuffer();
            }
        }
        return rows;
    }
}
