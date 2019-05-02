package com.example.myapplication

/**
 * Created by Alan on 2019/4/28.
 * 功能：kotlin对象demo
 */


class User(name: String, pass: String, age: Int) {

    init {
        println("初始化块1111")
    }

    init {
        println("初始化块2222222")
    }



    lateinit var width :String //加入lateinit关键字是延迟初始化，但是必须初始化后才能调用否则报异常
    private var alias  = name;//用private修饰的属性成为幕后属性，不会生成幕后字段（除了private，同时重写属性的get与set方法以及其中不使用field则也不会生成幕后字段）即不会生成get、set方法
    var aliasNamed
    get() = alias
    set(value) {
        alias = value
    }
    var name = name;
    var pass = pass;
    var age = age;
    inline  val fullName :String
        get() ="fullName设置"

    var auto :String
    get() = "v"
    inline  set(value){
           println("设置auto的值")

    }

    var setName :String = ""
        set(value) {

            field = value //field为幕后字段 （即为属性(除了private关键字修饰的)生成的字段类似Java的Field）
            println("开始修改setName：$field")
        }
    get() = "减肥减肥"





    /*
    operator仅用于操作符
    * Kotlin 允许我们为自己的类型提供预定义的一组操作符的实现。这些操作符具有固定的符号表示 （如 + 或 *）和固定的优先级。为实现这样的操作符，我们为相应的类型（即二元操作符左侧的类型和一元操作符的参数类型）提供了一个固定名字的成员函数或扩展函数。 重载操作符的函数需要用 operator 修饰符标记。

另外，我们描述为不同操作符规范操作符重载的约定。
    * */


    /**
     * component1 对应age，方法名是固定的用于解构，即把对象的属性赋值给多个参数
     *
     */
    operator fun component1(): Int {
        return age
    }

    /**
     *
     */
    operator fun component2(): String {
        return name
    }

    operator fun component3(): String {
        return pass
    }

    override fun toString(): String {
        return "User(name='$name', pass='$pass', age=$age)"
    }


}

/**
 * 数据类，class前加data关键字，系统会自动为data关键字修饰的class生成对应的解构方法 component1、component2等
 */
data class Result(var result: Int, var status: String)


fun main() {
    println("初始化： ${Init("张三").name}")
    println("初始化： ${Init()}")
    println("构造器：${Init("李四",12)}")
    println("构造器：${Init("旺旺",2,45)}")
}

private fun objectFunction() {
    /* var user = User("名称", "通过", 12)
    var (name1, pass1: String) = user//将User对象的属性赋值给name1,pass1
    println("name1: $name1:   pass1: $pass1")

    var (name2, pass2, age2) = user
    println("name2: $name2:   pass2: $pass2    age2： $age2")

    var (_, pass3, age3) = user//仅仅将User对象的第二和第三属性赋值给pass3、age3
    println("pass3: $pass3:   age3: $age3")*/

    var user = User("名称", "通过", 12)
    var age = user.age
    user.setName = "修改名称成功"
    user.auto = "修改auto的值"
//    println("age: $age")
    println("fullName: ${user.fullName}")
    println("auto: ${user.auto}")
//    println("setName: ${user.setName}")
//    println("aliasNamed: ${user.aliasNamed}")
//    user.aliasNamed = "修改幕后属性"
//    println("是否修改aliasNamed: ${user.aliasNamed}")
//    user.width = "宽"
//    println("延迟赋值 width: ${user.width}")
/*
    var (result,status) = Result(2,"昨天")
    var (result1,status1) = Pair<String,String>("张三","李四")
    println("数据类： result: $result:   status: $status")
    println("kotlin自带的数据类 Pair： result1: $result1:   status1: $status1")


    var mapValue = mapOf("1" to "一", "2" to "二", "3" to "三")//map的遍历方式
    mapValue.mapValues { (_,value)-> println("解构：仅仅解构value：$value") }//lambda表达式支持解构参数，但是参数需要用()括住*/
}


class Init(name:String="Lis"){
    var name :String
    var age :Int
    var height :Int
    init {
        this.name = name
        this.age = 0
        this.height = 0
    }

    constructor(name:String,age:Int):this(name){
        this.age = age
    }
    constructor(name:String,age:Int,height:Int):this(name,age){
        this.height = height
    }

    override fun toString(): String {
        return "Init(name='$name', age=$age, height=$height)"
    }

}