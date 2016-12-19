package com.rabarbers.restdoc;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.rabarbers.restdoc.domain.JavaType;

public class OperationFactory {

    /*
    private static String getGenericTypeReturn(Method method) {
        //http://stackoverflow.com/questions/1942644/get-generic-type-of-java-util-list
        Class<?> returnClass = method.getReturnType();
        //if (Collection.class.isAssignableFrom(returnClass)) {
            Type returnType = method.getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) returnType;
                Type[] argTypes = paramType.getActualTypeArguments();
                if (argTypes.length > 0) {
                    return argTypes[0].getTypeName();
                }
            }
        //}
        
        return null;
    }
    
    public static JavaType createReturn(Method method) {
        JavaType result = new JavaType(method.getReturnType().getName());
        
        if (method.getGenericReturnType() != null) {
            String genericTypeName = getGenericTypeReturn(method);
            if (genericTypeName != null) {
                JavaType generic = new JavaType(genericTypeName);
                result.setGenericType(generic);
            }
        }
        
        return result;        
    }
    */
    
    /**
     * recursively convert
     */
    public static JavaType convert(Type type) {
        JavaType result;
        
        if (type instanceof ParameterizedType) {
            result = new JavaType(type.getTypeName().substring(0, type.getTypeName().indexOf("<")));

            ParameterizedType paramType = (ParameterizedType) type;
            List<JavaType> genericTypes = new ArrayList<>();
            for (Type genType: paramType.getActualTypeArguments()) {
                genericTypes.add(convert(genType));
            }
            result.setGenericTypes(genericTypes);
        } else {
            result = new JavaType(type.getTypeName());
        }
        
        return result;
    }
    
    public static List<JavaType> createParams(Method method) {
        List<JavaType> result = new ArrayList<>();
        
        /*
        for (Parameter p: method.getParameters()) {
            //System.out.println(p.getType().getName());
            //result.add(new JavaType(p.getType().getName()));
            result.add(convert(p.getType()));
        }
        //System.out.println(method.getParameters());
         */
        
        for (Type t: method.getGenericParameterTypes()) {
            result.add(convert(t));
        }
        
        return result;
    }
}

