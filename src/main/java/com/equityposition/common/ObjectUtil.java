package com.equityposition.common;

import com.alibaba.fastjson.JSON;

public class ObjectUtil {
    /**
     * 将source转化为T类型
     * @param source
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T map(Object source,Class<T> cls){
        return JSON.parseObject(JSON.toJSONString(source),cls);
    }
}
