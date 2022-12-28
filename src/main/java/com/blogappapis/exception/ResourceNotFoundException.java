package com.blogappapis.exception;

public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    int fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s is not found with %s : %s", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
