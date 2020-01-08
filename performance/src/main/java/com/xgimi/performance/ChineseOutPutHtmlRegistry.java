package com.xgimi.performance;

import com.tencent.matrix.apk.model.result.TaskHtmlResult;
import com.tencent.matrix.apk.model.result.TaskJsonResult;
import com.tencent.matrix.apk.model.result.TaskResultRegistry;

import java.util.Map;

/**
 * @Author: tongpin.li
 * @Maintainer: alan.li@xgimi.com
 * @Date: 2019/9/19
 * @Copyright: 2019 www.andriodtvdev.com Inc. All rights reserved.
 * @description:
 */
public class ChineseOutPutHtmlRegistry extends TaskResultRegistry {
    @Override
    public Map<String, Class<? extends TaskHtmlResult>> getHtmlResult() {
        return null;
    }

    @Override
    public Map<String, Class<? extends TaskJsonResult>> getJsonResult() {
        return null;
    }
//    @Override
//    public Map<String, Class<? extends TaskHtmlResult>> getHtmlResult() {
//        Map<String, Class<? extends TaskHtmlResult>> map = new HashMap<>();
//        map.put("xgimi_apk_check_result.html",ChineseOutPutHtmlTask.class);
//        return map;
//    }
//
//    KeyguardManager
//
//    @Override
//    public Map<String, Class<? extends TaskJsonResult>> getJsonResult() {
//        return new HashMap<>();
//    }
}
