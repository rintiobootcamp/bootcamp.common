package com.bootcamp.commons.models;

/**
 * Created by darextossa on 11/19/17.
 */
public class Rule {
    private String column;
    private String operator;
    private Object value;

    public Rule() {
    }

    public Rule(String column, String operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getAsStringQuery(String entityPrefix){
        return entityPrefix+"."+column+" "+operator+" '"+value+"'";
    }

}
