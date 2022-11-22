package com.centime.assessment.service.impl;

import com.centime.assessment.response.Response;
import com.centime.assessment.service.ConcatenationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ConcatenationServiceImplementation implements ConcatenationService {
    @Override
    public Response<String> concatName(String name, String surname) {
        Response<String> response = new Response<>();
        response.setResponse(name + " " + surname);
        return response;
    }
}
