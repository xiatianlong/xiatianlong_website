package com.xiatianlong.test;


import java.util.ArrayList;
import java.util.List;

/**
 * Test Class
 * Created by xiatianlong on 2017/1/12.
 */
public class Test {

    public static void main(String[] args) throws Exception{

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");


        List<String> newList = list.subList(10,15);
        for (String str : newList){
            System.out.println(str);
        }



//        System.out.println(37%10);
//        System.out.println(2%10);

    }




}