package com.centime.assessment.controller;

import com.centime.assessment.model.Name;
import com.centime.assessment.response.Response;
import com.centime.assessment.service.ConcatenationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sumanth on 19/11/22.
 */
@EnableSwagger2
@RequestMapping("/api/v1/name")
@RestController
public class ConcatenationController {

    private static final Logger logger = LoggerFactory.getLogger(ConcatenationController.class);

    @Autowired
    ConcatenationService concatenationService;

    @PostMapping("/concat")
    public ResponseEntity<String> concatName(@Valid @RequestBody Name body, Errors errors){
        Response<String> response;
        logger.info("Logging from Concatenation Class");
        if (errors.hasErrors()) {
            Map<String, List<String>> errorMap = new HashMap<>();
            List<String> errorList = errors.getAllErrors().stream().map(o -> o.unwrap(ConstraintViolation.class).getMessage()).collect(Collectors.toList());
            errorMap.put("errors", errorList);
            response = new Response<>(errorMap.toString(), HttpStatus.BAD_REQUEST);
        } else {
            response = concatenationService.concatName(body.getName(), body.getSurname());
        }
        return new ResponseEntity<>(response.getResponse(), response.getStatusCode());
    }

}
