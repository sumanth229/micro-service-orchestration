package com.centime.assessment.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by Sumanth on 19/11/22.
 */
//@Getter
//@Setter
//@ToString(doNotUseGetters = true)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 5988199687608671318L;

    private T response;
    @JsonIgnore
    private HttpStatus statusCode;

    public Response(T response, HttpStatus statusCode) {
        this.response = response;
        this.statusCode = statusCode;
    }

    public Response() {
        this.statusCode = HttpStatus.OK;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }


}