package com.example.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.*;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/8/28
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class TestDemo {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) {
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))
        ){
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }catch (IOException e){

        }
    }


}
