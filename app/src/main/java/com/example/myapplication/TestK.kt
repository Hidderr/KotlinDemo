package com.example.myapplication

import java.util.*
typealias str = String
typealias Predicate<T> = (T)->Boolean

fun main() {
//    testDemo()

//    varDemo()


//    array()
    var arr3 : String? = null
//    arr3?.lastIndex//此写法不会有空指针异常
//    arr3!!.length //此写法如果arr3为空指针则报异常


    var set = setOf("java","kotlin","Go")//不可变集合
    var hashSet = hashSetOf("java","kotlin","Go")//不可变集合
    var mutableSet = mutableSetOf("java","kotlin","Go")//创建可变的集合
    var linkedHashSet = linkedSetOf("java","kotlin","Go")//创建顺序的集合
    var treeSet = sortedSetOf("java","kotlin","Go")//创建从小到大顺序的集合
    println(mutableSet)//集合元素按添加的顺序
    println(set)
    println(hashSet)
    println(linkedHashSet)
    println(treeSet)
    println("setof 的返回对象的实际类型： ${set.javaClass}")
    println("mutableSet 的返回对象的实际类型： ${mutableSet.javaClass}")
}

private fun array() {
    var strAr = arrayOf("1", "2", "3")//静态初始化
    var intAr = arrayOf(1, 3, 5)
    var fixAr = arrayOfNulls<String>(5)//指定数组大小与类型
    var emptyArr = emptyArray<Double>()//指定空数组
    for (i in intAr) {
//        println("数值：$i")
    }

    var laArr = Array(5, { it })//构造器构造数组 lamada方式
    for (c in laArr) {
//        println("数值：$c")
    }

    var inArr = intArrayOf(1, 2, 3)//指定类型数组
    println(inArr[0].toString() + "   " + inArr.get(0))
    for (i in 0 until inArr.size) {
        println("值：$i")
    }

    for (index in inArr.indices) {//arry.indices返回数值的下标区间
        println("属性区间：$index")
    }

    for (i in 1..4) {
        println("区间： $i")
    }

    println("数组转换成字符串： ${Arrays.toString(inArr)}")
    println(inArr.lastIndex == inArr.size - 1)

    var strArrays = IntArray(3, { it + it })

    var arr2 = arrayOf("就减肥减肥", "jfjfjfj", "经济覅附加费纠纷解决")
    var arr3 = arrayOf("1减肥减肥", "2jfjfjfj", "3经济覅附加费纠纷解决")
    println("合并两个数组" + (arr2 + arr3).contentToString())
    for ((index, value) in arr2.withIndex()) {//可以同时获取数组的下标与值
        println("index: $index value: $value")
    }
    println(intAr.all({ it > 4 }))//判读数组的元素是否都大于4
    println("数组转map: " + inArr.associate({ it + 2 to it + 3 }))
    println("计算数组总和： " + inArr.fold(0, { a, b -> a + b }))
    inArr!!.lastIndex//!!含义是为了防止数组为空
}

private fun varDemo() {
    var txts = "${Random().nextInt(10)}"
    var st: str = "解放军解放"
    println(txts)
    println(st.javaClass)
    val p: Predicate<String> = { it.length > 4 }

    println(arrayOf("java", "object-c", "go", "kotlin").filter(p))
    var so = 20
    println("方法为负" + so.unaryMinus())
}

private fun testDemo() {
    println("how art you:  " + Short.MAX_VALUE + " int _max: " + Int.MAX_VALUE)

    var sixty = 0xaf
    var sen = 123_456_789
    var four: Float = 3.44888f
    var wu = 3.0f / 0.0
    var dou: Double = four.toDouble()
    var b: Byte = 127

    var by: Byte = 79
    var sh: Short = 233
    val i: Char = 'i'
    var k: Char = 'k'
    println(i - k)
    println(i - 4)
    println("short: ${sh.toByte()}")
    print("s数值为：$sixty")
    print("数值：$sen")
    print("数值：$four")
    print("数值：$wu")
    var bq = true
    var insertValue = "$bq :为真"
    println(insertValue)
    var nuv = null
    var str: String? = "133"//意思是str变量可以赋值为null
    var str1 : String? = null
    //str1.length str1为空则无法调用其属性，编译不通过，想调用则用str1?.length 如果str1为空则直接返回null
    str?.length
    if (str != null) {
        str.length
    }

    var boo: Boolean? = false
    if (boo === true) {

    }
    var myArr: Array<String?> = arrayOf("1", "2", "3")
    for (s in myArr) {
        s?.let { println(s) }//s不为空才会调用let函数
    }

    var value = myArr?.size ?: -1
    println(value)
    var st: String? = null
    var value1 = st?.length ?: -1
    println(value1)

    var eng = "12345555"
    println(eng[0])
    for (c in eng) {
        println(c)
    }

//    val txt = """
//        ^哈哈哈哈哈
//        ^解放军减肥减肥减肥解放军
//        ^福建警方解放军防腐剂
//        ^解放军解放附加费
//    """.trimIndent("^")
}

class Test{

    fun testVar(){

        var num : Int
        num = 24
        var zi: String = "字符串"
        val final : Short = 23
        var charf = 'w'
        var fin : String;
        fin = "李四"
        var numInt :Long = 2999999999
        print(Short.MAX_VALUE)

        var page : Int? = null

        var sixty = 0xaf



    }

}