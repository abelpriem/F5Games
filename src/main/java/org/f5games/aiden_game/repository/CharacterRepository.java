package org.f5games.aiden_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.f5games.aiden_game.models.Character;

public interface CharacterRepository extends JpaRepository<Character, Long>{
}