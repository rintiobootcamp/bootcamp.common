package com.bootcamp.commons.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 11/19/17.
 */
public class Criterias {

    private Class entityClass;

    private List<Criteria> criteriaList;

    /**
     * Initialize the query criteria list
     */
    public Criterias() {
    }

    /**
     * Initialize the criteria list with the wanted criteria list
     * @param criteriaList
     */
    public Criterias(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     * Get the criteria list
     * @return the criteria list
     */
    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    /**
     * Set the criteria list
     * @param criteriaList
     */
    public void setCriteriaList(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    /**
     * Get the criteria list as a query string
     * @param entityPrefix
     * @return the query string
     */
    public String getAsStringQuery(String entityPrefix) {
        String str = "";
        for (Criteria criteria : criteriaList) {
            if(entityClass != null)
                criteria.setEntityClass(entityClass);

            str = str + criteria.getAsStringQuery(entityPrefix);
        }
        return str;
    }

    /**
     * Add a criteria to the list
     * @param criteria
     */
    public void addCriteria(Criteria criteria) {
        if (criteriaList == null) {
            criteriaList = new ArrayList<Criteria>();
        }
        criteriaList.add(criteria);
    }

    /**
     * Get the Criteria list entity class
     * @return the entity class
     */
    public Class getEntityClass() {
        return entityClass;
    }

    /**
     * Set the Criteria list entity class
     * @param entityClass
     */
    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
