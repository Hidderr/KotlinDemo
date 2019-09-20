package com.example.myapplication;

import com.example.myapplication.home.BCompannion;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) {
        System.out.println("s: =============== ");
//        Log.e("RxJava转换","开始。。。。。。。。。。。。。。");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
//                Log.e("RxJava转换",s);
                System.out.println("s:  "+s);
            }
        });
    }
}


