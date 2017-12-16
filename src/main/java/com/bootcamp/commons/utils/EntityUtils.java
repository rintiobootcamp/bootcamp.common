package com.bootcamp.commons.utils;


/**
 * Created by darextossa on 11/20/17.
 * @param <T>
 */
public class EntityUtils<T> {

    private Class entityClass;

    /**
     * Initialize the entity utility with the wanted entity class
     * @param entityClass
     */
    public EntityUtils(Class entityClass) {
        this.entityClass = entityClass;
    }
}
