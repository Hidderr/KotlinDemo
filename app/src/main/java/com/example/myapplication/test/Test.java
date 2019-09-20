package com.example.myapplication.test;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/9/20
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class Test<T extends Fruit> {

    /**
     * 吃水果
     * @param fruitHolder
     */
    public static void eatFruit(GenericHolder<? extends Fruit> fruitHolder){
        System.out.println("我正在吃 " + fruitHolder.getObj().getName());
    }

    public static void main(String args[]){
        //这是一个贴了水果标签的袋子
        GenericHolder<Fruit> fruitHolder = new GenericHolder<Fruit>();
        //这是一个贴了苹果标签的袋子
        GenericHolder<Apple> appHolder = new GenericHolder<Apple>();
        //这是一个水果
        Fruit fruit = new Fruit("水果");
        //这是一个苹果
        Apple apple = new Apple("苹果");

        //现在我们把水果放进去
        fruitHolder.setObj(fruit);
        //调用一下吃水果的方法
        eatFruit(fruitHolder);

        //放苹果的标签，自然只能放苹果
        appHolder.setObj(apple);
        // 这时候可以顺利把appHolder 传入eatFruit
        eatFruit(appHolder);


//        Object[] objects = new List<String>[3];
    }

}
