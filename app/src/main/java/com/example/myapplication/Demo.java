package com.example.myapplication;

import com.example.myapplication.home.BCompannion;

import java.util.ArrayList;

/**
 * Created by Alan on 2019/4/25.
 * 功能：
 */
public class Demo {

    /**
     *
     */
    public void sun(String... a){

        String s2 = BCompannion.testV;
        BCompannion.lateV =  "延迟初始化";
        int s1 = new BCompannion().age;
        for (String s : a) {
            TestKKt.main();
        }
        Object o = new AI() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }


    interface AI{

    }

    public static class DemoInner{

    }

    public  class DemoInne2r{

    }


    public <T> void show(T t)
    {
        System.out.println("show: "+t);
    }

    public <E,T> E get(ArrayList<E> arrayList,ArrayList<T> arrayList1){
        return  arrayList.get(0);
    }
    class Demo1
    {
        public <T> void show(T t)
        {
            System.out.println("show: "+t);
        }
        public <Q> void print(Q q)
        {
            System.out.println("print:"+q);
        }
    }
}


