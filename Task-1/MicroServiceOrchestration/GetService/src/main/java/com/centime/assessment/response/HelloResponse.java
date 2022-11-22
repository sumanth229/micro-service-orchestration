package com.centime.assessment.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Sumanth on 19/11/22.
 */
public class HelloResponse<T> implements Serializable {
    private static final long serialVersionUID = 5988199687608671318L;

    private T responseObject;
    @JsonIgnore
    private HttpStatus statusCode;

    public HelloResponse() {
        this.statusCode = HttpStatus.OK;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }


}