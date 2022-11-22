package com.centime.assessment.controller;

import com.centime.assessment.response.HelloResponse;
import com.centime.assessment.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Sumanth on 19/11/22.
 */
@EnableSwagger2
@RequestMapping("/api/v1")
@RestController
public class GetHelloController {

    private static final Logger logger = LoggerFactory.getLogger(GetHelloController.class);

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        logger.info("GET: Received Hello Request");
        HelloResponse<String> response = helloService.getHello();
        return new ResponseEntity<>(response.getResponseObject(), response.getStatusCode());
    }

}
