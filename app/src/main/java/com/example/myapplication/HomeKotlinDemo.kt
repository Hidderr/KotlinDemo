package com.example.myapplication

open class HomeKotlinDemo{
    fun info(){
        println("成员函数比相同签名的扩展函数调用优先级高")
    }
}

fun HomeKotlinDemo.info(){//添加类的扩展函数
    println("额外添加函数")
}
class SubHomeDemo:HomeKotlinDemo()
fun SubHomeDemo.info(){//子类扩展函数与父类扩展函数同名，不用覆盖
    println("subhome 额外添加函数")
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
    HomeKotlinDemo().info()
    invoke(SubHomeDemo())//扩展函数不受多态规则，调用的还是父类的扩展函数，专业术语即编扩展函数是静态解析（由编译时类型决定），成员函数动态解析，由运行时类型决定

    var a = null
    println(a.equals(null))
    println(a.equals("kotlin"))
}