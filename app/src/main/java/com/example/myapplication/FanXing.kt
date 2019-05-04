package com.example.myapplication


open class Fan{

}

/**
 * 指定泛型上限
 */
open class FanXing<T : Fan> {
    open var att : T
    constructor(catt: T){
        this.att = catt
    }
}

/**
 * 使用where 指定多个泛型上限
 */
open class FanXing3<T > where T : Comparable<T>, T : Cloneable{
    open var att : T
    constructor(catt: T){
        this.att = catt
    }
}


/**
 * 在泛型函数中为 T指定多个上限
 */
fun <T> cloneWhenGreater(list : List<T>,THRESHOLD: T) where T : Comparable<T>,T : Cloneable{}



/**
 * 形变就是类似与Java的通配符用于解决 泛型中的继承转换问题
 * 声明处型变 out 泛型只出现在方法的返回值中 可以向上转型 类似于Java通配符的上限 称为：协变
 * in 泛型只出现在方法的形参中 可以向下转型  类似于Java通配符的下限 称为：逆变
 *
 * 使用处形变 即使用时再声明 in 或者 out关键字
 *
 * 如 FaxXing1<in Int> =
 */
open class FanXing1<out T> {
    open val att : T
    constructor(catt: T){
        this.att = catt
    }
}
open class FanXing2<in T> {


    /**
     * 泛型函数
     */
    fun <T> test(t: T){

    }

}


/**
 * 内联函数使用 reified字段用于 具体化类型参数
 *
 * 使用 ： findData<String>()
 */
inline fun <reified T> findData(): T?{
    var db = "激发减肥减肥"
    for (c in db) {
        if (c is T) {
            return c
        }

    }
    return null
}

inline fun <reified  T> membersOf() = T::class.members


/**
 * 泛型函数也可以用于扩展函数
 */
fun <T> T.toBookString(): String{
    return "<${this.toString()}>"
}



/**
 * FanXing<? extend Number>泛型协变，通配符上限
 * FanXing<? super E>泛型逆变，通配符下限
 */
fun main() {
//    var one: FanXing<String> = FanXing<String>("苹果")
//    var one1: FanXing<Int> = FanXing(3)
//    var one2 = FanXing(3.45)

    var list: ArrayList<*> = arrayListOf(1,"kotlin")//星号投影,用于处理原始数据
}



