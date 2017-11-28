package com.bootcamp.commons.utils;

import java.io.IOException;
import com.google.gson.*;


/**
 * Created by darextossa on 11/27/17.
 */
public class GsonUtils {

    public static <T> T getObjectFromJson(String json, Class<T> theClass) throws IOException {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(json, theClass);
    }
}
