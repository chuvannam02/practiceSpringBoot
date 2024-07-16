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

    public static <T> List<Field> getAllFields(Class<T> kclass) {
        Class<? super T> cur = kclass;
        var fields = new ArrayList<Field>();
        while (cur != null) {
            fields.addAll(Arrays.asList(cur.getDeclaredFields()));
            cur = cur.getSuperclass();
        }
        return fields;
    }

    public static Map<String, Object> mapValues(Object object, String... fields) {
        var listFields = getAllFields(object.getClass());
        var map = new HashMap<String, Object>();
        for (String field : fields) {
            map.put(field, getValue(getField(listFields, field), object));
        }
        return map;
    }

    public static void copyData(Object from, Object to, boolean ignoreEmptyOrNull, String ...fields) {
        if (from == null || to == null) return;
        var fromFields = getAllFields(from.getClass());
        var toFields = getAllFields(to.getClass());
        for (String fieldName : fields) {
            var fromF = getField(fromFields, fieldName);
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
