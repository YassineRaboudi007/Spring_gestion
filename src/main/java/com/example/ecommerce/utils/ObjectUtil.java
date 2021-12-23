package com.example.ecommerce.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ObjectUtil {
    public static boolean isAnyFieldEmpty(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            try{
                Object value  = fields[i].get(object);
                if (value == null){
                    return true;
                }
                if (value instanceof String && ((String) value).length() == 0){
                    return true;
                }
            }catch (Exception e){
                System.out.println("probleme");
            }
        }

        return false;
    }
}
