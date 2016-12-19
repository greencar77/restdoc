package com.rabarbers.restdoc.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Operation {
    private String className;
    private String methodName;
    private JavaType returnType;
    private List<JavaType> params;
    
    private String path;
    private String verb;

    public Operation(String className, String methodName, JavaType returnType, List<JavaType> params, String path, String verb) {
        super();
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
        this.params = params;
        this.path = path;
        this.verb = verb;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public void setReturnType(JavaType returnType) {
        this.returnType = returnType;
    }

    public List<JavaType> getParams() {
        return params;
    }

    public void setParams(List<JavaType> params) {
        this.params = params;
    }
}
