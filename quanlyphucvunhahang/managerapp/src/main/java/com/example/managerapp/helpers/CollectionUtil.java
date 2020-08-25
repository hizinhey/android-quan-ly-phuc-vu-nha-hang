package com.example.managerapp.helpers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CollectionUtil {
    public <T> Map<String, Object> convertObjectToMap(T object) throws IllegalAccessException {
        Map<String, Object> packet = new HashMap<>();
        Field[] allFields = object.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = field.get(object);
            packet.put(field.getName(), value);
        }
        return packet;
    }
}
