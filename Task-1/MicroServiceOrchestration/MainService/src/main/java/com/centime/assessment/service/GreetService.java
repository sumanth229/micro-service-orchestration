package com.centime.assessment.service;

import com.centime.assessment.response.Response;

public interface GreetService {
    Response<String> greet(String name, String surname);
}
