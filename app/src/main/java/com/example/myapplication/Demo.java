package com.example.myapplication;

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
}


