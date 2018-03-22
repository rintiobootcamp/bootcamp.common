package com.bootcamp.commons.utils;

import java.io.IOException;
import com.google.gson.*;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by darextossa on 11/27/17.
 */
public class GsonUtils {

    /**
     * Convert a Json string in the object of the wanted class
     * @param <T>
     * @param json
     * @param theClass
     * @return the object
     * @throws IOException
     */
    public static <T> T getObjectFromJson(String json, Class<T> theClass) throws IOException {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(json, theClass);
    }

    /**
     * Convert a Json string in the object of the wanted class
     * @param <T>
     * @param json
     * @param type
     * @return
     * @throws IOException
     */
    public static <T> T getObjectFromJson(String json, Type type) throws IOException {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(json, type);
    }

    /**
     * Convert an object in a Json string
     * @param <T>
     * @param reader
     * @param theClass
     * @return
     * @throws IOException
     */
    public static <T> T fromJSON(Reader reader, Class<T> theClass) throws IOException {
        return new Gson().fromJson(reader, theClass);
    }

    /**
     * Convert an object in a Json string
     * @param <T>
     * @param reader
     * @param type
     * @return
     * @throws IOException
     */
    public static <T> T fromJSON(Reader reader, Type type) throws IOException {
        return new Gson().fromJson(reader, type);
    }

    /**
     * Convert an object in a Json string
     * @param object
     * @return
     */
    public static String toJSONWithoutClassName(Object object) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        return gson.toJson(object);
    }


}
