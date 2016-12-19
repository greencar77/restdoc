package com.rabarbers.restdoc.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JavaType {
    private String className;
    private List<JavaType> genericTypes = new ArrayList<>();

    public JavaType(String className) {
        super();
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<JavaType> getGenericTypes() {
        return genericTypes;
    }

    public void setGenericTypes(List<JavaType> genericTypes) {
        this.genericTypes = genericTypes;
    }
    
    public void addGenericType(JavaType type) {
        genericTypes.add(type);
    }
    
//    @Override
//    public String toString() {
//        return className + (genericType == null || genericType.getClassName().equals("void") || genericType.getClassName().equals("")? "": "<" + genericType + ">");
//    }

  @Override
  public String toString() {
      return className;
  }
}
