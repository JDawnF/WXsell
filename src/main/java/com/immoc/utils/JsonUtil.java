package com.immoc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @program: sell
 * @description: json格式化工具
 * @author: baichen
 * @create: 2018-08-23 19:46
 **/
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
