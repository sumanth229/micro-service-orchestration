package com.centime.assessment.dao;

import com.centime.assessment.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByIdOrParentId(Long id, Long parentId);
}
