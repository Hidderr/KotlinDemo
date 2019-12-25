package com.example.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/12/25
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class MyApplication extends Application {


    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


}
