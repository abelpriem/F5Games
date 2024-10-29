package org.f5games.aiden_game.repository;

import org.f5games.aiden_game.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {

}