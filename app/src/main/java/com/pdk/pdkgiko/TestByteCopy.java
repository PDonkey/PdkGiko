package com.pdk.pdkgiko;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by uatql90533 on 2018/4/23.
 * 利用字符流的缓冲区来进行文本文件的复制
 */
public class TestByteCopy {
    public static void main(String[] args) {
        String doc = "D:\\新建文件夹" + File.separator + "demo.txt";
//        String copy = "D:\\新建文件夹" + File.separator + "copy.txt";
//
//        FileReader r = null;
//        FileWriter w = null;
//        //创建缓冲区的引用
//        BufferedReader br = null;
//        BufferedWriter bw = null;
//
//        try {
//            r = new FileReader(doc);
//            w = new FileWriter(copy);
//
//            //创建缓冲区对象
//            //将需要提高效率的FileReader和FileWrite对象放入其构造函数内
//            //也可以使用匿名对象的方式br=new BufferReader(new FileReader(doc));
//            br = new BufferedReader(r);
//            bw = new BufferedWriter(w);
//
//            String line = null;
//            //读取行直到返回null
//            //readLine()方法只返回换行符之前的数据
//            while ((line = br.readLine()) != null) {
//                //使用BufferWrite对象的写入方法
//                bw.write(line);
//                //写完文件后换行
//                bw.newLine();
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (bw != null) {
//                try {
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        //

//        FileOutputStream out = null;//字节流的写入
//        try {
//            out = new FileOutputStream(doc, true);
//            String str = "thunder is lose\r\n";
//            byte[] buf = str.getBytes();
//            //也可以直接使用o.write(str.getBytes()); 字符串是一个对象可以直接调用方法
//            out.write(buf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        FileInputStream in = null;

        try {
            in = new FileInputStream(doc);
            //方法一：单字节符读取
            //中文会出问题
//            int ch = 0;
//            while ((ch = in.read()) != -1) {
//                System.out.println((char) ch);
//            }
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
