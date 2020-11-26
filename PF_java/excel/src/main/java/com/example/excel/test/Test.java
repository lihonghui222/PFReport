package com.example.excel.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static List<String> list = new ArrayList<String>();
    private static StringBuffer sb = new StringBuffer();
    public static void main(String[] args){
        /*String str1 = "本行已于今日。自贵帐户扣款1和我生日会的故事搭嘎胯骨规范化代发安徽联合国哈来得更好辣";
        String str2 = ".咖喱给道格拉斯德令哈刚开始.的两个拉开安徽但发了个哈里哈嘎后来稍等kghad联合国了螯合";
        System.out.println(str1.length()+","+str2.length());
        try {
            System.out.println(str1.getBytes("utf-8").length+","+str2.getBytes("utf-8").length);
            System.out.println(str1.getBytes("gbk").length+","+str2.getBytes("gbk").length);
            System.out.println(str1.getBytes("Unicode").length+","+str2.getBytes("Unicode").length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
*/
        /*String str = "本行已自贵帐户收取移动收款服务费如下：                                           \n" +
                "服务费  CNY0.02                                                                  \n" +
                "商户号  0002900F1786852                                                          \n" +
                "扣款帐户 510-252141-011                                                          \n" +
                "服务费扣费期间 20180301-20180301                                                 \n" +
                "本行已于今日。自贵帐户扣款1和我生日会的故事搭嘎胯骨规范化代发安徽联合国哈来得更好辣.咖喱给道格拉斯德令哈刚开始.的两个拉开安徽但发了个哈里哈嘎后来稍等kghad联合国了螯合钙卡和代发货格拉好几个拉到了好尬带回家改好了端口号法兰克好感度法火龙果。                                                       \n" +
                "本行已于今日自贵帐户扣款2。                                                       \n" +
                "本行已于今日自贵帐户扣款3。                                                       \n" +
                "本行已于今日自贵帐户扣款4。                                                       \n" +
                //"本行已于今日自贵帐户扣款5。                                                       \n" +
                //"本行已于今日自贵帐户扣款6。                                                       \n" +
                //"本行已于今日自贵帐户扣款7。                                                       \n" +
                "本行已于今日自贵帐户扣款8杀死偶死得更快房间号贵宾卡大概几点发是短路户口螺丝刀会更好范德华力公司地方沪杭公路阿里更好地发挥改好了贷款发或高或低挥洒符合公安等回复骨灰盒豆腐我是更奥地利非空格哈德良好更合理的发挥噶好得很公开哈达里发过火昂发来开个会辣豆腐联合国来合肥的拉花货到付款格拉看到回复两个号拉黑的双方各换了卡方大化工快乐哈单联开关开罗姬had分开了个喝辣的复核管理哈拉海更好地发阿道夫管理卡货到付款两个号辣豆腐黑古拉发几个哈哈福利开关机哈考虑到恢复高考了符合打了个老大哥 ad发了个哈伦裤读后感琅嬛福地考拉海购立刻恢复到路口好好地恢复的恢复的恢复的后代好多好多个大号阿萨德刚发的噶的噶电饭锅哦豁固化都哦噶后对合格。";*/
        String str = "WE CONFIRM HAVING CREDITED YOUR LOAN ACCOUNT AS FOLLOWS:                                                                          \n" +
                "                                                                                                                                  \n" +
                "VALUE ON 01FEB2024                                                                                                                \n" +
                "                                                                                                                                  \n" +
                "REPAYMENT TYPE\tINSTALMENT                                                                                                         \n" +
                "                                                                                                                                  \n" +
                "INSTALMENT AMOUNT                   HKD        12,814.81                                                                          \n" +
                "DEBIT INTEREST                      HKD           422.32                                                                          \n" +
                "                                                                                                                                  \n" +
                "TOTAL AMOUNT                        HKD        13,237.13                                                                          \n" +
                "                                                                                                                                  \n" +
                "THE NET PROCEEDS IS PAID BY :                                                                                                     \n" +
                "ACCOUNT 510-306673-057              HKD        13,237.13                                                                          \n" +
                "                                                                                                                                  \n" +
                "LOAN BALANCE OUTSTANDING            HKD     1,149,356.61                                                                          \n" +
                "                                                                                                                                  \n";
        int size = 83;//每行最大字节数
        int maxRows = 20;//每页最大行数
        analysisString(str,size,maxRows);
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
            System.out.println("");
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
            if(flag || ifLast){//当达到最大行数或最后一行时，对集合存入数据
                list.add(sb.toString());
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
            if(flag || ifLast){//当达到最大行数或最后一行时，对集合存入数据
                list.add(sb.toString());
                sb = new StringBuffer();
            }
            return rows;
        }else{
            byte[] byte3 = new byte[size + num];
            System.arraycopy(bytes, 0, byte3, 0,size + num);
            sb.append(new String(byte3,"gbk")+"\n");
            if(flag){//当达到最大行数时，对集合存入数据
                list.add(sb.toString());
                sb = new StringBuffer();
            }
            byte[] byte2 = new byte[bytes.length-(size + num)];
            System.arraycopy(bytes, size + num, byte2, 0,bytes.length-(size + num));
            rows = getRows(byte2,size,rows,maxRows,ifLast);//字节数不止一行时，递归
            if(ifLast && sb.toString().length()!=0) {//最后一行时，对集合存入数据
                list.add(sb.toString());
                sb = new StringBuffer();
            }
        }
        return rows;
    }
}
