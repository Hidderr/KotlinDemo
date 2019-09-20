package com.example.myapplication

import java.io.IOException
import java.net.*

class TryCach {
    var ex = try {//try表达式
        "张三"
    }catch (e:Exception){
        "异常"
    }

    fun et(a: Int){

        URL("").openConnection(Proxy.NO_PROXY)
       ProxySelector.getDefault()
//        java.lang.reflect.Proxy.newProxyInstance()
        ProxySelector.setDefault(object : ProxySelector(){//匿名对象
            override fun select(uri: URI?): MutableList<Proxy> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun connectFailed(uri: URI?, sa: SocketAddress?, ioe: IOException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
       if(a>0){
           throw java.lang.Exception("Java异常")
       }else{
           throw RuntimeException("运行时异常")
       }
        var b : String = ""
        val l: Int = if (b!=null) b.length else -1

        val ls = b.length?: -1
    }
}


/**
 * Elvis 操作符
当我们有一个可空的引用 r 时，我们可以说“如果 r 非空，我使用它；否则使用某个非空的值 x”：

val l: Int = if (b != null) b.length else -1
除了完整的 if-表达式，这还可以通过 Elvis 操作符表达，写作 ?:：

val l = b?.length ?: -1
如果 ?: 左侧表达式非空，elvis 操作符就返回其左侧表达式，否则返回右侧表达式。 请注意，当且仅当左侧为空时，才会对右侧表达式求值。

请注意，因为 throw 和 return 在 Kotlin 中都是表达式，所以它们也可以用在 elvis 操作符右侧。这可能会非常方便，例如，检查函数参数：

fun foo(node: Node): String? {
val parent = node.getParent() ?: return null
val name = node.getName() ?: throw IllegalArgumentException("name expected")
// ……
}
 */

