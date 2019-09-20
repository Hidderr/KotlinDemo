package com.example.myapplication

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/6/3
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
interface Call<T, V> {
    fun getData(t: T)
    fun getDataFail(t: V)

}
