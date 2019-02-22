package com.haohua;    /*
 * @author  Administrator
 * @date 2018/9/18
 */

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ViewTest {

    /**
     * 编写一段程序，输入任意数字，输出对应的大写
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入金额");
        String num = sc.next();

        String[] str = {"分", "角", "元", "十", "百", "千", "万", "十", "百", "千", "亿"};
        String[] str1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        String[] s = num.split("\\.");

        int j = 0;
        char temp;
        int i;
        int p;

        for (i = s[0].length(), j = 0; i > 0; i--, j++) {
            //若当前字符为零判断下一个字符是否为0，直到不为零的字符为止，中间写一个零
            temp = s[0].charAt(j);
            String ss = String.valueOf(temp);
            p = Integer.valueOf(ss);
          if(s[0].charAt(j)=='0'&&j!=s[0].length()-1&&s[0].charAt(j+1)=='0'){

          }else if(p==0){
              if(j==s[0].length()-1){
                  System.out.println(str[3]);
              }
              System.out.print(str1[0]);
          }else{
            System.out.print(str1[p] + str[i + 1]);
          }

        }
        if (s.length > 1) {
            for (i = s[1].length(), j = 0; i > 0; i--, j++) {
                temp = s[1].charAt(j);
                String ss = String.valueOf(temp);
                p = Integer.valueOf(ss);
                if(p==0){
                    System.out.println("零");
                }else{
                    System.out.print(str1[p] + str[i - 1]);
                }
            }
        }
    }

    @Test
    public void testRandom(){
        /**
         * 返回一个0-10之间的随机数
         */
       int random = (int) (10*(Math.random()));
        System.out.println(random);

        int s = (int) (Math.random() * 9000) + 1000;
        System.out.println(s);
        String code = String.valueOf(s);
        String s1 = String.valueOf(code.charAt(0));
        String s2 = String.valueOf(code.charAt(1));
        String s3 = String.valueOf(code.charAt(2));
        String s4 = String.valueOf(code.charAt(3));
        while(s1.equals(s2)){
            s = (int) (Math.random() * 9000) + 1000;
        }

        System.out.println(s);
    }

    @Test
    public void contextLoads() {
        StringBuffer buffer = new StringBuffer("爱");
        for(int i=225;i>0;i--){
            buffer.append("爱");
        }
        System.out.println(buffer);

    }

    @Test
    public void testLength() throws UnsupportedEncodingException {
        String textStr = "我爱你";
       /* byte[] unicodes = textStr.getBytes();
        System.out.println( unicodes.length);*/
        int decTotal=0;
        for(int i=0;i<textStr.length();i++){

            System.out.println("二进制表示"+Integer.toBinaryString(textStr.charAt(i)));
            System.out.println("十六进制"+Integer.toHexString(textStr.charAt(i)));
           decTotal+=textStr.charAt(i);
            System.out.println("十进制"+(int)textStr.charAt(i));
        }
        System.out.println(decTotal);

    }



    @Test
    public void testOrder(){
        Mythread mythread = new Mythread();
        mythread.start();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Test
    public void distinctList(){

        Integer[] muns = {1,2,3,4,5};

    }




}
