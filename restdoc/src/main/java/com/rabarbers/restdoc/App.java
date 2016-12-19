package com.rabarbers.restdoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabarbers.restdoc.domain.JavaType;
import com.rabarbers.restdoc.domain.Operation;

public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        //http://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
        //https://github.com/ronmamo/reflections
        
        Properties properties = loadProperties();
        
        String restResourcePath = properties.getProperty("scan");
        
        List<Operation> operations = new ArrayList<>();
        
        //ClassLoader additionalClassLoader = ArticlesResource.class.getClassLoader(); //TODO
        ClassLoader additionalClassLoader = null;
        System.out.println("additionalClassLoader=" + additionalClassLoader);
        //com.tele2.cpc.api.rs.resources.HealthResource.class.getClassLoader();
        
        //App.class.getClassLoader().getParent().
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("classLoader=" + classLoader);

        for (String packageString: restResourcePath.split(",")) {
            System.out.println(packageString);
            
            Set<Class<? extends Object>> allClasses = retrieveClasses(packageString, additionalClassLoader);
            allClasses.stream()
                //exact match, do not go deeper in package hierarchy 
                .filter(s -> s.getName().lastIndexOf(".") == (packageString + ".").lastIndexOf("."))
                .filter(s -> s.getName().indexOf("$") == -1) //skip proxies
                //.filter(s -> new HashSet<Annotation>(Arrays.asList(s.getAnnotations())).contains(javax.ws.rs.Path.class))
                .filter(s -> isResourceClass(s))
                .forEach(a -> process(a, operations))
                ;
            
            //new TreeSet<Annotation>(Arrays.asList(Object.class.getAnnotations())).contains(javax.ws.rs.Path.class);
            
            System.out.println("operations.size()=" + operations.size());            
        }
        
        unloadJson(operations);
    }
    
    private static boolean isResourceClass(Class<?> clazz) {
        //System.out.println(clazz.getName() + " " + new HashSet<Annotation>(Arrays.asList(clazz.getAnnotations())));
        //System.out.println(new HashSet<Annotation>(Arrays.asList(clazz.getAnnotations())).contains(javax.ws.rs.Path.class));
        //new HashSet<Annotation>(Arrays.asList(clazz.getClass().getAnnotations())).contains(javax.ws.rs.Path.class)
        for (Annotation a: clazz.getAnnotations()) {
            if (a.annotationType() == javax.ws.rs.Path.class) {
                return true;
            }
        }
        return false;
    }
    
    public static Package getPackage(String packageName) throws ClassNotFoundException {
        Class.forName(packageName+".package-info"); // makes sure package info exist and that the class loader already knows about the package
        return Package.getPackage(packageName);
    }
    
    private static Properties loadProperties() {
        Properties properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("restdoc.properties");
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        return properties;
    }
    
    private static void unloadJson(List<Operation> operations) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("operations.json"), operations);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void process(Class<?> clazz, List<Operation> operations) {
        //System.out.println(clazz.getName());
        
        List<Method> generalMethods = Arrays.asList(Object.class.getMethods());
        
        Arrays.stream(clazz.getMethods())
            //.filter(m -> m.isAccessible())
            .filter(m -> !generalMethods.contains(m))
            .forEach(m -> process(clazz, m, operations));
    }
    
    private static void process(Class<?> clazz, Method method, List<Operation> operations) {
        String httpPathClass = clazz.getAnnotation(javax.ws.rs.Path.class) == null? "" : clazz.getAnnotation(javax.ws.rs.Path.class).value();
        String httpPathMethod = method.getAnnotation(javax.ws.rs.Path.class) == null? "" : method.getAnnotation(javax.ws.rs.Path.class).value();
        String httpMethod = (method.getAnnotation(javax.ws.rs.POST.class) == null? "": "POST")
            + (method.getAnnotation(javax.ws.rs.GET.class) == null? "": "GET")
            + (method.getAnnotation(javax.ws.rs.PUT.class) == null? "": "PUT")
            + (method.getAnnotation(javax.ws.rs.DELETE.class) == null? "": "DELETE")            
                ;
        String roles = method.getAnnotation(javax.annotation.security.RolesAllowed.class) == null? "": Arrays.asList(method.getAnnotation(javax.annotation.security.RolesAllowed.class).value()).toString();
        String parent = clazz.getSuperclass() == Object.class? "": clazz.getSuperclass().getName();
        //JavaType returnType = OperationFactory.createReturn(method);
        JavaType returnType = OperationFactory.convert(method.getGenericReturnType());
        List<JavaType> params = OperationFactory.createParams(method);
        
        operations.add(new Operation(clazz.getName(), method.getName(), returnType, params,
                getCombinedPath(httpPathClass, httpPathMethod), httpMethod));
        
        System.out.println(clazz.getName()
                + "\t" + method.getName()
                + "\t" + getCombinedPath(httpPathClass, httpPathMethod)
                + "\t" + httpMethod
                + "\t" + roles
                + "\t" + parent
                + "\t" + returnType
                //+ "\t" + returnTypeGenericSuperclass
                );
    }
    
    private static String getCombinedPath(String httpPathClass, String httpPathMethod) {
        if (!httpPathClass.endsWith("/") && !httpPathMethod.startsWith("/")) {
            return httpPathClass + "/" + httpPathMethod;
//        } else if { //TODO
//            
        } else {
            return httpPathClass + httpPathMethod;
        }
    }

    private static Set<Class<? extends Object>> retrieveClasses(String pack, ClassLoader additionalClassLoader) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        classLoadersList.add(additionalClassLoader);

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                //.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .setUrls(ClasspathHelper.forPackage(pack))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(pack)))
                );

        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
        //System.out.println( allClasses );
        
        return allClasses;
    }
    
    private static int getLineNumber(String classname, String methodName) {
        //http://stackoverflow.com/a/14973713
        //http://stackoverflow.com/questions/12834887/how-to-get-the-line-number-of-a-method
        return -1; //TODO
    }
}
