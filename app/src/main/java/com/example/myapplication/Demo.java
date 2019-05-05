package com.example.myapplication;

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


