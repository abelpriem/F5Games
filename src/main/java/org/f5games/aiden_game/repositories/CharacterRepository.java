package org.f5games.aiden_game.repositories;

import org.f5games.aiden_game.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long>{
    
}
