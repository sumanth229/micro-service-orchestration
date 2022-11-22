package com.centime.assessment.service.impl;

import com.centime.assessment.model.Name;
import com.centime.assessment.response.Response;
import com.centime.assessment.service.GreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class GreetServiceImplementation implements GreetService {
    private static final Logger logger = LoggerFactory.getLogger(GreetServiceImplementation.class);

    @Autowired
    public RestTemplate restTemplate;

    @Value("${service.getservice.url}")
    public String getServiceUrl;

    @Value("${service.postservice.url}")
    public String postServiceUrl;
    @Override
    public Response<String> greet(String name, String surname) {
        try {
            logger.info("Greeting Service Implementation");
            //GET hello
            String hello = restTemplate.getForObject(getServiceUrl, String.class);
            //POST Concat Names
            String fullName = restTemplate.postForObject(postServiceUrl, new Name(name, surname), String.class);
            return new Response<>(hello + " " + fullName, HttpStatus.OK);
        }catch (HttpClientErrorException.BadRequest e){
            logger.error("Bad Request: Invalid Names Received");
            return new Response<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
