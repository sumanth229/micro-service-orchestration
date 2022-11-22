package com.centime.assessment.controller;

import com.centime.assessment.model.Name;
import com.centime.assessment.response.Response;
import com.centime.assessment.service.GreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;

/**
 * Created by Sumanth on 19/11/22.
 */

@RequestMapping("/api/v1")
@EnableSwagger2
@RestController
public class GreetController {
    private static final Logger logger = LoggerFactory.getLogger(GreetController.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    GreetService greetService;

    @PostMapping("/greet")
    public ResponseEntity<Response<String>> greet(@Valid @RequestBody Name body, Errors errors) throws Exception {
        Response<String> response;
        logger.info("POST: Received Greet Call");
        //Optional Log Trace & Span info
        Span span = tracer.currentSpan();
        if (span != null) {
            logger.info("Trace ID {}", span.context().traceId());
            logger.info("Span ID {}", span.context().spanId());
        }
            response = greetService.greet(body.getName(), body.getSurname());
        return new ResponseEntity<>(response, response.getStatusCode());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.info("GET: Received Health Call");
        return new ResponseEntity<>("Up", HttpStatus.OK);
    }
}
