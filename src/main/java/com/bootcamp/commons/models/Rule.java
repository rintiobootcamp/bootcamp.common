package com.bootcamp.commons.models;

import com.bootcamp.commons.annotations.NativeQueryResultColumn;
import com.bootcamp.commons.utils.NativeQueryResultsMapper;

import java.lang.reflect.Field;

/**
 * Created by darextossa on 11/19/17.
 */
public class Rule {

    private String column;
    private String operator;
    private Object value;
    private Class entityClass;

    /**
     * Initialize the criteria rule 
     */
    public Rule() {
    }

    /**
     * Initialize the Rule with the wanted table column name, query operator and column value
     * @param column
     * @param operator
     * @param value
     */
    public Rule(String column, String operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    /**
     * Get the Rule column name
     * @return the column name
     */
    public String getColumn() {
        return column;
    }

    /**
     * Set the Rule column name
     * @param column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * Get the Rule operator
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Set the Rule operator
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Get the Rule column value
     * @return
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the Rule column value
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get the Rule as a query string
     * @param entityPrefix
     * @return
     */
    public String getAsStringQuery(String entityPrefix) {
        if(entityClass != null){
            if(isNumericField())
                return entityPrefix + "." + column + " " + operator + " " + value + " ";
        }
        return entityPrefix + "." + column + " " + operator + " '" + value + "'";
    }

    /**
     * Check if the table column is a numeric field
     * @return
     */
    public Boolean isNumericField(){
        Field field = NativeQueryResultsMapper.getFieldByName(column, entityClass);
        Class fieldClass = field.getType();
        if(fieldClass == int.class ||
                fieldClass == double.class ||
                fieldClass == long.class )
            return true;

        return false;
    }

    /**
     * Get the Rule entity class
     * @return
     */
    public Class getEntityClass() {
        return entityClass;
    }

    /**
     * Set the Rule entity class
     * @param entityClass
     */
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
