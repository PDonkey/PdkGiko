package com.pdk.pdkgiko;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by uatql90533 on 2018/4/23.
 */
public class TestJ {
    public static void main(String[] args) {


//        String path = "D:\\新建文件夹" + File.separator + "demo.txt";
//        String copy = "D:\\新建文件夹" + File.separator + "copy.txt";

        Log.d("||||", "--------------------");
        //写
//        FileWriter w = null;
//        try {
//            //以path为路径创建一个新的FileWriter对象
//            //如果需要追加数据，而不是覆盖，则使用FileWriter（path，true）构造方法
//            w = new FileWriter(path);
//            w.write("playoff okc vs jazz");//字符串写入到流中
//            //如果想马上看到效果就调用w.flush()方法
//            w.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //如果前面发生了异常那么是无法产生w对象的，因此要做出判断，以免控制针
//            if (w != null) {
//                try {
//                    w.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }//

        //读
//        FileReader r = null;
//        try {
//            r = new FileReader(path);
//            //方法一：读取单个字符的方式，每读取一次向下移动一个字符单位
////            int temp1 = r.read();
////            System.out.println((char) temp1);
////            int temp2 = r.read();
////            System.out.println((char) temp2);
//
////            方法二：循环读取，read()读到文件末尾返回-1
//            while (true) {
//                int temp3 = r.read();
//                if (temp3 == -1) {
//                    break;
//                }
//                System.out.println((char) temp3);
//            }
////            //方法三：简化循环读取，read()读到文件末尾返回-1
////            int temp4 = 0;
////            while ((temp4 = r.read()) != -1) {
////                System.out.println((char) temp4);
////            }
////            //方法四：读入到字符数组
////            char[] buf = new char[1024];
////            int temp5 = 0;
////            //将数组转化为字符串打印
////            while ((temp5 = r.read(buf)) != -1) {
////                System.out.println(new String(buf, 0, temp5));
////            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (r != null) {
//                try {
//                    r.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.

        //复

        //
        //


        Log.d("|||||", "--------------------");

//        FileReader r = null;
//        FileWriter w = null;
//        try {
//            r = new FileReader(path);
//            w = new FileWriter(copy);
//
//            //方法一：单个字符写入
////            int temp=0;
////            while ((temp=r.read())!=-1){
////                w.write(temp);
////            }
//
//            //方法二：字符数组写入
//            char[] buf = new char[1024];
//            int temp = 0;
//            while ((temp = r.read(buf)) != -1) {
//                w.write(new String(buf, 0, temp));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (r != null) {
//                try {
//                    r.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (w != null) {
//                try {
//                    w.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.toString().length() > 4);
    }

    public static void filter(List<String> names, Predicate condition) {
        names.stream().filter(name -> condition.test(name)).forEach(name -> {
            System.out.println(name + " ");
        });


    }







}
