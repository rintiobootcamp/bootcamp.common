package com.bootcamp.commons.utils;

/**
 * Created by darextossa on 11/19/17.
 */
public class DatabaseUtils {

    /**
     * Check if a string is null or empty
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str){
        return (str ==null ||str=="" || str==" ");
    }
}