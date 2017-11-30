package com.bootcamp.commons.utils;

import com.bootcamp.commons.annotations.NativeQueryResultColumn;
import com.bootcamp.commons.exceptions.DatabaseException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 11/20/17.
 */
public class NativeQueryResultsMapper {

    private static Logger logger = LogManager.getLogger(NativeQueryResultsMapper.class);

    public static <T> List<T> map(List<Object[]> objectArrayList, List<String> fields, Class<T> genericType) throws IllegalAccessException, DatabaseException, InvocationTargetException {
        List<T> ret = new ArrayList<T>();
        for (Object[] objectArr : objectArrayList) {
            T t = map(objectArr, fields, genericType);
            ret.add(t);
        }

        return ret;
    }

    public static <T> T map(Object[] object, List<String> fieldNames, Class<T> genericType) throws DatabaseException, InvocationTargetException, IllegalAccessException {
        T t;
        try {
            t = genericType.newInstance();
        } catch (Exception e) {
            throw new DatabaseException(e.getStackTrace());
        }

        List<String> scanFields = new ArrayList<>();

        for (String fieldName : fieldNames) {
            fieldName = fieldName.split("\\.")[0];
            if (scanFields.contains(fieldName)) {
                continue;
            }

            Boolean isSimpleTypeField = isSimpleType(fieldName, genericType);
            List<Integer> indexes = getFieldIndex(fieldName, fieldNames);
            if (isSimpleTypeField) {
                //Field field = getFieldByName(fieldName, genericType);
                BeanUtils.setProperty(t, fieldName, object[indexes.get(0)]);
            } else {
                Field field = getFieldByName(fieldName, genericType);
                Class fieldClass = field.getType();
                Object[] objectFromIndexes = getObjectFromIndexes(object, indexes);
                List<String> fieldNamesFromIndexes = getFieldNamesFromIndexes(fieldName, fieldNames, indexes);
                Object result = map(objectFromIndexes, fieldNamesFromIndexes, fieldClass);
                BeanUtils.setProperty(t, fieldName, result);
            }
            scanFields.add(fieldName);
        }

        return t;
    }

    private static Object[] getObjectFromIndexes(Object[] object, List<Integer> indexes) {
        Object[] result = new Object[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            Integer index = indexes.get(i);
            result[i] = object[index];
        }

        return result;
    }

    private static List<String> getFieldNamesFromIndexes(String selectedFieldName, List<String> fieldNames, List<Integer> indexes) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < indexes.size(); i++) {
            Integer index = indexes.get(i);
            String fieldName = fieldNames.get(index);
            fieldName = fieldName.replace(selectedFieldName + ".", "");
            result.add(fieldName);
        }

        return result;
    }

    // Get ordered list of fields
    private static <T> List<Field> getNativeQueryResultColumnAnnotatedFields(Class<T> genericType) {
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(genericType, NativeQueryResultColumn.class);

        return fields;
    }



    private static <T> Field getFieldByName(String fieldName, Class<T> genericType) {
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(genericType, NativeQueryResultColumn.class);

        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        return null;
    }

    private static <T> List<String> getFieldByType(Class<T> genericType, NativeQueryResultColumn.COLUMNTYPE columntype) {
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(genericType, NativeQueryResultColumn.class);

        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            NativeQueryResultColumn nativeQueryResultColumn = field.getAnnotation(NativeQueryResultColumn.class);
            NativeQueryResultColumn.COLUMNTYPE fieldColumntype = nativeQueryResultColumn.columnType();

            if (fieldColumntype.equals(columntype)) {
                fieldNames.add(field.getName());
            }
        }

        return fieldNames;
    }

    private static List<Integer> getFieldIndex(String originalField, List<String> fields) {
        List<Integer> propertiesFieldIndex = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            if (field.startsWith(originalField)) {
                propertiesFieldIndex.add(i);
            }
        }

        return propertiesFieldIndex;
    }

    private static <T> boolean isSimpleType(String field, Class<T> genericType) {
        List<String> columnTypeFields = getFieldByType(genericType, NativeQueryResultColumn.COLUMNTYPE.SIMPLE);

        return columnTypeFields.contains(field);
    }

    private static <T> boolean isColumnType(String field, Class<T> genericType) {
        List<String> columnTypeFields = getFieldByType(genericType, NativeQueryResultColumn.COLUMNTYPE.COMPLEX);

        return columnTypeFields.contains(field);
    }
}
