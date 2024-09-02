package com.test.practiceProject.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ReflectionUtils {
    public static Field getField(List<Field> fieldList, String fieldName) {
        return fieldList.stream().filter(field -> field.getName().equals(fieldName)).findFirst().orElse(null);
    }

    public static void setValue(Object result, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(result, value);
        } catch (IllegalAccessException e) {
            log.error("Can not set value: {}", e.getMessage());
        }
    }

    public static Object getValue(Field field, Object object) {
        if (object == null || field == null) return null;
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static List<Field> getAllFields(Class<?> kclass) {
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = kclass;

        while (currentClass != null) {
            // Add declared fields of the current class
            // The difference between getFields() and getDeclaredFields() is that getFields() returns only public fields
            // while getDeclaredFields() returns all fields, regardless of their access modifier
            // The difference between List.of() and Arrays.asList() is that List.of() returns an immutable list
            // while Arrays.asList() returns a mutable list
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            // Move to the superclass
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }

    public static Map<String, Object> mapValues(Object object, String... fields) {
//        var listFields = getAllFields(object.getClass());
//        List<Field> allFields = getAllFields(YourClass.class);
        List<Field> allFields = getAllFields(object.getClass());
        var map = new HashMap<String, Object>();
        for (String field : fields) {
            map.put(field, getValue(getField(allFields, field), object));
        }
        return map;
    }

    public static void copyData(Object from, Object to, boolean ignoreEmptyOrNull, String ...fields) {
        if (from == null || to == null) return;
        List<Field> fromFields = getAllFields(from.getClass());
        List<Field> toFields = getAllFields(to.getClass());
        for (String fieldName : fields) {
            Field fromF = getField(fromFields, fieldName);
            var valueToSet = getValue(fromF, from);
            if (ignoreEmptyOrNull) {
                if (valueToSet == null) continue;
                if (valueToSet instanceof String vs && StringUtils.isEmpty(vs)) continue;
            }
            var toF = getField(toFields, fieldName);
            setValue(to, toF, valueToSet);
        }
    }

}
