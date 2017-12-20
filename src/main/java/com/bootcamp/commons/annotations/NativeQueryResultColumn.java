package com.bootcamp.commons.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by darextossa on 11/20/17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NativeQueryResultColumn {

    /**
     *Enumeration use to specify if an entity attribute is simple or complex (another entity that migrates)
     */
    public enum COLUMNTYPE {

        /**
         * Specify that the attribute has not migrate from another entity
         */
        SIMPLE,

        /**
         *Specify that the attribute has migrate from another entity
         */
        COMPLEX
        //LIST
    }

    /**
     * Set the default type to SIMPLE
     * @return
     */
    COLUMNTYPE columnType() default COLUMNTYPE.SIMPLE;
    //Class classReference() default String.class;

}
