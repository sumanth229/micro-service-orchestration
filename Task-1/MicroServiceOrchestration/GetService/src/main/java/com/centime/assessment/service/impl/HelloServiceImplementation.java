package com.centime.assessment.service.impl;

import com.centime.assessment.response.HelloResponse;
import com.centime.assessment.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImplementation implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImplementation.class);
    @Override
    public HelloResponse<String> getHello() {
        logger.info("Get Hello Service call");
        HelloResponse<String> response = new HelloResponse<>();
        response.setResponseObject("Hello");
        return response;
    }
}
