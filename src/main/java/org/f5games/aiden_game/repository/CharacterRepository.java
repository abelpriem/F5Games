package org.f5games.aiden_game.repository;

import java.util.Optional;

import org.f5games.aiden_game.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{
    Optional<Character> findByName(String name);

}