package com.centime.assessment.controller;

import com.centime.assessment.model.Character;
import com.centime.assessment.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RequestMapping("/api/v1/character")
@RestController
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    public CharacterService characterService;

    @GetMapping("/{id}")
    public ResponseEntity<Character> findById(@PathVariable Long id){
        logger.info("GET: Received findById");
        return new ResponseEntity<>(characterService.findCharacterById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Character>> findAll(){
        logger.info("GET: Received findAll");
        return new ResponseEntity<>(characterService.findAllCharacters(), HttpStatus.OK);
    }

}
