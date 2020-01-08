package com.example.myapplication

import com.example.myapplication.home.HomeKotlinDemo
import com.example.myapplication.home.outer

/**
 * Created by Alan on 2019/4/28.
 * 功能：
 */
class KtDemo {
    fun test(): Unit {
        HomeKotlinDemo().outer()
    }
}

/**
 * var、val 声明的变量分为三种类型：顶层属性、类属性和局部变量；
var 属性可以生成 getter 和 setter，是可变的（mutable），val 属性只有 getter，是只读的（read-only，注意不是 immutable）；
局部变量只是一个普通变量，不会有 getter 和 setter，它的 val 等价于 Java 的 final，在编译时做检查。
const 只能修饰没有自定义 getter 的 val 属性，而且它的值必须在编译时确定。

 ::用于函数或者属性的引用
 */
const val ha = ""


class HTML{
    fun body(): Unit {
        println(" <body></body>")
    }

    fun head(): Unit {
        println(" <head></head>")
        if (this::class==this.javaClass) {

        }
        println("class: "+this::class)
        println("this.javaClass: "+this.javaClass)
    }
}

fun html(init: HTML.()->Unit){//创建了的匿名扩展函数
        println("<html>")
        val html = HTML()
        html.init()
    println("/html")
}

fun main() {



    html {
        head()
        body()
    }

//    TODO("kotlin的final关键字不能修饰局部遍历")


    var inc = Bannaer().B()//创建非静态内部类
    var inc2 = Bannaer.C()//创建静态内部类

    var t = Runnable {
        println("kotlin的匿名内部类" )
    }
}


/**
 * 密封类本质是抽象类,并且其构造器默认添加private字段，这样不在同一个文件无法访问，但是其直接子类必须和它在同一个文件中
 * 密封类用来表示受限的类继承结构：当一个值为有限集中的类型、而不能有任何其他类型时。在某种意义上，他们是枚举类的扩展：枚举类型的值集合也是受限的，但每个枚举常量只存在一个实例，而密封类的一个子类可以有可包含状态的多个实例。
 */
sealed class Fruit(){
    abstract fun honey()
}

class Bannaer :Fruit(){
    override fun honey() {
        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.

        class Hc{//局部嵌套类

        }
    }


    inner class B{//非静态内部类

    }

    class C{//静态内部类，嵌套类，只能访问其他嵌套类，不能访问其宿主类，因为宿主类是非静态的

    }

}

/**
 * 接口没有初始化块
 */
interface A{
    var a :String
    fun getData()

    class C{//静态内部类在接口中默认是public修饰符，嵌套类，只能访问其他嵌套类，不能访问其宿主类，因为宿主类是非静态的

    }
}

interface B{

}


interface C : A,B{

}