package com.example.xml_parser.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class XMLBean{
    Class<?> tClass = null;

    String className;

    public List<String> parse(String className) {
        List<String> fields = new ArrayList<>();
        try {
            tClass =  Class.forName(className);
            Field[] declaredFields = tClass.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Class<?> type = declaredFields[i].getType();
                String name = declaredFields[i].getName();

                fields.add(name);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fields;
    }




}
