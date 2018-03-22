/**
 * Created by darextossa on 11/19/17.
 */
package com.bootcamp.commons.models;

import com.bootcamp.commons.utils.DatabaseUtils;

/**
 *
 * @author Bello
 */
public class Criteria {

    private Class entityClass;
    private Rule rule;
    private String linkOperator;

    /**
     * Initialize database query criteria
     */
    public Criteria() {
    }

    /**
     * Initialize the criteria with the wanted rule and link operator
     * @param rule
     * @param linkOperator
     */
    public Criteria(Rule rule, String linkOperator) {
        this.rule = rule;
        this.linkOperator = linkOperator;
    }

    /**
     * Initialize the criteria with the wanted table column name, query operator, column value and link operator
     * @param column
     * @param operator
     * @param value
     * @param linkOperator
     */
    public Criteria(String column, String operator, Object value, String linkOperator) {
        this.rule = new Rule(column, operator, value);
        this.linkOperator = linkOperator;
    }

    /**
     * Initialize the criteria with the wanted table column name, query operator and column value
     * @param column
     * @param operator
     * @param value
     */
    public Criteria(String column, String operator, Object value) {
        this.rule = new Rule(column, operator, value);
        this.linkOperator = null;
    }

    /**
     * Get the Criteria rule
     * @return the Rule
     */
    public Rule getRule() {
        return rule;
    }

    /**
     * Set the Criteria Rule
     * @param rule
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }

    /**
     * Get the Criteria link operator
     * @return the link operator
     */
    public String getLinkOperator() {
        return linkOperator;
    }

    /**
     * Set the Criteria link operator
     * @param linkOperator
     */
    public void setLinkOperator(String linkOperator) {
        this.linkOperator = linkOperator;
    }

    /**
     * Get the Criteria as a query string
     * @param entityPrefix
     * @return
     */
    public String getAsStringQuery(String entityPrefix) {
        if(entityClass != null)
            rule.setEntityClass(entityClass);

        if (DatabaseUtils.isNullOrEmpty(linkOperator)) {
            return rule.getAsStringQuery(entityPrefix);
        } else {
            return rule.getAsStringQuery(entityPrefix) + " " + linkOperator +" ";
        }
    }

    /**
     * Get the Criteria entity class
     * @return
     */
    public Class getEntityClass() {
        return entityClass;
    }

    /**
     * Set the Criteria entity class
     * @param entityClass
     */
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
