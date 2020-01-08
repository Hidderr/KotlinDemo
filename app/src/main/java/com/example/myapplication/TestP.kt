package com.example.myapplication

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/12/26
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
class TestP(var value: Int) {
    var other:Int = value
    init {
        LogUtils.log("测试数据","  value: $value  other: $other")
    }
}