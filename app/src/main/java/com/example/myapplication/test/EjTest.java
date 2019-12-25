package com.example.myapplication.test;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/10/15
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class EjTest {
    private Map<Class<?>,Object> fav = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public <T> void putF(Class<T> type, T instance){
        fav.put(Objects.requireNonNull(type),instance);
    }
    public <T> T getF(Class<T> type){

        return type.cast(fav.get(type));

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
        return c.stream().max(Comparator.naturalOrder()); }

}
