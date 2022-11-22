package com.centime.assessment.service;

import com.centime.assessment.response.HelloResponse;

public interface HelloService {
    HelloResponse<String> getHello();
}
