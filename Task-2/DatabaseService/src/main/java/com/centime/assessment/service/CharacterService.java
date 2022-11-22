package com.centime.assessment.service;

import com.centime.assessment.model.Character;

import java.util.List;
import java.util.Map;

public interface CharacterService {
    Character findCharacterById(Long id);

    List<Character> findAllCharacters();

}
