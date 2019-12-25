package com.example.myapplication.home

import kotlin.properties.Delegates

const val TEST = false
open class HomeKotlinDemo{
    fun info(){
        println("成员函数比相同签名的扩展函数调用优先级高")
    }

    fun drink(): Unit {
        println("HomeKotlinDemo 的drink方法")
    }
}

object Object1{//object关键字可以用作声明单例

}

fun HomeKotlinDemo.info(){//添加类的扩展函数
    println("额外添加函数")
}

fun HomeKotlinDemo.outer(){//添加类的扩展函数
    println("额外添加函数")
    /*Observable.create(ObservableOnSubscribe<Int> {
        emitter ->  emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
    }).flatMap(Function <Int,ObservableSource<String>>{
        arrayListOf<String>()

    })*/
}
open class SubHomeDemo:HomeKotlinDemo()
fun SubHomeDemo.info(){//子类扩展函数与父类扩展函数同名，不用覆盖
    println("subhome 额外添加函数")
}



class a : HomeKotlinDemo() {

}

//fun Any?.equals(other: Any?):Boolean{
//    if ((this == null)) {
//        return if (other == null) true else false
//
//    }
//
//    return other.equals(other)
//}

fun invoke( demo: HomeKotlinDemo){
    demo.info()
}

fun main() {
    println("val 的对象值：${F("JJ",2).a}")
}

private fun attributeObserver() {
    var p = E()
    var pp = p.vetoableProp;
    println("属性监听： $pp")
    p.vetoableProp = 45
    println("属性监听： ${p.vetoableProp}")


    p.vetoableProp = 5
    println("属性监听： ${p.vetoableProp}")
}

private fun enumClass() {
    for (value in Season.values()) {
//        println("枚举的值： $value")
    }

    val s: Season = Season.valueOf("SUMMER")
//    println("s: $s"+"   "+"${Season.WINTER}")
    printAllEnum<Season>()

    val g = Name.valueOf("FEMALE")
    println("${g}代表：${g.mingzi}")
    g.info()
}


inline  fun <reified T: Enum<T>> printAllEnum(){
    println(enumValues<T>().joinToString { it.name })
}
private fun objeBiaoDa() {
    var ob = object : SubHomeDemo(), A {//对象表达式相当于java的匿名内部类
        //对象表达
        fun test() {
            println("测试")


        }
    }

    println("对象表达式： " + ob.test())
    println("伴随对象的函数扩展： " + B.foo())
    println("伴随对象的属性扩展： " + B.value)

    println("伴随对象的调用方法： " + B.test4() + " 伴随对象的属性： " + B.testV)
}

private fun duixiang() {
    HomeKotlinDemo().info()
    invoke(SubHomeDemo())//扩展函数不受多态规则，调用的还是父类的扩展函数，专业术语即编扩展函数是静态解析（由编译时类型决定），成员函数动态解析，由运行时类型决定

    var a = null
//    println(a.equals(null))
//    println(a.equals("kotlin"))
    Normal().diao(HomeKotlinDemo())


}

class Normal{
    fun HomeKotlinDemo.eat(){
        println("在其他类中扩展其他类的方法")
        run()
        drink()//默认调用的是扩展类的方法
        this@Normal.drink()//指定调用Normal的drink方法
    }

    fun drink(): Unit {
        println("Normal的drink方法")
    }

    fun run(): Unit {
        println("Normal的 run 方法")
    }

    fun diao(home: HomeKotlinDemo){
        home.eat()
    }


}


const val haha = "解决方法解放军"

open interface A{
    fun testA(){
        println("接口A的测试方法a")
    }

}

open class C{

}

 interface D{
     abstract fun hi()

}

enum class Season{//枚举类
    SPRING,SUMMER,FALL,WINTER
}

enum class Name( val mingzi: String) : D{
    MALE("男") {
        override fun evalute() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun hi() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    },FEMALE("女") {
        override fun evalute() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun hi() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    };
    fun info(){
        when(this){
            MALE -> println("是男生")
            FEMALE -> println("是女生")
        }
    }

    abstract fun evalute()


}

/**
 * 对象声明，一般用于单例
 */
object obj4 : A,C(){//对象声明

}

class B{
    object obj3 : A,C(){//伴随对象，即
        fun test1(){
        println("对象test1")
    }
   }


    companion object obj4 : A,C(){//伴随对象，即companion修饰的对象，外部类可以直接访问其属性以及方法，用于弥补java的静态方法
        var testV = "哈哈哈回复"
    fun test4(){
        println("对象test4")
    }
    }
}

class BCompannion{
    @JvmField var age = 3
    object obj3 : A,C(){//伴随对象，即
    fun test1(){
        println("对象test1")
    }
    }


    /**
     * lateinit
     *   lateinit var只能用来修饰类属性，不能用来修饰局部变量，并且只能用来修饰对象，不能用来修饰基本类型(因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值)。
      lateinit var的作用也比较简单，就是让编译期在检查时不要因为属性变量未被初始化而报错。
      Kotlin相信当开发者显式使用lateinit var 关键字的时候，他一定也会在后面某个合理的时机将该属性对象初始化的(然而，谁知道呢，也许他用完才想起还没初始化)。

    作者：咸鱼不思议
    链接：https://juejin.im/post/5affc369f265da0b9b079629
    来源：掘金
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    companion object obj4 : A,C(){//伴随对象，即companion修饰的对象，外部类可以直接访问其属性以及方法，用于弥补java的静态方法
        lateinit var lateV : String//延迟初始化属性
    @JvmField var testV = "哈哈哈回复"

        fun test4(){
            println("对象test4")
        }
    }
}


fun B.obj4.foo(){
    println("伴随对象的函数扩展111")
}

val B.obj4.value
    get() = "伴随对象的属性扩展"


class E{
    var vetoableProp: Int by Delegates.vetoable(20){//属性值变化监听
            property, oldValue, newValue ->
        println("${property}的 $oldValue 被改为$newValue")
        newValue>oldValue
    }


    var observableProp: String by Delegates.observable("默认值"){//属性值变化监听有返回值Boolean
            property, oldValue, newValue ->
        println("${property}的 $oldValue 被改为$newValue")

    }
}

class F(val a: String ,val b: Int){

}