package com.example.myapplication

import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/7/15
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
class TestInstructor (context: Context){


    private var contextWeakReference:  WeakReference<Context>? = WeakReference(context)
    private var mContext = contextWeakReference?.get()
    var test = 4
    init {
        Log.e("初始化"," test:  $test,   已经赋值：$mContext")
        if (mContext != null) {
            Log.e("初始化","已经赋值：$mContext")
        }
    }



}