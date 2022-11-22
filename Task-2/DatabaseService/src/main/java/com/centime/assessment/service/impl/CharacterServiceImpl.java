package com.centime.assessment.service.impl;

import com.centime.assessment.dao.CharacterRepository;
import com.centime.assessment.logging.LogMethodParam;
import com.centime.assessment.model.Character;
import com.centime.assessment.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);

    @Autowired
    public CharacterRepository characterRepository;

    @LogMethodParam
    public Character findCharacterById(Long id) {
        Character mainCharacter = null;
        try {
            List<Character> character = characterRepository.findByIdOrParentId(id, id);
            mainCharacter = character.parallelStream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (mainCharacter != null) {
                List<Character> subCharacters = character.parallelStream().filter(x -> x.getParentId().equals(id)).collect(Collectors.toList());
                mainCharacter.setSubclasses(subCharacters);
            } else {
                logger.info("No record found with id: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error while getting character by ID: {} & error: {}", id, e.getMessage());
            e.printStackTrace();
        }
        return mainCharacter;
    }

    @LogMethodParam
    public List<Character> findAllCharacters() {
        List<Character> result = new LinkedList<>();
        HashSet<Long> visitedIds = new HashSet<>();
        try {
            List<Character> characters = characterRepository.findAll();
            Map<Long, Character> characterMap = characters.stream().collect(Collectors.toMap(Character::getId, x->x));
            Map<Long, List<Character>> childGroups = characters.stream().filter(c -> c.getParentId() > 0)
                    .collect(Collectors.groupingBy(Character::getParentId));
            for(Map.Entry<Long, List<Character>> e: childGroups.entrySet()){
                e.getValue().forEach(p-> visitedIds.add(p.getId()));
                characterMap.get(e.getKey()).setSubclasses(e.getValue());
                if(!visitedIds.contains(e.getKey())){
                    result.add(characterMap.get(e.getKey()));
                }
            }
        } catch (Exception e) {
            logger.error("Error while getting all characters && error: {}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}